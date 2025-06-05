package com.taskmanager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONObject;

/**
 * Ported boost.cpp functionality using Java NIO and org.json.JSONObject.
 */
public class BoostProcessor {

    /**
     * Reads and processes a JSON file, printing name and age fields.
     * @param filename the path to the JSON file
     */
    public static void processJsonFile(String filename) {
        Path filePath = Paths.get(filename);
        if (Files.exists(filePath)) {
            try {
                // Read entire file content as a UTF-8 string
                String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
                // Parse JSON
                JSONObject j = new JSONObject(content);
                // Extract and print fields
                System.out.println("Name: " + j.optString("name"));
                System.out.println("Age: " + j.optInt("age"));
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("File not found!");
        }
    }

    /**
     * Entry point, processes data.json in working directory.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Default file
        String filename = "data.json";
        if (args.length > 0) {
            filename = args[0];
        }
        processJsonFile(filename);
    }
}