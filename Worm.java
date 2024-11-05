import mayflower.*;
public class Worm extends Actor
{
    /**
     * Constructor for objects of class Block
     */
    public Worm()
    {
        setImage("img/Misc/Worm.png");
    
    }
    
    public void act()
    {
        
    }
    public void removeWorm()
    {
        //For use in Fisherman
        getWorld().removeObject(this);
    }
}