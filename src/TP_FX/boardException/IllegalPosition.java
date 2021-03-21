package TP_FX.boardException;

public class IllegalPosition extends Exception {
    private static final long serialVersionUID = 1L;

    public IllegalPosition(String t) {
        super(t);
    }
}
