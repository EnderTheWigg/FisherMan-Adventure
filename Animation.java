import mayflower.*;
/**
 * Write a description of class Animation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Animation
{
    private MayflowerImage[] frames;
    private int frameRate;
    private int currentFrame;

    /**
     * Constructor for objects of class Animation
     */
    public Animation(int rate, String[] fileName)
    {
        frameRate = rate;
        frames = new MayflowerImage[fileName.length];
       
       for (int i = 0; i < fileName.length; i++){
           frames[i]= new MayflowerImage(fileName[i]);
        }
    }
    
    public int getFrameRate(){
        return frameRate;
    }
    
    public MayflowerImage getNextFrame(){
       MayflowerImage currentImage = frames[currentFrame];
       currentFrame++;
       currentFrame %= frames.length;
       return currentImage;

    }
    
    public void scale(int w, int h){
        for (int i=0;i<frames.length;i++){
            frames[i].scale(w,h);
        }
    }
}
