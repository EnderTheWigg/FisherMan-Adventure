import mayflower.*;
/**
 *Creates a bird that can fly
 *Has movement 
 *Moving hazard in game
 */
public class Bird extends NPCActor
{
    private Animation fly;
    private Timer animationTimer;
    /** Constructor for objects of class Bird
     */
    public Bird()
    {
        animationTimer = new Timer(1000);
        //populating array that holds bird images
        String[] seagull = new String[9];
        for(int i = 0; i < seagull.length; i++){
            seagull[i] = "img/Bird/Bird"+ (i + 1) + ".png";
        }
        fly = new Animation(50, seagull);
        fly.scale(50, 50);
        setAnimation(fly);
        
    }
    public void move()
    {
        int x = getX();
        int y = getY();
        setLocation(x-3, y);
        outOfBounds();
        
    }
    public void outOfBounds()
    {
        int x = getX();
        if(x <= 0)
            getWorld().removeObject(this);
    }
    public void removeBird()
    {
        //Remove bird for use in Fisherman
        getWorld().removeObject(this);
    }
}
