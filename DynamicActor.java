import mayflower.*;
/**
 * Write a description of class DynamicActor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DynamicActor extends Actor
{
    private Animation animation;
    private Timer animationTimer;
    
    private Timer jump;
    boolean hasTimerStarted;
    
    private Animation idleLeft;
    private Animation idleRight;
    private Animation walkLeft;
    private Animation walkRight;
    private Animation jumpLeft;
    private Animation jumpRight;
    private Animation fallLeft;
    private Animation fallRight;
    private Animation climbUp;
    private Animation climbDown;
    
    private String currentAction;
    private String direction;
    
    private boolean isClimbing;
    /**
     * Constructor for objects of class DynamicActor
     */
    public DynamicActor()
    {
        isClimbing = false;
        animationTimer = new Timer(50000000);
        direction = "Right";
        hasTimerStarted = false;
        jump = new Timer(1000000000);
        currentAction = null;
    }
    public void setAnimation(Animation a)
    {
        animation = a;
    }
    public void act()
    {
        String newAction = null;
        //Default animation is idle
        if(currentAction == null)
        {
            if(direction == "Right")
            {
                newAction = "idleRight";
                currentAction = newAction;
            }
            if(direction == "Left")
            {
                newAction = "idleLeft";
                currentAction = newAction;
            }
        }
        takeDamage();
        newAction = move();
        //Sets animation
        if (animation != null){
            if (animationTimer.isDone()){
                animationTimer.reset();
                MayflowerImage image = animation.getNextFrame();
                setImage(image);
                
            }
        }
        //Sets animations based on value recieved from move
        if(!(newAction == null) && !newAction.equals(currentAction))
        {
            if(newAction.equals("walkLeft"))
            {
                setWalkLeftAnimation(walkLeft);
                currentAction = newAction;
            }
            if(newAction.equals("walkRight"))
            {
                setWalkRightAnimation(walkRight);
                currentAction = newAction;
            }
            if(newAction.equals("idleLeft"))
            {
                setIdleLeftAnimation(idleLeft);
                currentAction = newAction;
            }
            if(newAction.equals("idleRight"))
            {
                setIdleRightAnimation(idleRight);
                currentAction = newAction;
            }
            if(newAction.equals("fallLeft"))
            {
                setFallLeftAnimation(fallLeft);
                currentAction = newAction;
            }
            if(newAction.equals("fallRight"))
            {
                setFallRightAnimation(fallRight);
                currentAction = newAction;
            }
            if(newAction.equals("jumpLeft"))
            {
                setJumpLeftAnimation(jumpLeft);
                currentAction = newAction;
            }
            if(newAction.equals("jumpRight"))
            {
                setJumpRightAnimation(jumpRight);
                currentAction = newAction;
            }
            if(newAction.equals("climbUp"))
            {
                setClimbUpAnimation(climbUp);
                currentAction = newAction;
            }
            if(newAction.equals("climbDown"))
            {
                setClimbDownAnimation(climbDown);
                currentAction = newAction;
            }
        }
    }
    public String move()
    {
        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();
        //Moves character, returns animation to be used
        if (Mayflower.isKeyDown(Keyboard.KEY_D) && !(x + w > 800) && (this.isTouching(Grass.class) || (this.isTouching(Pier.class)))) 
        {
            setLocation(x + 3,y);
            direction = "Right";
            return "walkRight";
        }
        else if (Mayflower.isKeyDown(Keyboard.KEY_A) && !(x < 0) && (this.isTouching(Grass.class) || (this.isTouching(Pier.class)))) 
        {
            setLocation(x - 2,y);
            direction = "Left";
            return "walkLeft";
        }
        if((Mayflower.isKeyDown(Keyboard.KEY_W) && (this.isTouching(Ladder.class))))
        {
            setLocation(x, y-2);
            isClimbing = true;
            return "climbUp";
        }
        if((Mayflower.isKeyDown(Keyboard.KEY_S) && (this.isTouching(Ladder.class)) && !(this.isTouching(Grass.class))))
        {
            setLocation(x, y+2);
            isClimbing = true;
            return "climbDown";
        }
        fall(x, y);
        if ((Mayflower.isKeyDown(Keyboard.KEY_SPACE)))
        {
            if(direction == "Right" && ((Mayflower.isKeyDown(Keyboard.KEY_SPACE) == true)) && !(jump.isDone())){ //rightward jump
                setLocation(x+2, y-2);
            }
            if(direction == "Left" &&((Mayflower.isKeyDown(Keyboard.KEY_SPACE) == true)) && !(jump.isDone()) && (getX() > -1)){ //leftward jump
                setLocation(x-2, y-2);
            }
            if (!jump.isDone() && !(this.isTouching(Grass.class))){
                return "jump" + direction;
            }
        }
        if(((jump.isDone() || !(Mayflower.isKeyDown(Keyboard.KEY_SPACE))) && !(this.isTouching(Grass.class) || (this.isTouching(Pier.class)))))
                return "fall" + direction;
        return ("idle" + direction);
    }
    //Changes animation whenever animationTimer ends
    public void animateActor()
    {
        if (animation != null){
            if (animationTimer.isDone()){
                animationTimer.reset();
                MayflowerImage image = animation.getNextFrame();
                setImage(image);
                
            }
        }
    }
    public void fall(int x, int y)
    {
        if(!(isClimbing))
        {
            //If not jumping or touching ground - fall, if jump is timer done and not touching ground - fall
            // Reset jump timer if touching ground
            if(!(Mayflower.isKeyDown(Keyboard.KEY_SPACE)) && !(this.isTouching(Grass.class) || (this.isTouching(Pier.class))))
            {   
                setLocation(x, y + 2);
            }
            if (jump.isDone() && !(this.isTouching(Grass.class) || (this.isTouching(Pier.class)))){
                setLocation(x, y + 2);
            }
            if((this.isTouching(Grass.class) || (this.isTouching(Pier.class))))
            {
                jump.reset();
            }
        }
        isClimbing = false;
    }
    public void takeDamage()
    {
        //Overrided in Fisherman
    }
    //Setter methods for Animations
    public void setWalkRightAnimation(Animation a)
    {
        walkRight = a;
        setAnimation(a);
        
    }
    public void setWalkLeftAnimation(Animation a)
    {
        walkLeft = a;
        setAnimation(a);
    }
    public void setIdleRightAnimation(Animation a)
    {
        idleRight = a;
        setAnimation(a);
    }
    public void setIdleLeftAnimation(Animation a)
    {
        idleLeft = a;
        setAnimation(a);
    }
    public void setJumpLeftAnimation(Animation a)
    {
        jumpLeft = a;
        setAnimation(a);
    }
    public void setJumpRightAnimation(Animation a)
    {
        jumpRight = a;
        setAnimation(a);
    }
    public void setFallLeftAnimation(Animation a)
    {
        fallLeft = a;
        setAnimation(a);
    }
    public void setFallRightAnimation(Animation a)
    {
        fallRight = a;
        setAnimation(a);
    }
    public void setClimbUpAnimation(Animation a)
    {
        climbUp = a;
        setAnimation(a);
    }
    public void setClimbDownAnimation(Animation a)
    {
        climbDown = a;
        setAnimation(a);
    }
}
