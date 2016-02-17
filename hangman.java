// Hangman

import java.util.Scanner;
import java.util.Random;
import java.io.*;

// WARNING: IF THIS COMMENT IS DELETED THE PROGRAM WONT COMPILE

// ITS NOT DRAWING THE HANGMAN

public class Hangman {
	public static void main(String[] args) throws IOException {			//wanna make it so it does try catch instead
		
		// Does the file setup
		File wordListFile = new File("words.txt");
		
		if (!wordListFile.exists()) {
			System.out.println("The list of words file can not be found.");
			System.exit(0);
		}
		
		// Setups most variables
		Scanner keyboard = new Scanner(System.in);
		Random generator = new Random();
		StringBuilder word = new StringBuilder();
		Scanner inputFile = new Scanner(wordListFile);
		String originalString = "";	
		char guess;
		int wordIndex = generator.nextInt(20000);
		StringBuilder hangman = new StringBuilder(" ---------" + 
												  "\n |       |" + 
												  "\n         |" +														// 23 and 25
												  "\n         |" +														// 34 and 36
												  "\n         |" +															// 46
												  "\n         |" +														// 56 and 57 and 58
												  "\n         |" +															// 69
												  "\n         |" +													   // 79 and 81
												  "\n_________|"); 			
		System.out.println(hangman);
		
		// Gets a random word from the file
		for (int c = 0; c < wordIndex - 1; c++) {					// what if wordIndex = 0 or 1?
			inputFile.nextLine();				
		}
		originalString += inputFile.nextLine();
		
		// Creates the string with _ for letters
		for (int c = 0; c < originalString.length(); c++)  {
				word.append( '_');
		}
		
		// Starts the real game
		System.out.println("Start guessing letters.\n" + word);
		guess = keyboard.next().charAt(0);
		test(guess, originalString, word, keyboard, hangman);
			//System.out.println("Congrats you guessed the word after " + tries + " tries.");
	}		
	
	public static void test(char guess, String originalString, StringBuilder word, Scanner keyboard, StringBuilder hangman) {
		
		boolean correct = false;
		int amountWrong = 0;
		
		// Does this while your word doesn't equal the real word
		do {
			
			// Checks if your guess is equal to a letter in the word
			for (int c = 0; c < originalString.length(); c++) {		
				if (guess == originalString.charAt(c)) 					
					word.setCharAt(c, guess);
					correct = true;
			}
			
			// Builds the body of the hangman
			if (correct == false) {
				amountWrong++;
				hangTheMan(amountWrong, hangman);
			}
			
			// Setup for another guess
			System.out.println(hangman);
			System.out.println("\n" + word + "\n");
			correct = false;
			guess = keyboard.next().charAt(0);
			
		} while(!(word.equals(originalString)));
	}
	
	public static void hangTheMan(int amountWrong, StringBuilder hangman) {
		
		switch (amountWrong) {
			case 1: 
				hangman.setCharAt(23, '/');
			case 2:
				hangman.setCharAt(25, '\\');
			case 3:
				hangman.setCharAt(34, '\\');
			case 4:
				hangman.setCharAt(36, '/');
			case 5:
				hangman.setCharAt(46, '|');
			case 6:
				hangman.setCharAt(56, '|');
			case 7:
				hangman.setCharAt(57, '\\');
			case 8:
				hangman.setCharAt(58, '/');
			case 9:
				hangman.setCharAt(69, '|');
			case 10:
				hangman.setCharAt(79, '/');
			case 11:
				hangman.setCharAt(81, '\\');
				System.out.println(hangman);
				System.out.println("You Lost.");
				System.exit(0);
		}
				
	}
}

/*
 			    _________
			    |	            |
               / \              | 
               \ /		        |
                |		        |
               \|/		        |
                |		        |
               / \  	        |
                ________|_
	
					10x9
*/							
