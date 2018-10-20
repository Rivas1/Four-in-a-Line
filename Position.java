/*
	Christopher Rivas
	CS 420
	Project 3: 4-In-A-Line
	03/06/2018
*/
public class Position 
{
	private char x = 'A';
	private int y = 0;

	public Position(char x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public char getX() 
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}
	
	@Override
	public String toString()
	{
        return "(" + x + "," + y + ")";
    }
}
