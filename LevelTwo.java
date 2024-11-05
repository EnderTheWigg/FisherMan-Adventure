
/**
 * Write a description of class LevelTwo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LevelTwo extends MyWorld
{
    int worms = 3;
    /**
     * Constructor for objects of class LevelTwo
     */
    public LevelTwo()
    {
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
        for(int c = 1; c < 7; c+=2){
                tileArr[5][c] = "waterOne";
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
        for (int r = 0; r < tileArr.length; r++){
                tileArr[r][7] = "ladder";
            }
        tileArr[5][7] = "ground";
        return tileArr;
    }
}
