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

    private Image defaultImage;
    private Image loadedImage;

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

        String website = "https://www.w3schools.com/w3css/img_lights.jpg";
        Image image = applicationService.getImageByURL(website, false);

        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);

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
            textPathToImageFile.setText(file.toURI().toString());
        }
    }

    public void handleButtonLoadFromFile() {

    }

    public void handleButtonLoadFromURL() {

    }
}
