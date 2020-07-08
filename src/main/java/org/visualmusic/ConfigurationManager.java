package org.visualmusic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConfigurationManager {

    private final String configurationFilePath = "resources/configurations.txt";

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
        } catch (IOException e) {
            System.err.println("Warning! Configuration file reading was failed! Default configuration will be used.");
            System.err.println(e);
            loadDefaultConfigurations();
        }
    }

    private void loadDefaultConfigurations() {
        this.WINDOW_TITLE  = "VisualMusic (by Deesthortered and Stan)";
        this.WINDOW_HEIGHT = 600;
        this.WINDOW_WIDTH  = 800;
    }

    private void loadStoredConfigurations() throws IOException {
        File file = new File(configurationFilePath);
        Scanner scanner = new Scanner(file, StandardCharsets.UTF_8.name());

        Map<String, String> dataMap = new HashMap<>();
        while (scanner.hasNext()) {
            dataMap.put(scanner.next().trim(), scanner.nextLine().trim());
        }

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
