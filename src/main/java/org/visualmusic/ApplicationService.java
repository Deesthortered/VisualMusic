package org.visualmusic;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;

public class ApplicationService {

    // Thread-safe singleton pattern
    private static volatile ApplicationService instance = null;

    public static ApplicationService getInstance() {
        if (instance == null) {
            synchronized (ApplicationService.class) {
                if (instance == null) {
                    instance = new ApplicationService();
                }
            }
        }
        return instance;
    }

    private ApplicationService() {
        configurationManager = ConfigurationManager.getInstance();
    }

    // Services
    private final ConfigurationManager configurationManager;

    public Image getImageFromFile(String path, boolean saveRatio) throws IOException {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("Image file is not found by path " + path);
        }
        return new Image(
                file.toURI().toString(),
                configurationManager.getLOADED_IMAGE_FIXED_WIDTH(),
                configurationManager.getLOADED_IMAGE_FIXED_HEIGHT(),
                saveRatio, true, false
        );
    }

    public Image getImageByURL(String url, boolean saveRatio) {
        return new Image(
                url,
                configurationManager.getLOADED_IMAGE_FIXED_WIDTH(),
                configurationManager.getLOADED_IMAGE_FIXED_HEIGHT(),
                saveRatio, true, false
        );
    }
}
