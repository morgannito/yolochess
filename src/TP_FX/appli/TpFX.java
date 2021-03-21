package TP_FX.appli;

import java.io.IOException;

import TP_FX.boardException.*;
import TP_FX.fx.FXController;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import TP_FX.game.ChessBoard;
import TP_FX.game.Coord;
import TP_FX.game.Game;
import TP_FX.game.IMovable;
import TP_FX.game.Color;
import TP_FX.game.Piece;

public class TpFX extends Application {
    // *
    private Game game;
    private ChessBoard board;

    private Canvas canvas;

    private int w = 80, h = 80;

    private Coord currentlySelected;
    private Piece selectedPiece;

    private int turn = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IllegalPosition, IOException, IllegalMove {
        game = new Game();
        board = game.getBoard();
        canvas = new Canvas(w*8, h*8);
        FXController.initPC(primaryStage, canvas, w, h);
        FXController.loadAssets();

        canvas.setOnMouseClicked(e -> {
            Color players[] = {Color.WHITE, Color.BLACK};
            if (e.getButton() == MouseButton.PRIMARY) {
                int x = (int) ((e.getX() / (w * 8.0)) * 8);
                int y = (int) ((e.getY() / (h * 8.0)) * 8);
                if (currentlySelected != null && x == currentlySelected.getX() && y == currentlySelected.getY()) {
                    return;
                }
                currentlySelected = new Coord(x, y);
                FXController.casesColor = new int[8][8];
                try {
                    boolean isLegal = false;
                    if(selectedPiece != null){
                        for(Coord c : this.selectedPiece.legalMove()){
                            isLegal = isLegal || (c.getX() == currentlySelected.getX() && c.getY() == currentlySelected.getY());
                        }
                    }
                    if(isLegal){
                        selectedPiece.Move(currentlySelected, players[turn]);
                        String fp = this.selectedPiece.getPos().getX() + "" + this.selectedPiece.getPos().getY();
                        String lp = currentlySelected.getX() + "" + currentlySelected.getY();
                        game.moves += fp + lp + " ";
                        if(turn == 1) game.moves += "\n";
                        turn = (turn + 1) % 2;
                        currentlySelected = null;
                        selectedPiece = null;
                    }
                    else if (!board.getCase(currentlySelected).isOccupied()) {
                        FXController.ResetColors();
                        currentlySelected = null;
                        selectedPiece = null;
                    }
                    else{
                        IMovable clickedPiece = board.getCase(currentlySelected).getPiece();
                        if(clickedPiece.getColor() == (turn == 0 ? Color.WHITE : Color.BLACK)){
                            for (Coord c : clickedPiece.legalMove()) {
                                FXController.casesColor[c.getX()][c.getY()] = 1;
                            }
                            this.selectedPiece = (Piece)clickedPiece;
                        }
                    }
                    FXController.DrawScene(board);
                } catch (IllegalPosition | IllegalMove e1) {
                    System.err.println(e1);
                    e1.printStackTrace();
                }
            } else {
                FXController.casesColor = new int[8][8];
            }
        });
        

        try {
            FXController.DrawScene(board);
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }


    }

