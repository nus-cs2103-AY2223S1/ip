package duke;

import duke.exception.*;
import duke.task.Task;
import duke.util.CommandParser;
import duke.util.OutputFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static final String DELIMITER = "/";
    public static final String BY_DATE_DELIMITER = "/by";
    public static final String AT_DATE_DELIMITER = "/at";
    public static final String TAB = "    ";

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
        if (len == 0) {
            return "The list is empty.";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder
                    .append(i + 1)
                    .append(". ")
                    .append(taskList.get(i));
            if (i < len - 1) {
                stringBuilder.append('\n' + TAB);
            }
        }
        return stringBuilder.toString();
    }

    private String addNewTask(String input)
            throws DukeCommandFormatException, DukeTaskTitleMissingException, DukeTaskDateTimeMissingException {
        Task newTask = Task.valueOf(input);
        if (newTask == null) {
            return GENERAL_ERROR_STRING;
        }
        taskList.add(newTask);
        return "added: " + newTask.toString();
    }

    private String markTaskDone(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeIndexOutOfBoundException(MARK_DONE_ERROR_STRING);
        } else {
            Task targetTask = taskList.get(index);
            targetTask.markDone();
            return MARK_DONE_OUTPUT_STRING
                    + "\n"
                    + TAB
                    + targetTask;
        }
    }

    private String markTaskUndone(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeIndexOutOfBoundException(MARK_UNDONE_ERROR_STRING);
        } else {
            Task targetTask = taskList.get(index);
            targetTask.markUndone();
            return MARK_UNDONE_OUTPUT_STRING
                    + "\n"
                    + TAB
                    + targetTask;
        }
    }

    private String deleteTask(int index) throws DukeIndexOutOfBoundException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeIndexOutOfBoundException(DELETE_ERROR_STRING);
        } else {
            Task removedTask = taskList.remove(index);
            boolean onlyOneTask = taskList.size() == 1;
            return DELETE_OUTPUT_STRING
                    + "\n"
                    + TAB
                    + removedTask
                    + "\n"
                    + TAB
                    + "There "
                    + (onlyOneTask ? "is " : "are ")
                    + taskList.size()
                    + (onlyOneTask ? " task" : " tasks")
                    + " in the list";
        }
    }

    private void standBy() {
        Scanner scanner = new Scanner(System.in);
        boolean quited = false;

        while (!quited) {

            String output = "";
            String nextLine = scanner.nextLine();

            if (nextLine.isEmpty()) {
                continue;
            }

            boolean commandFetched = false;

            String firstWord = CommandParser.getFirstWord(nextLine);
            int index;

            try {
                switch (firstWord) {
                case (MARK_DONE_COMMAND_STRING):
                    index = CommandParser.getTaskIndexFromCommand(nextLine);
                    output = markTaskDone(index);
                    commandFetched = true;
                    break;

                case (MARK_UNDONE_COMMAND_STRING):
                    index = CommandParser.getTaskIndexFromCommand(nextLine);
                    output = markTaskUndone(index);
                    commandFetched = true;
                    break;

                case (DELETE_COMMAND_STRING):
                    index = CommandParser.getTaskIndexFromCommand(nextLine);
                    output = deleteTask(index);
                    commandFetched = true;
                    break;

                default:
                    break;
                }

                if (!commandFetched) {
                    switch (nextLine) {
                    case (EXIT_COMMAND_STRING):
                        output = EXIT_OUTPUT_STRING;
                        commandFetched = true;
                        quited = true;
                        break;

                    case (DISPLAY_LIST_COMMAND_STRING):
                        output = getListInfo();
                        commandFetched = true;
                        break;

                    default:
                        output = addNewTask(nextLine);
                        commandFetched = true;
                        break;
                    }
                }
            } catch (DukeIndexOutOfBoundException exception) {
                output = exception.getMessage();
            } catch (DukeCommandFormatException exception) {
                output = exception.getMessage();
            } catch (DukeIndexMissingException exception) {
                output = exception.getMessage();
            } catch (DukeTaskTitleMissingException exception) {
                output = exception.getMessage();
            } catch (DukeTaskDateTimeMissingException exception) {
                output = exception.getMessage();
            }

            System.out.println(OutputFormatter.formatOutput(output));
        }
    }
}
