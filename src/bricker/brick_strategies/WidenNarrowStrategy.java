package bricker.brick_strategies;

import bricker.gameobjects.Narrow;
import bricker.gameobjects.Widen;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Random;

public class WidenNarrowStrategy extends RemoveBrickStrategy
{
    private static final float WIDTH = 170;
    private static final float HEIGHT = 35;
    Random rnd = new Random();
    private GameObjectCollection gameObjectCollection;
    private WindowController windowController;
    private Renderable widenImage;
    private Renderable narrowImage;
    private Counter paddleSizeBuffChange;
    private GameObject paddle;

    public WidenNarrowStrategy(GameObjectCollection gameObjectCollection, Counter bricksLeft, WindowController windowController, Renderable widenImage, Renderable narrowImage, Counter paddleSizeBuffChange, GameObject paddle) {
        super(gameObjectCollection, bricksLeft);
        this.gameObjectCollection = gameObjectCollection;
        this.windowController = windowController;
        this.widenImage = widenImage;
        this.narrowImage = narrowImage;
        this.paddleSizeBuffChange = paddleSizeBuffChange;
        this.paddle = paddle;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj)
    {
        super.onCollision(thisObj, otherObj);

        GameObject buff;
        if (rnd.nextBoolean())
            {
                buff = new Narrow(
                        Vector2.ZERO,
                        new Vector2(WIDTH, HEIGHT),
                        narrowImage,
                        gameObjectCollection,
                        windowController,
                        paddleSizeBuffChange,
                        paddle);
            }
        
        else
            {
                buff = new Widen(
                        Vector2.ZERO,
                        new Vector2(WIDTH, HEIGHT),
                        widenImage,
                        gameObjectCollection,
                        windowController,
                        paddleSizeBuffChange,
                        paddle);
            }

        buff.setCenter(new Vector2(getThisObj().getCenter().x(), getThisObj().getCenter().y() + 60));

        gameObjectCollection.addGameObject(buff, Layer.STATIC_OBJECTS);
    }
}
