import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Bishop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bishop extends Piece
{
    public Bishop(int color){
        super(color);
        if(color == 0) setImage("bishop_white.png");
        else setImage("bishop_black.png");
    }
    
    public ArrayList<Position> getLegalPositions(){
        ArrayList<Position> list = new ArrayList<Position>();
        boolean upperLeft = true, upperRight = true, lowerLeft = true, lowerRight = true;
        ChessWorld c = ((ChessWorld)getWorld());
        int[] xCoords = {getX(), getX(), getX(), getX()}, yCoords = {getY(), getY(), getY(), getY()};
        
        for(int i = 1; i < 8; i++){
            List<Piece> one = c.getObjectsAt(xCoords[0]+1, yCoords[0]+1, Piece.class), two = c.getObjectsAt(xCoords[1]+1, yCoords[1]-1, Piece.class);
            List<Piece> three = c.getObjectsAt(xCoords[2]-1, yCoords[2]+1, Piece.class), four = c.getObjectsAt(xCoords[3]-1, yCoords[3]-1, Piece.class);
            
            if(xCoords[0]+1 < 8 && yCoords[0]+1 < 8 && lowerRight){
                if(one.size() == 0) list.add(new Position(xCoords[0]+1, yCoords[0]+1));
                else if(one.get(0).getColor() != this.getColor()){
                    list.add(new Position(xCoords[0]+1, yCoords[0]+1));
                    lowerRight = false;
                }
                else lowerRight = false;
                xCoords[0]++;
                yCoords[0]++;
            }
            if(xCoords[1]+1 < 8 && yCoords[1]-1 >= 0 && upperRight){
                if(two.size() == 0) list.add(new Position(xCoords[1]+1, yCoords[1]-1));
                else if(two.get(0).getColor() != this.getColor()){
                    list.add(new Position(xCoords[1]+1, yCoords[1]-1));
                    upperRight = false;
                }
                else upperRight = false;
                xCoords[1]++;
                yCoords[1]--;
            }
            if(xCoords[2]-1 >= 0 && yCoords[2]+1 < 8 && lowerLeft){
                if(three.size() == 0) list.add(new Position(xCoords[2]-1, yCoords[2]+1));
                else if(three.get(0).getColor() != this.getColor()){
                    list.add(new Position(xCoords[2]-1, yCoords[2]+1));
                    lowerLeft = false;
                }
                else lowerLeft = false;
                xCoords[2]--;
                yCoords[2]++;
            }
            if(xCoords[3]-1 >= 0 && yCoords[3]-1 >= 0 && upperLeft){
                if(four.size() == 0) list.add(new Position(xCoords[3]-1, yCoords[3]-1));
                else if(four.get(0).getColor() != this.getColor()){
                    list.add(new Position(xCoords[3]-1, yCoords[3]-1));
                    upperLeft = false;
                }
                else upperLeft = false;
                xCoords[3]--;
                yCoords[3]--;
            }
        }
        
        return list;
    }
}
