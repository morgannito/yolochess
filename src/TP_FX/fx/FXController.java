package TP_FX.fx;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import TP_FX.game.*;
import TP_FX.boardException.*;

public class FXController {

    public static HashMap<String, Image> pieces = new HashMap<String, Image>();
    private static Stage primaryStage;
    private static Canvas canvas;

    private static int w, h;

    public static int casesColor[][] = new int[8][8];

    private static ArrayList<Node> nodes = new ArrayList<Node>();

    public static void loadAssets() {
        String piecesWhite[] = { "♟", "♜", "♞", "♝", "♛", "♚" };
        String piecesBlack[] = { "♙", "♖", "♘", "♗", "♕", "♔" };
        try {
            Image imagePieces = new Image("TP_FX/fx/ChessPieces.png", false);
            int pieceHeight = ((int) imagePieces.getHeight()) / 2;
            int pieceWidth = ((int) imagePieces.getWidth()) / 6;

            PixelReader pixReader = imagePieces.getPixelReader();
            for (int i = 0; i < 6; i++) {
                pieces.put(piecesWhite[i], new WritableImage(pixReader, i * pieceWidth, 0, pieceWidth, pieceHeight));
                pieces.put(piecesBlack[5 - i],
                        new WritableImage(pixReader, i * pieceWidth, pieceHeight, pieceWidth, pieceHeight));
            }

        } catch (Exception e) {
            System.err.println("Null ppinter exception");
            System.err.println(System.getProperty("user.dir"));
            e.printStackTrace();
        }
    }

    public static void RegisterUI(Node elem){
        nodes.add(elem);
    }

    public static void ResetColors(){
        casesColor = new int[8][8];
    }

    public static void initPC(Stage ps, Canvas c, int width, int height) {
        primaryStage = ps;
        canvas = c;
        w = width;
        h = height;
    }

    public static void DrawScene(ChessBoard board) throws IllegalPosition {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (casesColor[i][j] == 1)
                    canvas.getGraphicsContext2D().setFill(Color.rgb(60, 230, 60, 0.5));
                else if (i % 2 != j % 2)
                    canvas.getGraphicsContext2D().setFill(Color.BLACK);
                else
                    canvas.getGraphicsContext2D().setFill(Color.WHITE);
                canvas.getGraphicsContext2D().fillRect(w * i, h * j, w, h);
            }
        }
        DrawPieces(board);
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(nodes);
        HBox box = new HBox(canvas, vBox);
        primaryStage.setScene(
            new Scene(box)
        );
        primaryStage.show();
    }

    public static void DrawPieces(ChessBoard board) throws IllegalPosition {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.isOccupied(new Coord(i, j))) {
                    canvas.getGraphicsContext2D().drawImage(
                            pieces.get(board.getCase(new Coord(i, j)).getPiece().toString().substring(0, 1)), i * w,
                            j * h, w, h);
                }
            }
        }
    }
}
