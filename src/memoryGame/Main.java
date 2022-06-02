package memoryGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	static int boardArray[][];
	static boolean flippedArray[][];

	public static void main(String[] args) {
		// Introduction
		System.out.println("This is a memory game made in Java.");
		System.out.println("Enter in the number of first the row, then the column to flip a card.");

		// 2 arrays for memory game are 4 by 4, flipped and not flipped
		boardArray = new int[4][4];
		flippedArray = new boolean[4][4];

		//
		ArrayList<Integer> numbersUsed = new ArrayList<Integer>();
		for (int i = 0; i < (boardArray.length * boardArray[0].length) / 2; i++) {
			numbersUsed.add(i + 1);
		}

		int first = 0;
		int number = numbersUsed.get(numbersUsed.size() - 1);

		for (int i = 0; i < boardArray.length; i++) {
			for (int j = 0; j < boardArray[i].length; j++) {
				boardArray[i][j] = number;
				first += 1;
				if (first == 2 && numbersUsed.size() > 1) {
					numbersUsed.remove(numbersUsed.size() - 1);
					number = numbersUsed.get(numbersUsed.size() - 1);
					first = 0;
				}
			}
		}
		shuffle(boardArray);
		board();

		Scanner scanMachine = new Scanner(System.in);

		String ans = "";
		while (!(ans.startsWith("q") || ans.startsWith("Q"))) {
			int col = -1, row = -1, col2 = -1, row2 = -1;

			while ((row > flippedArray.length || row < 1) || (col > flippedArray.length || col < 1)) {
				try {
					System.out.println("Enter the first column you'd like to pick.");
					col = scanMachine.nextInt();
					System.out.println("Enter the first row you'd like to pick.");
					row = scanMachine.nextInt();
					
					flippedArray[row - 1][col - 1] = true;
					board();
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("You've selected numbers that aren't in the column or row!");
					System.out.println("Please enter a number between 1 and 4.");
					System.out.println("");
				}
			}

			while ((row2 > flippedArray.length || row2 < 1) || (col2 > flippedArray.length || col2 < 1)) {
				try {
					System.out.println("Enter the second column you'd like to pick.");
					col2 = scanMachine.nextInt();
					System.out.println("Enter the second row you'd like to pick");
					row2 = scanMachine.nextInt();

					flippedArray[row2 - 1][col2 - 1] = true;
					board();
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("You've selected numbers that aren't in the column or row!");
					System.out.println("Please enter a number between 1 and 4.");
					System.out.println("");
				}
			}

			if (boardArray[row - 1][col - 1] != boardArray[row2 - 1][col2 - 1]) {

				flippedArray[row - 1][col - 1] = false;
				flippedArray[row2 - 1][col2 - 1] = false;
			}
			System.out.println("Type any key then enter to continue, Q to quit.");
			ans = scanMachine.next();
			if ((ans.startsWith("q") || ans.startsWith("Q"))) {
				System.out.println("Thanks for playing!");
			}else
			board();
		}
	
	}

	public static void board() {
		System.out.println("     |  1  |  2  |  3  |  4  | ");
		for (int i = 0; i < boardArray.length; i++) {
			System.out.println("------------------------------");
			System.out.print("  " + (i + 1) + "  |");
			for (int j = 0; j < boardArray[i].length; j++) {
				if (flippedArray[i][j]) {
					System.out.print("  " + boardArray[i][j] + "  |");
				} else {
					System.out.print("  @  |");
				}
			}
			System.out.println();
		}

	}

	static void shuffle(int[][] a) {
		Random random = new Random();

		for (int i = a.length - 1; i > 0; i--) {
			for (int j = a[i].length - 1; j > 0; j--) {
				int m = random.nextInt(i + 1);
				int n = random.nextInt(j + 1);

				int temp = a[i][j];
				a[i][j] = a[m][n];
				a[m][n] = temp;
			}
		}
	}
}
