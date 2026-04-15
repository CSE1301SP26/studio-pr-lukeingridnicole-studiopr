package game;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

public class Entity {
    private double xPosition;
    private Color color;
    private double yPosition;
    private double size;

    public Entity(double x, double y, double size, Color color) {
        this.xPosition = x;
        this.yPosition = y;
        this.size = size;
        this.color = color;
    }

    

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(xPosition, yPosition, size);
    }

    public double getSize() {
        return this.size;
    }

    public double getXPosition() {
        return this.xPosition;
    }

    public double getYPosition() {
        return this.yPosition;
    }

    public boolean collidesWith(Entity e) {
        double distance = Math.sqrt(Math.pow(this.xPosition - e.getXPosition(), 2) + Math.pow(this.yPosition - e.getYPosition(), 2));
        if(distance > this.getSize() + e.getSize()) {
            return false;
        } 
            return true;
    }

    public void setXPosition(double x) {
        this.xPosition = x;
    }

    public void setYPosition(double y) {
        this.yPosition = y;
    }
}
