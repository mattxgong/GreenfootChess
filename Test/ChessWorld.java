import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChessWorld extends World
{
    private int turn; // 0 for white and 1 for black
    private boolean inCheck;
    
    public ChessWorld()
    {    
        super(8, 8, 50); // each cell is 50x50
        for(int i = 0; i < 8; i++){
            addObject(new Pawn(0), i, 6);
        }
        addObject(new Bishop(0), 2, 7);
        addObject(new Bishop(0), 5, 7);
        addObject(new Knight(0), 1, 7);
        addObject(new Knight(0), 6, 7);
        addObject(new Rook(0), 0, 7);
        addObject(new Rook(0), 7, 7);
        addObject(new Queen(0), 3, 7);
        addObject(new King(0), 4, 7);
        
        for(int i = 0; i < 8; i++){
            addObject(new Pawn(1), i, 1);
        }
        addObject(new Bishop(1), 2, 0);
        addObject(new Bishop(1), 5, 0);
        addObject(new Knight(1), 1, 0);
        addObject(new Knight(1), 6, 0);
        addObject(new Rook(1), 0, 0);
        addObject(new Rook(1), 7, 0);
        addObject(new Queen(1), 3, 0);
        addObject(new King(1), 4, 0);
        
        inCheck = false;
        turn = 0;
    }
    
    public int getTurn(){
        return turn;
    }
    
    public void switchTurn(){
        if(turn == 0) turn = 1;
        else turn = 0;
    }
    
    public boolean inCheck(Piece moved, Position newPos){
        List<King> kings = getObjects(King.class);
        List<Piece> pieces = getObjects(Piece.class);
        Position kingsOriginal0 = new Position(kings.get(0)), kingsOriginal1 = new Position(kings.get(1)), theKing;
        boolean kingMoved = false;

        if(kings.get(0) == moved){
            kingsOriginal0 = new Position(kings.get(0));
            kings.get(0).setLocation(newPos.getX(), newPos.getY());
            kingMoved = true;
        }
        else if(kings.get(1) == moved){
            kingsOriginal1 = new Position(kings.get(1));
            kings.get(1).setLocation(newPos.getX(), newPos.getY());
            kingMoved = true;
        }
        if(kings.get(0).getColor() == turn) theKing = new Position(kings.get(0));
        else theKing = new Position(kings.get(1));
        
        for(Piece p : pieces){
            boolean ifMove = (p == moved);
            Position original = new Position(p), getEaten = new Position(-1, -1);
        
            if(ifMove){
                List<Piece> eaten = getObjectsAt(newPos.getX(), newPos.getY(), Piece.class);
                if(eaten.size() > 0){
                    if(eaten.get(0).getColor() != turn){
                        getEaten = new Position(eaten.get(0));
                    }
                }
                p.setLocation(newPos.getX(), newPos.getY());
                
                for(Piece i : pieces){
                    if(i.getColor() != turn && !getEaten.equals(new Position(i))){
                        ArrayList<Position> possibleMoves = i.getLegalPositions();
                        for(Position pos : possibleMoves){
                            if(pos.equals(theKing)){
                                p.setLocation(original.getX(), original.getY());
                                kings.get(0).setLocation(kingsOriginal0.getX(), kingsOriginal0.getY());
                                kings.get(1).setLocation(kingsOriginal1.getX(), kingsOriginal1.getY());
                                return true;
                            }
                        }
                    }
                }
            }
            
            p.setLocation(original.getX(), original.getY());
        }
        kings.get(0).setLocation(kingsOriginal0.getX(), kingsOriginal0.getY());
        kings.get(1).setLocation(kingsOriginal1.getX(), kingsOriginal1.getY());
        
        return false;
    }
    
    public void promote(Pawn current, int color){
        Position original = new Position(current);
        
        removeObject(current);
        addObject(new Queen(color), original.getX(), original.getY());
    }
}
