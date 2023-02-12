package org.cis120.twentyfortyeight;

public class Tile {

    private int value;

    public Tile() {
        this.value = 0;
    }

    // creates a Tile with a given value
    public Tile(int value) {
        this.value = value;
    }

    // gets the value of a Tile
    public int getTileValue() {
        return this.value;
    }

    // sets the value of a Tile to a given value (used for JUnit testing purposes)
    public void setTileValue(int v) {
        this.value = v;
    }
}
