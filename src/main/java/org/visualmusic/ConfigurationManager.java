package org.visualmusic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConfigurationManager {

    private final String configurationFilePath = "resources/configurations.txt";
    private final String configurationFilePreamble =
            "# Configuration file\n" +
            "#\n" +
            "# You can leave comments like this - first symbol of string must be # and second symbol must be Space.\n" +
            "# You can't leave empty strings!\n" +
            "#\n" +
            "# Syntax is \"PARAMETER_NAME VALUE\"\n" +
            "# PARAMETER_NAME is always one word\n" +
            "# Space after PARAMETER_NAME is mandatory\n" +
            "# VALUE will be whole string after space\n" +
            "# You can use more than 1 space between PARAMETER_NAME and VALUE\n" +
            "#";

    private String WINDOW_TITLE;
    private int WINDOW_HEIGHT;
    private int WINDOW_WIDTH;


    // Thread-safe singleton pattern

    private static volatile ConfigurationManager instance = null;

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    private ConfigurationManager() {
        loadConfiguration();
    }

    // Load configuration

    private void loadConfiguration() {
        try {
            loadStoredConfigurations();
        } catch (IOException loadFileException) {
            System.err.println("Warning! Configuration file reading was failed! Default configuration will be used.");
            System.err.println(loadFileException.toString());
            loadDefaultConfigurations();
            try {
                saveDefaultConfigurations();
            } catch (IOException saveFileException) {
                System.err.println("Error! Can't save default configuration to file by path: " + configurationFilePath);
                System.err.println(saveFileException.toString());
            }
        }
    }

    private void loadDefaultConfigurations() {
        this.WINDOW_TITLE  = "VisualMusic (by Deesthortered and Stan)";
        this.WINDOW_HEIGHT = 600;
        this.WINDOW_WIDTH  = 800;
    }

    private void saveDefaultConfigurations() throws IOException {
        String[] pathElements = configurationFilePath.split("/");
        String currentPath = "./";
        for (int i = 0; i < pathElements.length - 1; i++) {
            currentPath += "/" + pathElements[i];
            File currentDirectory = new File(currentPath);
            if (!currentDirectory.exists()){
                if (!currentDirectory.mkdir()) {
                    throw new IOException("Cant create directory " + currentDirectory);
                }
            }
        }
        String fileName = currentPath + "/" + pathElements[pathElements.length - 1];
        PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8.toString());

        writer.println(configurationFilePreamble);

        writer.println("WINDOW_TITLE " + WINDOW_TITLE);
        writer.println("WINDOW_HEIGHT " + WINDOW_HEIGHT);
        writer.println("WINDOW_WIDTH " + WINDOW_WIDTH);

        writer.close();
    }

    private void loadStoredConfigurations() throws IOException {
        File file = new File(configurationFilePath);
        Scanner scanner = new Scanner(file, StandardCharsets.UTF_8.name());

        Map<String, String> dataMap = new HashMap<>();
        while (scanner.hasNext()) {
            dataMap.put(scanner.next().trim(), scanner.nextLine().trim());
        }
        dataMap.put("#", null);

        try {
            this.WINDOW_TITLE  = dataMap.get("WINDOW_TITLE");
            this.WINDOW_HEIGHT = Integer.parseInt(dataMap.get("WINDOW_HEIGHT"));
            this.WINDOW_WIDTH  = Integer.parseInt(dataMap.get("WINDOW_WIDTH"));
        } catch (Exception e) {
            throw new IOException("Setting read value from map is failed!");
        }
    }

    // Getters

    public String getWINDOW_TITLE() {
        return WINDOW_TITLE;
    }

    public int getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }

    public int getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }
}
