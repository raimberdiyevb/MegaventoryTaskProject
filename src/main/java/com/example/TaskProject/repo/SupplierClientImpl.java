package com.example.TaskProject.repo;

import com.example.TaskProject.entity.SupplierClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.List;

@Repository
public class SupplierClientImpl implements SupplierClientRepo{
    private static final String API_KEY = "cfe543d2c31f2b29@m146529";
    private static final String API_URL = "https://api.megaventory.com/v2017a/json/reply/SupplierClientGet";
    @Override
    public List<SupplierClient> getAll() {
        List<SupplierClient> supplierClients = null;

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
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Process the response (parse JSON and create SupplierClient objects)
                String responseBody = response.toString();
                System.out.println(responseBody);
                supplierClients = getSupplierClientsFromJSON(responseBody);
            } else {
                System.out.println("Failed to retrieve supplier clients. Response Code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplierClients;
    }

    public List<SupplierClient> getSupplierClientsFromJSON(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        List<SupplierClient> supplierClients = new ArrayList<>();

        if (jsonNode.has("mvSupplierClients")) {
            JsonNode supplierClientList = jsonNode.get("mvSupplierClients");
            for (JsonNode supplierClientNode : supplierClientList) {
                SupplierClient supplierClient = new SupplierClient();
                supplierClient.setId(supplierClientNode.get("SupplierClientID").asInt());
                supplierClient.setName(supplierClientNode.get("SupplierClientName").asText());
                supplierClient.setEmail(supplierClientNode.get("SupplierClientEmail").asText()); // Populate email attribute
                supplierClient.setShippingAddress(supplierClientNode.get("SupplierClientShippingAddress1").asText()); // Populate shipping address attribute
                supplierClient.setPhone(supplierClientNode.get("SupplierClientPhone1").asText()); // Populate phone attribute
                supplierClients.add(supplierClient);
            }
        } else {
            System.out.println("Empty");
            return null;
        }

        return supplierClients;
    }

    @Override
    public SupplierClient getById() {
        return null;
    }

    @Override
    public void add(SupplierClient supplierClient) {

    }
}
