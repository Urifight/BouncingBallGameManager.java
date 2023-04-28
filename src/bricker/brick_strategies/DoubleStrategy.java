package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;

public class DoubleStrategy implements CollisionStrategy
{

    private GameObjectCollection gameObjectCollection;
    private CollisionStrategy collisionStrategy1;
    private CollisionStrategy collisionStrategy2;
    private Counter bricksLeft;

    public DoubleStrategy(GameObjectCollection gameObjectCollection, CollisionStrategy collisionStrategy1, CollisionStrategy collisionStrategy2, Counter bricksLeft)
    {

        this.gameObjectCollection = gameObjectCollection;
        this.collisionStrategy1 = collisionStrategy1;
        this.collisionStrategy2 = collisionStrategy2;
        this.bricksLeft = bricksLeft;

    }
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj)
    {
        collisionStrategy1.onCollision(thisObj, otherObj);

        collisionStrategy2.onCollision(thisObj, otherObj);
        bricksLeft.increaseBy(1);


    }
}
