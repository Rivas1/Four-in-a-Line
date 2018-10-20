/*
	Christopher Rivas
	CS 420
	Project 3: 4-In-A-Line
	03/06/2018
*/
public class AI 
{
	
	private float alpha;
	private float beta;
	private boolean timerFlag;
	private Position currMove;

	public Position search(Board current) 
	{
		int depth = 1;
		float v;
		
		while (depth <= 4) 
		{
			v = MaxValue(current, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, depth);
			if (v >= 5000) 	
				return currMove;
			depth++;
		}
                
		timerFlag = false;
        int counter = 0;
        while(!current.isEmpty((int)(currMove.getX()-65),currMove.getY()))
		{
            currMove = new Position((char)(counter+65), counter);
            counter++;
		}
		return currMove;
	}
        
	private float MaxValue(Board currBoard, float alpha, float beta, int depth) 
	{
		float score = currBoard.calculateScore();
		if (depth <= 0 || score >= 15000 || currBoard.isBoardFull())
		{
			currMove = currBoard.getMove();
			return evaluate(currBoard);
		}
		float v = Float.NEGATIVE_INFINITY;
		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			{
				Board temp = new Board(currBoard);
				if (temp.isEmpty(i,j)) 
				{
					temp.setPiece(i, j, 'X');
					temp.setMyMove(new Position((char)(i+65), j));
					v = Math.max(v, MinValue(temp, alpha, beta, depth - 1));
					if (v >= beta) 
					{
                        currMove = currBoard.getMove();
						return v;
					}
					else 
					{
						alpha = Math.max(alpha, v);
					}
				}
			}
		}
		return v;
	}

	private float MinValue(Board currBoard, float alpha, float beta, int depth) 
	{
		float score = currBoard.calculateScore();
		if (depth <= 0 ||  score <= -15000 || currBoard.isBoardFull())
		{
			currMove = currBoard.getMove();
			return evaluate(currBoard);
		}
		float v = Float.POSITIVE_INFINITY;
		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			{
				Board temp = new Board(currBoard);
				if (temp.isEmpty(i,j)) 
				{
					temp.setPiece(i, j, 'O');
					temp.setMyMove(new Position((char) (i+65), j));
					v = Math.min(v, MaxValue(temp, alpha, beta, depth - 1));
					if (v <= alpha) 
					{
						return v;
					}
					else 
					{
						beta = Math.min(beta, v);
					}
				}
			}
		}
		return v;
	}

	private float evaluate(Board currBoard) 
	{
		return currBoard.calculateScore();
	}

}
