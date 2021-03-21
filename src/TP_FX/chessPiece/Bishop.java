package TP_FX.chessPiece;

import java.util.ArrayList;
import java.util.List;

import TP_FX.boardException.*;
import TP_FX.game.*;

public class Bishop extends Piece {
    private static final long serialVersionUID = 1L;

    public Bishop(int x, int y, Color c, ChessBoard b) throws IllegalPosition {
        super(x, y, c, b);
    }
    
    public boolean isValidMove(Coord c) throws IllegalPosition{
        if (c.getX() == this.pos.getX() && c.getY() == this.pos.getY()) {
            return false;
        } else if (Math.abs(this.pos.getX()-c.getX()) == Math.abs(this.pos.getY()-c.getY())){
            int m = (this.pos.getX() - c.getX()) > 0 ? -1 : 1;
            int n = (this.pos.getY() - c.getY()) > 0 ? -1 : 1;
            int i = this.pos.getX() + m;
            int j = this.pos.getY() + n;
            while(i != c.getX() && j != c.getY()){
                if(this.board.isOccupied(new Coord(i, j))){
                    return false;
                }
                i += m;
                j += n;
            }
        } else {
            return false;
        }

        return true;
    }

    public List<Coord> legalMove() throws IllegalPosition {
        List<Coord> coords = new ArrayList<Coord>();

        // UP LEFT
        int i = this.pos.getX();
        int j = this.pos.getY();
        while(--i >= 0 && --j >= 0){
            Coord c = new Coord(i, j);
            if(board.isOccupied(c)){
                if(this.getColor() == board.getCase(c).getPiece().getColor()){
                    break;
                }
                coords.add(c);
                break;
            }
            coords.add(c);
        }

        // DOWN LEFT
        i = this.pos.getX();
        j = this.pos.getY();
        while(--i >= 0 && ++j < 8){
            Coord c = new Coord(i, j);
            if(board.isOccupied(c)){
                if(this.getColor() == board.getCase(c).getPiece().getColor()){
                    break;
                }
                coords.add(c);
                break;
            }
            coords.add(c);
        }

        // UP RIGHT
        i = this.pos.getX();
        j = this.pos.getY();
        while(++i < 8 && --j >= 0){
            Coord c = new Coord(i, j);
            if(board.isOccupied(c)){
                if(this.getColor() == board.getCase(c).getPiece().getColor()){
                    break;
                }
                coords.add(c);
                break;
            }
            coords.add(c);
        }

        // RIGHT DOWN
        i = this.pos.getX();
        j = this.pos.getY();
        while(++i < 8 && ++j < 8){
            Coord c = new Coord(i, j);
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
        return (col == Color.BLACK ? "♗" : "♝") + super.toString();
    }

}