package TP_FX.game;

import java.io.Serializable;

public class Case implements Serializable {
    private static final long serialVersionUID = 1L;

    private IMovable piece;

    public Case(){}
    public Case(IMovable p){
        this.piece = p;
    }

    public IMovable getPiece(){return this.piece;}
    public void setPiece(IMovable piece){this.piece = piece;}

    public boolean isOccupied(){
        return this.piece != null;
    }
}
