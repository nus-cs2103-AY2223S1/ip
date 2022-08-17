import java.time.DateTimeException;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return "bye";
        }
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
        String[] input = askForInput("").split(" ");

        while (!input[0].toLowerCase(Locale.ROOT).equals("bye")) {
            switch (input[0]) {
                case "list":
                    listCheckList();
                    break;

                case "mark": case "unmark":
                   markCheckList(input);
                   break;

                default:
                    try {
                        addToCheckList(input);
                    } catch (InvalidJobException ex) {
                        printInStyle(ex.getMessage());
                    }
            }
            input = askForInput("").split(" ");
        }
    }

    private static void listCheckList() {
        printInStyle(toDoList);
    }

    private static void markCheckList(String[] input) {
        final int ALL = -1;

        int index;
        if (input.length > 1) {
            if (input[1].equals("all")) {
                index = ALL;
            } else {
                try {
                    index = Integer.parseInt(input[1]) - 1;
                } catch (NumberFormatException ex) {
                    printInStyle(String.format("I cannot understand what %s means in this context.", input[1]));
                    return;
                }
            }
        } else {
            printInStyle("Please specify which task you want to mark or unmark " +
                    "after the command. Otherwise, you may also specify all.");
            return;
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
    }

    private static void addToCheckList(String[] input) throws InvalidJobException{
        Job job;
        String[][] nameAndDate;
        String name;
        switch (input[0]) {
            case "todo":
                try {
                    job = new ToDo(concatName(input));
                } catch (JobCreationException ex) {
                    printInStyle(ex.getMessage());
                    return;
                }
                break;

            case "deadline":
                nameAndDate = splitStringArray(input, "/by");
                if (nameAndDate.length != 2) {
                    printInStyle("Please use /by to set a time");
                    return;
                }

                try {
                    job = new Deadline(concatName(nameAndDate[0]), String.join(" ", nameAndDate[1]));
                } catch (JobCreationException ex) {
                    printInStyle(ex.getMessage());
                    return;
                }
                break;

            case "event":
                nameAndDate = splitStringArray(input, "/at");
                if (nameAndDate.length != 2) {
                    printInStyle("Please use /at to set a time");
                    return;
                }

                try {
                    job = new Event(concatName(nameAndDate[0]), String.join(" ", nameAndDate[1]));
                } catch (JobCreationException ex) {
                    printInStyle(ex.getMessage());
                    return;
                }
                break;
            default:
                throw new InvalidJobException();
        }
        toDoList.add(job);
        printInStyle(
                "Got it. I've added this task:",
                job.toString(),
                String.format("Now you have %d tasks in the list", toDoList.size()));
    }

    private static String concatName(String[] input) throws JobCreationException{
        String name = Arrays.stream(input).skip(1).collect(Collectors.joining(" "));
        if (name.equals("")) {
            throw new JobCreationException(input[0]);
        }
        return name;
    }

    private static String[][] splitStringArray(String[] input, String delimiter) {
        int[] splitIndices = IntStream
                .range(0, input.length)
                .filter(i -> input[i].equals(delimiter))
                .toArray();

        String[][] splitArrays = new String[splitIndices.length + 1][];
        int lastIndex = 0;
        for (int i = 0; i < splitIndices.length; i++) {
            int splitIndex = splitIndices[i];
            splitArrays[i] = new String[splitIndex - lastIndex];
            System.arraycopy(input, lastIndex, splitArrays[i], 0, splitArrays[i].length);
            lastIndex = splitIndex + 1;
        }
        splitArrays[splitArrays.length - 1] = new String[input.length - lastIndex];
        System.arraycopy(input, lastIndex, splitArrays[splitArrays.length - 1],
                0, splitArrays[splitArrays.length - 1].length);
        return splitArrays;
    }


    /**
     * Parses a given input command to its Name and Data components
     * @param input the given input
     * @param outName an array of 1 element, the name will be stored in element 0
     * @param outDate an array of 3 elements, the day is stored in element 0,
     *                month in element 1 and year in element 2
     * @return A boolean to signal if the parse was successful or not
     */
    private static boolean parseInputToNameDate(String[] input, String delimiter,
                                                String[] outName, Object[] outDate) {

        String[][] nameAndDate = splitStringArray(input, delimiter);
        if (nameAndDate.length != 2) {
            printInStyle(String.format("Please use %s to set a time", delimiter));
            return false;
        }

        String[] namePart = nameAndDate[0];
        String[] datePart = nameAndDate[1];

        outName[0] = Arrays.stream(namePart).skip(1).collect(Collectors.joining(" "));

        int year, day;
        Month month;
        try {
            day = Integer.parseInt(datePart[0]);
            month = Month.of(Integer.parseInt(datePart[1]));
            year = Integer.parseInt(datePart[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            outDate[0] = String.join(" ", datePart);
            return false;
        } catch (DateTimeException ex) {
            printInStyle(String.format("%s is not a valid month", datePart[1]));
            outDate[0] = String.join(" ", datePart);
            return false;
        }

        outDate[0] = day;
        outDate[1] = month;
        outDate[2] = year;

        return true;
    }
}
