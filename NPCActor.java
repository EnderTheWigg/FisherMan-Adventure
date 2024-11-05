import mayflower.*;
/**
 Creates movable sprite that cannot be affected by the player
 Autonomous actor
 */
public class NPCActor extends Actor
{
    private Timer animationTimer;
    private Animation animation;
    
    /**
     * Constructor for objects of class NPCActor
     */
    public NPCActor()
    {
        //Timer for bird's animation
        animationTimer = new Timer(500000);
    }
    public void setAnimation(Animation a)
    {
        animation = a;
        
    }
    public void act()
    {
        //Bird only moves and has one animation
        move();
                if (animation != null){
            if (animationTimer.isDone()){
                animationTimer.reset();
                MayflowerImage image = animation.getNextFrame();
                setImage(image);
                
            }
        }
    }
     public void  move()
    {
    }
}
