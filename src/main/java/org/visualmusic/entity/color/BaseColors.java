package org.visualmusic.entity.color;

public interface BaseColors {
    double epsilon = 0.00000000000001;
    MultiColor BLACK = new MultiColor(new ColorRGB(0.0,0.0, 0.0));
    MultiColor WHITE = new MultiColor(new ColorRGB(1.0,1.0, 1.0));
    MultiColor RED   = new MultiColor(new ColorRGB(1.0,0.0, 0.0));
    MultiColor GREEN = new MultiColor(new ColorRGB(0.0,1.0, 0.0));
    MultiColor BLUE  = new MultiColor(new ColorRGB(0.0,0.0, 1.0));
    // ...
}
