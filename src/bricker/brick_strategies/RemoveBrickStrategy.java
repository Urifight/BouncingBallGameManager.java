package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Counter;

public class RemoveBrickStrategy implements  CollisionStrategy
{
    private GameObjectCollection gameObjectCollection;
    private final Counter bricksLeft;
    private GameObject thisObj;

    private boolean wasBrickBroken = false;
    public RemoveBrickStrategy(GameObjectCollection gameObjectCollection, Counter bricksLeft)
    {

        this.gameObjectCollection = gameObjectCollection;

        this.bricksLeft = bricksLeft;
    }


    GameObject getThisObj()
    {
        return thisObj;
    }
    public void onCollision(GameObject thisObj, GameObject otherObj)
    {
        gameObjectCollection.removeGameObject(thisObj, Layer.STATIC_OBJECTS); //MUST ADD STATIC_OBJECTS WHEN DELETING
        bricksLeft.decrement();
        System.out.println(bricksLeft.value());
        this.thisObj = thisObj;
        wasBrickBroken = true;


    }



}
