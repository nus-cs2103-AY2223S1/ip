import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String GREETING_MESSAGE = "Hi there! I' am Duke, your personal time manager."
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

    private CommandParser commandParser;
    List<Task> taskList;

    Duke() {
        this.commandParser = new CommandParser();
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
                    .append(i)
                    .append(". ")
                    .append(taskList.get(i))
                    .append('\n');
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

    private int getTaskIndexForMarking(String input) {
        int indexOfFirstWhiteSpace = CommandParser.getIndexOfFirstWhiteSpace(input);
        String tailSubString = input.substring(indexOfFirstWhiteSpace, input.length());
        tailSubString = tailSubString.replace(" ", "");
        if (tailSubString.isEmpty()) {
            return -1;
        }
        int taskIndex = -1;
        try {
            Integer.parseInt(tailSubString);
        } catch (NumberFormatException ex) {
            // No need to do anything, because the output will be -1
            // which will be handled in the higher layer
        }
        return taskIndex;
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
            switch (firstWord) {
            case (MARK_DONE_COMMAND_STRING):
                int index = getTaskIndexForMarking(nextLine);
                output = markTaskDone(index);
                break;

            case (MARK_UNDONE_COMMAND_STRING):
                index = getTaskIndexForMarking(nextLine);
                output = markTaskUndone(index);
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
