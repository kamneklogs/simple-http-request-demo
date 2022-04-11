package com.camilo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.camilo.dto.company.CompanyCreationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Hello world!
 *
 */
public class App {

    private static final Log log = LogFactory.getLog(App.class);

    public static void main(String[] args) {
        try {
            sendToSave(extractCompanies());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendToSave(List<CompanyCreationDTO> companies) throws JsonProcessingException {
        String companiesJSON = new ObjectMapper().writeValueAsString(companies);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/company"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(companiesJSON))
                .build();

        try {

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            log.info(response.body());

        } catch (IOException | InterruptedException e) {
            log.warn("Connection error");
        }

    }

    private static List<List<String>> readCSV() throws IOException, CsvValidationException, FileNotFoundException {
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader("REPLACE WITH PATH"));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }

    private static List<CompanyCreationDTO> extractCompanies()
            throws CsvValidationException, NumberFormatException, FileNotFoundException, IOException {
        List<CompanyCreationDTO> companies = new ArrayList<CompanyCreationDTO>();
        for (List<String> companyRAW : readCSV()) {
            companies.add(new CompanyCreationDTO(companyRAW.get(0),
                    companyRAW.get(1),
                    Long.parseLong(companyRAW.get(2)),
                    companyRAW.get(3),
                    companyRAW.get(4)));
        }
        return companies;
    }

}