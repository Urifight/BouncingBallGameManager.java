package bricker.movement_stragety;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.util.Vector2;

public class UserMovementStrategy implements MovementStrategy
{
    private UserInputListener inputListener;
    private int left;
    private int right;

    public UserMovementStrategy(UserInputListener inputListener, int left, int right) {
        this.inputListener = inputListener;
        this.left = left;
        this.right = right;
    }


    @Override
    public Vector2 calcMovementDirection(GameObject owner)
    {
        Vector2 movementDirection = Vector2.ZERO;
        if (inputListener.isKeyPressed(left))
        {
            movementDirection = movementDirection.add(Vector2.LEFT);
        }
        if (inputListener.isKeyPressed(right))
        {
            movementDirection = movementDirection.add(Vector2.RIGHT);
        }

        return movementDirection;
    }


}
