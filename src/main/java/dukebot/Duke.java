package dukebot;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int currentPos = 0;

    public static void main(String[] args) {
        System.out.println(Messages.startup);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputWords = input.split(" ");
            switch (inputWords[0]) {
                case "bye":
                    System.out.println(Messages.ending);
                    System.exit(0);
                case "list":
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i+1) + ".[" + taskList.get(i).getStatusIcon() +
                                "] "+ taskList.get(i).description);
                    }
                    break;
                case "mark":
                    currentPos = Integer.parseInt(inputWords[1]) - 1;
                    taskList.get(currentPos).markDone();
                    System.out.println(Messages.taskMarked);
                    System.out.println("[" + taskList.get(currentPos).getStatusIcon() +
                    "] "+ taskList.get(currentPos).description);
                    break;
                case "unmark":
                    currentPos = Integer.parseInt(inputWords[1]) - 1;
                    taskList.get(currentPos).markUndone();
                    System.out.println(Messages.taskUnmarked);
                    System.out.println("[" + taskList.get(currentPos).getStatusIcon() +
                            "] "+ taskList.get(currentPos).description);
                    break;
                default:
                    taskList.add(new Task(input));
                    System.out.println("added: " + input);
            }

        }
    }
}
