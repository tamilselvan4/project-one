package com.first.application.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.application.model.Stock;
import com.first.application.service.YahooService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

@Service
public class YahooServiceImpl implements YahooService {

    @Override
    public List<Stock> getTopGainers() {
        List<Stock> stocks = new ArrayList<>();
        try {
            String url = "https://www.nseindia.com/get-quotes/equity?symbol=TCS"; // Replace with stock symbol
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10 * 1000)
                    .get();

            // Example: Extract stock price
            Element priceElement = doc.selectFirst(".stock-price"); // Update selector as needed
            if (priceElement != null) {
                String stockPrice = priceElement.text();
                System.out.println("Stock Price: " + stockPrice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        // Replace with your Alpha Vantage API Key
//        String apiKey = "2IINWLY9I7UB7G64";
//        String apiUrl = "https://www.alphavantage.co/query?function=TOP_GAINERS_LOSERS&apikey=" + apiKey;
//
//        try {
//            // Open HTTP Connection
//            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//            // Ensure the connection is successful
//            int responseCode = connection.getResponseCode();
//            if (responseCode != HttpURLConnection.HTTP_OK) {
//                System.err.println("Failed to fetch data from Alpha Vantage API. Response code: " + responseCode);
//                return stocks;
//            }
//
//            // Read the Response
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//                StringBuilder response = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//
//                // Parse the JSON Response
//                ObjectMapper objectMapper = new ObjectMapper();
//                JsonNode rootNode = objectMapper.readTree(response.toString());
//                JsonNode timeSeriesNode = rootNode.get("Time Series (5min)");
//
//                if (timeSeriesNode != null) {
//                    // Iterate through the time series data and extract stock details
//                    Iterator<Map.Entry<String, JsonNode>> fields = timeSeriesNode.fields();
//                    while (fields.hasNext()) {
//                        Map.Entry<String, JsonNode> entry = fields.next();
//                        JsonNode dataNode = entry.getValue();
//
//                        double open = dataNode.get("1. open").asDouble();
//                        double close = dataNode.get("4. close").asDouble();
//                        double change = close - open;
//                        double changePercent = (change / open) * 100;
//
//                        // Add stock data to the list (using symbol MSFT for demonstration)
//                        stocks.add(new Stock("MSFT", "Microsoft", close, changePercent, change));
//                    }
//                } else {
//                    System.err.println("No time series data found in the API response.");
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("IOException occurred while fetching data: " + e.getMessage());
//        } catch (Exception e) {
//            System.err.println("An error occurred: " + e.getMessage());
//        }

        return stocks;
    }



}
