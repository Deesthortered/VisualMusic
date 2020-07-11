package org.visualmusic.entity.color;

import lombok.*;

import java.util.Arrays;

@ToString
public class ColorCMYK {
    @With @Getter
    private double cyan;    // C: [0, 1]
    @With @Getter
    private double magenta; // M: [0, 1]
    @With @Getter
    private double yellow;  // Y: [0, 1]
    @With @Getter
    private double black;   // K: [0, 1]

    private ColorCMYK() {
    }

    public ColorCMYK(@NonNull ColorCMYK that) {
        this.cyan = that.cyan;
        this.magenta = that.magenta;
        this.yellow = that.yellow;
        this.black = that.black;
    }

    public ColorCMYK(double c, double m, double y, double k) {
        validateInputs(c, m, y, k);
        this.cyan = c;
        this.magenta = m;
        this.yellow = y;
        this.black = k;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[] {
                cyan,
                magenta,
                yellow,
                black
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
        ColorCMYK thatColor = (ColorCMYK) that;
        return Math.abs(this.cyan    - thatColor.cyan)    < BaseColors.epsilon &&
               Math.abs(this.magenta - thatColor.magenta) < BaseColors.epsilon &&
               Math.abs(this.yellow  - thatColor.yellow)  < BaseColors.epsilon &&
               Math.abs(this.black   - thatColor.black)   < BaseColors.epsilon;
    }

    private void validateInputs(double c, double m, double y, double k) {
        if (c < 0 || c > 1 || m < 0 || m > 1 || y < 0 || y > 1 || k < 0 || k > 1)
            throw new IllegalArgumentException(
                    "CMYK: Values are not in the needed range: " +
                    "c = " + c + ", " +
                    "m = " + m + ", " +
                    "y = " + y + ", " +
                    "k = " + k +
                    ".");
    }
}
