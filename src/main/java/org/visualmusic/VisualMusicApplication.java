package org.visualmusic;

import javafx.application.Application;
import javafx.geometry.Insets;
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

import java.io.IOException;


public class VisualMusicApplication extends Application {
    public static void main(String[] args) {
        ConfigurationManager.getInstance();
        launch(args);
    }

    private ConfigurationManager configurationManager;
    private ApplicationService applicationService;

    private Image defaultImage;
    private Image loadedImage;

    @Override
    public void start(Stage primaryStage) {
        configurationManager = ConfigurationManager.getInstance();
        applicationService = ApplicationService.getInstance();
        loadDefaultImage();

        Scene scene = new Scene(makeMainPane(), configurationManager.getWINDOW_WIDTH(), configurationManager.getWINDOW_HEIGHT());
        primaryStage.setScene(scene);
        primaryStage.setTitle(configurationManager.getWINDOW_TITLE());
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void loadDefaultImage() {
        try {
            defaultImage = applicationService.getImageFromFile(
                    configurationManager.getPATH_DEFAULT_IMAGE(),
                    false);
        } catch (IOException e) {
            e.printStackTrace();
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

        Button buttonLoadFromFile = new Button("Load image from file");
        paneButtonBox.getChildren().add(buttonLoadFromFile);
        buttonLoadFromFile.setMinWidth(buttonWidth);
        buttonLoadFromFile.pressedProperty().addListener((observable, wasPressed, pressed) -> {
            if (wasPressed) {
                System.out.println("wasPressed");
            }
        });

        Button buttonLoadFromURL = new Button("Load image from URL");
        paneButtonBox.getChildren().add(buttonLoadFromURL);
        buttonLoadFromURL.setMinWidth(buttonWidth);
        buttonLoadFromURL.pressedProperty().addListener((observable, wasPressed, pressed) -> {
            if (wasPressed) {
                System.out.println("wasPressed");
            }
        });

        HBox boxFilePath = new HBox();
        paneTextFieldsBox.getChildren().add(boxFilePath);

        Button buttonOpenFileDialog = new Button("Browse");
        boxFilePath.getChildren().add(buttonOpenFileDialog);
        buttonOpenFileDialog.setMinWidth(buttonBrowseWidth);
        buttonOpenFileDialog.pressedProperty().addListener((observable, wasPressed, pressed) -> {
            if (wasPressed) {
                System.out.println("wasPressed");
            }
        });

        TextField textPathToImageFile = new TextField();
        boxFilePath.getChildren().add(textPathToImageFile);
        textPathToImageFile.setMinWidth(configurationManager.getWINDOW_WIDTH() - buttonWidth - buttonBrowseWidth - 30);
        textPathToImageFile.setMaxWidth(configurationManager.getWINDOW_WIDTH() - buttonWidth - buttonBrowseWidth - 30);

        TextField textURLToImageFile = new TextField();
        paneTextFieldsBox.getChildren().add(textURLToImageFile);
        textURLToImageFile.setMinWidth(configurationManager.getWINDOW_WIDTH() - buttonWidth - 30);
        textURLToImageFile.setMaxWidth(configurationManager.getWINDOW_WIDTH() - buttonWidth - 30);

        paneButtonBox.setPadding(new Insets(padding,padding,padding,padding));
        paneTextFieldsBox.setPadding(new Insets(padding,padding,padding,0));

        paneButtonBox.setPadding(new Insets(padding,padding,padding,padding));

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
}
