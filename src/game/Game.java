package game;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.introcs.StdDraw;


public class Game {
    
    private Player player;
    private List<Projectile> enemyProjectiles;
    private List<Projectile> playerProjectiles;
    private List<Enemy> enemies;
    private int numberOfEnemies;
    private int score;

    public Game() {
        player = new Player();
        enemyProjectiles = new LinkedList<>();
        playerProjectiles = new LinkedList<>();
        enemies = new LinkedList<>();
        numberOfEnemies = 3;
        score = 0; 
    }

    public void run() {
        StdDraw.enableDoubleBuffering();
        while(true) {
            score = 0;
            initializeLevel();
            boolean gameOver = false;
            while(gameOver == false) {
                updatePositions();
                fireProjectiles();
                gameOver = checkCollisions();
                if(enemies.size() == 0) {
                    numberOfEnemies++;
                    initializeLevel();
                }
                draw();
            }
        }
    }

    public void initializeLevel() {
        player = new Player();
        enemyProjectiles.clear();
        playerProjectiles.clear();
        enemies.clear();
        for(int i = 0; i < numberOfEnemies; i++) {
            Enemy e = new Enemy();
            enemies.add(e);
        }  
    }

    private void updatePositions() {
        for(Projectile enemyProjectile : enemyProjectiles) {
            enemyProjectile.moveDown();
            if(enemyProjectile.isOutOfBounds() == true) {
                enemyProjectiles.remove(enemyProjectile);
            }
        }

        for(Projectile playerProjectile : playerProjectiles) {
            playerProjectile.moveUp();
            if(playerProjectile.isOutOfBounds() == true) {
                playerProjectiles.remove(playerProjectile);
            }
        }

        for(Enemy enemy : enemies) {
            enemy.move();
        }

        player.move();
    }

    private void fireProjectiles() {
        for(Enemy enemy : enemies) {
            if(enemy.isFiring() == true) {
                Projectile p = new Projectile(enemy.getXPosition(), enemy.getYPosition() - enemy.getSize(), Color.RED);
                enemyProjectiles.add(p);
            }
            
        }
        if(player.isFiring() == true) {
            Projectile p = new Projectile(player.getXPosition(), player.getYPosition() + player.getSize(), Color.BLACK);
            playerProjectiles.add(p);
        }
    }

    private boolean checkCollisions() {
        for(Projectile playerProjectile : playerProjectiles) {
            for(Enemy enemy : enemies) {
                if(playerProjectile.collidesWith(enemy) == true) {
                    enemies.remove(enemy);
                    playerProjectiles.remove(playerProjectile);
                    score++;
                }
            }
        }

        for(Projectile enemyProjectile : enemyProjectiles) {
            if(enemyProjectile.collidesWith(player)) {
                return true;
            }
        }
        return false;
    }

    private void draw() {
        StdDraw.clear();
        for(Enemy enemy : enemies) {
            enemy.draw();
        }
        for(Projectile playerProjectile : playerProjectiles) {
            playerProjectile.draw();
        }
        for(Projectile enemyProjectile : enemyProjectiles) {
            enemyProjectile.draw();
        }
        player.draw();
        StdDraw.text(0.1, 0.9, "Score: " + score);
        StdDraw.pause(40);
        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.run();
    }
}
