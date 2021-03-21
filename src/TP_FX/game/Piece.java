package TP_FX.game;

import java.util.List;

import TP_FX.boardException.IllegalMove;
import TP_FX.boardException.IllegalPosition;

public abstract class Piece implements IMovable {
    private static final long serialVersionUID = 1L;
    
    protected Coord pos;
    protected Color col;
    protected ChessBoard board;

    public Piece(int x, int y, Color c, ChessBoard b) throws IllegalPosition {
        this.pos = new Coord(x, y);
        this.col = c;
        this.board = b;
        this.board.setOccupation(this.pos, this);
    }

    public Coord getPos(){ return this.pos; }

    public Color getColor(){ return this.col; }

    public abstract List<Coord> legalMove() throws IllegalPosition ;

    public abstract boolean isValidMove(Coord c) throws IllegalPosition;

    public void Move(Coord c, Color player) throws IllegalPosition, IllegalMove {
        if(player != this.col){
            throw new IllegalMove("Not your color.");
        }

        if (this.pos.getX() == c.getX() && this.pos.getY() == c.getY()){
            throw new IllegalMove("The piece is already here.");
        }

        if (!isValidMove(c)) {
            throw new IllegalMove("Movement not possible.");
        }

        if (this.board.isOccupied(c) && this.getColor() == this.board.getCase(c).getPiece().getColor()){
            throw new IllegalMove("This case is occupied by another one of your piece.");
        }

        this.board.setOccupation(this.pos, null);
        this.pos.setX(c.getX());
        this.pos.setY(c.getY());
        this.board.setOccupation(this.pos, this);
    }

    @Override
    public String toString() {
        return " (" + this.col.name() + ") : {" + this.pos.getX() + ", " + this.pos.getY() + "}";
    }
}