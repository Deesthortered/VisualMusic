package org.visualmusic.entity.color;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.With;

@EqualsAndHashCode
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
        this.cyan = c;
        this.magenta = m;
        this.yellow = y;
    }
}
