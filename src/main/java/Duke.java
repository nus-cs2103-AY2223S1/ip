import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Job> toDoList = new ArrayList<Job>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        useToDoList();

        printInStyle("Goodbye! Hope to see you again soon!");
    }

    public static void printInStyle(String stringToPrint) {
        System.out.println("\t" + "-".repeat(25) + "\n\t\t" + stringToPrint);
        System.out.println("\t" + "-".repeat(25));
    }

    public static void printInStyle(String... stringsToPrint) {
        System.out.println("\t" + "-".repeat(25));
        for (String string : stringsToPrint) {
            System.out.println("\t\t" + string);
        }
        System.out.println("\t" + "-".repeat(25));
    }

    public static void printInStyle(Iterable<?> itemsToPrint, String... others) {
        System.out.println("\t" + "-".repeat(25));
        for (Object item : itemsToPrint) {
            System.out.println("\t\t" + item.toString());
        }

        for (String string : others) {
            System.out.println("\t\t" + string);
        }
        System.out.println("\t" + "-".repeat(25));
    }

    public static String askForInput(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);

        String input = askForInput("Type something:");
        while (!input.toLowerCase(Locale.ROOT).equals("bye")) {
            printInStyle(input);
            input = askForInput("Type something:");
        }
    }

    private static void useToDoList() {
        String[] input = askForInput("Add something:").split(" ");

        final int ALL = -1;

        while (!input[0].toLowerCase(Locale.ROOT).equals("bye")) {
            switch (input[0]) {
                case "list":
                    printInStyle(toDoList);
                    break;

                case "mark": case "unmark":
                    int index;
                    if (input.length > 1) {
                        if (input[1].equals("all")) {
                            index = ALL;
                        } else {
                            try {
                                index = Integer.parseInt(input[1]) - 1;
                            } catch (NumberFormatException ex) {
                                printInStyle(String.format("I cannot understand what %s means in this context.", input[1]));
                                break;
                            }
                        }
                    } else {
                        printInStyle("Please specify which task you want to mark or unmark " +
                                "after the command. Otherwise, you may also specify all.");
                        break;
                    }

                    if (index == ALL) {
                        if (input[0].equals("mark")) {
                            for (Job job : toDoList) {
                                job.MarkJobState(true);
                            }
                            printInStyle("Ok, I've marked all items as completed!");
                        } else if (input[0].equals("unmark")) {
                            for (Job job : toDoList) {
                                job.MarkJobState(false);
                            }
                            printInStyle("Ok, I've unmarked all items as undone.");
                        }
                    } else if (index < toDoList.size() && index >= 0) {
                        String response;
                        if (input[0].equals("mark")) {
                            toDoList.get(index).MarkJobState(true);
                            response = "Nice! I've marked this task as done:";
                        } else {
                            toDoList.get(index).MarkJobState(false);
                            response = "Ok, I've marked this task as not done yet:";
                        }

                        printInStyle(
                                response,
                                toDoList.get(index).toString()
                        );
                    } else if (toDoList.isEmpty()) {
                        printInStyle(String.format("%d is out of range, " +
                                "you need to first add some items into the To-Do List", index + 1));
                    } else {
                        printInStyle(
                                String.format("%d is out of range, please choose an integer between 1 to %d",
                                        index + 1,
                                        toDoList.size()));
                    }
                    break;

                default:
                    String jobName = String.join(" ", input);
                    toDoList.add(new Job(jobName));
                    printInStyle(String.format("added: %s", jobName));
            }
            input = askForInput("Add something:").split(" ");
        }
    }
}
