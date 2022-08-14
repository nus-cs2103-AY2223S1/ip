import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String straightLine = "  ----------------------------------";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listOfThings = new ArrayList<>(100);

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm KiwiQE :) \nWhat can I do for you? \n");

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("Bye")) {

            if (input.equalsIgnoreCase("list")) {
                if (listOfThings.isEmpty()) {
                    System.out.println(straightLine + "\n" + "  Nothing to do currently ehe\n" + straightLine);
                    input = sc.nextLine();
                    continue;
                }

                System.out.println(straightLine);

                for (Task task : listOfThings) {
                    if (task.isDone) {
                        System.out.println("  " + task.index + ".[X] " + task.taskDescription);
                    } else {
                        System.out.println("  " + task.index + ".[ ] " + task.taskDescription);
                    }

                }

                System.out.println(straightLine + "\n");

                input = sc.nextLine();
                continue;
            }

            if (input.startsWith("mark")) {

                char i = input.charAt(5);
                int index = Character.getNumericValue(i);
                Task t = listOfThings.get(index - 1);

                t.markDone();

                System.out.println(straightLine + "\n  Good Job! You're Killing It!\n  [X] " + t.taskDescription
                                   + "\n" + straightLine + "\n");

                input = sc.nextLine();
                continue;

            }

            if (input.startsWith("unmark")) {

                char i = input.charAt(7);
                int index = Character.getNumericValue(i);
                Task t = listOfThings.get(index - 1);

                t.markUndone();

                System.out.println(straightLine + "\n  AAaaa please get it done soon...\n  [ ] " + t.taskDescription
                                   + "\n" + straightLine + "\n");

                input = sc.nextLine();
                continue;
            }


            Task newTask = new Task(false, input, listOfThings.size() + 1);
            listOfThings.add(newTask);

            System.out.print(straightLine + "\n  added: " + input + "\n" + straightLine + "\n\n");

            input = sc.nextLine();
        }

        System.out.println(straightLine + "\n  さよなら, goodbye\n" + straightLine);


    }
}
