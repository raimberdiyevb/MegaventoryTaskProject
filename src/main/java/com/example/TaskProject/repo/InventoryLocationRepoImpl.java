package com.example.TaskProject.repo;

import com.example.TaskProject.TaskProjectApplication;
import com.example.TaskProject.entity.InventoryLocation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Repository
public class InventoryLocationRepoImpl implements BaseRepo<InventoryLocation>{

    private final String API_KEY = TaskProjectApplication.API_KEY;

    private static final String API_URL = TaskProjectApplication.BASE_URL+"/json/reply/InventoryLocationGet";

    @Override
    public List<InventoryLocation> getAll() {
        List<InventoryLocation> inventoryLocations = new ArrayList<>();

        try {
            // Construct the URL with API key
            URL url = new URL(API_URL + "?APIKEY=" + API_KEY);

            // Create a HTTP GET request
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Send the request and handle the response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String responseBody = response.toString();
                inventoryLocations = parseInventoryLocations(responseBody);
            } else {
                System.out.println("Failed to retrieve inventory locations. Response Code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inventoryLocations;
    }

    private List<InventoryLocation> parseInventoryLocations(String responseBody) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<InventoryLocation> inventoryLocations = new ArrayList<>();

        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode inventoryLocationsNode = root.get("mvInventoryLocations");

        if (inventoryLocationsNode != null && inventoryLocationsNode.isArray()) {
            for (JsonNode locationNode : inventoryLocationsNode) {
                InventoryLocation location = new InventoryLocation();
                location.setId(locationNode.get("InventoryLocationID").asInt());
                location.setName(locationNode.get("InventoryLocationName").asText());
                location.setAbbreviation(locationNode.get("InventoryLocationAbbreviation").asText());
                location.setAddress(locationNode.get("InventoryLocationAddress").asText());
                inventoryLocations.add(location);
            }
        }

        return inventoryLocations;
    }

    @Override
    public void add(InventoryLocation inventoryLocation) {
        try {
            // Construct the URL for the API endpoint
            URL url = new URL(TaskProjectApplication.BASE_URL+"/InventoryLocation/InventoryLocationUpdate");

            // Create a HTTP POST request
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Construct the JSON payload
            String payload = "{\n" +
                    "  \"APIKEY\": \"" + API_KEY + "\",\n" +
                    "  \"mvInventoryLocation\": {\n" +
                    "    \"InventoryLocationName\": \"" + inventoryLocation.getName() + "\",\n" +
                    "    \"InventoryLocationAbbreviation\": \"" + inventoryLocation.getAbbreviation() + "\",\n" +
                    "    \"InventoryLocationAddress\": \"" + inventoryLocation.getAddress() + "\"\n" +
                    "  },\n" +
                    "  \"mvRecordAction\": \"Insert\"\n" +
                    "}";

            // Write the payload to the connection
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
            }

            // Send the request and handle the response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Inventory location added successfully");
            } else {
                System.out.println("Failed to add inventory location. Response Code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
