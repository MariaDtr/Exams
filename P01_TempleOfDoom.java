package Exams;

import java.util.*;
import java.util.stream.Collectors;

public class P01_TempleOfDoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> tools = new ArrayDeque<>();
        Deque<Integer> substances = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt)
                .forEach(tools::offer);
        Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt)
                .forEach(substances::push);
        List<Integer> challenges = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        while (!tools.isEmpty() && !substances.isEmpty() && !challenges.isEmpty()){

            int currentResult = tools.peek() * substances.peek();

            if (challenges.contains(currentResult)){

                challenges.remove(Integer.valueOf(currentResult));
                tools.poll();
                substances.pop();
            } else {
                int currentTool = tools.poll();
                tools.addLast(currentTool + 1);
                int newSubstance = substances.peek() - 1;
                if (newSubstance == 0) {
                    substances.pop();

                } else {
                    substances.pop();
                    substances.push(newSubstance);
                }
            }


        }
        if (challenges.isEmpty()) {
            System.out.println("Harry found an ostracon, which is dated to the 6th century BCE.");
        } else {
            System.out.println("Harry is lost in the temple. Oblivion awaits him.");

        }
        if (!tools.isEmpty()){

            System.out.println("Tools: " + tools.stream().map(Object::toString).collect(Collectors.joining(", ")));
        } if (!substances.isEmpty()){

            System.out.println("Substances: " + substances.stream().map(Object::toString).collect(Collectors.joining(", ")));
        } if (!challenges.isEmpty()){

            System.out.println("Challenges: " + challenges.stream().map(Object::toString).collect(Collectors.joining(", ")));
        }

    }
}
