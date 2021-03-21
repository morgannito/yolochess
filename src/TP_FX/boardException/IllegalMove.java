package TP_FX.boardException;

public class IllegalMove extends Exception {
    private static final long serialVersionUID = 1L;

    public IllegalMove(String t) {
        super(t);
    }
}
