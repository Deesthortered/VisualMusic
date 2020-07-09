package org.visualmusic.color;

import lombok.Getter;
import lombok.NonNull;

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
        this.colorRGB = new ColorRGB(that.colorRGB);
        this.colorCMY = new ColorCMY(that.colorCMY);
        this.colorCMYK = new ColorCMYK(that.colorCMYK);
        this.colorHSV = new ColorHSV(that.colorHSV);
    }

    public MultiColor(@NonNull ColorRGB inputColor) {
        this.colorRGB = new ColorRGB(inputColor);
        this.colorCMY = new ColorCMY(MultiColor.fromRGBtoCMY(inputColor));
        this.colorCMYK = new ColorCMYK(MultiColor.fromRGBtoCMYK(inputColor));
        this.colorHSV = new ColorHSV(MultiColor.fromRGBtoHSV(inputColor));
    }

    public MultiColor(@NonNull ColorCMY inputColor) {
        this.colorRGB = new ColorRGB(MultiColor.fromCMYtoRGB(inputColor));
        this.colorCMY = new ColorCMY(MultiColor.fromRGBtoCMY(this.colorRGB));
        this.colorCMYK = new ColorCMYK(MultiColor.fromRGBtoCMYK(this.colorRGB));
        this.colorHSV = new ColorHSV(MultiColor.fromRGBtoHSV(this.colorRGB));
    }

    public MultiColor(@NonNull ColorCMYK inputColor) {
        this.colorRGB = new ColorRGB(MultiColor.fromCMYKtoRGB(inputColor));
        this.colorCMY = new ColorCMY(MultiColor.fromRGBtoCMY(this.colorRGB));
        this.colorCMYK = new ColorCMYK(MultiColor.fromRGBtoCMYK(this.colorRGB));
        this.colorHSV = new ColorHSV(MultiColor.fromRGBtoHSV(this.colorRGB));
    }

    public MultiColor(@NonNull ColorHSV inputColor) {
        this.colorRGB = new ColorRGB(MultiColor.fromHSVtoRGB(inputColor));
        this.colorCMY = new ColorCMY(MultiColor.fromRGBtoCMY(this.colorRGB));
        this.colorCMYK = new ColorCMYK(MultiColor.fromRGBtoCMYK(this.colorRGB));
        this.colorHSV = new ColorHSV(MultiColor.fromRGBtoHSV(this.colorRGB));
    }


    public static ColorCMY fromRGBtoCMY(@NonNull ColorRGB inputColor) {
        return new ColorCMY(1, 1, 1);
    }

    public static ColorRGB fromCMYtoRGB(@NonNull ColorCMY inputColor) {
        return new ColorRGB(1, 1, 1);
    }


    public static ColorCMYK fromRGBtoCMYK(@NonNull ColorRGB inputColor) {
        return new ColorCMYK(1, 1, 1, 1);
    }

    public static ColorRGB fromCMYKtoRGB(@NonNull ColorCMYK inputColor) {
        return new ColorRGB(1, 1, 1);
    }


    public static ColorHSV fromRGBtoHSV(@NonNull ColorRGB inputColor) {
        return new ColorHSV(1, 1, 1);
    }

    public static ColorRGB fromHSVtoRGB(@NonNull ColorHSV inputColor) {
        return new ColorRGB(1, 1, 1);
    }
}
