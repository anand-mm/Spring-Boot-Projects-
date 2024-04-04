package com.codeforyou.jsontocsv;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVWriter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/convert")
public class csvController {

    private final ObjectMapper objectMapper;

    public csvController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/json-to-csv")
    public String convertJsonToCsv() throws IOException {
        String jsonData = "[\n" +
                "    {\n" +
                "        \"demoeproc.nic.in\": [\n" +
                "            {\n" +
                "                \"goods_tendercount\": 1448,\n" +
                "                \"goods_tendervalue\": 5.00098596593799E7,\n" +
                "                \"services_tendercount\": 612\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"demoetenders.tn.nic.in\": [\n" +
                "            {\n" +
                "                \"goods_tendercount\": 313,\n" +
                "                \"goods_tendervalue\": 45023.691820700005,\n" +
                "                \"services_tendercount\": 62\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"etenders.chd.nic.in\": [\n" +
                "            {\n" +
                "                \"goods_tendercount\": 1808,\n" +
                "                \"goods_tendervalue\": 1069.9604866\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"eprocurehsl.nic.in\": [\n" +
                "            {\n" +
                "                \"goods_tendercount\": 2209,\n" +
                "                \"goods_tendervalue\": 905.9225536,\n" +
                "                \"services_tendercount\": 959\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"tendersodisha.gov.in\": [\n" +
                "            {\n" +
                "                \"goods_tendercount\": 264,\n" +
                "                \"goods_tendervalue\": 280.09174459999997,\n" +
                "                \"services_tendercount\": 758\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        JsonNode rootNode = objectMapper.readTree(jsonData);

        // Extract column names from the JSON keys
        Set<String> columnNames = new LinkedHashSet<>();
        for (JsonNode node : rootNode) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                columnNames.add(fieldNames.next());
            }
        }

        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer);

        // Write the column headers
        String[] headers = columnNames.toArray(new String[0]);
        csvWriter.writeNext(headers);

        // Write data rows
        for (JsonNode node : rootNode) {
            List<String> values = new ArrayList<>();
            for (String columnName : columnNames) {
                JsonNode valueNode = node.get(columnName);
                if (valueNode != null) {
                    values.add(valueNode.toString());
                } else {
                    values.add(""); // Handle missing values
                }
            }
            csvWriter.writeNext(values.toArray(new String[0]));
        }

        csvWriter.close();
        return writer.toString();
    }
    }
