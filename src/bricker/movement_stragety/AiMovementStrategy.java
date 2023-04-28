package bricker.movement_stragety;

import danogl.GameObject;
import danogl.util.Vector2;

public class AiMovementStrategy implements MovementStrategy
{

    private GameObject objectToFollow;
    private float apsilon = 5;


    public AiMovementStrategy(GameObject objectToFollow) {
        this.objectToFollow = objectToFollow;
    }


    @Override
    public Vector2 calcMovementDirection(GameObject owner) {
        Vector2 movementDir = Vector2.ZERO;
        float range = Math.abs(objectToFollow.getCenter().x() - owner.getCenter().x());
        if ((range > apsilon + 10 || range < apsilon - 10))
        {
            if (objectToFollow.getCenter().x() < owner.getCenter().x())
                movementDir = Vector2.LEFT;

            if (objectToFollow.getCenter().x() > owner.getCenter().x())
                movementDir = Vector2.RIGHT;

        }
        return movementDir;
    }
}
