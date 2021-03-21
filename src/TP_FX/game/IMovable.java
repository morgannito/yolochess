package TP_FX.game;

import java.io.Serializable;
import java.util.List;

import TP_FX.boardException.IllegalMove;
import TP_FX.boardException.IllegalPosition;

public interface IMovable extends Serializable {
    void Move(Coord c, Color player) throws IllegalPosition, IllegalMove;
    Color getColor();
    List<Coord> legalMove() throws IllegalPosition;
}
