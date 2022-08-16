import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> userData = new ArrayList<>();
        boolean isActive = true;

        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        System.out.println("-----------------------------------");
        while (isActive) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();

            String userInputs[] = line.split(" ");
            String mainCommand = userInputs[0].toLowerCase();

            switch (mainCommand) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isActive = false;
                    break;
                case "list":
                    for (int i = 0; i < userData.size(); i++) {
                        System.out.format("%s. %s\n", i, userData.get(i));
                    }
                    break;
                case "mark":
                    userData.get(Integer.valueOf(userInputs[1])).setIsComplete(true);
                    System.out.format("Nice! I've marked this task as done: %s\n", userData.get(Integer.valueOf(userInputs[1])).toString());
                    break;
                case "unmark":
                    userData.get(Integer.valueOf(userInputs[1])).setIsComplete(false);
                    System.out.format("OK, I've marked this task as not done yet: %s\n", userData.get(Integer.valueOf(userInputs[1])).toString());
                    break;
                default:
                    userData.add(new Task(line));
                    System.out.format("added: %s\n", line);
            }
            System.out.println("-----------------------------------");
        }
    }


}
