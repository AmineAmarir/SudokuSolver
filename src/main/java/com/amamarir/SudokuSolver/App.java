package com.amamarir.SudokuSolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Amine Amarir
 *
 */
public class App {
	private static int solution = 0;

	public static boolean check(int[][] sudoku, int n, int ligne, int colonne) {
		boolean exist = false;

		for (int x = 0; x < 9 && !exist; x++)
			if (sudoku[x][colonne] == n)
				exist = true;

		for (int y = 0; y < 9 && !exist; y++)
			if (sudoku[ligne][y] == n)
				exist = true;

		return !exist;
	}

	public static boolean EnCage(int[][] sudoku, int n, int ligne, int colonne) {
		boolean enCage = false;
		int cageX = ((ligne / 3) * 3);
		int cageY = ((colonne / 3) * 3);

		for (int y = 0; y < 3 && !enCage; y++)
			for (int x = 0; x < 3 && !enCage; x++)
				if (sudoku[cageX + x][cageY + y] == n)
					enCage = true;

		return enCage;
	}

	public static boolean sudokuComplete(int[][] sudoku) {
		boolean complete = true;

		for (int y = 0; y < 9 && complete; y++)
			for (int x = 0; x < 9 && complete; x++)
				if (sudoku[x][y] == 0)
					complete = false;

		return complete;
	}

	public static void compute_sudoku(int[][] table, int ligne, int colonne) {
		boolean entred = false;
		while (ligne < 9 && colonne < 9) {
			if (table[ligne][colonne] == 0) {
				for (int numero = 1; numero <= 9; numero++) {
					entred = true;
					if (!EnCage(table, numero, ligne, colonne) && check(table, numero, ligne, colonne)) {
						table[ligne][colonne] = numero;

						compute_sudoku(table, ligne, colonne);
					}
					table[ligne][colonne] = 0;
				}
			}
			if (entred) {
				return;

			} else if (ligne == 8) {
				ligne = 0;
				colonne++;
			} else
				ligne++;
		}
		if (sudokuComplete(table)) {
			solution += 1;
			imprimerSudoku(table);
		}
	}

	public static void imprimerSudoku(int[][] s) {
		int ligne, colonne;
		System.out.println();
		for (ligne = 0; ligne < 3; ligne++) {

			for (colonne = 0; colonne < 9; colonne++) {
				System.out.print(s[colonne][ligne] + " ");
				if (colonne == 2 || colonne == 5)
					System.out.print("| ");
			}
			System.out.print("| ");

			System.out.println();
		}
		System.out.println("----------------------");
		for (ligne = 3; ligne < 6; ligne++) {

			for (colonne = 0; colonne < 9; colonne++) {
				System.out.print(s[colonne][ligne] + " ");
				if (colonne == 2 || colonne == 5)
					System.out.print("| ");
			}
			System.out.print("| ");

			System.out.println();
		}
		System.out.println("----------------------");
		for (ligne = 6; ligne < 9; ligne++) {

			for (colonne = 0; colonne < 9; colonne++) {
				System.out.print(s[colonne][ligne] + " ");
				if (colonne == 2 || colonne == 5)
					System.out.print("| ");
			}
			System.out.print("| ");

			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("sudoku.ini"));
		int[][] sudoku = new int[9][9]; // [x].[y]

		for (int y = 0; y < 9; y++)
			for (int x = 0; x < 9; x++)
				sudoku[x][y] = scanner.nextInt();

		compute_sudoku(sudoku, 0, 0);
		System.out.print("Nombre de solutions trouvées : " + solution);

	}

}
