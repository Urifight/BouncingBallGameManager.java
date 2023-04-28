package bricker.brick_strategies;

import danogl.GameObject;

public interface StatusStrategy
{
    public boolean shouldCollideWith(GameObject otherObj);
}
