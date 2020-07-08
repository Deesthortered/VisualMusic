package org.visualmusic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class VisualMusicApplication extends Application implements EventHandler<Event> {
    public static void main(String[] args) {
        ConfigurationManager.getInstance();
        launch(args);
    }

    private ConfigurationManager configurationManager;
    private ApplicationService applicationService;

    private Stage mainStage;
    private Scene mainScene;
    private Button buttonLoadFromFile;
    private Button buttonLoadFromURL;
    private Button buttonOpenFileDialog;
    private TextField textPathToImageFile;
    private TextField textURLToImageFile;

    private ImageView displayedImageView;

    private Image defaultImage;
    private Image loadedDisplayedImage;
    private Image loadedOriginalImage;

    @Override
    public void start(Stage primaryStage) {
        try {
            this.mainStage = primaryStage;
            configurationManager = ConfigurationManager.getInstance();
            applicationService = ApplicationService.getInstance();
            loadDefaultImage();

            this.mainScene = new Scene(makeMainPane(), configurationManager.getWINDOW_WIDTH(), configurationManager.getWINDOW_HEIGHT());
            primaryStage.setScene(this.mainScene);
            primaryStage.setTitle(configurationManager.getWINDOW_TITLE());
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Throwable rootException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Root error!");
            alert.setHeaderText("There is root error! Application will be closed.");
            alert.setContentText("There is an error that application can't handle. \nException message: " +
                    rootException.getMessage());
            alert.showAndWait();

            Platform.exit();
        }
    }

    private void loadDefaultImage() {
        try {
            defaultImage = applicationService.getImageFromFile(
                    configurationManager.getPATH_DEFAULT_IMAGE(),
                    false);
        } catch (IOException e) {
            defaultImage = null;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error! Default image not found!");
            alert.setContentText("Exception message: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private Pane makeLoadImagePane() {
        int buttonWidth = 150;
        int buttonBrowseWidth = 70;
        int padding = 5;

        VBox paneButtonBox = new VBox();
        VBox paneTextFieldsBox = new VBox();

        HBox paneSummaryBox = new HBox();
        paneSummaryBox.getChildren().addAll(paneButtonBox, paneTextFieldsBox);

        buttonLoadFromFile = new Button("Load image from file");
        paneButtonBox.getChildren().add(buttonLoadFromFile);
        buttonLoadFromFile.setMinWidth(buttonWidth);
        buttonLoadFromFile.setOnAction(this::handle);

        buttonLoadFromURL = new Button("Load image from URL");
        paneButtonBox.getChildren().add(buttonLoadFromURL);
        buttonLoadFromURL.setMinWidth(buttonWidth);
        buttonLoadFromURL.setOnAction(this::handle);

        HBox boxFilePath = new HBox();
        paneTextFieldsBox.getChildren().add(boxFilePath);

        buttonOpenFileDialog = new Button("Browse");
        boxFilePath.getChildren().add(buttonOpenFileDialog);
        buttonOpenFileDialog.setMinWidth(buttonBrowseWidth);
        buttonOpenFileDialog.setOnAction(this::handle);

        textPathToImageFile = new TextField();
        boxFilePath.getChildren().add(textPathToImageFile);
        textPathToImageFile.setMinWidth(configurationManager.getWINDOW_WIDTH() - buttonWidth - buttonBrowseWidth - 30);
        textPathToImageFile.setMaxWidth(configurationManager.getWINDOW_WIDTH() - buttonWidth - buttonBrowseWidth - 30);

        textURLToImageFile = new TextField();
        paneTextFieldsBox.getChildren().add(textURLToImageFile);
        textURLToImageFile.setMinWidth(configurationManager.getWINDOW_WIDTH() - buttonWidth - 30);
        textURLToImageFile.setMaxWidth(configurationManager.getWINDOW_WIDTH() - buttonWidth - 30);

        paneButtonBox.setPadding(new Insets(padding, padding, padding, padding));
        paneTextFieldsBox.setPadding(new Insets(padding, padding, padding, 0));

        paneButtonBox.setPadding(new Insets(padding, padding, padding, padding));

        return paneSummaryBox;
    }

    private Pane makeShowImagePane() {
        Pane pane = new StackPane();

//        String website = "https://www.w3schools.com/w3css/img_lights.jpg";
//        Image image = applicationService.getImageByURL(website, false);

        displayedImageView = new ImageView(defaultImage);
        pane.getChildren().add(displayedImageView);

        return pane;
    }

    private Pane makeMainPane() {
        Pane paneLoadImage = makeLoadImagePane();
        Pane paneShowImage = makeShowImagePane();

        VBox mainPane = new VBox();
        mainPane.getChildren().addAll(paneLoadImage, paneShowImage);

        return mainPane;
    }

    @Override
    public void handle(Event event) {
        Object source = event.getSource();
        if (source == buttonOpenFileDialog) {
            handleButtonOpenFileDialog();
        } else if (source == buttonLoadFromFile) {
            handleButtonLoadFromFile();
        } else if (source == buttonLoadFromURL) {
            handleButtonLoadFromURL();
        }
    }

    public void handleButtonOpenFileDialog() {
        FileChooser.ExtensionFilter fileImageExtensions =
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png");
        FileChooser.ExtensionFilter fileAllExtensions =
                new FileChooser.ExtensionFilter("All files", "*.*");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(fileImageExtensions);
        fileChooser.getExtensionFilters().add(fileAllExtensions);

        File file = fileChooser.showOpenDialog(this.mainStage);
        if (file != null) {
            String path = file.toURI().toString();
            path = path.substring(path.indexOf('/') + 1);
            path = path.replace("%20", " ");
            textPathToImageFile.setText(path);
        }
    }

    public void handleButtonLoadFromFile() {
        try {
            String path = this.textPathToImageFile.getText();
            if (path.equals("")) {
                throw new Exception("Path is empty.");
            }

            File file = new File(path);
            if (!file.exists() || !file.isFile()) {
                throw new IOException("File is not found by the path: " + path);
            }

            Set<String> possibleExtensions = new HashSet<>(Arrays.asList("png", "jpg"));
            String extension = path.substring(path.lastIndexOf(".") + 1);

            if (!possibleExtensions.contains(extension)) {
                throw new Exception("File extension \"" + extension + "\" is not supported.");
            }
            this.loadedDisplayedImage = applicationService.getImageFromFile(path, false);
            this.loadedOriginalImage = applicationService.getImageFromFile(path, true);

            changeDisplayedImage(this.loadedDisplayedImage);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mistake!");
            alert.setHeaderText("File is not found.");
            alert.setContentText("Exception message: " + e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mistake!");
            alert.setHeaderText("You can't choose the file.");
            alert.setContentText("Exception message: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void handleButtonLoadFromURL() {
        try {
            String website = this.textURLToImageFile.getText();
            if (website.equals("")) {
                throw new Exception("URL is empty.");
            }

            this.loadedDisplayedImage = applicationService.getImageByURL(website, false);
            this.loadedOriginalImage = applicationService.getImageByURL(website, true);

            changeDisplayedImage(this.loadedDisplayedImage);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mistake!");
            alert.setHeaderText("Can't load the file.");
            alert.setContentText("Exception message: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void changeDisplayedImage(Image image) {
        displayedImageView.setImage(image);
    }
}
