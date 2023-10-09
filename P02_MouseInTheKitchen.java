package Exams;

import java.util.Arrays;
import java.util.Scanner;

public class P02_MouseInTheKitchen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] tokens = scanner.nextLine().split(",");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);

        int cheeseCount = 0; //знаем колко са сиренцата
        int rowMouse = 0; // знаем първоначалното местоположение на мишката и ще променяме данните след всяко следващо движение
        int colMouse = 0;

        char [][] cupboard = new char[N][M];
        for (int i = 0; i < cupboard.length; i++) {
            String newRow = scanner.nextLine();
            for (int j = 0; j < M; j++) {
                char cell = newRow.charAt(j);
                cupboard[i][j] = cell;
                if (cell == 'M'){
                    rowMouse = i;
                    colMouse = j;
                } else if (cell == 'C') {
                    cheeseCount ++;
                }
            }

        }
        boolean dangerCommandReceived = false;
        boolean allCheeseEaten = false;
        boolean isTrapped = false;

          while (true){
            String command = scanner.nextLine();

            if (command.equals("danger")){
                dangerCommandReceived = true;
                break;
            }
            int newRow = rowMouse;
            int newCol = colMouse;

            switch (command){
                case "up":
                    newRow--;
                    break;
                case "down":
                    newRow++;
                    break;
                case "left":
                    newCol--;
                    break;
                case "right":
                    newCol++;
                    break;
            }
            if (isOutOfBounds(newRow, newCol, N, M)){
                System.out.println("No more cheese for tonight!");
                break;
            }

            char newElement = cupboard[newRow][newCol];
            if (newElement == '@'){
                continue;
            } else if (newElement == 'C') {
                cheeseCount --;
                cupboard[newRow][newCol] = '*';

            } else if (newElement == 'T') {
                isTrapped = true;
                cupboard[rowMouse][colMouse] = '*';
                cupboard[newRow][newCol] = 'M';
                rowMouse = newRow;
                colMouse = newCol;
                break;

            }

              cupboard[rowMouse][colMouse] = '*';
              cupboard[newRow][newCol] = 'M';
              rowMouse = newRow;
              colMouse = newCol;

              if (cheeseCount == 0){
                  allCheeseEaten = true;
                  break;
              }

          }
        if (allCheeseEaten){
            System.out.println("Happy mouse! All the cheese is eaten, good night!");
        } else if (isTrapped){
            System.out.println("Mouse is trapped!");

        } else if (dangerCommandReceived){
            System.out.println("Mouse will come back later!");

        }
        for (int rows = 0; rows < N; rows++) {
            for (int cols = 0; cols < M; cols++) {
                System.out.print(cupboard[rows][cols]);

            }
            System.out.println();
        }

    }
    private static boolean isOutOfBounds (int rowMouse, int colMouse, int N, int M){ // ако върне TRUE то значи сме излезнали от границите
        return rowMouse < 0 || rowMouse >= N || colMouse < 0 || colMouse >= M;
    }




}
