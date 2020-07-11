package org.visualmusic.entity.color;

import lombok.*;

import java.util.Arrays;

@ToString
public class ColorCMY {
    @With @Getter
    private double cyan;    // C: [0, 1]
    @With @Getter
    private double magenta; // M: [0, 1]
    @With @Getter
    private double yellow;  // Y: [0, 1]

    private ColorCMY() {
    }

    public ColorCMY(@NonNull ColorCMY that) {
        this.cyan = that.cyan;
        this.magenta = that.magenta;
        this.yellow = that.yellow;
    }

    public ColorCMY(double c, double m, double y) {
        validateInputs(c, m, y);
        this.cyan = c;
        this.magenta = m;
        this.yellow = y;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {
                cyan,
                magenta,
                yellow,
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
        ColorCMY thatColor = (ColorCMY) that;
        return Math.abs(this.cyan    - thatColor.cyan)    < BaseColors.epsilon &&
               Math.abs(this.magenta - thatColor.magenta) < BaseColors.epsilon &&
               Math.abs(this.yellow  - thatColor.yellow)  < BaseColors.epsilon;
    }

    private void validateInputs(double c, double m, double y) {
        if (c < 0 || c > 1 || m < 0 || m > 1 || y < 0 || y > 1)
            throw new IllegalArgumentException(
                    "CMY: Values are not in the needed range: " +
                    "c = " + c + ", " +
                    "m = " + m + ", " +
                    "y = " + y +
                    ".");
    }
}
