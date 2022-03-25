import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class preview here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Preview extends Actor
{
    private Piece original;
    
    public Preview(Piece p){
        original = p;
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            if(original instanceof King){
                if(original.getX() == this.getX()+2){
                    ChessWorld c = (ChessWorld)getWorld();
                    List<Rook> toMove = c.getObjectsAt(getX()-2, getY(), Rook.class);
                    toMove.get(0).setLocation(getX()+1, getY());
                }
                if(original.getX() == this.getX()-2){
                    ChessWorld c = (ChessWorld)getWorld();
                    List<Rook> toMove = c.getObjectsAt(getX()+1, getY(), Rook.class);
                    toMove.get(0).setLocation(getX()-1, getY());
                }
            }
            original.movePiece(this);
        }
    }
    
    public Piece getConnectedPiece(){
        return original;
    }
}
