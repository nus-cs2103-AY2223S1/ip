import exceptions.IncorrectArgumentException;
import exceptions.MissingArgumentException;
import exceptions.TaskNotFoundException;
import exceptions.UnknownCommandException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    static private final String exitCommand = "bye";
    static private final String listCommand = "list";
    static private final String markCommand = "mark";
    static private final String todoCommand = "todo";
    static private final String deadlineCommand = "deadline";
    static private final String deadlineSubCommand = " /by ";
    static private final String eventCommand = "event";
    static private final String eventSubCommand = " /at ";
    static private final String unmarkCommand = "unmark";
    static private final String deleteCommand = "delete";
    static private final String exitMessage = "Goodbye and have a nice day!";

    /**
     * Returns a boolean corresponding to whether the given string is numeric.
     *
     * @param strNum String to be tested
     * @return boolean representing whether the string is numeric
     */
    static private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Outputs the greeting message.
     */
    private static void greet() {
        File file = new File("src/main/assets/logo.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
        }

        System.out.println("Welcome to Aladdin Services");
    }

    /**
     * Outputs the goodbye message.
     */
    private static void goodbye() {
        System.out.println(exitMessage);
    }

    public static void main(String[] args) {
        // Set up
        TaskController taskController = new TaskController();

        Duke.greet();

        Scanner scannerObj = new Scanner(System.in); // Create a Scanner object
        String userInput;

        boolean flag = true;

        while (flag) {
            System.out.println("Enter Command: ");
            userInput = scannerObj.nextLine(); // Read user input

            String[] commandArgs = userInput.split(" ");
            String[] commandArgsCopy = new String[commandArgs.length - 1];
            System.arraycopy(commandArgs, 1, commandArgsCopy, 0, commandArgs.length - 1);

            try {
                switch (commandArgs[0]) {
                    case exitCommand:
                        flag = false;
                        break;

                    case todoCommand:
                        String todoText = String.join(" ", commandArgsCopy);

                        if (commandArgsCopy.length > 0) {
                            Task newTodo = new Todo(todoText);
                            taskController.addTask(newTodo);
                        } else {
                            throw new MissingArgumentException("The description of the todo cannot be empty!");
                        }
                        break;

                    case deadlineCommand:
                        String deadlineText = String.join(" ", commandArgsCopy);
                        if (deadlineText.contains(deadlineSubCommand)) {
                            String[] deadlineArgs = deadlineText.split(deadlineSubCommand);
                            String deadlineTitle = deadlineArgs[0];
                            String deadline = deadlineArgs[1];

                            Task newDeadline = new Deadline(deadlineTitle, deadline);
                            taskController.addTask(newDeadline);
                        } else {
                            throw new MissingArgumentException("Deadlines need a /by command");
                        }
                        break;

                    case eventCommand:
                        String eventText = String.join(" ", commandArgsCopy);
                        if (eventText.contains(eventSubCommand)) {
                            String[] eventArgs = eventText.split(eventSubCommand);
                            String eventTitle = eventArgs[0];
                            String eventDateTime = eventArgs[1];

                            Task newEvent = new Event(eventTitle, eventDateTime);
                            taskController.addTask(newEvent);
                        } else {
                            throw new MissingArgumentException("Events need a /at command");
                        }
                        break;

                    case markCommand:
                        if (isNumeric(commandArgs[1])) {
                            int idx = Integer.parseInt(commandArgs[1]);
                            taskController.markTask(idx - 1);
                        } else {
                            throw new IncorrectArgumentException("Sorry the second argument is not a number");
                        }
                        break;

                    case unmarkCommand:
                        if (isNumeric(commandArgs[1])) {
                            int idx = Integer.parseInt(commandArgs[1]);
                            taskController.unmarkTask(idx - 1);
                        } else {
                            throw new IncorrectArgumentException("Sorry the second argument is not a number");
                        }
                        break;

                    case deleteCommand:
                        if (isNumeric(commandArgs[1])) {
                            int idx = Integer.parseInt(commandArgs[1]);
                            taskController.deleteTask(idx - 1);
                        } else {
                            throw new IncorrectArgumentException("Sorry the second argument is not a number");
                        }
                        break;

                    case listCommand:
                        taskController.listTasks();
                        break;

                    default:
                        throw new UnknownCommandException("Sorry I don't understand that command");
                }
            } catch (UnknownCommandException | MissingArgumentException | TaskNotFoundException | IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Duke.goodbye();

        scannerObj.close();
    }
}
