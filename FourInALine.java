/*
	Christopher Rivas
	CS 420
	Project 3: 4-In-A-Line
	03/06/2018
*/
public class FourInALine 
{
	final char CPU = 'X';	
	final char HUMAN = 'O';
	char first;
	int thinkTime;
	UserInterface ui;
	Board game;
	AI ai;

	public static void main(String[] args) throws InterruptedException
	{
		new FourInALine();
	}

	public FourInALine() throws InterruptedException
	{
		ui = new UserInterface();
		game = new Board();
		ai = new AI();
		start();
	}

	private void start() throws InterruptedException
	{
		
		char result = ui.welcome();

		if (result == 'y')
			first = HUMAN;
		else
			first = CPU;
		thinkTime = ui.thinkTime();
		gameloop();
		ui.exit();
	}

	private void gameloop() throws InterruptedException
	{
        int turn = 1;
		while (true) 
		{
			System.out.println(game.toString());
			
			if (CPU == first) 
			{
				
                boolean cpuWin = botTakeTurn(CPU);
				if (cpuWin) break;
				
				boolean plrWin = plrTakeTurn(HUMAN);
				if (plrWin) break;
                                
                System.out.println("Turn " + turn + " Score:" + game.calculateScore());
                turn++;
			} 
			else 
			{
				// Player 1 is Human
				boolean plrWin = plrTakeTurn(HUMAN);
				if (plrWin) break;
				
				// Player 2 is Bot
                boolean cpuWin = botTakeTurn(CPU);
				if (cpuWin) break;
                
                System.out.println("Turn " + turn + " Score:" + game.calculateScore());
                turn++;
			}
		}

	}

	private boolean plrTakeTurn(char player) throws InterruptedException
	{
		Position pos = ui.enterPosition();
		
		while (!game.setPiece(pos.getX(), pos.getY()-1, player)) 
		{
			System.out.println("Not a legal move!\n");
			pos = ui.enterPosition();
		}
		System.out.println(game);
        System.out.println("Score: " + game.calculateScore());
		if (game.checkWinCondition(pos.getX(), pos.getY()-1)) 
		{
			System.out.println("Player wins the game!");
			return true;
		}
		return false;
	}
	
	private boolean botTakeTurn(char player) throws InterruptedException
	{
		Position pos = ai.search(game);
        Thread.sleep(thinkTime*1000);
        System.out.println(pos.getX() + "" + pos.getY());
		game.setPiece(pos.getX(), pos.getY(), player);
		System.out.println(game);
        System.out.println("Score: " + game.calculateScore());

		if(game.checkWinCondition(pos.getX(), pos.getY()))
		{
			System.out.println("Computer wins the game!");
			return true;
		}
		return false;
	}

}
