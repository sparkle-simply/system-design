// Player class
public class Player {
    private String name;
    private boolean isWhiteSide;
    public Piece(String name, boolean isWhiteSide) {
        this.name = name;
        this.isWhiteSide = isWhiteSide;
    }
    public String getName() {
        return this.name;
    }
    public boolean isWhiteSide {
        return this.isWhiteSide;
    }
}

// Status for tracking Game State
public enum Status {
    ACTIVE, SAVED, BLACK_WIN, WHITE_WIN, STALEMATE
}

// Interface for movement strategy (pattern) for piece
interface MovementStrategy {
    public boolean canMove(Board board, Cell startCell, Cell endCell);
}

// Abstract class for Piece
public abstract Piece {
    private boolean isWhitePiece;
    private boolean killed = false;
    protected MovementStrategy strategy;

    public Piece(boolean isWhitePiece, MovementStrategy strategy) {
        this.isWhitePiece = isWhitePiece;
        this.strategy = strategy;
    }
    public boolean isWhite() {
        return this.isWhitePiece;
    }
    public boolean isKilled() {
        return this.killed;
    }
    public boolean setKilled(boolean killed) {
        this.killed = killed;
    }
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        strategy.canMove(board, startCell, endCell);
    }
}

// Concrete pieces
public class King extends Piece {
    public King(boolean isWhitePiece) {super(isWhitePiece, new KingMovementStrategy()); }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return strategy.canMove()
    }
}

public class Queen extends Piece {
    public Queen(boolean isWhitePiece) {
        super(isWhitePiece, new QueenMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return strategy.canMove()
    }
}

public class Bishop extends Piece {
    public Bishop(boolean isWhitePiece) {
        super(isWhitePiece, new BishopMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return strategy.canMove()
    }
}

public class Knight extends Piece {
    public Knight(boolean isWhitePiece) {
        super(isWhitePiece, new KnightMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return strategy.canMove()
    }
}

public class Rook extends Piece {
    public Rook(boolean isWhitePiece) {
        super(isWhitePiece, new RookMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return strategy.canMove()
    }
}

public class Pawn extends Piece {
    public Pawn(boolean isWhitePiece) {
        super(isWhitePiece, new PawnMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return strategy.canMove()
    }
}

// PieceFactory class, maybe abstract with concrete piece factory
public abstract class PieceFactory {
    public Piece createPiece(String pieceType, boolean isWhitePiece) {
        switch (pieceType.toLowerCase()) {
            case "king":  return new King(isWhitePiece);
            case "queen": return new Queen(isWhitePiece);
            case "bishop": return new Bishop(isWhitePiece);
            case "knight": return new Knight(isWhitePiece);
            case "rook": return new Rook(isWhitePiece);
            case "pawn": return new Pawn(isWhitePiece);
            default: throw new IllegalArgumentException("Unsupported pieceType: "+pieceType);
        }
    }
}

// Cell class
public class Cell {
    private int row, col;
    private Piece piece;
    public Cell(int row, int col, Piece piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
    }
    public Piece getPiece() {
        return this.piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}

// Move class
public class Move {
    private Cell startCell, endCell;

    public Move(Cell startCell, Cell endCell) {
        this.startCell = startCell;
        this.endCell = endCell;
    }

    public Cell getStartCell() {
        return startCell;
    }

    public void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }

    public void setEndCell(Cell endCell) {
        this.endCell = endCell;
    }

    public boolean isValidMove() {
        return !(this.startCell.getPiece().isWhite() == this.endCell.getPiece().isWhite());
    }
}

// Singleton class for Board
public class Board {
    private Board instance;
    private Cell[][] board;
    private Board(int rows) {
        this.initializeBoard(rows);
    }

    public Board getInstance(int rows) {
        if(this.instance == null) {
            this.instance = new Board(rows);
        }
        return this.instance;
    }

