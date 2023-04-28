package bricker.movement_stragety;

import danogl.GameObject;
import danogl.util.Vector2;

/**
 * An interface for defining the movement of Paddle.
 * @author uriya
 */
public interface MovementStrategy
{
    /**
     * The main method of the strategy, used by the paddle to determain direction.
     * @param owner The paddle that owns this strategy
     * @return A movement direction of type Vector 2 whose y coordinate zero,
     * and x coordinate is -1, 1, or 0.
     */
    Vector2 calcMovementDirection(GameObject owner);
}
