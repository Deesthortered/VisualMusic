package org.visualmusic.entity.color;

import lombok.*;

import java.util.Arrays;

@ToString
public class ColorRGB {
    @With @Getter
    private double red;   // R: [0, 1]
    @With @Getter
    private double green; // G: [0, 1]
    @With @Getter
    private double blue;  // B: [0, 1]

    private ColorRGB() {
    }

    public ColorRGB(@NonNull ColorRGB that) {
        this.red = that.red;
        this.green = that.green;
        this.blue = that.blue;
    }

    public ColorRGB(double r, double g, double b) {
        validateInputs(r, g, b);
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {
                red,
                green,
                blue
        });
    }

    @Override
    public boolean equals(Object that) {
        if (this == that)
            return true;
        if (that == null)
            return false;
        if (getClass() != that.getClass())
            return false;
        ColorRGB thatColor = (ColorRGB) that;
        return Math.abs(this.red   - thatColor.red)   < BaseColors.epsilon &&
               Math.abs(this.green - thatColor.green) < BaseColors.epsilon &&
               Math.abs(this.blue  - thatColor.blue)  < BaseColors.epsilon;
    }

    private void validateInputs(double r, double g, double b) {
        if (r < 0 || r > 1 || g < 0 || g > 1 || b < 0 || b > 1)
            throw new IllegalArgumentException(
                    "RGB: Values are not in the needed range: " +
                    "r = " + r + ", " +
                    "g = " + g + ", " +
                    "b = " + b +
                    ".");
    }
}
