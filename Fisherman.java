import mayflower.*;
/**
 *movable player character
 */
public class Fisherman extends DynamicActor
{
    //tracking directions for animations
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
    //tracking statistics
    private int worms;
    private int lives;
    /**
     * Constructor for objects of class Fisherman
     */
    public Fisherman()
    {
        //creates string array of rightward walking animations
        String[] walkRightFiles = new String[8];
        for (int i = 0; i<walkRightFiles.length; i++){
            walkRightFiles[i] = new String ("img/Fisherman/WalkRight"+(i + 1)+".png");    
        }         
        //creates object
        walkRight = new Animation(50, walkRightFiles);
        walkRight.scale(80, 80);
        setWalkRightAnimation(walkRight);
        
        //creates string array of leftward walking animations
        String[] walkLeftFiles = new String[8];
        for (int i = 0; i<walkLeftFiles.length; i++){
            walkLeftFiles[i] = new String ("img/Fisherman/WalkLeft"+(i + 1)+".png");    
        }         
        //creates object
        walkLeft = new Animation(50, walkLeftFiles);
        walkLeft.scale(80, 80);
        setWalkLeftAnimation(walkLeft);
        
        //creates string array of rightward idling animations
        String[] idleRightFiles = new String[8];
        for (int i = 0; i<idleRightFiles.length; i++){
            idleRightFiles[i] = new String ("img/Fisherman/IdleRight"+(i + 1)+".png");    
        }         
        //creates object
        idleRight = new Animation(50, idleRightFiles);
        idleRight.scale(80, 80);
        setIdleRightAnimation(idleRight);
        
        
        //creates string array of leftward idling animations
        String[] idleLeftFiles = new String[8];
        for (int i = 0; i<idleLeftFiles.length; i++){
            idleLeftFiles[i] = new String ("img/Fisherman/IdleLeft"+(i + 1)+".png");    
        }         
        //creates object
        idleLeft = new Animation(50, idleLeftFiles);
        idleLeft.scale(80, 80);
        setIdleLeftAnimation(idleLeft);
        
        //creates string array of leftward jumping animations
        String[] jumpLeftFiles = new String[8];
        for (int i = 0; i<jumpLeftFiles.length; i++){
            jumpLeftFiles[i] = new String ("img/Fisherman/JumpLeft"+(i + 1)+".png");    
        }         
        //creates object
        jumpLeft = new Animation(50, jumpLeftFiles);
        jumpLeft.scale(80, 80);
        setJumpLeftAnimation(jumpLeft);
        
        //creates string array of righward jumping animations
        String[] jumpRightFiles = new String[8];
        for (int i = 0; i<jumpRightFiles.length; i++){
            jumpRightFiles[i] = new String ("img/Fisherman/JumpRight"+(i + 1)+".png");    
        }         
        //creates object
        jumpRight = new Animation(50, jumpRightFiles);
        jumpRight.scale(80, 80);
        setJumpRightAnimation(jumpRight);
        
        //creates string array of leftward jumping animations
        String[] fallLeftFiles = new String[5];
        for (int i = 0; i<fallLeftFiles.length; i++){
            fallLeftFiles[i] = new String ("img/Fisherman/JumpLeft"+(i + 9)+".png");    
        }         
        //creates object
        fallLeft = new Animation(50, fallLeftFiles);
        fallLeft.scale(80, 80);
        setFallLeftAnimation(fallLeft);
        
        //creates string array of righward jumping animations
        String[] fallRightFiles = new String[5];
        for (int i = 0; i<fallRightFiles.length; i++){
            fallRightFiles[i] = new String ("img/Fisherman/JumpRight"+(i + 9)+".png");    
        }         
        //creates object
        fallRight = new Animation(50, fallRightFiles);
        fallRight.scale(80, 80);
        setFallRightAnimation(fallRight);
        
        //creates string array of climbing down animations
        String[] climbUpFiles = new String[11];
        for (int i = 0; i<climbUpFiles.length; i++){
            climbUpFiles[i] = new String ("img/Fisherman/climbRight"+(i + 1)+".png");    
        }         
        //creates object
        climbUp = new Animation(50, climbUpFiles);
        climbUp.scale(80, 80);
        setClimbUpAnimation(climbUp);
        
        //creates string array of climbing down animations
        String[] climbDownFiles = new String[11];
        for (int i = 0; i<climbDownFiles.length; i++){
            climbDownFiles[i] = new String ("img/Fisherman/climbRight"+(11 - i)+".png");    
        }         
        //creates object
        climbDown = new Animation(50, climbDownFiles);
        climbDown.scale(80, 80);
        setClimbDownAnimation(climbDown);
         
        //tracking stats
        worms = 0;
        lives = 3;
    }
    public void act()
    {
      takeDamage();
      collectWorm();
      super.act();
    }
    public void resetLocation()
    {
        //resets fisherman location upon entering level or getting hurt
        setLocation(0, 425);
    }
    public void takeDamage()
    {
        //conditional to track whether the player-character and hazard are in contact
        if(this.isTouching(Bird.class))
        {
          //Decrements lives and worms, and removes bird if fisherman touches bird
          World w = new MyWorld();
          lives--;
          worms--;
          Bird a = getOneIntersectingObject(Bird.class);
          a.removeBird();
          resetLocation();
        }
        if(this.isTouching(WaterOne.class))
        {
          //Decrements lives and worms, and removes bird if fisherman touches water
          World w = new MyWorld();
          lives--;
          worms--;
          resetLocation();
        }
    }
    public void collectWorm()
    {
       if(this.isTouching(Worm.class))
       {
           //Removes worm and increments worms if fisherman touches worm
           Worm a = getOneIntersectingObject(Worm.class);
           worms++;
           a.removeWorm();
        }
    }
    public int getWorms()
    {
        //getter method
        return worms;
    }
    public int getLives()
    {
        return lives;
    }
}