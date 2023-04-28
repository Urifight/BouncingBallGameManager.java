//package bricker.gameobjects;
//
//import danogl.GameObject;
//import danogl.collisions.Collision;
//import danogl.collisions.GameObjectCollection;
//import danogl.collisions.Layer;
//import danogl.gui.rendering.Renderable;
//import danogl.util.Vector2;
//
//public class redBrick extends GameObject
//{
//    private int collisions = 0;
//    private GameObjectCollection gameObjectCollection;
//
//    /**
//     * Construct a new GameObject instance.
//     *
//     * @param topLeftCorner Position of the object, in window coordinates (pixels).
//     *                      Note that (0,0) is the top-left corner of the window.
//     * @param dimensions    Width and height in window coordinates.
//     * @param renderable    The renderable representing the object. Can be null, in which case
//     *                      the GameObject will not be rendered.
//     */
//    public redBrick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, GameObjectCollection gameObjectCollection) {
//        super(topLeftCorner, dimensions, renderable);
//        this.gameObjectCollection = gameObjectCollection;
//    }
//
//    @Override
//    public void onCollisionEnter(GameObject other, Collision collision) {
//        super.onCollisionEnter(other, collision);
//        collisions++;
//        if (collisions == 3)
//        {
//            gameObjectCollection.removeGameObject(this, Layer.STATIC_OBJECTS);
//        }
//    }
//}
