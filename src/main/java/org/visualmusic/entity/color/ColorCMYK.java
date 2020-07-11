package org.visualmusic.entity.color;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.With;

@EqualsAndHashCode
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
        this.cyan = c;
        this.magenta = m;
        this.yellow = y;
        this.black = k;
    }
}
