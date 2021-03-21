package TP_FX.game;

import java.io.Serializable;

public class Coord implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int x, y;

    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Coord(){
        this(0, 0);
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}

    @Override
    public String toString() {
        return "{"+ this.x +", "+ this.y +"}";
    }

    public String toPrint(){
        return (this.x+1) +""+ (this.y+1);
    }
}