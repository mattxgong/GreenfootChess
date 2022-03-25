/**
 * Write a description of class Position here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Position  
{
    // instance variables - replace the example below with your own
    private int x, y;

    /**
     * Constructor for objects of class Position
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Position(greenfoot.Actor a){
        this.x = a.getX();
        this.y = a.getY();
    }

    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public boolean equals(Position other){
        return other.getX() == x && other.getY() == y;
    }
}
