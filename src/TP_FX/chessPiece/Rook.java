package TP_FX.chessPiece;

import java.util.ArrayList;
import java.util.List;

import TP_FX.boardException.*;
import TP_FX.game.*;

public class Rook extends Piece {
    private static final long serialVersionUID = 1L;

    public Rook(int x, int y, Color c, ChessBoard b) throws IllegalPosition {
        super(x, y, c, b);
    }
    
    public boolean isValidMove(Coord c) throws IllegalPosition {
        if (c.getX() == this.pos.getX()) {
            int n = (this.pos.getY() - c.getY()) > 0 ? -1 : 1;
            for (int j = this.pos.getY()+n; j < c.getY(); j += n) {
                if (this.board.isOccupied(new Coord(c.getX(), j))) {
                    return false;
                }
            }
        } else if (c.getY() == this.pos.getY()){
            int n = (this.pos.getX() - c.getX()) > 0 ? -1 : 1;
            for(int i=this.pos.getX()+n; i<c.getX(); i += n){
                if(this.board.isOccupied(new Coord(i, c.getY()))){
                    return false;
                }
            }
        } else {
            return false;
        }
        
        return true;
    }

    public List<Coord> legalMove() throws IllegalPosition{
        List<Coord> coords = new ArrayList<Coord>();
        
        // X UP
        for(int i = this.pos.getX() -1; i >= 0; i--){
            Coord c = new Coord(i, this.pos.getY());
            if(board.isOccupied(c)){
                if(this.getColor() == board.getCase(c).getPiece().getColor()){
                    break;
                }
                coords.add(c);
                break;
            }
            coords.add(c);
        }

        // X DOWN
        for(int i = this.pos.getX() +1; i < 8; i++){
            Coord c = new Coord(i, this.pos.getY());
            if(board.isOccupied(c)){
                if(this.getColor() == board.getCase(c).getPiece().getColor()){
                    break;
                }
                coords.add(c);
                break;
            }
            coords.add(c);
        }

        // Y UP
        for(int i = this.pos.getY() -1; i >= 0; i--){
            Coord c = new Coord(this.pos.getX(), i);
            if(board.isOccupied(c)){
                if(this.getColor() == board.getCase(c).getPiece().getColor()){
                    break;
                }
                coords.add(c);
                break;
            }
            coords.add(c);
        } 

        // Y DOWN
        for(int i = this.pos.getY() +1; i < 8; i++){
            Coord c = new Coord(this.pos.getX(), i);
            if(board.isOccupied(c)){
                if(this.getColor() == board.getCase(c).getPiece().getColor()){
                    break;
                }
                coords.add(c);
                break;
            }
            coords.add(c);
        }

        return coords;
    }

    public String toString() {
        return (col == Color.BLACK ? "♖" : "♜") + super.toString();
    }

}