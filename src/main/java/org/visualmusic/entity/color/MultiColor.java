package org.visualmusic.entity.color;

import lombok.*;

import static com.sun.javafx.util.Utils.RGBtoHSB;
import static com.sun.javafx.util.Utils.HSBtoRGB;


@EqualsAndHashCode @ToString
public class MultiColor {
    @Getter
    private ColorRGB colorRGB;
    @Getter
    private ColorCMY colorCMY;
    @Getter
    private ColorCMYK colorCMYK;
    @Getter
    private ColorHSV colorHSV;

    private MultiColor() {
    }

    public MultiColor(@NonNull MultiColor that) {
        this.colorRGB  = that.colorRGB;
        this.colorCMY  = that.colorCMY;
        this.colorCMYK = that.colorCMYK;
        this.colorHSV  = that.colorHSV;
    }

    public MultiColor(@NonNull ColorRGB inputColor) {
        this.colorRGB  = inputColor;
        this.colorCMY  = MultiColor.fromRGBtoCMY(inputColor);
        this.colorCMYK = MultiColor.fromRGBtoCMYK(inputColor);
        this.colorHSV  = MultiColor.fromRGBtoHSV(inputColor);
    }

    public MultiColor(@NonNull ColorCMY inputColor) {
        this.colorRGB  = MultiColor.fromCMYtoRGB(inputColor);
        this.colorCMY  = MultiColor.fromRGBtoCMY(this.colorRGB);
        this.colorCMYK = MultiColor.fromRGBtoCMYK(this.colorRGB);
        this.colorHSV  = MultiColor.fromRGBtoHSV(this.colorRGB);
    }

    public MultiColor(@NonNull ColorCMYK inputColor) {
        this.colorRGB  = MultiColor.fromCMYKtoRGB(inputColor);
        this.colorCMY  = MultiColor.fromRGBtoCMY(this.colorRGB);
        this.colorCMYK = MultiColor.fromRGBtoCMYK(this.colorRGB);
        this.colorHSV  = MultiColor.fromRGBtoHSV(this.colorRGB);
    }

    public MultiColor(@NonNull ColorHSV inputColor) {
        this.colorRGB  = MultiColor.fromHSVtoRGB(inputColor);
        this.colorCMY  = MultiColor.fromRGBtoCMY(this.colorRGB);
        this.colorCMYK = MultiColor.fromRGBtoCMYK(this.colorRGB);
        this.colorHSV  = MultiColor.fromRGBtoHSV(this.colorRGB);
    }


    public static ColorCMY fromRGBtoCMY(@NonNull ColorRGB inputColor) {
        double c = 1.0 - inputColor.getRed();
        double m = 1.0 - inputColor.getGreen();
        double y = 1.0 - inputColor.getBlue();

        return new ColorCMY(c, m, y);
    }

    public static ColorRGB fromCMYtoRGB(@NonNull ColorCMY inputColor) {
        double r = 1.0 - inputColor.getCyan();
        double g = 1.0 - inputColor.getMagenta();
        double b = 1.0 - inputColor.getYellow();

        return new ColorRGB(r, g, b);
    }


    public static ColorCMYK fromRGBtoCMYK(@NonNull ColorRGB inputColor) {
        double min = Math.min(1.0 - inputColor.getRed(),
                     Math.min(1.0 - inputColor.getGreen(),
                              1.0 - inputColor.getBlue()));

        double c = 1.0 - inputColor.getRed()   - min;
        double m = 1.0 - inputColor.getGreen() - min;
        double y = 1.0 - inputColor.getBlue()  - min;
        double k = min;

        return new ColorCMYK(c, m, y, k);
    }

    public static ColorRGB fromCMYKtoRGB(@NonNull ColorCMYK inputColor) {
        double r = 1.0 - (inputColor.getCyan()    + inputColor.getBlack());
        double g = 1.0 - (inputColor.getMagenta() + inputColor.getBlack());
        double b = 1.0 - (inputColor.getYellow()  + inputColor.getBlack());

        return new ColorRGB(r, g, b);
    }


    public static ColorHSV fromRGBtoHSV(@NonNull ColorRGB inputColor) {
        double r = inputColor.getRed();
        double g = inputColor.getGreen();
        double b = inputColor.getBlue();

        double[] hsv = RGBtoHSB(r, g, b);
        double h = hsv[0];
        double s = hsv[1];
        double v = hsv[2];

        return new ColorHSV(h, s, v);
    }

    public static ColorRGB fromHSVtoRGB(@NonNull ColorHSV inputColor) {
        double h = inputColor.getHue();
        double s = inputColor.getSaturation();
        double v = inputColor.getValue();

        double[] rgb = HSBtoRGB(h, s, v);
        double r = rgb[0];
        double g = rgb[1];
        double b = rgb[2];

        return new ColorRGB(r, g, b);
    }
}
