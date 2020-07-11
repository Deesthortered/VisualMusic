package org.visualmusic.entity.color;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.With;

@EqualsAndHashCode
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
        this.hue = h;
        this.saturation = s;
        this.value = v;
    }
}
