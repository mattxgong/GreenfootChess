import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Piece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Piece extends Actor
{
    private boolean selected;
    private int color; // 0 for white and 1 for black
    
    public Piece(){
        super();
    }
    
    public Piece(int color){
        this.color = color;
        selected = false;
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            select();
        }
    }  

    public int getColor(){
        return color;
    }
    
    public void select() {
        if (((ChessWorld)getWorld()).getTurn() == color) {
            if(selected){
                unselect();
            }
            else{
                showLegalPositions();
                selected = true;
            }
            adjustAppearance();
        }
    }
    
    public void unselect() {
        selected = false;
        removeLegalPositions();
        adjustAppearance();
    }

    private void adjustAppearance() {
        if (selected) getImage().setTransparency(100);
        else getImage().setTransparency(255); 
    }
    
    private void showLegalPositions(){
        ArrayList<Position> list = getLegalPositions();
        for(Position p : list){
            ((ChessWorld)getWorld()).addObject(new Preview(this), p.getX(), p.getY());
        }
    }
    
    private void removeLegalPositions(){
        ArrayList<Preview> list = (ArrayList<Preview>)getWorld().getObjects(Preview.class);
        for(Preview p : list){
            ((ChessWorld)getWorld()).removeObject(p);
        }
    }
    
    public boolean movePiece(Preview chosen){
        ChessWorld c = (ChessWorld)getWorld();
        Position pos = new Position(chosen);
        if(!c.inCheck(chosen.getConnectedPiece(), pos)){
            List<Piece> p = c.getObjectsAt(chosen.getX(), chosen.getY(), Piece.class);
            if(p.size() > 0){
                c.removeObject(p.get(0));
            }
            this.setLocation(chosen.getX(), chosen.getY());
            c.switchTurn();
            unselect();
            return true;
        }
        return false;
    }
    
    public abstract ArrayList<Position> getLegalPositions();
}
