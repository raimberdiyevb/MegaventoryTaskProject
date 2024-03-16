package com.example.TaskProject.repo;

import com.example.TaskProject.TaskProjectApplication;
import com.example.TaskProject.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Repository
public class ProductRepoImpl implements ProductRepo{
    private final String API_KEY = TaskProjectApplication.API_KEY;
    private final String API_URL = TaskProjectApplication.BASE_URL + "/json/reply/ProductGet";

    @Override
    public void add(Product product) {
        try {
            // Construct the URL for the API endpoint
            URL url = new URL(API_URL+"/Product/ProductUpdate");

            // Create a HTTP POST request
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Construct the JSON payload
            String payload = "{\n" +
                    "  \"APIKEY\": \"" + API_KEY + "\",\n" +
                    "  \"mvProduct\": {\n" +
                    "    \"ProductSKU\": \"" + product.getSku() + "\",\n" +
                    "    \"ProductDescription\": \"" + product.getDescription() + "\",\n" +
                    "    \"ProductSellingPrice\": " + product.getSalesPrice() + ",\n" +
                    "    \"ProductPurchasePrice\": " + product.getPurchasePrice() + "\n" +
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
                System.out.println("Product added successfully");
            } else {
                System.out.println("Failed to add product. Response Code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Product getProductById(int id) {
        Product product = null;

        try {
            // Construct the URL with API key and product ID
            URL url = new URL(API_URL + "?APIKEY=" + API_KEY + "&ProductID=" + id);

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

                // Process the response (parse JSON and create a Product object)
                String responseBody = response.toString();
                System.out.println(responseBody);
                product = getProductFromJSON(responseBody);
            } else {
                System.out.println("Failed to retrieve product. Response Code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = null;

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

                // Process the response (parse JSON and create Product objects)
                String responseBody = response.toString();
                System.out.println(responseBody);
                products = getProductsFromJSON(responseBody);
            } else {
                System.out.println("Failed to retrieve products. Response Code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    public List<Product> getProductsFromJSON(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        List<Product> products = new ArrayList<>();

        if (jsonNode.has("mvProducts")) {
            JsonNode productList = jsonNode.get("mvProducts");
            for (JsonNode productNode : productList) {
                Product product = new Product();
                product.setId(productNode.get("ProductID").asInt());
                product.setSku(productNode.get("ProductSKU").asText());
                product.setDescription(productNode.get("ProductDescription").asText());
                product.setSalesPrice(productNode.get("ProductSellingPrice").asDouble());
                product.setPurchasePrice(productNode.get("ProductPurchasePrice").asDouble());
                products.add(product);
            }
        } else {
            System.out.println("Empty");
            return null;
        }
        return products;
    }
    public Product getProductFromJSON(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        Product product = null;

        if (jsonNode.has("mvProducts")) {
            JsonNode productList = jsonNode.get("mvProducts");
            if (productList.isArray() && !productList.isEmpty()) {
                // Assuming there's only one product in the array
                JsonNode productNode = productList.get(0);
                product = new Product();
                product.setId(productNode.get("ProductID").asInt());
                product.setSku(productNode.get("ProductSKU").asText());
                product.setDescription(productNode.get("ProductDescription").asText());
                product.setSalesPrice(productNode.get("ProductSellingPrice").asDouble());
                product.setPurchasePrice(productNode.get("ProductPurchasePrice").asDouble());
            } else {
                System.out.println("No products found");
            }
        } else {
            System.out.println("Product list not found");
        }
        return product;
    }

}
