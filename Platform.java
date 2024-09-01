package se233.chapter4.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import se233.chapter4.Launcher;
import se233.chapter4.model.Character;
import se233.chapter4.model.Keys;

public class Platform extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public final static int GROUND = 300;
    private Image platformImg;
    private Character character;
    private Character character2;
    private Keys keys;
    public Platform() {
        keys = new Keys();
        platformImg = new Image(Launcher.class.getResourceAsStream("assets/Background.png"));
        ImageView backgroundImg = new ImageView(platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        character = new Character(30, 30, 0, 0, KeyCode.A, KeyCode.D, KeyCode.W, "assets/MarioSheet.png", 16, 32);
//        character2 = new Character(700, 30, 15, 11, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, "assets/MegaMan6.png", 32, 32);
//        character2 = new Character(700, 30, 0, 0, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, "assets/Megaman2-resize.png", 28, 33);
        character2 = new Character(700, 30, 1, 1, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, "assets/compiled_mega_man_sprite_sheet_by_metroidpeter_dahqbt6-fullview.png", 21, 24);
        character2.setxAcceleration(2);
        character2.setyAcceleration(2);
        character2.setxMaxVelocity(20);
        character2.setyMaxVelocity(30);
        getChildren().addAll(backgroundImg,character,character2);
//        getChildren().addAll(backgroundImg,character);
    }

    public Character getCharacter() {
        return character;
    }

    public Character getCharacter2() {
        return character2;
    }

    public Keys getKeys() { return keys; }
}

