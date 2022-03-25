import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class King here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class King extends Piece
{
    private boolean hasMoved;
    
    public King(int color){
        super(color);
        if(color == 0) setImage("king_white.png");
        else setImage("king_black.png");
        hasMoved = false;
    }
    
    public boolean ifMoved(){
        return hasMoved;
    }
    
    public ArrayList<Position> getLegalPositions(){
        ArrayList<Position> list = new ArrayList<Position>();
        ChessWorld c = (ChessWorld)getWorld();
        List<Piece> one = c.getObjectsAt(getX()+1, getY()+1, Piece.class), two = c.getObjectsAt(getX()-1, getY()+1, Piece.class);
        List<Piece> three = c.getObjectsAt(getX()+1, getY()-1, Piece.class), four = c.getObjectsAt(getX()-1, getY(), Piece.class);
        List<Piece> five = c.getObjectsAt(getX()+1, getY(), Piece.class), six = c.getObjectsAt(getX()-1, getY()-1, Piece.class);
        List<Piece> seven = c.getObjectsAt(getX(), getY()-1, Piece.class), eight = c.getObjectsAt(getX(), getY()+1, Piece.class);
        List<Rook> left = c.getObjectsAt(getX()-4, getY(), Rook.class), right = c.getObjectsAt(getX()+3, getY(), Rook.class);
            
        if(getX()+1 < 8 && getY()+1 < 8){
            if(one.size() == 0 || one.get(0).getColor() != this.getColor()) list.add(new Position(getX()+1, getY()+1));
        }
        if(getX()-1 >= 0 && getY()+1 < 8){
            if(two.size() == 0 || two.get(0).getColor() != this.getColor()) list.add(new Position(getX()-1, getY()+1));
        }
        if(getX()+1 < 8 && getY()-1 >= 0){
            if(three.size() == 0 || three.get(0).getColor() != this.getColor()) list.add(new Position(getX()+1, getY()-1));
        }
        if(getX()-1 >= 0){
            if(four.size() == 0 || four.get(0).getColor() != this.getColor()) list.add(new Position(getX()-1, getY()));
        }            
        if(getX()+1 < 8){
            if(five.size() == 0 || five.get(0).getColor() != this.getColor()) list.add(new Position(getX()+1, getY()));
        }
        if(getX()-1 >= 0 && getY()-1 >= 0){
            if(six.size() == 0 || six.get(0).getColor() != this.getColor()) list.add(new Position(getX()-1, getY()-1));
        }
        if(getY()-1 >= 0){
            if(seven.size() == 0 || seven.get(0).getColor() != this.getColor()) list.add(new Position(getX(), getY()-1));
        }
        if(getY()+1 < 8){
            if(eight.size() == 0 || eight.get(0).getColor() != this.getColor()) list.add(new Position(getX(), getY()+1));
        }
        if(getX()-4 == 0 && !hasMoved){
            boolean possible = true;
            
            if(left.size() == 1 && left.get(0).getColor() == this.getColor() && left.get(0).ifMoved() == false){
                for(int i = getX()-1; i > 0; i--){
                    List<Piece> check = c.getObjectsAt(i, getY(), Piece.class);
                    if(check.size() != 0){
                        possible = false;
                        break;
                    }
                }
            }
            if(possible && c.inCheck(this, new Position(getX()-1, getY())) == false) list.add(new Position(getX()-2, getY()));
        }
        if(getX()+3 == 7 && !hasMoved){
            boolean possible = true;
            
            if(right.size() == 1 && right.get(0).getColor() == this.getColor() && right.get(0).ifMoved() == false){
                for(int i = getX()+1; i < 7; i++){
                    List<Piece> check = c.getObjectsAt(i, getY(), Piece.class);
                    if(check.size() != 0){
                        possible = false;
                        break;
                    }
                }
            }
            if(possible && c.inCheck(this, new Position(getX()+1, getY())) == false) list.add(new Position(getX()+2, getY()));
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
