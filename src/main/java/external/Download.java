package external;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import java.net.URL;

public class Download {
    public static void main(String[] args) throws Exception {
        String fileName = "digital_image_processing.jpg";
        String website = "https://www.w3schools.com/w3css/img_lights.jpg";

        System.out.println("Downloading File From: " + website);

        URL url = new URL(website);
        BufferedImage image = ImageIO.read(url);

        OutputStream outputStream = new FileOutputStream(fileName);
        ImageIO.write(image, "jpg", new File(fileName));
        outputStream.close();
    }
}