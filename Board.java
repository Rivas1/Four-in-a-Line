/*
	Christopher Rivas
	CS 420
	Project 3: 4-In-A-Line
	03/06/2018
*/
public class Board 
{

	private final char EMPTY = '-';
	private final char BOT = 'X';
	private final char HUMAN = 'O';
	final int SIZE = 8;
	char[][] board;
	private Position myMove;
	private float myScore;

	public Board() 
	{
		board = new char[8][8];
		
		myMove = new Position('A', 0);
		for (int r = 0; r < board.length; r++) 
		{
			for (int c = 0; c < board.length; c++) 
			{
				board[r][c] = EMPTY;
			}
		}
		myScore = 0.0f;
	}

	public Board(Board other) 
	{
		board = new char[8][8];
		
		myMove = other.getMove();
		for (int r = 0; r < board.length; r++) 
		{
			for (int c = 0; c < board.length; c++) 
			{
				board[r][c] = other.board[r][c];
			}
		}
		myScore = other.getScore();
	}

	public boolean setPiece(char x, int y, char piece) 
	{
		int ix = Character.toUpperCase(x) - 65;
		if (isEmpty(ix, y)) 
		{
			if (checkBounds(ix, y)) 
			{
				board[ix][y] = piece;
				return true;
			} else 
			{
				return false;
			}
		}
		return false;
	}

	public boolean setPiece(int x, int y, char piece) 
	{
		if (checkBounds(x, y) && board[x][y] == EMPTY) 
		{
			board[x][y] = piece;
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public char getPiece(char x, int y)
	{
		int ix = Character.toUpperCase(x) - 65;
		if (checkBounds(ix, y)) 
		{
			return board[ix][y];
		}
		else 
		{
			return ' ';
		}
	}
	
	public char getPiece(int x, int y)
	{
		if (checkBounds(x, y)) 
		{
			return board[x][y];
		}
		else 
		{
			return ' ';
		}
	}

	public void setMyMove(Position myMove) 
	{
		this.myMove = myMove;
	}

	public float getScore() 
	{
		return myScore;
	}

	public void setMyScore(float myScore) 
	{
		this.myScore = myScore;
	}

	public Position getMove() 
	{
		return myMove;
	}
	
	public boolean isEmpty(int x, int y) 
	{
		if (checkBounds(x, y)) 
		{
			if (board[x][y] == EMPTY) 
			{
				return true;
			}else 
			{
				return false;
			}
		}
		return false;
	}

	private boolean checkBounds(int x, int y) 
	{
		if (x >= SIZE || y >= SIZE) 
		{
			return false;
		} 
		else if (x < 0 || y < 0) 
		{
			return false;
		} 
		else 
		{
			return true;
		}
	}

	public float calculateScore() 
	{
		float score = 0;
        int countBot = 0, countHu = 0;
		for (int i = 0; i < SIZE; ++i) 
		{
			for (int j = 0; j < SIZE; ++j) 
			{
				char piece = board[i][j];
				if (piece == BOT) 
				{ 
					score += computePlrScore(piece, i, j);
                    countBot++;
				}
				else if (piece == HUMAN) 
				{ 
					score -= computePlrScore(piece, i, j);
                    countHu++;
				}
			}
		}
		return score;
	}

	public boolean checkWinCondition(char x, int y) 
	{
		int ix = (int)(Character.toUpperCase(x) - 65);
		return (checkHorizontal(ix, y) == true || checkVertical(ix, y) == true);
	}

	private float computePlrScore(char plr, int row, int col) 
	{
		float score = 0;
		int inARow = 1, inACol = 1, totalRow = 1, totalCol = 1;
		//Horizontal 
		if (row < SIZE - 3) 
		{
			for (int r = row + 1; r <= row + 3; ++r) 
			{
				char nextPiece = board[r][col];
				if (nextPiece == plr || nextPiece == EMPTY) 
				{
					if (nextPiece == plr) {
						inARow++;
						totalRow++;
					}
					else if(r != row + 3)
					{
						inARow = 0;
					}
				}
				else 
				{ 
					inARow = 0;
					totalRow = 0;
					break; 
				}
			}
		}
		//Vertical 
		if (col < SIZE - 3) 
		{
			for (int c = col + 1; c <= col + 3; ++c) 
			{
				char nextPiece = board[row][c];
				if (nextPiece == plr || nextPiece == EMPTY) 
				{
					if (nextPiece == plr) 
					{
						inACol++;
						totalCol++;
					}
					else if (c != col+3)
					{
						inACol = 0;
					}
				}
				else 
				{
					inACol = 0;
					totalCol = 0;
					break; 
				}
			}
		}
		if (inARow == 4 || inACol == 4) 
			score += 100000;
		if (inARow == 3) 
		{
			if (row - 1 >= 0 && board[row-1][col] == EMPTY) 
			{
				score += 10000;
			}
			else 
			{
				score += 1000;
			}
		} 
		else 
		{
            if (totalRow > 0) 
			{
                if (totalRow == 3) 
					score += 1000;
                else 
					score += totalRow * 10;
			}
                }
		if (inACol == 3) 
		{
			if (col - 1 >= 0 && board[row][col-1] == EMPTY) 
			{
				score += 10000;
			}
			else 
			{
				score += 1000;
			}
		}
		else 
		{
			if (totalCol > 0) 
			{
				if (totalCol == 3) 
					score += 1000;
				else 
					score += totalCol * 10;
			}
		}

		return score;
	}

	private boolean checkVertical(int x, int y) 
	{
		char piece = board[x][y];
		int start = y - 3;

		while (start < 0) 
		{
			start++;
		}
		int end = y + 3;

		while (end >= 8) 
		{
			end--;
		}
		int count = 0;

		for (int i = start; i <= end; ++i) 
		{
			if (board[x][i] == piece) 
			{
				count++;
			}
			else 
			{
				count = 0;
			}
			if (count == 4) 
			{
				return true;
			}
		}
		return false;
	}

	private boolean checkHorizontal(int x, int y) 
	{
		char piece = board[x][y];
		int start = x - 3;

		while (start < 0) 
		{
			start++;
		}
		int end = x + 3;

		while (end >= 8) 
		{
			end--;
		}
		int count = 0;

		for (int i = start; i <= end; ++i) 
		{
			if (board[i][y] == piece) 
			{
				count++;
			}
			else
			{
				count = 0;
			}
			if (count == 4) 
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isBoardFull()
	{
		for (int r = 0; r < board.length; r++) 
		{
			for (int c = 0; c < board.length; c++) 
			{
				if (board[r][c] == EMPTY) 
				{
					return false;
				}
			}
		}
		return true;
	}

	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < SIZE; ++i)
		{
			char label = 'A';
			if (i == 0)
			{
				s.append( " ");
				for (int j = 0; j < SIZE; j++) 
				{
					s.append( " ").append( j + 1);
				}
				s.append( "\n");
			}
			s.append( (char)(label + i)).append( " " );
			for(int j = 0; j < SIZE; ++j)
			{
				s.append(board[i][j]).append( " ");
			}
			s.append( "\n");
		}
		
		return s.toString();
	}

}
