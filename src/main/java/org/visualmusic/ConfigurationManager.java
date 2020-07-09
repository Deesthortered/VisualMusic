package org.visualmusic;

import lombok.Getter;

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
                    "# You can leave empty strings\n" +
                    "#\n" +
                    "# Syntax is \"PARAMETER_NAME VALUE\"\n" +
                    "# PARAMETER_NAME is always one word\n" +
                    "# Space after PARAMETER_NAME is mandatory\n" +
                    "# VALUE will be whole string after space\n" +
                    "# You can use more than 1 space between PARAMETER_NAME and VALUE\n" +
                    "#";

    @Getter
    private String WINDOW_TITLE;
    @Getter
    private int WINDOW_HEIGHT;
    @Getter
    private int WINDOW_WIDTH;
    @Getter
    private int LOADED_IMAGE_FIXED_WIDTH;
    @Getter
    private int LOADED_IMAGE_FIXED_HEIGHT;
    @Getter
    private String PATH_DEFAULT_IMAGE;

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
        this.WINDOW_TITLE = "VisualMusic (by Deesthortered and Stan)";
        this.WINDOW_HEIGHT = 760;
        this.WINDOW_WIDTH = 1100;
        this.LOADED_IMAGE_FIXED_WIDTH = 1100;
        this.LOADED_IMAGE_FIXED_HEIGHT = 680;
        this.PATH_DEFAULT_IMAGE = "resources/default_image.png";
    }

    private void saveDefaultConfigurations() throws IOException {
        String[] pathElements = configurationFilePath.split("/");
        String currentPath = "./";
        for (int i = 0; i < pathElements.length - 1; i++) {
            currentPath += "/" + pathElements[i];
            File currentDirectory = new File(currentPath);
            if (!currentDirectory.exists()) {
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
        writer.println("LOADED_IMAGE_FIXED_WIDTH " + LOADED_IMAGE_FIXED_WIDTH);
        writer.println("LOADED_IMAGE_FIXED_HEIGHT " + LOADED_IMAGE_FIXED_HEIGHT);
        writer.println("PATH_DEFAULT_IMAGE " + PATH_DEFAULT_IMAGE);

        writer.close();
    }

    private void loadStoredConfigurations() throws IOException {
        File file = new File(configurationFilePath);
        Scanner scanner = new Scanner(file, StandardCharsets.UTF_8.name());

        Map<String, String> dataMap = new HashMap<>();
        while (scanner.hasNext()) {
            dataMap.put(scanner.next().trim(), scanner.nextLine().trim());
        }
        dataMap.remove("#");

        try {
            this.WINDOW_TITLE = validateConfigurationValue(dataMap.get("WINDOW_TITLE"));
            this.WINDOW_HEIGHT = Integer.parseInt(validateConfigurationValue(dataMap.get("WINDOW_HEIGHT")));
            this.WINDOW_WIDTH = Integer.parseInt(validateConfigurationValue(dataMap.get("WINDOW_WIDTH")));
            this.LOADED_IMAGE_FIXED_WIDTH = Integer.parseInt(validateConfigurationValue(dataMap.get("LOADED_IMAGE_FIXED_WIDTH")));
            this.LOADED_IMAGE_FIXED_HEIGHT = Integer.parseInt(validateConfigurationValue(dataMap.get("LOADED_IMAGE_FIXED_HEIGHT")));
            this.PATH_DEFAULT_IMAGE = validateConfigurationValue(dataMap.get("PATH_DEFAULT_IMAGE"));
        } catch (Exception e) {
            throw new IOException("Setting read value from map is failed!");
        }
    }

    private String validateConfigurationValue(String value) throws IOException {
        if (value == null || value.equals("") || value.equals("\n"))
            throw new IOException("Configuration value is not valid!");
        return value;
    }
}
