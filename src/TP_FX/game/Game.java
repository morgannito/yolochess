package TP_FX.game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import TP_FX.boardException.*;
import TP_FX.chessPiece.*;

public class Game {

    private ChessBoard board;
    public String moves = "";
    private int starting_turn = 0;

    public Game() throws IOException, IllegalPosition, IllegalMove {
        board = ChessBoard.getInstance();
        InitBoard(board);
        // Load();
    }

    public ChessBoard getBoard() {
        return this.board;
    }

    public void Play() throws IOException, IllegalPosition {
        int i = starting_turn % 2;
        int turn = (starting_turn - i) / 2;
        Scanner input = new Scanner(System.in);
        Color players[] = { Color.WHITE, Color.BLACK };
        for (; turn < 500; turn++) {
            for (i = starting_turn % 2; i < 2; i++) {
                starting_turn = 0;
                board.smartPrint(players[i]);

                System.out.println(players[i].name() + "'s turn.");
                Coord pos = new Coord(-1, -1);
                Coord move = new Coord(-1, -1);

                boolean valid = false;

                while (!valid) {
                    try {
                        String move_coord = input.nextLine();
                        String u_in[] = move_coord.split("");
                        pos.setX(Integer.parseInt(u_in[0]) - 1);
                        pos.setY(Integer.parseInt(u_in[1]) - 1);
                        if (u_in.length == 2 && board.isOccupied(pos)) {
                            for (Coord c : board.getCase(pos).getPiece().legalMove()) {
                                System.out.println("{" + (c.getX() + 1) + ", " + (c.getY() + 1) + "}");
                            }
                            System.out.println(players[i].name() + "'s turn.");
                            continue;
                        }
                        move.setX(Integer.parseInt(u_in[2]) - 1);
                        move.setY(Integer.parseInt(u_in[3]) - 1);

                        try {
                            board.getCase(pos).getPiece().Move(move, players[i]);
                        } catch (Exception e) {
                            System.out.println(e);
                            continue;
                        }
                        valid = true;
                        moves += move_coord + " ";
                        Save();

                    } catch (NumberFormatException e) {
                        System.out.println("Needs to be an int (xyxy).");
                        continue;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Needs to be an int (xyxy).");
                        continue;
                    }
                }
            }
            moves += "\n";
        }
        input.close();
    }

    public void Save() throws IOException {
        FileWriter writer = new FileWriter("moves.txt");
        writer.write(moves);
        writer.close();
    }

    private void InitBoard(ChessBoard b) throws IllegalPosition {
        b.Reset();

        b.setOccupation(new Coord(0, 7), new Rook(0, 7, Color.WHITE, b));
        b.setOccupation(new Coord(1, 7), new Knight(1, 7, Color.WHITE, b));
        b.setOccupation(new Coord(2, 7), new Bishop(2, 7, Color.WHITE, b));
        b.setOccupation(new Coord(3, 7), new Queen(3, 7, Color.WHITE, b));
        b.setOccupation(new Coord(4, 7), new King(4, 7, Color.WHITE, b));
        b.setOccupation(new Coord(5, 7), new Bishop(5, 7, Color.WHITE, b));
        b.setOccupation(new Coord(6, 7), new Knight(6, 7, Color.WHITE, b));
        b.setOccupation(new Coord(7, 7), new Rook(7, 7, Color.WHITE, b));
        b.setOccupation(new Coord(0, 6), new Pawn(0, 6, Color.WHITE, b));
        b.setOccupation(new Coord(1, 6), new Pawn(1, 6, Color.WHITE, b));
        b.setOccupation(new Coord(2, 6), new Pawn(2, 6, Color.WHITE, b));
        b.setOccupation(new Coord(3, 6), new Pawn(3, 6, Color.WHITE, b));
        b.setOccupation(new Coord(4, 6), new Pawn(4, 6, Color.WHITE, b));
        b.setOccupation(new Coord(5, 6), new Pawn(5, 6, Color.WHITE, b));
        b.setOccupation(new Coord(6, 6), new Pawn(6, 6, Color.WHITE, b));
        b.setOccupation(new Coord(7, 6), new Pawn(7, 6, Color.WHITE, b));

        b.setOccupation(new Coord(0, 0), new Rook(0, 0, Color.BLACK, b));
        b.setOccupation(new Coord(1, 0), new Knight(1, 0, Color.BLACK, b));
        b.setOccupation(new Coord(2, 0), new Bishop(2, 0, Color.BLACK, b));
        b.setOccupation(new Coord(3, 0), new Queen(3, 0, Color.BLACK, b));
        b.setOccupation(new Coord(4, 0), new King(4, 0, Color.BLACK, b));
        b.setOccupation(new Coord(5, 0), new Bishop(5, 0, Color.BLACK, b));
        b.setOccupation(new Coord(6, 0), new Knight(6, 0, Color.BLACK, b));
        b.setOccupation(new Coord(7, 0), new Rook(7, 0, Color.BLACK, b));
        b.setOccupation(new Coord(0, 1), new Pawn(0, 1, Color.BLACK, b));
        b.setOccupation(new Coord(1, 1), new Pawn(1, 1, Color.BLACK, b));
        b.setOccupation(new Coord(2, 1), new Pawn(2, 1, Color.BLACK, b));
        b.setOccupation(new Coord(3, 1), new Pawn(3, 1, Color.BLACK, b));
        b.setOccupation(new Coord(4, 1), new Pawn(4, 1, Color.BLACK, b));
        b.setOccupation(new Coord(5, 1), new Pawn(5, 1, Color.BLACK, b));
        b.setOccupation(new Coord(6, 1), new Pawn(6, 1, Color.BLACK, b));
        b.setOccupation(new Coord(7, 1), new Pawn(7, 1, Color.BLACK, b));
    }
}
