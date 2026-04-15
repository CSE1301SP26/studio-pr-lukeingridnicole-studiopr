package game;

import java.awt.Color;

public class Projectile extends Entity {

    public Projectile(double x, double y, Color color) {
        super(x, y, .01, color);
    }

    public void moveUp() {
        setYPosition(this.getYPosition() + super.getSize());
    }

    public void moveDown() {
        setYPosition(this.getYPosition() - super.getSize());
    }

    public boolean isOutOfBounds() {
        if(this.getYPosition() > 1 || this.getYPosition() < 0) {
            return true;
        }
        return false;
    }

}
