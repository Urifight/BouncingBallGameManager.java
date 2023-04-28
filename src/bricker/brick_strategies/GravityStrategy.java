package bricker.brick_strategies;

import bricker.gameobjects.GravityArrow;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.Sound;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Random;

public class GravityStrategy extends RemoveBrickStrategy
{
    private Counter direction;


    private int SIZE = 100;
    private Renderable gravityImage;
    private WindowController windowController;
    private Sound collisionSound;
    private Counter collisions;
    private GameObjectCollection gameObjectCollection;

    public GravityStrategy(GameObjectCollection gameObjectCollection, Counter bricksLeft, Renderable gravityImage, WindowController windowController, Sound collisionSound, Counter collisions, Counter direction) {
        super(gameObjectCollection, bricksLeft);
        this.gameObjectCollection = gameObjectCollection;
        this.gravityImage = gravityImage;
        this.windowController = windowController;
        this.collisionSound = collisionSound;
        this.collisions = collisions;
        this.direction = direction;
    }

    public void setDirection()
    {
        Random rnd = new Random();
        int num = rnd.nextInt(4);
        switch (num)
        {
            case 0:
                direction.increaseBy(90);
                break;

            case 1:
                direction.increaseBy(180);
                break;

            case 2:
                direction.increaseBy(270);
                break;

            case 3:
                direction.increaseBy(0);
                break;
        }
    }
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj)
    {
        direction.reset();

        setDirection();

        super.onCollision(thisObj, otherObj);

        Vector2 windowDimensions = windowController.getWindowDimensions();

        GameObject gravityArrow = new GravityArrow(
                Vector2.ZERO, new Vector2(SIZE, SIZE), gravityImage, collisions, direction, gameObjectCollection);

        gravityArrow.setCenter(new Vector2(windowDimensions.x() / 2, windowDimensions.y() / 2));

        gameObjectCollection.addGameObject(gravityArrow, Layer.BACKGROUND);
    }
}
