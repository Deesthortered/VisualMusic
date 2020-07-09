package org.visualmusic.entity.color;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.With;

@EqualsAndHashCode
public class ColorCMY {
    @With @Getter
    private int cyan;    // C
    @With @Getter
    private int magenta; // M
    @With @Getter
    private int yellow;  // Y

    private ColorCMY() {
    }

    public ColorCMY(@NonNull ColorCMY that) {
        this.cyan = that.cyan;
        this.magenta = that.magenta;
        this.yellow = that.yellow;
    }

    public ColorCMY(int c, int m, int y) {
        this.cyan = c;
        this.magenta = m;
        this.yellow = y;
    }
}
