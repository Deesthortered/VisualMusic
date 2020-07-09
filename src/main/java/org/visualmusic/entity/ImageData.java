package org.visualmusic.entity;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import org.visualmusic.entity.color.BaseColors;
import org.visualmusic.entity.color.MultiColor;

public class ImageData {
    private Matrix<MultiColor> pixelMatrix;

    private ImageData() {
    }

    public ImageData(ImageData that) {
        this.pixelMatrix = new Matrix<>(that.pixelMatrix);
    }

    public ImageData(int width, int height, MultiColor backgroundColor) {
        this.pixelMatrix = new Matrix<>(width, height, backgroundColor);
    }

    public ImageData(Image javafxImage) {
        ImageData imageData = new ImageData((int) javafxImage.getWidth(), (int) javafxImage.getHeight(), BaseColors.BLACK);
        PixelReader pixelReader = javafxImage.getPixelReader();
        for (int i = 0; i < imageData.getWidth(); i++) {
            for (int j = 0; j < imageData.getHeight(); j++) {
                imageData.setPixel(i, j, new MultiColor(pixelReader.getColor(i, j)));
            }
        }
    }

    public int getWidth() {
        return this.pixelMatrix.getWidth();
    }

    public int getHeight() {
        return this.pixelMatrix.getHeight();
    }

    public MultiColor getPixel(int x, int y) {
        return this.pixelMatrix.getElement(x, y);
    }

    public void setPixel(int x, int y, MultiColor color) {
        this.pixelMatrix.setElement(x, y, color);
    }
}
