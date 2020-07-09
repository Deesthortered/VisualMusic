package org.visualmusic.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matrix<ImmutableEntity> {
    private List<List<ImmutableEntity>> matrixArray;
    private int matrixWidth;
    private int matrixHeight;

    private Matrix() {
    }

    public Matrix(Matrix<ImmutableEntity> that) {
        this.matrixWidth = that.matrixWidth;
        this.matrixHeight = that.matrixHeight;

        this.matrixArray = new ArrayList<>(this.matrixWidth);
        for (int i = 0; i < this.matrixWidth; i++) {
            this.matrixArray.add(new ArrayList<>(that.matrixArray.get(i)));
        }
    }

    public Matrix(int width, int height, ImmutableEntity baseValue) {
        List<ImmutableEntity> list = Collections.nCopies(height, baseValue);
        this.matrixArray = new ArrayList<>(width);
        for (int i = 0; i < width; i++) {
            this.matrixArray.add(new ArrayList<>(list));
        }
    }

    public int getWidth() {
        return this.matrixWidth;
    }

    public int getHeight() {
        return this.matrixHeight;
    }

    public ImmutableEntity getElement(int x, int y) {
        validateCoordinates(x, y);
        return this.matrixArray.get(x).get(y);
    }

    public void setElement(int x, int y, ImmutableEntity value) {
        validateCoordinates(x, y);
        this.matrixArray.get(x).set(y, value);
    }


    private void validateCoordinates(int x, int y) {
        if (x < 0 || x > matrixWidth || y < 0 || y > matrixHeight) {
            throw new IllegalArgumentException(
                    "Coordinates are out of range:" +
                            " width = " + matrixWidth +
                            " height = " + matrixHeight +
                            " x = " + x +
                            " y = " + y + "."
            );
        }
    }
}
