package org.visualmusic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;


public class VisualMusicApplication extends Application {
    public static void main(String[] args) {
        ConfigurationManager.getInstance();
        launch(args);
    }

    private ConfigurationManager configurationManager;

    private Image defaultImage;
    private Image loadedImage;

    @Override
    public void start(Stage primaryStage) {
        configurationManager = ConfigurationManager.getInstance();
        loadDefaultImage();

        Scene scene = new Scene(makeMainPane(), configurationManager.getWINDOW_WIDTH(), configurationManager.getWINDOW_HEIGHT());
        primaryStage.setScene(scene);
        primaryStage.setTitle(configurationManager.getWINDOW_TITLE());
        primaryStage.show();
    }

    private void loadDefaultImage() {
        File file = new File(configurationManager.getPATH_DEFAULT_IMAGE());
        defaultImage = new Image(
                file.toURI().toString(),
                configurationManager.getLOADED_IMAGE_FIXED_WIDTH(),
                configurationManager.getLOADED_IMAGE_FIXED_HEIGHT(),
                false, true, false);
    }

    private Pane makeLoadImagePane() {
        VBox paneButtonBox = new VBox();
        VBox paneTextFieldsBox = new VBox();

        HBox paneSummaryBox = new HBox();
        paneSummaryBox.getChildren().addAll(paneButtonBox, paneTextFieldsBox);

        Button buttonLoadFromFile = new Button("Load image from file");
        paneButtonBox.getChildren().add(buttonLoadFromFile);
        buttonLoadFromFile.pressedProperty().addListener((observable, wasPressed, pressed) -> {
            if (wasPressed) {
                System.out.println("wasPressed");
            }
        });

        Button buttonLoadFromURL = new Button("Load image from URL");
        paneButtonBox.getChildren().add(buttonLoadFromURL);
        buttonLoadFromURL.pressedProperty().addListener((observable, wasPressed, pressed) -> {
            if (wasPressed) {
                System.out.println("wasPressed");
            }
        });


        TextField textPathToImageFile = new TextField();
        paneTextFieldsBox.getChildren().add(textPathToImageFile);

        TextField textURLToImageFile = new TextField();
        paneTextFieldsBox.getChildren().add(textURLToImageFile);

        return paneSummaryBox;
    }

    private Pane makeShowImagePane() {
        Pane pane = new StackPane();

        String website = "https://www.w3schools.com/w3css/img_lights.jpg";
        Image image = new Image(
                website,
                configurationManager.getLOADED_IMAGE_FIXED_WIDTH(),
                configurationManager.getLOADED_IMAGE_FIXED_HEIGHT(),
                false, true, false);

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
}
