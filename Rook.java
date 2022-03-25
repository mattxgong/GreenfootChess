import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Rook here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rook extends Piece
{
    private boolean hasMoved;
    
    public Rook(int color){
        super(color);
        if(color == 0) setImage("rook_white.png");
        else setImage("rook_black.png");
        hasMoved = false;
    }
    
    public boolean ifMoved(){
        return hasMoved;
    }
    
    public ArrayList<Position> getLegalPositions(){
        ArrayList<Position> list = new ArrayList<Position>();
        boolean up = true, down = true, left = true, right = true;
        ChessWorld c = ((ChessWorld)getWorld());
        int[] coords = {getX(), getX(), getY(), getY()};
        
        for(int i = 1; i < 8; i++){
            List<Piece> one = c.getObjectsAt(coords[0]-1, getY(), Piece.class), two = c.getObjectsAt(coords[1]+1, getY(), Piece.class);
            List<Piece> three = c.getObjectsAt(getX(), coords[2]-1, Piece.class), four = c.getObjectsAt(getX(), coords[3]+1, Piece.class);
            
            if(coords[0]-1 >= 0 && up){
                if(one.size() == 0) list.add(new Position(coords[0]-1, getY()));
                else if(one.get(0).getColor() != this.getColor()){
                    list.add(new Position(coords[0]-1, getY()));
                    up = false;
                }
                else up = false;
                coords[0]--;
            }
            if(coords[1]+1 < 8 && down){
                if(two.size() == 0) list.add(new Position(coords[1]+1, getY()));
                else if(two.get(0).getColor() != this.getColor()){
                    list.add(new Position(coords[1]+1, getY()));
                    down = false;
                }
                else down = false;
                coords[1]++;
            }
            if(coords[2]-1 >= 0 && left){
                if(three.size() == 0) list.add(new Position(getX(), coords[2]-1));
                else if(three.get(0).getColor() != this.getColor()){
                    list.add(new Position(getX(), coords[2]-1));
                    left = false;
                }
                else left = false;
                coords[2]--;
            }
            if(coords[3]+1 < 8 && right){
                if(four.size() == 0) list.add(new Position(getX(), coords[3]+1));
                else if(four.get(0).getColor() != this.getColor()){
                    list.add(new Position(getX(), coords[3]+1));
                    right = false;
                }
                else right = false;
                coords[3]++;
            }
        }
        
        return list;
    }
    
    public boolean movePiece(Preview chosen){
        if(super.movePiece(chosen)){
            hasMoved = false;
            return true;
        }
        return false;
    }
}
