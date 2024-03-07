package main;

class Vector2D {
    int gridX;
    int gridY;

    public Vector2D(int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
    }

    // Getters y setters (si son necesarios)
    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    @Override
    public String toString() {
        return "Vector2D{" + "gridX=" + gridX +", gridY=" + gridY +'}';
    }
}
