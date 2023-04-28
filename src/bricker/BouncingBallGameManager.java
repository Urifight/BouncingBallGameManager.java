package bricker;

import bricker.brick_strategies.BrickStrategyFactory;
import bricker.brick_strategies.CollisionStrategy;
import bricker.gameobjects.*;
import bricker.movement_stragety.AiMovementStrategy;
import bricker.movement_stragety.UserMovementStrategy;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;
import java.util.Random;


/**
 * ==================================================================
 * @author uriya
 * @date 27/04/2023
 * This code manages and creates the objects, checks when there is win
 *
 */
public class BouncingBallGameManager extends GameManager
{

    GameObject ball;
    private Random rnd = new Random();

    private Vector2 windowDimensions;

    GameObject heart1;



    private Counter randomStrategy = new Counter(0);
    private Counter paddleSizeBuffChange = new Counter(0);

    private Counter ballX = new Counter(0);
    private Counter ballY = new Counter(0);

    private Counter ballCollisionsForCamera = new Counter(0);

    private Counter activateCamera = new Counter(0);

    private Counter activateGravity = new Counter(0);

    private GameObjectCollection gameObjectCollection;

    private Counter slowOrQuicken = new Counter(0);
    private int lives;
    private static final float WALL_SIZE = 5;
    private static final float PADDLE_HEIGHT = 30;//20;
    private static final float PADDLE_WIDTH = 210;//120;

    private Counter collisions = new Counter();

    private Counter direction = new Counter();

    private Renderable heartImage;

    private GameObject heart;

    private static final float BALL_RADIUS = 35;
    private static final float BALL_SPEED = 400;



    private static final float BRICKS_PER_ROW = 10;

    private static final float NUM_OF_ROWS_OF_BRICKS = 6;

    private Counter bricksLeft = new Counter();

    private static final float BRICK_HEIGHT = 30;//15;

    private static final float SPACE_SIZE_BETWEEN_BRICKS = 0f;

    private static final float SPACE_SIZE_BETWEEN_FRAME_TO_BRICKS = 15f;
    private float brickWidth;
    private WindowController windowController;
    GameObject[] hearts;
    private GameObject heart2;
    private GameObject heart3;
    private int maxStrategies = 8;
    private int minStrategies = 1;
    private GameObject userPaddle;

    public BouncingBallGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    private void createBall(ImageReader imageReader, SoundReader soundReader, WindowController windowController)
    {
        Renderable ballImage =
                imageReader.readImage("assets/ball.png", true);
        Sound collisionSound = soundReader.readSound("assets/blop.wav");
        ball = new Ball(
                new Vector2(0, 0), new Vector2(BALL_RADIUS, BALL_RADIUS), ballImage, collisionSound, windowController, (int)BALL_SPEED, collisions, direction, ballCollisionsForCamera);

        Vector2 windowDimensions = windowController.getWindowDimensions();
        ball.setCenter(windowDimensions.mult(0.5f));;
        gameObjects().addGameObject(ball);

        float ballVelX = BALL_SPEED;
        float ballVelY = BALL_SPEED;
        if (rnd.nextBoolean())
        {
            ballVelX *= rnd.nextFloat(-1, 1);
        }
        if (rnd.nextBoolean())
        {
            ballVelY *= rnd.nextFloat(-1, 1);
        }
        ball.setVelocity(new Vector2(ballVelX, ballVelY));



    }



    private void createUserPaddle(Renderable paddleImage, UserInputListener inputListener, WindowController windowController, SoundReader soundReader,
                                  int posX, int posY, int left, int right)
    {
        Vector2 windowDimensions = windowController.getWindowDimensions();

        Sound collisionSound = soundReader.readSound("assets/blop.wav");
        userPaddle = new Paddle(
                Vector2.ZERO,
                new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT),
                paddleImage,
                windowDimensions,
                new UserMovementStrategy(inputListener, left, right), false, gameObjectCollection, paddleSizeBuffChange);
//                left,
//                right);

        userPaddle.setCenter(
                new Vector2(posX, posY));

