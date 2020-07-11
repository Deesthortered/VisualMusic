package org.visualmusic.entity.color;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MultiColorTest {

    @Test
    public void testRGB_CMY() {
        for (int i = 0; i < 1000; i++) {
            ColorRGB redRGB1 = getRandomRgbColor();
            ColorCMY redCMY1 = MultiColor.fromRGBtoCMY(redRGB1);
            ColorRGB redRGB2 = MultiColor.fromCMYtoRGB(redCMY1);
            ColorCMY redCMY2 = MultiColor.fromRGBtoCMY(redRGB2);

            Assert.assertEquals(redRGB1, redRGB2);
            Assert.assertEquals(redCMY1, redCMY2);
        }
    }

    @Test
    public void testRGB_CMYK() {
        for (int i = 0; i < 1000; i++) {
            ColorRGB  redRGB1  = getRandomRgbColor();
            ColorCMYK redCMYK1 = MultiColor.fromRGBtoCMYK(redRGB1);
            ColorRGB  redRGB2  = MultiColor.fromCMYKtoRGB(redCMYK1);
            ColorCMYK redCMYK2 = MultiColor.fromRGBtoCMYK(redRGB2);

            Assert.assertEquals(redRGB1, redRGB2);
            Assert.assertEquals(redCMYK1, redCMYK2);
        }
    }

    @Test
    public void testRGB_HSV() {
        for (int i = 0; i < 1000; i++) {
            ColorRGB redRGB1 = getRandomRgbColor();
            ColorHSV redHSV1 = MultiColor.fromRGBtoHSV(redRGB1);
            ColorRGB redRGB2 = MultiColor.fromHSVtoRGB(redHSV1);
            ColorHSV redHSV2 = MultiColor.fromRGBtoHSV(redRGB2);

            Assert.assertEquals(redRGB1, redRGB2);
            Assert.assertEquals(redHSV1, redHSV2);
        }
    }

    private ColorRGB getRandomRgbColor() {
        double r = Math.random();
        double g = Math.random();
        double b = Math.random();
        return new ColorRGB(r, g, b);
    }
}