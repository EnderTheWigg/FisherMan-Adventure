import mayflower.*;
/**
 * Write a description of class World here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Fisherman fisherman;
    private Bird seagull;
    
    private String startScreen;
    private String world1;
    private String world2;
    private String world3;
    private String endScreenLose;
    private String endScreenWin;
    
    private int worldCounter;
    
    Actor[] groundTiles;
    String[][] tiles;
    
    private Timer birdDelay;
    
    Actor[] lifeArr;
    Actor[] wormArr;
    
    boolean isLastLevel;
    /**
     * Constructor for objects of class World
     */
    public MyWorld()
    {
        //Initializing variables
        Bird bird = new Bird();
        fisherman = new Fisherman();
        
        groundTiles = new Actor[20];
        tiles = new String[6][8];
        lifeArr = new Actor[3];
        wormArr = new Actor[15];
        
        birdDelay = new Timer(1000000000);
        
        isLastLevel = false;
        worldCounter = 1;
        
        
        startScreen = "img/Screens/StartScreen.png";
        world1 = "img/Screens/World1BG.png";
        world2 = "img/Screens/World2BG.png";
        world3 = "img/Screens/World3BG.png";
        endScreenLose = "img/Screens/EndScreenLose.png";
        endScreenWin = "img/Screens/EndScreenWin.png";
        //Calling methods
        setBackground(startScreen);
        
        addObject(bird, 500, 50);
        
        showText("Lives: " + fisherman.getLives(), 30, 50);
        showText("Worms: " + fisherman.getWorms(), 30, 90);
    }
    public void act()
    {
        //Changes heart/worm score
        updateScore();
        //Displays win or lose screen
        winOrLose();
        //Spawns birds
       unleashTheBirds();
        //switch to world 1
        if ((Mayflower.isKeyPressed(Keyboard.KEY_SPACE) && worldCounter == 1)){
           switchWorld(world1);
           worldCounter++;
       }
       //switch to world 2
       if ((touchingEnd()) && worldCounter == 2){
           switchWorld(world2);
           worldCounter++;
       }
       //switch to world 3
       if ((fisherman.getY() <= 0) && worldCounter == 3 && isLastLevel == false){
           switchWorld(world3);
           isLastLevel = true;
       }
    }
    public void changeLevel(){
        //uses integer variable worldCounter to keep track of the world layout as it switches between them          
        if (worldCounter == 1){
            LevelOne one = new LevelOne();
            tiles = one.buildWorld(tiles);
            fillWorld();
        }
        
        if (worldCounter == 2){
            clearWorld(tiles);
            LevelTwo two = new LevelTwo();
            tiles = two.buildWorld(tiles);
            fillWorld();
            fisherman.resetLocation();
        }
        
         if (worldCounter == 3){ 
            clearWorld(tiles);
            LevelThree three = new LevelThree();
            tiles = three.buildWorld(tiles);
            fillWorld();
            fisherman.resetLocation();
        }
    }
    
    public void switchWorld(String screen)
    {
        setBackground(screen); // switches backdrop
        changeLevel(); //sets up world format
        
        //accounting for fisherman player-character location
        if(worldCounter == 1)
        {
            addObject(fisherman, 0, 425);
        }
        if(worldCounter != 1) {
            addObject(fisherman, 0, 425);
        }
    }
    public void clearWorld(String[][] array)
    {
        //fills array with empty strings
        for (int r = 0; r < array.length; r++){
                for (int c = 0; c < array[r].length; c++){
                    array[r][c] = " ";
                }
            }
        //clears screen of objects
        for (int i = 0; i < groundTiles.length; i++){
                    removeObject(groundTiles[i]);
                    groundTiles[i] = null;
                }    
     }
    public void fillWorld()
    {
        //This method turns the String array into actual objects
        int i = 0;
        for (int r = 0; r < tiles.length; r++){ //cycle thru row
                for (int c = 0; c < tiles[r].length; c++){ //cycle thru column
                    if (tiles[r][c].equals("ground")){
                        //Putting this in groundtiles allows for removing it later
                        groundTiles[i] = new Grass();
                        addObject(groundTiles[i], c*100, r*100);
                        i++;
                    }
                }
            }
        for (int r = 0; r < tiles.length; r++){ //cycle thru row
                for (int c = 0; c < tiles[r].length; c++){ //cycle thru column
                    if (tiles[r][c].equals("waterOne")){
                        groundTiles[i] = new WaterOne();
                        addObject(groundTiles[i], c*100, r*105);
                        i++;
                    }
                }
            }
        for (int r = 0; r < tiles.length; r++){ //cycle thru row
                for (int c = 0; c < tiles[r].length; c++){ //cycle thru column
                    if (tiles[r][c].equals("pier")){
                        groundTiles[i] = new Pier();
                        addObject(groundTiles[i], c*100, r*100);
                        i++;
                    }
                }
            }    
        for (int r = 0; r < tiles.length; r++){ //cycle thru row
                for (int c = 0; c < tiles[r].length; c++){ //cycle thru column
                    if (tiles[r][c].equals("ladder")){
                        groundTiles[i] = new Ladder();
                        addObject(groundTiles[i], c*100, r*100);
                        i++;
                }
            }
        }
        for (int r = 0; r < tiles.length; r++){ //cycle thru row
                for (int c = 0; c < tiles[r].length; c++){ //cycle thru column
                    if (tiles[r][c].equals("worm")){
                        groundTiles[i] = new Worm();
                        addObject(groundTiles[i], c*100, r*105);
                        i++;
                }
            }
        }
    }
    public void unleashTheBirds()
    {
        //Makes birds as long as the fisherman has health and birdDelay is finished
        int num = (int)(Math.random() * 100);
        
        if(num < 3 && birdDelay.isDone() && fisherman.getLives() >= 1)
        {
            addObject(new Bird(), 800, (int)(Math.random() * 90 * 4));
            birdDelay.reset();
        }
    }
    public void updateScore()
    {
        //declare and initialize local variables
        int lives = fisherman.getLives();
        int worms = fisherman.getWorms();
        //Show text
        showText("Lives: ", 30, 50);
        showText("Worms: ", 30, 90);
        //Remove and recreate worms/hearts, this will change the number of worms/hearts if some are lost
        for(int i = 0; i < lifeArr.length; i++)
        {
            removeObject(lifeArr[i]);
            lifeArr[i] = null;
        }
        for(int i = 0; i < wormArr.length; i++)
        {
            removeObject(wormArr[i]);
            wormArr[i] = null;
        }
        for(int i = 0; i < lives; i++)
        {
            removeObject(lifeArr[i]);
            lifeArr[i] = null;
            lifeArr[i] = new Heart();
            addObject(lifeArr[i], 120 + (i*20), 30);
        }
        for(int i = 0; i < worms; i++)
        {
            removeObject(wormArr[i]);
            wormArr[i] = null;
            wormArr[i] = new SmallWorm();
            addObject(wormArr[i], 145  + (i*20), 65);
        }
    }
    public void winOrLose()
    {
        //If fisherman wins/loses, set win/lose screen and clear world
        if(fisherman.getLives() <= 0){
            setBackground(endScreenLose);
            removeObject(fisherman);
            clearWorld(tiles);
        }
        if((touchingEnd()) && (isLastLevel)){
            setBackground(endScreenWin);
            removeObject(fisherman);
            clearWorld(tiles);
        }
    }
    public boolean touchingEnd()
    {
        //returns whether the fisherman is touching the end of the world
        if((fisherman.getX() + fisherman.getWidth()) >= 800)
            return true;
        return false;
    }
}