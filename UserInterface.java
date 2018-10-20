/*
	Christopher Rivas
	CS 420
	Project 3: 4-In-A-Line
	03/06/2018
*/
import java.util.Scanner;

public class UserInterface 
{
	private Scanner sc = new Scanner(System.in);

	public char welcome() 
	{
		char answer = 'm';
		System.out.println("Welcome to Christopher's 4-In-A-Line\nWould you like to go first?(y/n) ");
		answer = sc.nextLine().toLowerCase().charAt(0);
		while (answer != 'y' && answer != 'n') 
		{
			System.out.println("Invalid Input!\n Would you like to go first?(y/n)");
			answer = sc.nextLine().toLowerCase().charAt(0);
		}
		return answer;
	}

	public Position enterPosition() 
	{
		String input;
		System.out.println("Choose your next move[a-h][1-8]: ");
		input = sc.nextLine();
		while (!input.matches("[a-hA-H][1-8]")) 
		{
			System.out.println("Not a legal move!\\nChoose your next move[a-h][1-8]: ");
			input = sc.nextLine();
		}
		
		return new Position(input.charAt(0), Integer.parseInt(input.substring(1, 2)));
	}

	public int thinkTime() 
	{
		int answer = 30;
		System.out.println("Enter the amount of time in seconds for the bot to think: ");
		answer = Integer.parseInt(sc.nextLine());
		while (answer > 30) 
		{
			System.out.println("Time may NOT be greater than 30 seconds!\\nEnter the amount of time in seconds for the bot to think[1-30]: ");
			answer = Integer.parseInt(sc.nextLine());
		}
		
		return answer;
	}
	
	public void exit()
	{
		System.out.println("Press [Enter] to exit . . . ");
		sc.nextLine();
	}

}
