package bricker.brick_strategies;

import bricker.gameobjects.BadBot;
import bricker.gameobjects.GoodBot;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Random;

public class BotStrategy extends RemoveBrickStrategy
{
    Random rnd = new Random();
    private WindowController windowController;
    private float bricksTotalHeight;
    private Renderable goodBotImage;
    private Renderable badBotImage;
    private GameObject ball;
    private GameObjectCollection gameObjectCollection;


    private static final float BOT_HEIGHT = 40;
    private static final float BOT_WIDTH = 160;
    private static final int SIZE = 40;

    public BotStrategy(GameObjectCollection gameObjectCollection, Counter bricksLeft, WindowController windowController, float bricksTotalHeight, Renderable goodBotImage, Renderable badBotImage, GameObject ball) {
        super(gameObjectCollection, bricksLeft);
        this.windowController = windowController;
        this.bricksTotalHeight = bricksTotalHeight;
        this.goodBotImage = goodBotImage;
        this.badBotImage = badBotImage;
        this.ball = ball;
        this.gameObjectCollection = gameObjectCollection;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        GameObject bot;
        Vector2 windowDimensions = windowController.getWindowDimensions();
        if (rnd.nextBoolean())
        {
            bot = new GoodBot(Vector2.ZERO, new Vector2(BOT_WIDTH, BOT_HEIGHT), goodBotImage, windowController, ball);
        }
        else
            bot = new BadBot(Vector2.ZERO, new Vector2(BOT_WIDTH, BOT_HEIGHT), badBotImage, windowController, ball);

        bot.setCenter(new Vector2(rnd.nextFloat(10, windowDimensions.x() - 10), rnd.nextFloat(bricksTotalHeight + 30, windowDimensions.y() - 10)));

        gameObjectCollection.addGameObject(bot);

    }
}
