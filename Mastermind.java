
/*
* Mastermind.java
* Author: Joshua Smith
* Last edited: 10/03/2011 *
* Purpose: This program lets the user play the game "Mastermind." The game also takes
* a bet and produces a calculated balance post-game. *
* Statement of Academic Honesty: *
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. */

import java.util.Scanner;
import java.util.Random;

public class Mastermind {
	
	public static final double MIN_BET = 2.0;

	public static void main(String[] args) {
		
		//here is the testing mode, which, when set to true, displays the secret code
		boolean repeat, testingMode;
		testingMode = true; repeat = true;
		
		//this variable must be declared outside of the while loop in order to function correctly
		int gameNumber = 1;
		
		//the user initially has no money, but the money will transfer from previous rounds should the user play more than once
		double balance = 0;
		
		while (repeat)
		{
			Scanner keyboard = new Scanner(System.in);
			
			int secretCodeDigits, secretCode, maxGuesses, userGuess, userDigitOne, userDigitTwo, userDigitThree, userDigitFour,
				userDigitFive, secretDigitOne, secretDigitTwo, secretDigitThree, secretDigitFour, secretDigitFive, correctDigits,
				addOne, addTwo, addThree, addFour, addFive, guesses;
			addOne = 0; addTwo = 0; addThree = 0; addFour = 0; addFive = 0; maxGuesses = 0; correctDigits = 0;
			secretCode = 0; secretCodeDigits = 0; userGuess = 0;
			
			double wager, moneyEarned, moneyToPlay;
			moneyToPlay = 2.0; wager = 0;
			
			String yesOrNo, difficulty, winLoss;
			difficulty = ""; winLoss = "loss"; yesOrNo = "";
			
			
			//this loop is what makes the game repeat, if the user wants to play agian
			while (repeat)
			{
				//asking if the user wants to play and verifying the input
				if (gameNumber == 1)
				{
					System.out.print("Would you like to play Mastermind? (yes/no)? ");
					yesOrNo = keyboard.next();
					keyboard.nextLine();
				}
				else
				{
					System.out.println();
					System.out.print("Would you like to play again (yes/no)? "); 
					yesOrNo = keyboard.next();
					keyboard.nextLine();
					
				}
				
				if (yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no"))
				{
					if (yesOrNo.equalsIgnoreCase("yes"))
					{
						break;
					}
					else if (yesOrNo.equalsIgnoreCase("no"))
					{
						System.out.println("The game has terminated.");
						System.out.println("Bye. Come to play again!!");
						System.exit(0);
					}
				}
				else
				{
					System.out.print("Your input is invalid. Please enter a valid input: ");
				}
			}
			
			if (gameNumber == 1)
			{
				//asking how much money the user has and verifying that input
				System.out.print("Enter the amount of money you currently have: ");
				balance = keyboard.nextDouble();
				
				if (balance < moneyToPlay)
				{
					System.out.println("Sorry, you should have at least 2.0 dollars to play the game. Bye!");
					System.exit(0);
				}
			}
			
			//this code is essential to make the subsequent games print "play again" rather than just "play Mastermind"
			gameNumber = 2;
			
			//asking the user what difficulty they would like to play the game at and verifying the input
			while (repeat)
			{
				System.out.print("Please enter the level of difficulty (beginner, intermediate, advanced): ");
				difficulty = keyboard.next();
				if (difficulty.equalsIgnoreCase("beginner") || difficulty.equalsIgnoreCase("intermediate")
						|| difficulty.equalsIgnoreCase("advanced"))
				{
					if (difficulty.equalsIgnoreCase("beginner"))
					{
						difficulty = "beginner";
						maxGuesses = 30;
						secretCodeDigits = 3;
						break;
					}
					else if (difficulty.equalsIgnoreCase("intermediate"))
					{
						difficulty = "intermediate";
						maxGuesses = 20;
						secretCodeDigits = 4;
						break;
					}
					else if (difficulty.equalsIgnoreCase("advanced"))
					{
						difficulty = "advanced";
						maxGuesses = 10;
						secretCodeDigits = 5;
						break;
					}
				}
				else
				{
					System.out.println("Sorry, this is not a correct level.");
				}
			}
			
			//asking the user how much they want to bet and checking to see if the input is valid
			while (repeat)
			{
				System.out.print("Enter the amount of money you want to bet: ");
				wager = keyboard.nextDouble();
				if (wager < MIN_BET)
				{
					System.out.println("Sorry, the minimum amount of money is 2.00 dollars.");
				}
				else if (wager > balance)
				{
					System.out.println("Sorry, you cannot bet more money than what you have.");
				}
				else
				{
					break;
				}
			}
			
			//generating the secret code
			Random r = new Random();
			while (repeat)
			{
				if (difficulty.equals("beginner"))
				{
					secretCode = r.nextInt(999);
					if (secretCode > 99)
					{
						break;
					}
				}
				else if (difficulty.equals("intermediate"))
				{
					secretCode = r.nextInt(9999);
					if (secretCode > 999)
					{
						break;
					}
				}
				else if (difficulty.equals("advanced"))
				{
					secretCode = r.nextInt(99999);
					if (secretCode > 9999)
					{
						break;
					}
				}	
			}
			
			//
			if (testingMode = true)
			{
				System.out.println();
				System.out.println(" (Testing Mode - the " + secretCodeDigits + " digit code is: " + secretCode + ")");
				System.out.println();
			}
			
			//this is the code for the beginner level, followed by subsequent else if statements for the two higher levels of play
			if (difficulty.equals("beginner"))
			{
				//breaking up the secret code for comparison
				secretDigitThree = (secretCode % 10);
				secretCode = (secretCode / 10);
				secretDigitTwo = (secretCode % 10);
				secretCode = (secretCode /10);
				secretDigitOne = (secretCode % 10);
				
				for (guesses = 0;guesses <= 30;guesses++)
				{
					while (repeat)
					{
						System.out.print("Please guess the 3 digit number: ");
						userGuess = keyboard.nextInt();
						if ((userGuess < 100) || (userGuess > 999))
						{
							System.out.println("The guess you have entered is ill formed.");
						}
						else
						{
							break;
						}
					}
					
					//breaking up the user's guess to compare to the secret code
					userDigitThree = (userGuess % 10);
					userGuess = (userGuess / 10);
					userDigitTwo = (userGuess % 10);
					userGuess = (userGuess  /10);
					userDigitOne = (userGuess % 10);
					
					correctDigits = 0;
					addOne = 0; addTwo = 0; addThree = 0; addFour = 0; addFive = 0;
					
					if (secretDigitOne == userDigitOne)
					{
						correctDigits += 1;
						addOne = userDigitOne;
					}
					if (secretDigitTwo == userDigitTwo)
					{
						correctDigits += 1;
						addTwo = userDigitTwo;
					}
					if (secretDigitThree == userDigitThree)
					{
						correctDigits += 1;
						addThree = userDigitThree;
					}
					
					if ((secretDigitOne == userDigitOne) && (secretDigitTwo == userDigitTwo) && (secretDigitThree == userDigitThree))
					{
						winLoss = "win";
						moneyEarned = (wager * secretCodeDigits * (maxGuesses - guesses)) / maxGuesses;
						System.out.println(balance);
						balance = balance + moneyEarned;
						System.out.println();
						System.out.println("	You won!!!");
						System.out.println("	You have had " + guesses + " wrong guesses. You have earned " + moneyEarned + " dollars.");
						System.out.println("	Your balance is now " + (balance) + " dollars.");
						break;
					}
					
					System.out.println("Number of correct digits: " + correctDigits);
					System.out.println("Sum: " + (addOne + addTwo + addThree + addFour + addFive));
				}
				
				if (winLoss == "loss")
				{
					System.out.println();
					System.out.println("	Sorry, you lost!!!");
					System.out.println("	You have guessed 30 times.");
					System.out.println("	The code was: " + secretCode);
					System.out.println("	You have lost " + moneyToPlay + " dollars.");
					balance = balance - wager;
					System.out.println("	Your balance is now: " + balance);
				}
			}
			else if (difficulty.equals("intermediate"))
			{
				//breaking up the secret code for comparison
				secretDigitFour = (secretCode % 10);
				secretCode = (secretCode / 10);
				secretDigitThree = (secretCode % 10);
				secretCode = (secretCode / 10);
				secretDigitTwo = (secretCode % 10);
				secretCode = (secretCode /10);
				secretDigitOne = (secretCode % 10);
				
				for (guesses = 0;guesses <= 20;guesses++)
				{
					while (repeat)
					{
						System.out.print("Please guess the 4 digit number: ");
						userGuess = keyboard.nextInt();
						if ((userGuess < 1000) || (userGuess > 9999))
						{
							System.out.println("The guess you have entered is ill formed.");
						}
						else
						{
							break;
						}
					}
					
					//breaking up the user's guess to compare to the secret code
					userDigitFour = (userGuess % 10);
					userGuess = (userGuess / 10);
					userDigitThree = (userGuess % 10);
					userGuess = (userGuess / 10);
					userDigitTwo = (userGuess % 10);
					userGuess = (userGuess  /10);
					userDigitOne = (userGuess % 10);
					
					correctDigits = 0;
					addOne = 0; addTwo = 0; addThree = 0; addFour = 0; addFive = 0;
					
					if (secretDigitOne == userDigitOne)
					{
						correctDigits += 1;
						addOne = userDigitOne;
					}
					if (secretDigitTwo == userDigitTwo)
					{
						correctDigits += 1;
						addTwo = userDigitTwo;
					}
					if (secretDigitThree == userDigitThree)
					{
						correctDigits += 1;
						addThree = userDigitThree;
					}
					if (secretDigitFour == userDigitFour)
					{
						correctDigits += 1;
						addFour = userDigitFour;
					}
					
					if ((secretDigitOne == userDigitOne) && (secretDigitTwo == userDigitTwo) && (secretDigitThree == userDigitThree) &&
							(secretDigitFour == userDigitFour))
					{
						winLoss = "win";
						moneyEarned = (wager * secretCodeDigits * (maxGuesses - guesses)) / maxGuesses;
						balance = balance + moneyEarned;
						System.out.println();
						System.out.println("	You won!!!");
						System.out.println("	You have had " + guesses + " wrong guesses. You have earned " + moneyEarned + " dollars.");
						System.out.println("	Your balance is now " + (balance) + " dollars.");
						break;
					}
					
					System.out.println("Number of correct digits: " + correctDigits);
					System.out.println("Sum: " + (addOne + addTwo + addThree + addFour + addFive));
				}
				
				if (winLoss == "loss")
				{
					System.out.println();
					System.out.println("	Sorry, you lost!!!");
					System.out.println("	You have guessed 20 times.");
					System.out.println("	The code was: " + secretCode);
					System.out.println("	You have lost " + moneyToPlay + " dollars.");
					balance = balance - wager;
					System.out.println("	Your balance is now: " + balance);
				}
			}
			else if (difficulty.equals("advanced"))
			{
				//breaking up the secret code for comparison
				secretDigitFive = (secretCode % 10);
				secretCode = (secretCode /10);
				secretDigitFour = (secretCode % 10);
				secretCode = (secretCode / 10);
				secretDigitThree = (secretCode % 10);
				secretCode = (secretCode / 10);
				secretDigitTwo = (secretCode % 10);
				secretCode = (secretCode /10);
				secretDigitOne = (secretCode % 10);
				
				for (guesses = 0;guesses <= 10;guesses++)
				{
					while (repeat)
					{
						System.out.print("Please guess the 5 digit number: ");
						userGuess = keyboard.nextInt();
						if ((userGuess < 10000) || (userGuess > 99999))
						{
							System.out.println("The guess you have entered is ill formed.");
						}
						else
						{
							break;
						}
					}
					
					//breaking up the user's guess to compare to the secret code
					userDigitFive = (userGuess % 10);
					userGuess = (userGuess / 10);
					userDigitFour = (userGuess % 10);
					userGuess = (userGuess / 10);
					userDigitThree = (userGuess % 10);
					userGuess = (userGuess / 10);
					userDigitTwo = (userGuess % 10);
					userGuess = (userGuess  /10);
					userDigitOne = (userGuess % 10);
					
					correctDigits = 0;
					addOne = 0; addTwo = 0; addThree = 0; addFour = 0; addFive = 0;
					
					if (secretDigitOne == userDigitOne)
					{
						correctDigits += 1;
						addOne = userDigitOne;
					}
					if (secretDigitTwo == userDigitTwo)
					{
						correctDigits += 1;
						addTwo = userDigitTwo;
					}
					if (secretDigitThree == userDigitThree)
					{
						correctDigits += 1;
						addThree = userDigitThree;
					}
					if (secretDigitFour == userDigitFour)
					{
						correctDigits += 1;
						addFour = userDigitFour;
					}
					if (secretDigitFive == userDigitFive)
					{
						correctDigits += 1;
						addFive = userDigitFive;
					}
					
					if ((secretDigitOne == userDigitOne) && (secretDigitTwo == userDigitTwo) && (secretDigitThree == userDigitThree) &&
							(secretDigitFour == userDigitFour) && (secretDigitFive == userDigitFive))
					{
						winLoss = "win";
						moneyEarned = (wager * secretCodeDigits * (maxGuesses - guesses)) / maxGuesses;
						balance = balance + moneyEarned;
						System.out.println();
						System.out.println("	You won!!!");
						System.out.println("	You have had " + guesses + " wrong guesses. You have earned " + moneyEarned + " dollars.");
						System.out.println("	Your balance is now " + (balance) + " dollars.");
						break;
					}
					
					System.out.println("Number of correct digits: " + correctDigits);
					System.out.println("Sum: " + (addOne + addTwo + addThree + addFour + addFive));
				}
				
				if (winLoss == "loss")
				{
					System.out.println();
					System.out.println("	Sorry, you lost!!!");
					System.out.println("	You have guessed 10 times.");
					System.out.println("	The code was: " + secretCode);
					System.out.println("	You have lost " + moneyToPlay + " dollars.");
					balance = balance - wager;
					System.out.println("	Your balance is now: " + balance);
				}
			}
					
		}
	}

}
