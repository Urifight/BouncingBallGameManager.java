package bricker.brick_strategies;

import bricker.gameobjects.QuickenMotion;
import bricker.gameobjects.SlowMotion;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Random;

public class MotionStrategy extends RemoveBrickStrategy {

    private Random rnd = new Random();
    private GameObjectCollection gameObjectCollection;
    private WindowController windowController;
    private Renderable slowMotionImage;
    private Renderable quickenMotionImage;
    private Counter slowOrQuicken;

    private static final int HEIGHT = 90;
    private static final int WIDTH = 170;


    public MotionStrategy(GameObjectCollection gameObjectCollection, Counter bricksLeft, WindowController windowController, Renderable slowMotionImage, Renderable quickenMotionImage, Counter slowOrQuicken) {
        super(gameObjectCollection, bricksLeft);
        this.gameObjectCollection = gameObjectCollection;
        this.windowController = windowController;
        this.slowMotionImage = slowMotionImage;
        this.quickenMotionImage = quickenMotionImage;
        this.slowOrQuicken = slowOrQuicken;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj)
    {
        super.onCollision(thisObj, otherObj);

        GameObject motion;
        if (slowOrQuicken.value() == -1)
        {
            if (rnd.nextBoolean())
            {
                motion = new SlowMotion(
                        Vector2.ZERO,
                        new Vector2(WIDTH, HEIGHT),
                        slowMotionImage,
                        gameObjectCollection,
                        windowController,
                        slowOrQuicken);
            }
            else
            {
                motion = new QuickenMotion(
                        Vector2.ZERO,
                        new Vector2(WIDTH, HEIGHT),
                        quickenMotionImage,
                        gameObjectCollection,
                        windowController,
                        slowOrQuicken);
            }
        }
        else
        {
            if (slowOrQuicken.value() == 0) //if the last motion was slow, we make it fast
            {
                motion = new QuickenMotion(
                        Vector2.ZERO,
                        new Vector2(WIDTH, HEIGHT),
                        quickenMotionImage,
                        gameObjectCollection,
                        windowController,
                        slowOrQuicken);
            } else {
                motion = new SlowMotion(
                        Vector2.ZERO,
                        new Vector2(WIDTH, HEIGHT),
                        slowMotionImage,
                        gameObjectCollection,
                        windowController,
                        slowOrQuicken);
            }


        }
        motion.setCenter(new Vector2(getThisObj().getCenter().x(), getThisObj().getCenter().y() + 60));

        gameObjectCollection.addGameObject(motion, Layer.STATIC_OBJECTS);
    }

}
