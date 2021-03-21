package TP_FX.chessPiece;

import java.util.ArrayList;
import java.util.List;

import TP_FX.boardException.*;
import TP_FX.game.*;

public class King extends Piece {
    private static final long serialVersionUID = 1L;

    public King(int x, int y, Color c, ChessBoard b) throws IllegalPosition{
        super(x, y, c, b);
    }

    public boolean isValidMove(Coord c) throws IllegalPosition{
        if(c.getX() == this.pos.getX() && c.getY() == this.pos.getY()){
            return false;
        }
        if(Math.abs(this.pos.getX() - c.getX()) > 1
        || Math.abs(this.pos.getY() - c.getY()) > 1){
            return false;
        }

        return true;
    }

    public List<Coord> legalMove() throws IllegalPosition {
        List<Coord> coords = new ArrayList<Coord>();

        for(int i=-1; i<=1; i++){
            for(int j=-1; j<=1; j++){
                if(i == 0 && j == 0){
                    continue;
                }
                Coord c = new Coord(this.pos.getX() + i, this.pos.getY() + j);
                if(c.getX() < 8 && c.getX() >= 0 && c.getY() < 8 && c.getY() >= 0){
                    if(board.isOccupied(c)){
                        if(this.getColor() == board.getCase(c).getPiece().getColor()){
                            break;
                        }
                        coords.add(c);
                        break;
                    }
                    coords.add(c);
                }
            }
        }

        return coords;
    }

    public String toString() {
        return (col == Color.BLACK ? "♔" : "♚") + super.toString();
    }
}