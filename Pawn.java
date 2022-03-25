import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Pawn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pawn extends Piece
{
    private boolean firstMove = true;
    private int direction;
    
    public Pawn(int color){
        super(color);
        if(color == 0){
            setImage("pawn_white.png");
            direction = -1;
        }
        else{
            setImage("pawn_black.png");
            direction = 1;
        }
    }
    
    public ArrayList<Position> getLegalPositions(){
        ArrayList<Position> list = new ArrayList<Position>();
        ChessWorld c = (ChessWorld)getWorld();
        List<Piece> one = c.getObjectsAt(getX(), getY() + direction, Piece.class);
        List<Piece> two = c.getObjectsAt(getX(), getY() + 2*direction, Piece.class);
        if(getY() + direction < 8 && getY() + direction >= 0){
            if(firstMove && one.size() == 0 && two.size() == 0) list.add(new Position(getX(), getY() + 2*direction));
            if(one.size() == 0) list.add(new Position(getX(), getY() + direction));
            
            Piece p1 = (Piece) getOneObjectAtOffset(-1, direction, Piece.class);
            Piece p2 = (Piece) getOneObjectAtOffset(1, direction, Piece.class);
            if (p1 != null && p1.getColor() != this.getColor()) list.add(new Position(p1));
            if (p2 != null && p2.getColor() != this.getColor()) list.add(new Position(p2));        
        }
        return list;
    }
    
    public boolean movePiece(Preview chosen){
        ChessWorld c = (ChessWorld)getWorld();
        
        if(super.movePiece(chosen)){
            firstMove = false;
            if(this.getColor() == 0 && getY() == 0) c.promote(this, 0);
            if(this.getColor() == 1 && getY() == 7) c.promote(this, 1);
            return true;
        }
        return false;
    }
}
