package TP_FX.chessPiece;

import java.util.ArrayList;
import java.util.List;

import TP_FX.boardException.*;
import TP_FX.game.*;

public class Knight extends Piece {
    private static final long serialVersionUID = 1L;

    public Knight(int x, int y, Color c, ChessBoard b) throws IllegalPosition {
        super(x, y, c, b);
    }

    public boolean isValidMove(Coord c) throws IllegalPosition {
        if (c.getX() == this.pos.getX() && c.getY() == this.pos.getY()) {
            return false;
        } else if ((Math.abs(this.pos.getX() - c.getX()) == 1 && Math.abs(this.pos.getY() - c.getY()) == 2)
                || (Math.abs(this.pos.getX() - c.getX()) == 2 && Math.abs(this.pos.getY() - c.getY()) == 1)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Coord> legalMove() throws IllegalPosition{
        List<Coord> coords = new ArrayList<Coord>();

        Coord[] tests = {
            new Coord(this.pos.getX() + 1, this.pos.getY() + 2),
            new Coord(this.pos.getX() + 1, this.pos.getY() - 2),
            new Coord(this.pos.getX() - 1, this.pos.getY() + 2),
            new Coord(this.pos.getX() - 1, this.pos.getY() - 2),
            new Coord(this.pos.getX() + 2, this.pos.getY() + 1),
            new Coord(this.pos.getX() + 2, this.pos.getY() - 1),
            new Coord(this.pos.getX() - 2, this.pos.getY() + 1),
            new Coord(this.pos.getX() - 2, this.pos.getY() - 1)
        };

        for(Coord c : tests){
            if(c.getX() >= 0 && c.getX() < 8 && c.getY() >= 0 && c.getY() < 8){
                if(board.isOccupied(c)){
                    if(this.getColor() == board.getCase(c).getPiece().getColor()){
                        continue;
                    }
                }
                coords.add(c);
            }
        }

        return coords;
    }

    public String toString() {
        return (col == Color.BLACK ? "♘" : "♞") + super.toString();
    }

}