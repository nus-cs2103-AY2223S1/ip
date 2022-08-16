import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> userData = new ArrayList<>();

        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        System.out.println("-----------------------------------");
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();

            String userInputs[] = line.split(" ");
            if ("bye".equalsIgnoreCase(userInputs[0])) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equalsIgnoreCase(userInputs[0])) {
                for (int i = 0; i < userData.size(); i++) {
                    System.out.format("%s. %s\n", i, userData.get(i));
                }
            } else if ("mark".equalsIgnoreCase(userInputs[0])) {
                int position = Integer.valueOf(userInputs[1]);
                userData.get(position).setIsComplete(true);
                System.out.format("Nice! I've marked this task as done: [X] %s\n", userData.get(Integer.valueOf(userInputs[1])).toString());
            } else if ("unmark".equalsIgnoreCase(userInputs[0])) {
                int position = Integer.valueOf(userInputs[1]);
                userData.get(position).setIsComplete(false);
                System.out.format("OK, I've marked this task as not done yet: [ ] %s\n", userData.get(Integer.valueOf(userInputs[1])).toString());
            } else{
                userData.add(new Task(line, false));
                System.out.format("added: %s\n", line);
            }
            System.out.println("-----------------------------------");
        }
    }


}
