import mayflower.*;
/**
 Creates world 1
 Bottom row is filled with ground tiles
 */
public class LevelOne extends MyWorld
{
    int worms = 3;
    /**
     * Constructor for objects of class LevelOne
     */
    public LevelOne()
    {
        super();
    }
    
    public String[][] buildWorld(String[][] tileArr){
        //filling it with empty strings
        for (int r = 0; r < tileArr.length; r++){
                for (int c = 0; c < tileArr[r].length; c++){
                    tileArr[r][c] = " ";
                }
            }
        //permanent obj
        for (int c = 0; c < tileArr[5].length; c++){
            tileArr[5][c] = "ground";
        }
        while(worms > 0)
        {
            //Random worm placement
            int num = (int)(Math.random() * 8);
        
        if((tileArr[4][num] == " "))
        {
            tileArr[4][num] = "worm";
            worms--;
        }
        }
        return tileArr;
    }
}

