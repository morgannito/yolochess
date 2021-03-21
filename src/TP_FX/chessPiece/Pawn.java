package TP_FX.chessPiece;

import java.util.ArrayList;
import java.util.List;

import TP_FX.boardException.*;
import TP_FX.game.*;

public class Pawn extends Piece {
    private static final long serialVersionUID = 1L;

    boolean firstMove = true;

    public Pawn(int x, int y, Color c, ChessBoard b) throws IllegalPosition {
        super(x, y, c, b);
    }

    public boolean isValidMove(Coord c) throws IllegalPosition {
        if (c.getX() == this.pos.getX() && c.getY() == this.pos.getY()) {
            return false;
        } else if (this.pos.getX() == c.getX()) {
            if(this.getColor() == Color.WHITE && this.pos.getY() - c.getY() < 0
            || this.getColor() == Color.BLACK && this.pos.getY() - c.getY() > 0){
                return false;
            }
            int pos_diff = Math.abs(this.pos.getY() - c.getY());
            if(pos_diff > 1){
                if(pos_diff > 2 || !firstMove){
                    return false;
                }
            }
        } else if (Math.abs(this.pos.getX() - c.getX()) == 1 && Math.abs(this.pos.getX() - c.getX()) == 1
        && this.board.isOccupied(c)){
            firstMove = false;
            return true;
        }else {
            return false;
        }

        firstMove = false;
        return true;
    }

    public List<Coord> legalMove() throws IllegalPosition {
        List<Coord> coords = new ArrayList<Coord>();

        Coord line1 = new Coord(this.pos.getX(), this.pos.getY() + (this.getColor() == Color.WHITE ? -1 : 1));
        Coord line2 = new Coord(this.pos.getX(), this.pos.getY() + (this.getColor() == Color.WHITE ? -2 : 2));
        Coord diag1 = new Coord(this.pos.getX() + 1, this.pos.getY() + (this.getColor() == Color.WHITE ? -1 : 1));
        Coord diag2 = new Coord(this.pos.getX() - 1, this.pos.getY() + (this.getColor() == Color.WHITE ? -1 : 1));

        if(line1.getY() >= 0 && line1.getY() < 8 && !this.board.isOccupied(line1)) {
            coords.add(line1);
            if(firstMove && line2.getY() >= 0 && line2.getY() < 8 && !this.board.isOccupied(line2)) {
                coords.add(line2);
            }
        }
        
        if(diag1.getY() >= 0 && diag1.getY() < 8 && diag1.getX() < 8
        && this.board.isOccupied(diag1) && this.board.getCase(diag1).getPiece().getColor() != this.getColor()){
            coords.add(diag1);
        }
        if(diag2.getY() >= 0 && diag2.getY() < 8 && diag2.getX() >= 0
        && this.board.isOccupied(diag2) && this.board.getCase(diag2).getPiece().getColor() != this.getColor()){
            coords.add(diag2);
        }

        return coords;
    }

    public String toString() {
        return (col == Color.BLACK ? "♙" : "♟") + super.toString();
    }

}