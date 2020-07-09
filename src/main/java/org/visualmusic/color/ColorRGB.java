package org.visualmusic.color;

import lombok.Getter;
import lombok.NonNull;
import lombok.With;

public class ColorRGB {
    @With @Getter
    private int red;   // R
    @With @Getter
    private int green; // G
    @With @Getter
    private int blue;  // B

    private ColorRGB() {
    }

    public ColorRGB(@NonNull ColorRGB that) {
        this.red = that.red;
        this.green = that.green;
        this.blue = that.blue;
    }

    public ColorRGB(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
    }
}
