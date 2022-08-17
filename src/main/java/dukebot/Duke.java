package dukebot;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(Messages.startup);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            switch (input) {
                case "bye":
                    System.out.println(Messages.ending);
                    System.exit(0);
                case "list":
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i+1) + ". " + taskList.get(i).description);
                    }
                    break;
                default:
                    taskList.add(new Task(input));
                    System.out.println("added: " + input);
            }

        }
    }
}
