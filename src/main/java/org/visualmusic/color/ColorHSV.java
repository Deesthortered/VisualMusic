package org.visualmusic.color;

import lombok.Getter;
import lombok.NonNull;
import lombok.With;

public class ColorHSV {
    @With @Getter
    private int hue;        // H
    @With @Getter
    private int saturation; // S
    @With @Getter
    private int value;      // V

    private ColorHSV() {
    }

    public ColorHSV(@NonNull ColorHSV that) {
        this.hue = that.hue;
        this.saturation = that.saturation;
        this.value = that.value;
    }

    public ColorHSV(int h, int s, int v) {
        this.hue = h;
        this.saturation = s;
        this.value = v;
    }
}
