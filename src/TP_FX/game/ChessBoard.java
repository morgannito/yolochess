package TP_FX.game;

import java.io.Serializable;

import TP_FX.boardException.IllegalPosition;

public class ChessBoard implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Case cases[][] = new Case[8][8];
    public Case[][] getCases(){ return cases; }
    public Case getCase(Coord c) throws IllegalPosition{ 
        try{
            return cases[c.getX()][c.getY()];
        } catch (Exception e){
            throw new IllegalPosition("Case is empty.");
        }
    }

    private ChessBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                cases[i][j] = new Case();
            }
        }
    }

    private static ChessBoard INSTANCE;

    public static ChessBoard getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ChessBoard();
        }
        return INSTANCE;
    }
    
    public boolean isOccupied(Coord pos) throws IllegalPosition{
        try {
            return cases[pos.getX()][pos.getY()].isOccupied();
        } catch (Exception e) {
            throw new IllegalPosition("Position out of bounds");
        }
        
    }

    public void setOccupation(Coord pos, IMovable piece) throws IllegalPosition{
        try {
            cases[pos.getX()][pos.getY()].setPiece(piece);
        } catch (Exception e) {
            throw new IllegalPosition("Position out of bounds");
        }
    }

    public void Reset(){
        for(int i=0; i<8;i++){
            for(int j=0; j<8; j++){
                cases[i][j] = new Case();
            }
        }
    }

    /**
     * Prints the board, with 0 for empty cases and
     * 1 for occupied ones.
     */
    public void smartPrint(Color turn){
        System.out.println("╔═════════════════╦═══╗");
        System.out.println("║ 1 2 3 4 5 6 7 8 ║ "+ (turn == Color.WHITE ? "W" : "B") +" ║");
        System.out.println("╠═════════════════╬═══╣");
        for(int i=0; i<8; i++){
            String txt = "║ ";
            for(int j=0; j<8; j++){
                String toAdd = "";
                if(cases[j][i].getPiece() == null){
                    toAdd = "¤";
                } else {
                    toAdd = cases[j][i].getPiece().toString().substring(0, 1);
                }
                txt += toAdd + " ";
            }
            System.out.println(txt + "║ "+ (i+1) + " ║");
        }
        System.out.println("╚═════════════════╩═══╝");
    }
}