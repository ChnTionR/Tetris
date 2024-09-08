public class Main {
    static ScoreBoard scoreBoard = new ScoreBoard();
    static Piece piece = new Piece(scoreBoard);

    public static void main(String[] args) {
        GameFrame frame = new GameFrame(piece, scoreBoard);
    }
}