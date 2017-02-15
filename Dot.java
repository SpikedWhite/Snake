import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dot  extends Actor
{
    private int x;
    private int y;
    private int d;
    private final int DOT_SIZE=20;
    
    public Dot(int dot){
        GreenfootImage image1 = new GreenfootImage("SnakeHead.gif");
        image1.mirrorHorizontally();
        d = dot;
            if (d == 0){
            setImage(image1);
        } else {
            setImage("close.png");
        }
    }

    /**
    * Act - do whatever the Head wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
    */
    public void act()
    {
        if( d == 0 ){
            lead();
            lookForEdge();
            lookForFood();
            lookForDot();
        }   
        else
        {
            follow();
        }

    }
    
    /**
     * lead controls the movement of the head of our snake
     * @param there are no parameters
     * @return There is nothing to return
     */
    private void lead(){
        double angle;
        SnakeWorld myWorld = (SnakeWorld)getWorld();
        x = getX();
        y = getY();
        
        if( Greenfoot.isKeyDown("left") ){
            
            setRotation(180);
        
        }
        else if( Greenfoot.isKeyDown("Right") ){
        
            setRotation(0);
        }
        
        else if( Greenfoot.isKeyDown("Up") ){
        
            setRotation(270);
        }
        
        else if( Greenfoot.isKeyDown("Down") ){
        
            setRotation(90);
        }
        
        angle = Math.toRadians( getRotation() );
        x = (int) Math.round( getX() + Math.cos(angle) * DOT_SIZE);
        y = (int) Math.round( getY() + Math.sin(angle) * DOT_SIZE);
        
        setLocation(x, y);
        myWorld.setDX(d, x);
        myWorld.setDY(d, y);
    }
    
    
    
    private void lookForEdge(){
            if( isAtEdge() )
            {
                getWorld().showText("you have lost!", getWorld().getWidth()/2, getWorld().getHeight()/2);
                Greenfoot.stop();
            }
    }
        
    private void lookForFood(){
        SnakeWorld world  = (SnakeWorld)getWorld();  
        
        /**
         *lookForfood checks if our Dot is touching Food then grows its tail then grows tail if true
         *@param ther are no parameters
         *@return nothing is returned
         */
        
        if( isTouching(Food.class))
            {
                    getWorld().removeObject( getOneIntersectingObject(Food.class));
                    growTail();
                    world.addFood();
            }
    }
        
    private void lookForDot(){
            if( isTouching(Dot.class) )
            {
                getWorld().showText("you have lost!", getWorld().getWidth()/2, getWorld().getHeight()/2);
                Greenfoot.stop();
            }
    }
        
    /**
    * follow handles the movement for every body part of the snake
    * @param There are no parameters
    * @return There is nothing to return
    */
    private void follow(){
            SnakeWorld myWorld = (SnakeWorld)getWorld();
            x = myWorld.getMyX(d);
            y = myWorld.getMyY(d);
            setLocation(x, y);
    }
    
    /**
     * growTail will add a dot to tge end of our snake when we eat a food object
     * @param there are no parameters
     * @return nothing is returned
     */
    private void growTail(){
        SnakeWorld world = (SnakeWorld)getWorld();
        world.addDot();
    }
}
    