        gameObjects().addGameObject(userPaddle);
    }

    private void createAiPaddle(Renderable paddleImage, WindowController windowController)
    {
        float Height = 15;
        GameObject aiPaddle  = new Paddle(Vector2.ZERO, new Vector2(100, Height), paddleImage, windowDimensions,
                new AiMovementStrategy(ball), false, gameObjects(), paddleSizeBuffChange);
        aiPaddle.setCenter(
                new Vector2(windowDimensions.x() / 2, BRICK_HEIGHT * NUM_OF_ROWS_OF_BRICKS + Height));

        gameObjects().addGameObject(aiPaddle);
    }

    private void createBrick(Renderable brickImage, WindowController windowController,
                             CollisionStrategy collisionStrategy, float posX, float posY)
    {

        //Vector2 windowDimensions = windowController.getWindowDimensions();
        GameObject brick = new Brick(
                Vector2.ZERO,
                new Vector2(brickWidth, BRICK_HEIGHT),
                brickImage,
                collisionStrategy);
        brick.setCenter(new Vector2(posX, posY));
        gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkForGameEnd();
        System.out.println(bricksLeft.value());

    }

    private void checkForGameEnd()
    {
        float ballHeight = ball.getCenter().y();

        String prompt = "";
        if (ballHeight > windowDimensions.y())
        {

            loseLife();
            gameObjects().removeGameObject(hearts[lives], Layer.STATIC_OBJECTS);

            if (lives == 0)
                prompt = "You lose";
            else
                ball.setCenter(windowDimensions.mult(0.5f));
        }

        if (bricksLeft.value() <= 0)
        {
            prompt = "You win!";
        }

        if (!prompt.isEmpty())
        {
            prompt += " Play again?";
            if(windowController.openYesNoDialog(prompt))
                windowController.resetGame();
            else
                windowController.closeWindow();
        }

        //textRenderable.render(new Graphics2D, 50, new Vector2(100, 100), false, false, false);
    }

