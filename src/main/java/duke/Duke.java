package duke;

import duke.exception.*;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.Scanner;

public class Duke {

    public static final String DELIMITER = "/";
    public static final String BY_DATE_DELIMITER = "/by";
    public static final String AT_DATE_DELIMITER = "/at";
    public static final String FILE_WRITING_DELIMITER = "|";
    public static final String TAB = "    ";

    public static final String FILE_PATH = "../saved_list.txt";
    public static final String GREETING_MESSAGE = "Hi there! I' am duke.Duke, your personal time manager."
            + "\nWhat can I help you?";
    public static final String EXIT_OUTPUT_STRING = "Bye! See you next time!";
    public static final String EXIT_COMMAND_STRING = "bye";
    public static final String DISPLAY_LIST_COMMAND_STRING = "list";
    public static final String MARK_DONE_COMMAND_STRING = "mark";
    public static final String MARK_DONE_OUTPUT_STRING = "Good to hear that! I have marked this as done: ";
    public static final String MARK_DONE_ERROR_STRING = "Oops! Do check the index range, and the format should be \"mark <index>\"";
    public static final String MARK_UNDONE_COMMAND_STRING = "unmark";
    public static final String MARK_UNDONE_OUTPUT_STRING = "Sure, I have marked this as not done yet";
    public static final String MARK_UNDONE_ERROR_STRING = "Oops! Do check the index range, and the format should be \"unmark <index>\"";
    public static final String GENERAL_ERROR_STRING = "Sorry, I don't understand that!";
    public static final String DELETE_COMMAND_STRING = "delete";
    public static final String DELETE_ERROR_STRING = "Oops! Do check the index range, and the format should be \"delete <index>\"";
    public static final String DELETE_OUTPUT_STRING = "Sure, I have removed this task from the list: ";

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    Duke(String filePath) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    public void run() {
        greet();
        listen();
    }

    private void greet() {
        System.out.println(GREETING_MESSAGE);
    }

    private void listen() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {

            String output = "";
            String nextLine = scanner.nextLine();

            if (nextLine.isEmpty()) {
                continue;
            }

            boolean commandFetched = false;

            String firstWord = Parser.getFirstWord(nextLine);
            int index;

            try {
                switch (firstWord) {
                case (MARK_DONE_COMMAND_STRING):
                    index = Parser.getTaskIndexFromCommand(nextLine);
                    output = taskList.markTaskDone(index);
                    storage.saveFile(taskList.getFileStream());
                    commandFetched = true;
                    break;

                case (MARK_UNDONE_COMMAND_STRING):
                    index = Parser.getTaskIndexFromCommand(nextLine);
                    output = taskList.markTaskUndone(index);
                    storage.saveFile(taskList.getFileStream());
                    commandFetched = true;
                    break;

                case (DELETE_COMMAND_STRING):
                    index = Parser.getTaskIndexFromCommand(nextLine);
                    output = taskList.deleteTask(index);
                    storage.saveFile(taskList.getFileStream());
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
                        isExit = true;
                        break;

                    case (DISPLAY_LIST_COMMAND_STRING):
                        output = taskList.getListInfo();
                        commandFetched = true;
                        break;

                    default:
                        output = taskList.addNewTask(nextLine);
                        commandFetched = true;
                        storage.saveFile(taskList.getFileStream());
                        break;
                    }
                }
            } catch (DukeIndexOutOfBoundException | DukeDateTimeFormatException | DukeTaskDateTimeMissingException |
                     DukeTaskTitleMissingException | DukeIndexMissingException | DukeCommandFormatException |
                     DukeIoException exception) {
                output = exception.getMessage();
            }

            ui.printOutput(output);
        }
    }

    private void saveFile() {

    }

    public static void main(String[] args) {
        Duke chatBot = new Duke(FILE_PATH);
        chatBot.run();
    }
}
