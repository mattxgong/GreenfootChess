import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Queen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Queen extends Piece
{
    public Queen(int color){
        super(color);
        if(color == 0) setImage("queen_white.png");
        else setImage("queen_black.png");
    }
    
    public ArrayList<Position> getLegalPositions(){
        ArrayList<Position> list = new ArrayList<Position>();
        ChessWorld c = (ChessWorld)getWorld();
        boolean[] continuing = {true, true, true, true, true, true, true, true};
        int[] xCoords = {getX(), getX(), getX(), getX(), getX(), getX(), getX(), getX()};
        int[] yCoords = {getY(), getY(), getY(), getY(), getY(), getY(), getY(), getY()};
        
        for(int i = 1; i < 8; i++){
            List<Piece> one = c.getObjectsAt(xCoords[0]+1, getY(), Piece.class), two = c.getObjectsAt(xCoords[1]-1, getY(), Piece.class);
            List<Piece> three = c.getObjectsAt(getX(), yCoords[2]-1, Piece.class), four = c.getObjectsAt(getX(), yCoords[3]+1, Piece.class);
            List<Piece> five = c.getObjectsAt(xCoords[4]+1, yCoords[4]+1, Piece.class), six = c.getObjectsAt(xCoords[5]+1, yCoords[5]-1, Piece.class);
            List<Piece> seven = c.getObjectsAt(xCoords[6]-1, yCoords[6]-1, Piece.class), eight = c.getObjectsAt(xCoords[7]-1, yCoords[7]+1, Piece.class);
            
            if(xCoords[0]+1 < 8 && continuing[0]){
                if(one.size() == 0) list.add(new Position(xCoords[0]+1, getY()));
                else if(one.get(0).getColor() != this.getColor()){
                    list.add(new Position(xCoords[0]+1, getY()));
                    continuing[0] = false;
                }
                else continuing[0] = false;
                xCoords[0]++;
            }
            if(xCoords[1]-1 >= 0 && continuing[1]){
                if(two.size() == 0) list.add(new Position(xCoords[1]-1, getY()));
                else if(two.get(0).getColor() != this.getColor()){
                    list.add(new Position(xCoords[1]-1, getY()));
                    continuing[1] = false;
                }
                else continuing[1] = false;
                xCoords[1]--;
            }
            if(yCoords[2]-1 >= 0 && continuing[2]){
                if(three.size() == 0) list.add(new Position(getX(), yCoords[2]-1));
                else if(three.get(0).getColor() != this.getColor()){
                    list.add(new Position(getX(), yCoords[2]-1));
                    continuing[2] = false;
                }
                else continuing[2] = false;
                yCoords[2]--;
            }
            if(yCoords[3]+1 < 8 && continuing[3]){
                if(four.size() == 0) list.add(new Position(getX(), yCoords[3]+1));
                else if(four.get(0).getColor() != this.getColor()){
                    list.add(new Position(getX(), yCoords[3]+1));
                    continuing[3] = false;
                }
                else continuing[3] = false;
                yCoords[3]++;
            }
            if(xCoords[4]+1 < 8 && yCoords[4]+1 < 8 && continuing[4]){
                if(five.size() == 0) list.add(new Position(xCoords[4]+1, yCoords[4]+1));
                else if(five.get(0).getColor() != this.getColor()){
                    list.add(new Position(xCoords[4]+1, yCoords[4]+1));
                    continuing[4] = false;
                }
                else continuing[4] = false;
                xCoords[4]++;
                yCoords[4]++;
            }
            if(xCoords[5]+1 < 8 && yCoords[5]-1 >= 0 && continuing[5]){
                if(six.size() == 0) list.add(new Position(xCoords[5]+1, yCoords[5]-1));
                else if(six.get(0).getColor() != this.getColor()){
                    list.add(new Position(xCoords[5]+1, yCoords[5]-1));
                    continuing[5] = false;
                }
                else continuing[5] = false;
                xCoords[5]++;
                yCoords[5]--;
            }
            if(xCoords[6]-1 >= 0 && yCoords[6]-1 >= 0 && continuing[6]){
                if(seven.size() == 0) list.add(new Position(xCoords[6]-1, yCoords[6]-1));
                else if(seven.get(0).getColor() != this.getColor()){
                    list.add(new Position(xCoords[6]-1, yCoords[6]-1));
                    continuing[6] = false;
                }
                else continuing[6] = false;
                xCoords[6]--;
                yCoords[6]--;
            }
            if(xCoords[7]-1 >= 0 && yCoords[7]+1 < 8 && continuing[7]){
                if(eight.size() == 0) list.add(new Position(xCoords[7]-1, yCoords[7]+1));
                else if(eight.get(0).getColor() != this.getColor()){
                    list.add(new Position(xCoords[7]-1, yCoords[7]+1));
                    continuing[7] = false;
                }
                else continuing[7] = false;
                xCoords[7]--;
                yCoords[7]++;
            }
        }
        
        return list;
    }
}
