package org.visualmusic.entity.color;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.With;

@EqualsAndHashCode
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
        this.red = r;
        this.green = g;
        this.blue = b;
    }
}
