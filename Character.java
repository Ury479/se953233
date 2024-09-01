package src.main.java.se233.chapter4.model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.chapter4.Launcher;
import se233.chapter4.view.Platform;

public class Character extends Pane {
    Logger logger = LoggerFactory.getLogger(Character.class);
//    Logger logger = LogManager.getLogger(Character.class.getResource("log4j2.properties"));
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    private Image characterImg;
    private AnimatedSprite imageView;
    private int x;
    private int y;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    int xVelocity = 0;
    int yVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 7;
    int yMaxVelocity = 17;

    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;

    public Character(int x, int y, int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey, String imgPath, int width, int height) {
        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.characterImg = new Image(Launcher.class.getResourceAsStream(imgPath));
        this.imageView = new AnimatedSprite(characterImg, 4, 4, 1, offsetX, offsetY, width, height);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
    }

    public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
        } else if (x + getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int) getWidth();
        }
    }

    public void jump() {
        if (canJump) {
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }

    public void checkReachHighest() {
        if (isJumping && yVelocity <= 0) {
            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }

    public void checkReachFloor() {
        if (isFalling && y >= Platform.GROUND - CHARACTER_HEIGHT) {
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }

    public void moveLeft() {
        isMoveLeft = true;
        isMoveRight = false;
    }

    public void moveRight() {
        isMoveLeft = false;
        isMoveRight = true;
    }

    public void stop() {
        isMoveLeft = false;
        isMoveRight = false;
    }

    public void moveX() {
        setTranslateX(x);
        if (isMoveLeft) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x - xVelocity;
        }
        if (isMoveRight) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x + xVelocity;
        }
    }

    public void moveY() {
        setTranslateY(y);
        if (isFalling) {
            yVelocity = yVelocity >= yMaxVelocity ? yMaxVelocity : yVelocity + yAcceleration;
            y = y + yVelocity;
        } else if (isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity - yAcceleration;
            y = y - yVelocity;
        }
    }

    public void repaint() {
        moveX();
        moveY();
    }

    // Exercise 2 START
    public void setxAcceleration(int xAcceleration) {
        this.xAcceleration = xAcceleration;
    }

    public void setyAcceleration(int yAcceleration) {
        this.yAcceleration = yAcceleration;
    }

    public void setxMaxVelocity(int xMaxVelocity) {
        this.xMaxVelocity = xMaxVelocity;
    }

    public void setyMaxVelocity(int yMaxVelocity) {
        this.yMaxVelocity = yMaxVelocity;
    }
//    public void moveX2() {
//        setTranslateX(x);
//        if (isMoveLeft) {
//            xVelocity2 = xVelocity2 >= xMaxVelocity2 ? xMaxVelocity2 : xVelocity2 + xAcceleration2;
//            x = x - xVelocity2;
//        }
//        if (isMoveRight) {
//            xVelocity2 = xVelocity2 >= xMaxVelocity2 ? xMaxVelocity2 : xVelocity2 + xAcceleration2;
//            x = x + xVelocity2;
//        }
//    }
//
//    public void moveY2() {
//        setTranslateY(y);
//        if (isFalling) {
//            yVelocity2 = yVelocity2 >= yMaxVelocity2 ? yMaxVelocity2 : yVelocity2 + yAcceleration2;
//            y = y + yVelocity2;
//        } else if (isJumping) {
//            yVelocity2 = yVelocity2 <= 0 ? 0 : yVelocity2 - yAcceleration2;
//            y = y - yVelocity2;
//        }
//    }
//
//    public void repaint2() {
//        moveX2();
//        moveY2();
//    }
    // Exercise 2 END

    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}", x, y, xVelocity, yVelocity);
//        System.out.println(x + " " + y + " " + xVelocity + " " + yVelocity);
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }

    public KeyCode getUpKey() {
        return upKey;
    }
}
