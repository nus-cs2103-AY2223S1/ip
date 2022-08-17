import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String straightLine = "  ----------------------------------------------------------------------------------";
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
                    task.printTask();
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

                input = sc.nextLine();
                continue;

            }

            if (input.startsWith("unmark")) {

                char i = input.charAt(7);
                int index = Character.getNumericValue(i);
                Task t = listOfThings.get(index - 1);

                t.markUndone();

                input = sc.nextLine();
                continue;
            }

            if (input.startsWith("todo")) {

                try {
                    String task = input.substring(5);

                    ToDo newToDo = new ToDo(false, task, listOfThings.size() + 1);
                    listOfThings.add(newToDo);

                    newToDo.printAdded();
                    input = sc.nextLine();
                    continue;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  Insufficient information to make a to do! Please input more ;-;\n"
                                        + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                }

            }

            if (input.startsWith("deadline")) {

                try {
                    int indexOfSlash = input.indexOf("/");
                    String dateStr = input.substring(indexOfSlash + 3);
                    String task = input.substring(9, indexOfSlash);
                    Deadline newDeadline = new Deadline(false, task, listOfThings.size() + 1, dateStr);
                    listOfThings.add(newDeadline);

                    newDeadline.printAdded();

                    input = sc.nextLine();
                    continue;

                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  Insufficient information to make a deadline! Please input more ;-;\n"
                            + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                }
            }

            if (input.startsWith("event")) {

                try {

                    int indexOfSlash = input.indexOf("/");
                    String dateAndTime = input.substring(indexOfSlash + 3);
                    String task = input.substring(6, indexOfSlash);
                    Event newEvent = new Event(false, task, listOfThings.size() + 1, dateAndTime);
                    listOfThings.add(newEvent);

                    newEvent.printAdded();

                    input = sc.nextLine();
                    continue;


                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  Insufficient information to make a event! Please input more ;-;\n"
                            + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                }
            }


            System.out.println(straightLine + "\n  What do you mean by Justin Bieber plays~\n" + straightLine + "\n");

            input = sc.nextLine();
        }

        System.out.println(straightLine + "\n  さよなら, goodbye\n" + straightLine);

    }
}