    private void initializeBoard(int rows) {
        this.board = new Cell[rows][rows];

        // setting white pieces on board
        this.setPieceRow(0, true);
        this.setPawnRow(1, rows, true);

        // setting black pieces on board
        this.setRow(rows-1, false);
        this.setPawnRow(rows-2, false);

        // setting other cells of board
        for(int i=2; i<rows-2; i++) {
            for(int j=0; j<rows; j++)
                this.board[i][j] = new Cell(i, j, null);
        }
    }

    private void setPieceRow(int row, boolean isWhite) {
        this.board[row][0] = new Cell(row, 0, new PieceFactory.createPiece("rook", isWhite));
        this.board[row][1] = new Cell(row, 1, new PieceFactory.createPiece("bishop", isWhite));
        this.board[row][2] = new Cell(row, 2, new PieceFactory.createPiece("knight", isWhite));
        this.board[row][3] = new Cell(row, 3, new PieceFactory.createPiece("king", isWhite));
        this.board[row][4] = new Cell(row, 4, new PieceFactory.createPiece("queen", isWhite));
        this.board[row][5] = new Cell(row, 5, new PieceFactory.createPiece("knight", isWhite));
        this.board[row][6] = new Cell(row, 6, new PieceFactory.createPiece("bishop", isWhite));
        this.board[row][7] = new Cell(row, 7, new PieceFactory.createPiece("rook", isWhite));
    }

    private void setPawnRow(int row, int rows, boolean isWhite) {
        for(int i=0; i<rows; i++) {
            this.board[row][i] = new Cell(row, i, new PieceFactory.createPiece("pawn", isWhite));
        }
    }

    public Cell getCell(int row, int col) {
        if(row>=0 && row<board.length && col>=0 && col<board.length) {
            return this.board[i][j];
        }
    }
}

public abstract BoardGame {}

public class ChessGame extends BoardGame {
    private Board board;
    private Player player1, player2;
    private ArrayList<Move> gameLog;
    private Status status;
    private boolean isWhiteturn;

    public ChessGame(Player player1, Player player2) {
        this.board = new Board(8);
        this.player1 = player1;
        this.player2 = player2;
        gameLog = new ArrayList<Move>();
        this.status = Status.ACTIVE;
        this.isWhiteturn = true;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (this.status == Status.ACTIVE) {
            Player currentPlayer = this.isWhiteturn ? this.player1 : this.player2;

            System.out.println("Input start row and column location: ");
            int row1 = sc.nextInt();
            int col1 = sc.nextInt();

            System.out.println("Input destination row and column location: ");
            int row2 = sc.nextInt();
            int col2 = sc.nextInt();

            Cell startCell = this.board.getCell(row1, col1);
            Cell endCell = this.board.getCell(row2, col2);

            Piece sourcePiece = startCell.getPiece();
            if(sourcePiece == null) {
                System.out.println("Invalid move, no piece on source cell");
                continue;
            }
            this.makeMove(new Move(startCell, endCell), currentPlayer);
        }
        System.out.println("Game Over! status: "+this.status);
    }

    private void makeMove(Move move, Player player) {
        if(move.isValidMove()) {
            Piece sourcePiece = move.getStartCell().getPiece();
            if(sourcePiece.canMove(this.board, move.getStartCell(), move.getEndCell())) {
                destinationPiece = move.getEndCell().getPiece();
                if(destinationPiece != null) {
                    if(destinationPiece instanceof King && this.isWhiteturn) {
                        this.status = Status.WHITE_WIN;
                        return;
                    }
                    if(destinationPiece instanceof King && !this.isWhiteturn) {
                        this.status = Status.BLACK_WIN;
                        return;
                    }
                    destinationPiece.setKilled(true);
                }
                move.getEndCell().setPiece(sourcePiece);
                move.getStartCell().setPiece(null);
                this.gameLog.add(move);

                this.isWhiteturn = !this.isWhiteturn;
            }
        }
    }
}
