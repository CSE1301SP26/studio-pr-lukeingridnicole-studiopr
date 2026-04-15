package game;

import java.awt.Color;

public class Enemy extends Entity {

    private double xSpeed;
    private double ySpeed;
    private long lastFired;

    public Enemy() {
        super(Math.random() * 0.9, Math.random() * 0.8 + 0.15, 0.03, Color.RED);
        xSpeed = Math.random() * 0.05;
        ySpeed = Math.random() * 0.05;
        lastFired = System.currentTimeMillis();
    }

    public void move() {
        bounceOffWall();
        setXPosition(this.getXPosition() + xSpeed);
        setYPosition(this.getYPosition() + ySpeed);
    }

    private void bounceOffWall() {
        if(this.getXPosition() < 0) {
            xSpeed = -xSpeed;
        }
        if(this.getXPosition() > 1) {
            xSpeed = -xSpeed;
        }
        if(this.getYPosition() > 1) {
            ySpeed = -ySpeed;
        }
        if(this.getYPosition() < 0.15) {
            ySpeed = -ySpeed;
        }
    }

    public boolean isFiring() {
        long now = System.currentTimeMillis();
        if(now - lastFired > 1000) {
            lastFired = now;
            return true;
        } else {
            return false;
        }
    }
    
}
