package org.visualmusic.entity.color;

import lombok.*;

import java.util.Arrays;

@ToString
public class ColorHSV {
    @With @Getter
    private double hue;        // H: [0, 360)
    @With @Getter
    private double saturation; // S: [0, 1]
    @With @Getter
    private double value;      // V: [0, 1]

    private ColorHSV() {
    }

    public ColorHSV(@NonNull ColorHSV that) {
        this.hue = that.hue;
        this.saturation = that.saturation;
        this.value = that.value;
    }

    public ColorHSV(double h, double s, double v) {
        validateInputs(h, s, v);
        this.hue = h;
        this.saturation = s;
        this.value = v;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {
                hue,
                saturation,
                value
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
        ColorHSV thatColor = (ColorHSV) that;
        return Math.abs(this.hue        - thatColor.hue)        < BaseColors.epsilon * 360 &&
               Math.abs(this.saturation - thatColor.saturation) < BaseColors.epsilon &&
               Math.abs(this.value      - thatColor.value)      < BaseColors.epsilon;
    }

    private void validateInputs(double h, double s, double v) {
        if (h < 0 || h > 360 || s < 0 || s > 1 || v < 0 || v > 1)
            throw new IllegalArgumentException(
                    "HSV: Values are not in the needed range: " +
                    "h = " + h + ", " +
                    "s = " + s + ", " +
                    "v = " + v +
                    ".");
    }
}
