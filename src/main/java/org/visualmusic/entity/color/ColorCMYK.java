package org.visualmusic.entity.color;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.With;

@EqualsAndHashCode
public class ColorCMYK {
    @With @Getter
    private int cyan;    // C
    @With @Getter
    private int magenta; // M
    @With @Getter
    private int yellow;  // Y
    @With @Getter
    private int black;   // K

    private ColorCMYK() {
    }

    public ColorCMYK(@NonNull ColorCMYK that) {
        this.cyan = that.cyan;
        this.magenta = that.magenta;
        this.yellow = that.yellow;
        this.black = that.black;
    }

    public ColorCMYK(int c, int m, int y, int k) {
        this.cyan = c;
        this.magenta = m;
        this.yellow = y;
        this.black = k;
    }
}
