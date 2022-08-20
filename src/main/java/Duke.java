import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String TOB_TOB_LOGO = "            __                  __           __                  __\n\t"
                                            + "           |  |____    _____   |  |_____    |  |____    _____   |  |_____\n\t"
                                            + "           |   ___/   /     \\  |        \\   |   ___/   /     \\  |        \\\n\t"
                                            + "           |  |      |  / \\  | |   / \\   |  |  |      |  / \\  | |   / \\   |\n\t"
                                            + "           |  \\_____ |  \\ /  | |   \\ /   |  |  \\_____ |  \\ /  | |   \\ /   |\n\t"
                                            + "            \\______/  \\_____/   \\_______/    \\______/  \\_____/   \\_______/";

    private static final int TOB_TOB_LUCKY_NUMBER = 88;
    private static final String TOB_TOB_LINE = new String(new char[TOB_TOB_LUCKY_NUMBER]).replace("\0", "~");
    private static final ArrayList<Task> tobTobBrain = new ArrayList<>();
    private static final String EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE = "Oopsieee! Command description cannot be empty";
    private static final String INTEGER_INDEX_ERROR_MESSAGE = "Oopsieee! Command \"%s\" should be followed with an integer index";
    private static final String TASK_INCORRECT_FORMAT_ERROR_MESSAGE = "Oopsieee! Task name and datetime format is incorrect.\n\t"
                                                                    + "Please indicate task name and datetime separated using \"%s\" for %s";
    private static final String COMMAND_NOT_RECOGNIZED_ERROR_MESSAGE = "Oopsieee! Command \"%s\" is not in Tob Tob Dictionary";
    private static final String SHOULD_HAVE_NO_DESCRIPTION_ERROR_MESSAGE = "Oopsieee! Command \"list\" shouldn't have any description";
    private static final String INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE = "Oopsieee! There are only %s tasks in Tob Tob Brain.\n\t"
                                                                + "Index should be a positive number and less than %s.\n\t"
                                                                + "To see the task list, input \"list\"";

    public static void printTobTobIndent(String s) {
        System.out.print("\t");
        System.out.println(s);
    }

    public static void printTobTobBoundary() {
        printTobTobIndent(TOB_TOB_LINE);
    }

    public static void tobTobGreets() {
        printTobTobIndent(TOB_TOB_LOGO);
        printTobTobIndent("");
        printTobTobBoundary();
        printTobTobIndent("Tob Tob! Who's there?");
        printTobTobIndent("What do you want Tob Tob to do for you today?");
        printTobTobBoundary();
    }

    public static void tobTobKonslet(String konsletMessage) throws DukeException {
        throw new DukeException(konsletMessage);
    }

    public static void showTobTobBrain(String commandDescription) throws DukeException {
        if (!commandDescription.equals("")) {
            tobTobKonslet(SHOULD_HAVE_NO_DESCRIPTION_ERROR_MESSAGE);
        }

        printTobTobIndent("Abrakadabraaa! Here's what's inside Tob Tob's Brain:");
        for (int i = 0; i < tobTobBrain.size(); i++) {
            Task task = tobTobBrain.get(i);
            printTobTobIndent(String.format("%s.%s", i + 1, task.toString()));
        }
    }

    public static void markTask(String commandDescription) throws DukeException {
        if (commandDescription.equals("")) {
            tobTobKonslet(EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE);
        }

        try {
            int index = Integer.parseInt(commandDescription);
            Task task = tobTobBrain.get(index - 1);
            task.markAsDone();

            printTobTobIndent("Yoohooo! Tob Tob has marked this task as done:");
            printTobTobIndent(task.toString());
        } catch (NumberFormatException e) {
            tobTobKonslet(String.format(INTEGER_INDEX_ERROR_MESSAGE, "mark"));
        } catch (IndexOutOfBoundsException e) {
            tobTobKonslet(String.format(INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE, tobTobBrain.size(), tobTobBrain.size()));
        }
    }

    public static void unmarkTask(String commandDescription) throws DukeException {
        if (commandDescription.equals("")) {
            tobTobKonslet(EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE);
        }

        try {
            int index = Integer.parseInt(commandDescription);
            Task task = tobTobBrain.get(index - 1);
            task.markAsUndone();

            printTobTobIndent("Saddd! Tob Tob has marked this task as not done yet:");
            printTobTobIndent(task.toString());
        } catch (NumberFormatException e) {
            tobTobKonslet(String.format(INTEGER_INDEX_ERROR_MESSAGE, "unmark"));
        } catch (IndexOutOfBoundsException e) {
            tobTobKonslet(String.format(INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE, tobTobBrain.size(), tobTobBrain.size()));
        }
    }

    public static void putInTobTobBrain(String taskType, String commandDescription) throws DukeException {
        if (commandDescription.equals("")) {
            tobTobKonslet(EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE);
        }

        Task task;
        String taskDescription;
        String separator;
        String datetime;
        int separatorIndex;

        switch (taskType) {
            case "todo":
                taskDescription = commandDescription;
                task = new Todo(taskDescription);
                tobTobBrain.add(task);

                printTobTobIndent("Wiii! Now Tob Tob's brain has more stuffs");
                printTobTobIndent(task.toString());
                printTobTobIndent("");
                showTobTobBrain("");
                break;
            case "deadline":
                separator = " /by ";
                separatorIndex = commandDescription.indexOf(separator);

                if (separatorIndex == -1) {
                    tobTobKonslet(String.format(TASK_INCORRECT_FORMAT_ERROR_MESSAGE, separator, taskType));
                } else {
                    taskDescription = commandDescription.substring(0, separatorIndex);
                    datetime = commandDescription.substring(separatorIndex + 5);
                    task = new Deadline(taskDescription, datetime);
                    tobTobBrain.add(task);

                    printTobTobIndent("Wiii! Now Tob Tob's brain has more stuffs");
                    printTobTobIndent(task.toString());
                    printTobTobIndent("");
                    showTobTobBrain("");
                }
                break;
            default:
                separator = " /at ";
                separatorIndex = commandDescription.indexOf(separator);

                if (separatorIndex == -1) {
                    tobTobKonslet(String.format(TASK_INCORRECT_FORMAT_ERROR_MESSAGE, separator, taskType));
                } else {
                    taskDescription = commandDescription.substring(0, separatorIndex);
                    datetime = commandDescription.substring(separatorIndex + 5);
                    task = new Event(taskDescription, datetime);
                    tobTobBrain.add(task);

                    printTobTobIndent("Wiii! Now Tob Tob's brain has more stuffs");
                    printTobTobIndent(task.toString());
                    printTobTobIndent("");
                    showTobTobBrain("");
                }
        }
    }

    public static void trashFromTobTobBrain(String commandDescription) throws DukeException {
        if (commandDescription.equals("")) {
            tobTobKonslet(EMPTY_COMMAND_DESCRIPTION_ERROR_MESSAGE);
        }

        try {
            int index = Integer.parseInt(commandDescription);
            Task task = tobTobBrain.get(index - 1);
            tobTobBrain.remove(index - 1);

            printTobTobIndent("Ewww! This task is no longer in Tob Tob's Brain:");
            printTobTobIndent("");
            printTobTobIndent(task.toString());
            showTobTobBrain("");
        } catch (NumberFormatException e) {
            tobTobKonslet(String.format(INTEGER_INDEX_ERROR_MESSAGE, "delete"));
        } catch (IndexOutOfBoundsException e) {
            tobTobKonslet(String.format(INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE, tobTobBrain.size(), tobTobBrain.size()));
        }
    }

    public static void tobTobHibernates() {
        printTobTobIndent("Byeee! Tob Tob is sick of you");
        printTobTobIndent("Don't you dare come back");
        printTobTobIndent("Tob Tob hates you");
        printTobTobBoundary();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        tobTobGreets();

        while (true) {
            try {
                String input = sc.nextLine().strip();
                System.out.println(input);
                String command = input.split(" ")[0].toLowerCase();
                int inputLength = input.length();
                int commandLength = command.length();
                String commandDescription;

                if (inputLength == commandLength) {
                    commandDescription = "";
                } else {
                    commandDescription = input.substring(commandLength + 1).strip();
                }

                printTobTobBoundary();

                switch (command) {
                    case "list":
                        showTobTobBrain(commandDescription);
                        break;
                    case "mark":
                        markTask(commandDescription);
                        break;
                    case "unmark":
                        unmarkTask(commandDescription);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        putInTobTobBrain(command, commandDescription);
                        break;
                    case "delete":
                        trashFromTobTobBrain(commandDescription);
                        break;
                    case "bye":
                        tobTobHibernates();
                        return;
                    default:
                        tobTobKonslet(String.format(COMMAND_NOT_RECOGNIZED_ERROR_MESSAGE, command));
                }

                printTobTobBoundary();
            } catch (DukeException e) {
                printTobTobIndent(e.getMessage());
                printTobTobBoundary();
            }
        }
    }
}