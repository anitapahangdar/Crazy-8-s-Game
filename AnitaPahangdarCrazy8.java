/*
 * Name: Anita Pahangdar
 * Start date may 14th
 * Finish date may 31st
 * Teacher: Mr.Chu
 * Description: Crazy 8's game, where the two player get to play their cards based on the instruction untill someone runs out of cards and wins the game!
 * the game will either make an account for you or log you in the existing account that you have if the password is right
 * after that instructions are printed and cards are handed out 
 * computer will play first and then user can play
 * this pattern goes on until one of them run out of card and that player is the winner
 * user will be updated on the number of times that they have won and if they wan to play again or not
 */

import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class AnitaPahangdarCrazy8 {
	public static void shuffleDeck(String[] array) {
		//generating a random number
		Random rnd = new Random();
		for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Swap elements at i and index
            String temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
	}
	public static int checkRankandSuit(String[] comHand, String mainCard) {
		//separating the elements needed to be compared 
		char suitMain = mainCard.charAt(mainCard.length() - 2);
		char rankMain = mainCard.charAt(1);
		int index = -1;
		//checking all the elements and comparing then 
		for(int i = 0; i < comHand.length; i++) {
			String cardToCompare = comHand[i];
			char comRank = cardToCompare.charAt(1);
			char comSuit = cardToCompare.charAt(cardToCompare.length() - 2);
			//if it matches breaking out the loop
			if(comRank == rankMain || comSuit == suitMain) {
				index = i;
				break;
			}
		}
		return index;
	}
	public static int checkFor8(String[] comHand) {
		int index = -1;
		//checking for the cards that have a rank of 8
		for(int i = 0; i < comHand.length; i++) {
			String cardToCompare = comHand[i];
			char comRank = cardToCompare.charAt(1);
			if(comRank == '8') {
				index = i;
				break;
			}
		}
		return index;
	}
	public static boolean choiceValidation(String[] userHand, int cardIndPlay, String mainCard) {
		//separating the elements needed to be compared 
		char suitMain = mainCard.charAt(mainCard.length() - 2);
		char rankMain = mainCard.charAt(1);
		String card = userHand[cardIndPlay-1];
		char suitCheck = card.charAt(card.length() - 2);
		char rankCheck = card.charAt(1);
		//checking to see if the card can be played
		if (suitMain == suitCheck || rankMain == rankCheck || rankCheck == '8') {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean userLoginValid(File file, String username, String password) throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            // Check if current line matches username
            if (line.equals(username)) {
                // Read next line (password)
                String storedPassword = reader.readLine();
                //close the reader
                reader.close();
                // Compare stored password with entered password
                return storedPassword != null && storedPassword.equals(password);
            }
        }
        reader.close();
        return false;
	}
	public static void shiftUserHand(String[] userHand, int numCardUser) {
		int index = -1;
		
		//find the index of the " - "
		for(int i = 0; i <= numCardUser; i++) {
			if(userHand[i].equals(" - ")) {
				index = i;
				break;
			}
		}
		
		//shifting the cards to left
		if (index != -1) {
			for(int i = index; i < numCardUser; i++) {
				userHand[i] = userHand[i + 1];
			}
			//set the last element to " - "
			userHand[numCardUser - 1] = " - ";
		}
	}
	public static void optionsToPlay(String[] userHand, int numCardUser) {
		for(int i = 1; i <= numCardUser; i++) {
			if(i ==  numCardUser) {
				System.out.print(i);
			}
			else {
				System.out.print(i + ",");
			}
		}
		System.out.print("): ");
	}
	public static void userHandPrint(String[] userHand, int numCardUser) {
		System.out.println("Your hand: ");
		for(int i = 0; i < numCardUser; i++) {
			if(userHand[i] != " - ") {
			System.out.print(userHand[i] + " ");
			}
		}
		System.out.println();
		for(int i = 1; i <= numCardUser; i++) {
			System.out.print(" (" + i + ")  ");
		}
	}
	public static void discardPile(String mainCard) {
		//repetitive format for discard pile
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Current discard pile: " + mainCard);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}
	public static int timesWonUser(File file, String username) throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            //check if current line matches username
            if (line.equals(username)) {
                //skip the line that is the password 
            	reader.readLine();
            	
            	//read the line that is the number
                String store = reader.readLine();
                
                //changing it into integer
                int timesWon = Integer.parseInt(store);
                
                //close the reader
                reader.close();
                return timesWon;
            }
        }
        reader.close();
        return 0;
	}

	public static void main(String[] args) throws Exception {
		//declaring the scanner
		Scanner sc = new Scanner(System.in);
		//Create the local account data file on first run. It is excluded from Git.
		File file = new File("information.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		 
		//declaring the arrays for the deck of cards, computer's hand and user's hand 
		String[] deck = new String[52];
		String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		String[] suits = {"♣", "♦", "♥", "♠"};
		String[] comHand = new String[52];
		String[] userHand = new String[52];
		int counterIndx = 0; //to track the index of the deck
		
		//array to randomize the messages that will print after user wins
		String[] messages = {
				"Congratulations! You win!",
				"Well done! You've won the game! Keep up the great work!",
				"Victory is yours! Amazing job!",
				"\"You outsmarted the computer! Fantastic win!"
		};
		
		//initializing a variable to keep track of the times the user has won 
		int numWins = 0;
		
		//initializing the array and adding the cards to it using for loop
		for (int i = 0; i < ranks.length; i++) { //going through all the ranks
			String r = ranks[i]; 
			for(int j = 0; j < suits.length; j++) { //going through all suit for each rank 
				String s = suits[j];
				
				//adding the combination to the deck and updating the index counter
				deck[counterIndx] = "[" + r + " " + s + "]";
				counterIndx++;
			}
		}

		//using the method to shuffle the array
		shuffleDeck(deck);
		
		//reseting the counterIndx variable to zero
		counterIndx = 0;
		
		//initializing/handing out cards to computer 
		for(int i = 0; i < 7; i++) {
			comHand[i] = deck[counterIndx];
			counterIndx++; 
		}
		//filling the rest of the array with empty spacesinstead of nul
		for(int i = 7; i < 52; i++) {
			comHand[i] = " - "; 
		}
		
		//initializing/handing out cards to the user
		for(int i = 0; i < 7; i++) {
			userHand[i] = deck[counterIndx];
			counterIndx++;
		}
	
		//using two counetr variables to keep track of the number of cards in each hand
		int numCardCom = 7;
		int numCardUser = 7;
		
		//declaring and initializing the card that is now on the top of the deck 
		String mainCard = deck[counterIndx];
		counterIndx++;
		
		//printing the instruction 
		System.out.println("==================================");
		System.out.println("            Crazy 8's");
		System.out.println("==================================");
		System.out.println("WELCOME!!");
		
		//asking if the user has an account or they have to make an account
		System.out.print("Do you have an acocunt?(y/n) ");
		String account = sc.nextLine();
		
		//Keep prompting until the user enters a supported option.
		while (!account.equalsIgnoreCase("y") && !account.equalsIgnoreCase("n")) {
			System.out.println("Invalid input. Please try again.");
			System.out.print("Do you have an account? (y/n): ");
			account = sc.nextLine().trim();
		}
			
		//login if they already have an account
		if(account.equalsIgnoreCase("y")) {
			//getting the username and password 
			System.out.println("↪ Please enter username and password to log into your account ");
			System.out.print("Username(testrun): ");
			String username = sc.nextLine();
			System.out.print("Password(123456): ");
			String password = sc.nextLine();
			
			//checking for the usrename matching the password 
			boolean authentication = userLoginValid(file, username, password);
			while (!authentication) {
				System.out.println("Invalid username or password. Please try again");
				System.out.print("Username(testrun): ");
				username = sc.nextLine();
				System.out.print("Password(123456): ");
				password = sc.nextLine();
				authentication = userLoginValid(file, username, password);
			}
			
			//storing the number of times user has won
			numWins = timesWonUser(file, username);
			
		}
		//meaking an account for the user
		else { 
			System.out.println("Let's make an account for you!");
			
			// Input validation loop for username
            String username;
            do {
                System.out.print("Username (letters only): ");
                username = sc.nextLine();
                if (!username.matches("[a-zA-Z]+")) {
                    System.out.println("Invalid input. Username must contain only letters.");
                }
            } while (!username.matches("[a-zA-Z]+"));

            // Input validation loop for a six-character alphanumeric password.
            String password;
            do {
                System.out.print("Password (exactly 6 letters or digits): ");
                password = sc.nextLine();
                if (!password.matches("[a-zA-Z0-9]{6}")) {
                    System.out.println("Invalid input. Password must contain exactly 6 letters or digits.");
                }
            } while (!password.matches("[a-zA-Z0-9]{6}"));
            
            //adding the username and password to the file
			PrintWriter filePW = new PrintWriter(new FileWriter(file, true));
			filePW.println();
            filePW.println(username);
            filePW.println(password);
            filePW.println(0);
            filePW.close();
            
            System.out.println("Account created successfully!");
		}
		
		
		//Printing instruction
		System.out.println(); 
		System.out.println("You are logged-in!");
		System.out.println("___________________");
		System.out.println();
		System.out.println("Instructions:");
		System.out.println("- The goal of the game is to be the first player to get rid of all your cards");
		System.out.println("- You can play a card if it matches the suit or rank of the top card in the discard pile");
		System.out.println("- If you don't have a playable card, you must draw from the deck until you get one");
		System.out.println("- The game ends when a player runs out of cards");
		System.out.println();
		
		
		//printing user's hand
		userHandPrint(userHand, numCardUser);
		
		discardPile(mainCard);
		
		while(numCardCom > 0 && numCardUser > 0) {
			System.out.println("Computer's turn");
			
			//using the methods to check for the cards matching 
			int indexMatchMain = checkRankandSuit(comHand, mainCard);
			int indexMatch8 = checkFor8(comHand);
			
			if(indexMatchMain != -1) {
				System.out.println("→ Player #2 played a card");
				mainCard = comHand[indexMatchMain]; //updating the new card on top 
				comHand[indexMatchMain] = " - "; //removing the played card 
				numCardCom--;
			}
			else if(indexMatch8 != -1) {
				System.out.println("→ Player #2 played a card");
				mainCard = comHand[indexMatch8]; //updating the new card on top 
				comHand[indexMatch8] = " - "; //removing the played card 
				numCardCom--;
			}
			else{
				System.out.println("→ Player #2 drew a card from pile");
				
				//adding the new card to computer's hand
				comHand[numCardCom] = deck[counterIndx];
				numCardCom++;
				counterIndx++;
			}
			
			//printing the main card and jumping out the loops 
			discardPile(mainCard);
			
			//printing information - getting user's decision on what they want to play
			System.out.println("Your turn to play");
			System.out.println("Options:");
			System.out.println("1. Play a card");
			System.out.println("2. Draw from the deck");
			System.out.println();
			System.out.print("Your choice(1/2): ");
			int choice = 0;
			
			//checking for the user's input validtion
			while (sc.hasNext()) {
	            if (sc.hasNextInt()) {
	                choice = sc.nextInt();
	                if (choice == 1 || choice == 2) {
	                    break; // Valid choice exit the loop
	                } else {
	                    System.out.println("Invalid input. Please try again.");
	                }
	            } else {
	                System.out.println("Invalid input. Please enter a number (1 or 2).");
	                sc.next(); // Consume the invalid input
	            }
	            System.out.print("Your choice (1/2): ");
	        }
			
			if(choice == 1) {
				System.out.print("Choose the card you want to play(");
				optionsToPlay(userHand, numCardUser);
				int cardIndPlay = sc.nextInt();
				
				//checking for the user's input validtion
				while (cardIndPlay > numCardUser || cardIndPlay <= 0 ) {
					System.out.println("Invalid input. Please try again, you have to choose from the cards you have.");
					System.out.print("Choose the card you want to play(");
					optionsToPlay(userHand, numCardUser);
					if (sc.hasNextInt()) {
			            cardIndPlay = sc.nextInt();
			        } 
					else {
			            sc.next(); // Consume the invalid input
			        }

				}
				
				//checking for the user's input validtion
				while(!choiceValidation(userHand, cardIndPlay, mainCard)) {
					System.out.println(userHand[cardIndPlay-1]);
					System.out.println("You can't play this card! The rank/suit doesn't match the card on the discard pile ");
					System.out.print("Choose the card you want to play(");
					optionsToPlay(userHand, numCardUser);
					if (sc.hasNextInt()) {
			            cardIndPlay = sc.nextInt();
			        } 
					else {
			            sc.next(); // Consume the invalid input
			        }
				}
				
				System.out.println("→ Player #1 played the card " + userHand[cardIndPlay-1]);
				System.out.println();
				mainCard = userHand[cardIndPlay-1];
				userHand[cardIndPlay-1] = " - ";
				shiftUserHand(userHand, numCardUser);
				numCardUser--;
			}
			
			else {
				System.out.println("→ Player #1 drew a card from pile");
				System.out.println();
				userHand[numCardUser] = deck[counterIndx];
				numCardUser++;
				counterIndx++;
				
			}
			
			//printing the user's hand and updating the discard pile
			userHandPrint(userHand, numCardUser);
			discardPile(mainCard);
		}
		
		//if the comptuer is out of cards
		if (numCardCom == 0 ) {
			System.out.println("The computer wins this round!!");
			System.out.println("Great effort! Try again to beat the computer!");
			System.out.println();
		}
		
		//if the user is out of cards
		if (numCardUser == 0 ) {
			//generating a random number
			Random rnd = new Random();
			int index = rnd.nextInt(messages.length);
			
			//printing a random message from the array
	        System.out.println(messages[index]);
			System.out.println();
			
			//updating the value of the number of times they have won
			numWins++;
		}
		
		//updating the user on how many times they've won
		System.out.println("You have total number of " + numWins + " until now!");
		
		//asking if they want to play again or not 
		sc.nextLine(); // consume the newline left by numeric card input
		System.out.print("Do you want to play again? (y/n): ");
		String playAgain = sc.nextLine().trim();
		while (!playAgain.equalsIgnoreCase("y") && !playAgain.equalsIgnoreCase("n")) {
			System.out.println("Invalid input. Please try again.");
			System.out.print("Do you want to play again? (y/n): ");
			playAgain = sc.nextLine().trim();
		}
		
		//printing instruction
		if(playAgain.equalsIgnoreCase("y")) {
			System.out.println("You can play again by running the game again!");
			System.out.println("We will keep track of the times you've won");
		}
		else {
			System.out.println("Thanks for playing! See you the next time.");
		}
		
		
		//closing scanner
		sc.close();
		
	}
}
