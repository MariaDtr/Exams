package Exams;

import java.util.Scanner;

public class P02_DeliveryBoy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] tokens = scanner.nextLine().split(" ");

        int row = Integer.parseInt(tokens[0]);
        int col = Integer.parseInt(tokens[1]);

        int startRow = 0;
        int startCol = 0;

        char road [][] = new char[row][col];

        for (int rows = 0; rows < row; rows++) {
            String input = scanner.nextLine();
            for (int cols = 0; cols < col; cols++) {
                char cell = input.charAt(cols);
                road[rows][cols] = cell;
                if (input.charAt(cols) == 'B'){
                    startRow = rows;
                    startCol = cols;
                }
            }
        }
        int currentRow = startRow;
        int currentCol = startCol;
        while (true){

            String command = scanner.nextLine();
            switch (command){
                case "up":
                    currentRow--;
                    break;
                case "down":
                    currentRow++;
                    break;
                case "right":
                    currentCol++;
                    break;
                case "left":
                    currentCol--;
                    break;
            }
            if (outOfBoundaries(currentRow, currentCol, row, col)){
                System.out.println("The delivery is late. Order is canceled.");
                road[startRow][startCol] = ' ';
                break;
            }
            char currentChar = road[currentRow][currentCol];
            if (currentChar == '-'){
                road[currentRow][currentCol] = '.';
            } else if (currentChar == 'A') {
                road[currentRow][currentCol] = 'P';
                System.out.println("Pizza is delivered on time! Next order...");
                road[startRow][startCol] = 'B';
                break;
            }else if (currentChar == 'P') {
                road[currentRow][currentCol] = 'R';
                System.out.println("Pizza is collected. 10 minutes for delivery.");

            } else if (currentChar == '*') {
                switch (command){
                    case "up":
                        currentRow++;
                        break;
                    case "down":
                        currentRow--;
                        break;
                    case "right":
                        currentCol--;
                        break;
                    case "left":
                        currentCol++;
                        break;
                }
            }
        }

        for (int rows = 0; rows < row; rows++) {
            for (int cols = 0; cols < col; cols++) {
                System.out.print(road[rows][cols]);
            }
            System.out.println();
        }
    }
    public static boolean outOfBoundaries (int currentRow, int currentCol, int row, int col){
        return currentRow < 0 || currentRow >= row || currentCol < 0 || currentCol >= col;
    }
}
