package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

public class GravityArrow extends GameObject {

    private Counter direction;
    private Counter collisions;
    private GameObjectCollection gameObjectCollection;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public GravityArrow(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Counter collisions, Counter direction, GameObjectCollection gameObjectCollection) {
        super(topLeftCorner, dimensions, renderable);
        this.direction = direction;
        this.collisions = collisions;
        this.gameObjectCollection = gameObjectCollection;
        collisions.reset();
        this.renderer().setOpaqueness(1f);

        this.renderer().setRenderableAngle(direction.value());
        collisions.increment();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkBallCollisions();
    }

    public void checkBallCollisions()
    {
        if (collisions.value() == 2)
            this.renderer().setOpaqueness(0.75f);
        else if (collisions.value() == 3)
            this.renderer().setOpaqueness(0.5f);
        else if (collisions.value() == 4)
            this.renderer().setOpaqueness(0.25f);
        else {
            this.renderer().setOpaqueness(0f);
            gameObjectCollection.removeGameObject(this, Layer.BACKGROUND);

        }

    }
}

