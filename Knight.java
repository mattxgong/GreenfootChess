import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Piece
{
    public Knight(int color){
        super(color);
        if(color == 0) setImage("knight_white.png");
        else setImage("knight_black.png");
    }
    
    public ArrayList<Position> getLegalPositions(){
        ArrayList<Position> list = new ArrayList<Position>();
        ChessWorld c = (ChessWorld)getWorld();
        List<Piece> one = c.getObjectsAt(getX()+1, getY()+2, Piece.class), two = c.getObjectsAt(getX()-1, getY()+2, Piece.class);
        List<Piece> three = c.getObjectsAt(getX()+2, getY()-1, Piece.class), four = c.getObjectsAt(getX()+2, getY()+1, Piece.class);
        List<Piece> five = c.getObjectsAt(getX()+1, getY()-2, Piece.class), six = c.getObjectsAt(getX()-1, getY()-2, Piece.class);
        List<Piece> seven = c.getObjectsAt(getX()-2, getY()-1, Piece.class), eight = c.getObjectsAt(getX()-2, getY()+1, Piece.class);
            
        if(getX()+1 < 8 && getY()+2 < 8){
            if(one.size() == 0 || one.get(0).getColor() != this.getColor()) list.add(new Position(getX()+1, getY()+2));
        }
        if(getX()-1 >= 0 && getY()+2 < 8){
            if(two.size() == 0 || two.get(0).getColor() != this.getColor()) list.add(new Position(getX()-1, getY()+2));
        }
        if(getX()+2 < 8 && getY()-1 >= 0){
            if(three.size() == 0 || three.get(0).getColor() != this.getColor()) list.add(new Position(getX()+2, getY()-1));
        }
        if(getX()+2 < 8 && getY()+1 < 8){
            if(four.size() == 0 || four.get(0).getColor() != this.getColor()) list.add(new Position(getX()+2, getY()+1));
        }            
        if(getX()+1 < 8 && getY()-2 >= 0){
            if(five.size() == 0 || five.get(0).getColor() != this.getColor()) list.add(new Position(getX()+1, getY()-2));
        }
        if(getX()-1 >= 0 && getY()-2 >= 0){
            if(six.size() == 0 || six.get(0).getColor() != this.getColor()) list.add(new Position(getX()-1, getY()-2));
        }
        if(getX()-2 >= 0 && getY()-1 >= 0){
            if(seven.size() == 0 || seven.get(0).getColor() != this.getColor()) list.add(new Position(getX()-2, getY()-1));
        }
        if(getX()-2 >= 0 && getY()+1 < 8){
            if(eight.size() == 0 || eight.get(0).getColor() != this.getColor()) list.add(new Position(getX()-2, getY()+1));
        }
        
        return list;
    }
}
