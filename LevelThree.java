
/**
 * Write a description of class LevelThree here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LevelThree extends MyWorld
{
    int worms = 4;
    /**
     * Constructor for objects of class LevelThree
     */
    public LevelThree()
    {

    }
     public String[][] buildWorld(String[][] tileArr){
        //filling it with empty strings
        for (int r = 0; r < tileArr.length; r++){
            for (int c = 0; c < tileArr[r].length; c++){
                    tileArr[r][c] = " ";
            }
        }
        //Placing objects in string
        for(int c = 0; c < tileArr[5].length; c++){
                tileArr[5][c] = "pier";
        }
        for(int c = 1; c < 8; c+=2){
                tileArr[5][c] = "waterOne";
        }
        tileArr[5][7] = "pier";
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
