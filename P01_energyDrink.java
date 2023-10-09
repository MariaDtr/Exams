package Exams;

import java.util.*;
import java.util.stream.Collectors;

public class P01_energyDrink {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxCaffeine = 300;
        int stamatCaffeine = 0;

        ArrayDeque<Integer> milligramsOfCaffeine = new ArrayDeque<>();

        ArrayDeque<Integer> energyDrinks = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt)
                .forEach(milligramsOfCaffeine::push);

        Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt)
                .forEach(energyDrinks::offer);

        // докато някое от двете не е празно
        while (!milligramsOfCaffeine.isEmpty() && !energyDrinks.isEmpty()){
            int currentCaffeine = milligramsOfCaffeine.peek() * energyDrinks.peek();

            if ((stamatCaffeine + currentCaffeine) <= maxCaffeine){
                stamatCaffeine += currentCaffeine;
                milligramsOfCaffeine.pop();
                energyDrinks.poll();
            } else {
                milligramsOfCaffeine.pop();
                int currentEnergy = energyDrinks.poll();
                energyDrinks.offer(currentEnergy);

                if (stamatCaffeine >= 30){
                    stamatCaffeine -= 30;
                }
            }
        }

        if (!energyDrinks.isEmpty()){
            System.out.print("Drinks left: ");
            energyDrinks.stream().map(e -> {
                return e.toString();
            }).forEach(e -> System.out.print(String.join(", ", e)));
        } else {
            System.out.print("At least Stamat wasn't exceeding the maximum caffeine.");
        }
        System.out.println();
        System.out.printf("Stamat is going to sleep with %d mg caffeine.", stamatCaffeine);
    }
}
