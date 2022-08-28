package duke;

import java.util.Scanner;

/**
 * Handles interpretation of user inputs.
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

    /**
     * Parser constructor.
     *
     * @param tasks Object that deals with tracking Tasks added.
     * @param ui Object that deals with user interaction.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Listens for user inputs and decipher them to call required methods.
     */
    public void takeUserInput() {
        // Listening to inputs from user
        Scanner in = new Scanner(System.in);
        while (!Duke.terminate) {
            try {
                String userInput = in.nextLine();
                // retrieve first word
                int indexOfFirstSpace = userInput.indexOf(" ");
                int indexOfFirstSlash = userInput.indexOf("/");
                String firstWord = userInput;
                String taskDescription = "";
                String time = "";

                // int to store info for numbered operations
                char charOfInt = '\0';
                if (indexOfFirstSpace != -1) {
                    firstWord = userInput.substring(0, indexOfFirstSpace);
                    taskDescription = userInput.substring(indexOfFirstSpace + 1);

                    // retrieve int for numbered operations
                    charOfInt = userInput.charAt(userInput.length() - 1);
                }

                // retrieve task details
                if (indexOfFirstSlash != -1) {
                    taskDescription = userInput.substring(indexOfFirstSpace + 1, indexOfFirstSlash - 1);
                    time = userInput.substring(indexOfFirstSlash + 1);
                }

                // convert ASCII character of integer to int
                int taskNumber = charOfInt - '0';

                // choose instruction to execute
                switch (firstWord) {
                case "bye":
                    tasks.exit();
                    break;
                case "unmark":
                    tasks.unmarkTask(taskNumber, taskDescription);
                    break;
                case "mark":
                    tasks.markTask(taskNumber, taskDescription);
                    break;
                case "list":
                    tasks.displayList();
                    break;
                case "deadline":
                    tasks.addDeadline(taskDescription, time);
                    break;
                case "todo":
                    tasks.addTodo(taskDescription);
                    break;
                case "event":
                    tasks.addEvent(taskDescription, time);
                    break;
                case "delete":
                    tasks.deleteTask(taskNumber, taskDescription);
                    break;
                case "find":
                    tasks.findTask(taskDescription);
                    break;
                case "":
                    throw new DukeException("OOPS!!! Please enter an instruction");
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException | IndexOutOfBoundsException e) {
                ui.printWithLineFormat("     " + e.getMessage());
            }
        }
        in.close();
    }
}