public void loseLife()
{
    lives--;
    if (lives == 2)
    {
        gameObjects().removeGameObject(heart3, -199);
    }
    else if (lives == 1)
    {
        gameObjects().removeGameObject(heart2, -199);

    }
    else
        gameObjects().removeGameObject(heart1, -199);


}


    public void createHearts() {
        float size = (windowDimensions.x() + windowDimensions.y()) / 40;
        float posX = windowDimensions.x() / 20;
        float posY = windowDimensions.y() - 50;


        heart1 = new GameObject(Vector2.ZERO,
                new Vector2(size, size),
                heartImage);
        heart2 = new GameObject(Vector2.ZERO,
                new Vector2(size, size),
                heartImage);
        heart3 = new GameObject(Vector2.ZERO,
                new Vector2(size, size),
                heartImage);



        heart1.setCenter(new Vector2(posX, posY));
        gameObjects().addGameObject(heart1, -199);
        posX += size + 10;
        heart2.setCenter(new Vector2(posX, posY));
        gameObjects().addGameObject(heart2, -199);
        posX += size + 10;
        heart3.setCenter(new Vector2(posX, posY));
        gameObjects().addGameObject(heart3, -199);
        posX += size + 10;


    }



    public void initializeGame(ImageReader imageReader,
                               SoundReader soundReader,
                               UserInputListener inputListener,
                               WindowController windowController)
    {
        slowOrQuicken.decrement();
        boolean twoPlayer = false;//windowController.openYesNoDialog("2 player?");
        boolean normalMode = windowController.openYesNoDialog("Normal mode?");
        if (!normalMode) {
            minStrategies = 7;
            maxStrategies = 8;
        }
        else
            maxStrategies = 7;

        lives = 3;
        hearts = new GameObject[lives];
        this.windowController = windowController;
        //new AnimationRenderable()

        //initialization
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        windowController.setTargetFramerate(50);
        windowDimensions = windowController.getWindowDimensions();


        //creating ball
        createBall(imageReader, soundReader, windowController);


        //hearts


        heartImage = imageReader.readImage("assets/heart.png", true);
        createHearts();

        //create user paddle
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", false);

        createUserPaddle(paddleImage, inputListener, windowController, soundReader, (int) windowDimensions.x() / 2, (int) windowDimensions.y() - 30,
                KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        if (twoPlayer)
            createUserPaddle(paddleImage, inputListener, windowController, soundReader, (int) windowDimensions.x() / 2, (int) windowDimensions.y() - 60,
                KeyEvent.VK_A, KeyEvent.VK_D);

        //create ai paddle
        //createAiPaddle(paddleImage, windowController);


        // creating the top
        Renderable topImage = imageReader.readImage("assets/top.png", true);
        GameObject top = new Top(Vector2.ZERO, new Vector2(windowDimensions.x(), 5), topImage);
        gameObjects().addGameObject(top, Layer.STATIC_OBJECTS);



        // screen

        Renderable screenImage = imageReader.readImage("assets/DARK_BG2_small.jpeg", false);
        GameObject screen = new Top(Vector2.ZERO, new Vector2(windowDimensions.x(), windowDimensions.y()), screenImage);
        gameObjects().addGameObject(screen, Layer.BACKGROUND);
        // creating walls

        Renderable wallImage = imageReader.readImage("assets/wall.png", true);
        GameObject wall = new Wall(Vector2.ZERO, new Vector2(WALL_SIZE + 10, windowDimensions.y()), wallImage);
        gameObjects().addGameObject(wall, Layer.STATIC_OBJECTS);
        GameObject wall2 = new Wall(new Vector2(windowDimensions.x() - WALL_SIZE - 10, 0), new Vector2(windowDimensions.x() - WALL_SIZE, windowDimensions.y()), wallImage);
        gameObjects().addGameObject(wall2, Layer.STATIC_OBJECTS);


        // gravityArrow
        Renderable gravityImage = imageReader.readImage("assets/gravity.png", true);

        //create brick

        //setting brick size
        brickWidth = ((windowDimensions.x() - (BRICKS_PER_ROW * SPACE_SIZE_BETWEEN_BRICKS - SPACE_SIZE_BETWEEN_BRICKS))) / BRICKS_PER_ROW
        - SPACE_SIZE_BETWEEN_FRAME_TO_BRICKS * 2 / BRICKS_PER_ROW;


        Renderable brickImage = imageReader.readImage("assets/brick.png", false);

        // widen and narrow
        Renderable widen = imageReader.readImage("assets/buffWiden.png", true);
        Renderable narrow = imageReader.readImage("assets/buffNarrow.png", true);

        // red brick
        //Renderable redBrick = imageReader.readImage("redBrick.png", false);

        // slowMotion and quickenMotion
        Renderable slowMotionImage = imageReader.readImage("assets/slow.png", true);
        Renderable quickenMotionImage = imageReader.readImage("assets/quicken.png", true);

        // mockBall
        Renderable mockBallImage = imageReader.readImage("assets/mockBall.png", false);
        Sound collisionSound = soundReader.readSound("assets/blop.wav");

        // bots
        Renderable goodBotImage = imageReader.readImage("assets/botGood.png", false);
        Renderable badBotImage = imageReader.readImage("assets/botBad.png", false);

        // brick factory
        float bricksTotalHeight = BRICK_HEIGHT * NUM_OF_ROWS_OF_BRICKS;
        BrickStrategyFactory brickStrategyFactory = new BrickStrategyFactory(gameObjects(),
                bricksLeft,
                mockBallImage,
                windowController,
                inputListener,
                collisionSound,
                paddleImage,
                bricksTotalHeight,
                (int) BALL_SPEED,
                gravityImage,
                collisions,
                direction,
                slowMotionImage,
                quickenMotionImage,
                slowOrQuicken,
                widen,
                narrow,
                paddleSizeBuffChange,
                this,
                randomStrategy,
                ballX,
                ballY,
                activateCamera,
                activateGravity,
                ballCollisionsForCamera,
                ball,
                goodBotImage,
                badBotImage,
                minStrategies,
                maxStrategies,
                userPaddle,
                normalMode);

        CollisionStrategy collisionStrategy;
        float positionX;
        for (int i = 0; i < NUM_OF_ROWS_OF_BRICKS; i += 1)
        {
            collisionStrategy = brickStrategyFactory.getStrategy();
            bricksLeft.increaseBy(1);
            positionX = brickWidth / 2 + SPACE_SIZE_BETWEEN_FRAME_TO_BRICKS;
            createBrick(brickImage,
                    windowController,
                    collisionStrategy,
                    positionX,
                    BRICK_HEIGHT * (i + 1));

            for (int j = 0; j < BRICKS_PER_ROW; j += 1)
            {
                collisionStrategy = brickStrategyFactory.getStrategy();

                bricksLeft.increaseBy(1);
                createBrick(brickImage,
                        windowController,
                        collisionStrategy,
                        positionX,
                        BRICK_HEIGHT * (i + 1));

                positionX += brickWidth + SPACE_SIZE_BETWEEN_BRICKS;

            }


        }

        // author credit
        Renderable authorImage = imageReader.readImage("assets/author.png", false);
        GameObject author = new Author(Vector2.ZERO, new Vector2(windowDimensions.x() / 8, windowDimensions.y() / 12), authorImage);
        author.setCenter(new Vector2(windowDimensions.x() - windowDimensions.x() / 10, windowDimensions.y() - 50));
        gameObjects().addGameObject(author, -198);


        //windowController.setTimeScale(0.5f);
    }

    public static void main(String[] args)
    {
        Math.pow(1, 2);
        new BouncingBallGameManager(
                "Bricker",
                new Vector2(1535, 800)).run();
    }
}
