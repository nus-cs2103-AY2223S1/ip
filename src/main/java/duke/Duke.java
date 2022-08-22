package duke;

import duke.task.Task;
import duke.util.CommandParser;
import duke.util.OutputFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String GREETING_MESSAGE = "Hi there! I' am duke.Duke, your personal time manager."
            + "\nWhat can I help you?";
    private static final String EXIT_OUTPUT_STRING = "Bye! See you next time!";
    private static final String EXIT_COMMAND_STRING = "bye";
    private static final String DISPLAY_LIST_COMMAND_STRING = "list";
    private static final String MARK_DONE_COMMAND_STRING = "mark";
    private static final String MARK_DONE_OUTPUT_STRING = "Good to hear that! I have marked this as done: ";
    private static final String MARK_DONE_ERROR_STRING = "Oops! Do check the index range, and the format should be \"mark <index>\"";
    private static final String MARK_UNDONE_COMMAND_STRING = "unmark";
    private static final String MARK_UNDONE_OUTPUT_STRING = "Sure, I have marked this as not done yet";
    private static final String MARK_UNDONE_ERROR_STRING = "Oops! Do check the index range, and the format should be \"unmark <index>\"";
    private static final String GENERAL_ERROR_STRING = "Sorry, I don't understand that!";
    private static final String DELETE_COMMAND_STRING = "delete";
    private static final String DELETE_ERROR_STRING = "Oops! Do check the index range, and the format should be \"delete <index>\"";
    private static final String DELETE_OUTPUT_STRING = "Sure, I have removed this task from the list: ";
    public static final String DELIMITER = "/";
    public static final String BY_DATE_DELIMITER = "/by";
    public static final String AT_DATE_DELIMITER = "/at";

    List<Task> taskList;

    Duke() {
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke chatBox = new Duke();
        chatBox.greet();
        chatBox.standBy();
    }

    private void greet() {
        System.out.println(GREETING_MESSAGE);
    }

    private String getListInfo() {
        int len = taskList.size();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder
                    .append(i + 1)
                    .append(". ")
                    .append(taskList.get(i));
            if (i < len - 1) {
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }

    private String addNewTask(String input) {
        Task newTask = Task.valueOf(input);
        if (newTask == null) {
            return GENERAL_ERROR_STRING;
        }
        taskList.add(newTask);
        return "added: " + newTask.toString();
    }

    private String markTaskDone(int index) {
        if (index < 0 || index >= taskList.size()) {
            return MARK_DONE_ERROR_STRING; // error message
        } else {
            Task targetTask = taskList.get(index);
            targetTask.markDone();
            return MARK_DONE_OUTPUT_STRING
                    + "\n    "
                    + targetTask;
        }
    }

    private String markTaskUndone(int index) {
        if (index < 0 || index >= taskList.size()) {
            return MARK_UNDONE_ERROR_STRING; // error message
        } else {
            Task targetTask = taskList.get(index);
            targetTask.markUndone();
            return MARK_UNDONE_OUTPUT_STRING
                    + "\n    "
                    + targetTask;
        }
    }

    private String deleteTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return DELETE_ERROR_STRING; // error message
        } else {
            Task removedTask = taskList.remove(index);
            return DELETE_OUTPUT_STRING
                    + "\n    "
                    + removedTask;
        }
    }

    private void standBy() {
        Scanner scanner = new Scanner(System.in);
        boolean quited = false;
        String output = "";

        while (!quited) {
            String nextLine = scanner.nextLine();
            if (nextLine.isEmpty()) {
                continue;
            }

            boolean commandFetched = true;

            String firstWord = CommandParser.getFirstWord(nextLine);
            int index = CommandParser.getTaskIndexFromCommand(nextLine);
            switch (firstWord) {
            case (MARK_DONE_COMMAND_STRING):
                output = markTaskDone(index);
                break;

            case (MARK_UNDONE_COMMAND_STRING):
                output = markTaskUndone(index);
                break;

            case (DELETE_COMMAND_STRING):
                output = deleteTask(index);
                break;

            default:
                commandFetched = false;
                break;
            }

            if (!commandFetched) {
                switch (nextLine) {
                case (EXIT_COMMAND_STRING):
                    output = EXIT_OUTPUT_STRING;
                    quited = true;
                    break;

                case (DISPLAY_LIST_COMMAND_STRING):
                    output = getListInfo();
                    break;

                default:
                    output = addNewTask(nextLine);
                    commandFetched = false;
                    break;
                }
            }

            System.out.println(OutputFormatter.formatOutput(output));
        }
    }
}
