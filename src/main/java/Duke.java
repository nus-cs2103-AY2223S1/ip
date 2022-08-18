import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String GREETING_MESSAGE = "Hi there! I' am Duke, your personal time manager."
            + "\nWhat can I help you?";
    private static final String EXIT_OUTPUT_STRING = "Bye! See you next time!";
    private static final String EXIT_COMMAND_STRING = "bye";
    private static final String DISPLAY_LIST_COMMAND_STRING = "list";
    private static final String MARK_DONE_COMMAND_STRING = "mark";
    private static final String MARK_UNDONE_COMMAND_STRING = "unmark";


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

    private String getOutputForNewTask(String taskTitle) {
        taskList.add(new Task(taskTitle));
        return "added: " + taskTitle;
    }

    private int getIndexOfFirstWhiteSpace(String input) {
        int indexOfFirstWhiteSpace = 0;
        for (; indexOfFirstWhiteSpace < input.length(); indexOfFirstWhiteSpace++) {
            if (input.charAt(indexOfFirstWhiteSpace) == ' ') {
                break;
            }
        }
        return indexOfFirstWhiteSpace;
    }

    private String getFirstWord(String input) {
        int indexOfFirstWhiteSpace = getIndexOfFirstWhiteSpace(input);
        return input.substring(0, indexOfFirstWhiteSpace);
    }

    private int getTaskIndexForMarking(String input) {
        int indexOfFirstWhiteSpace = getIndexOfFirstWhiteSpace(input);
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

    private void markTaskDone(int index) {
        if (index < 0 || index >= taskList.size()) {

        } else {
            taskList.get(index).markDone();
        }
    }

    private void markTaskUndone(int index) {
        if (index < 0 || index >= taskList.size()) {

        } else {
            taskList.get(index).markUndone();
        }
    }

    private void standBy() {
        Scanner scanner = new Scanner(System.in);
        boolean quited = false;
        String output;

        while (!quited) {
            String nextLine = scanner.nextLine();
            if (nextLine.isEmpty()) {
                continue;
            }

            boolean commandFetched = false;

            String firstWord = getFirstWord(nextLine);
            switch (firstWord) {
            case (MARK_DONE_COMMAND_STRING):
                int index = getTaskIndexForMarking(nextLine);
                markTaskDone(index);
                break;

            case (MARK_UNDONE_COMMAND_STRING):
                index = getTaskIndexForMarking(nextLine);
                markTaskUndone(index);
                break;

            default:
                break;
            }

            switch (nextLine) {
            case (EXIT_COMMAND_STRING):
                output = EXIT_OUTPUT_STRING;
                quited = true;
                break;

            case (DISPLAY_LIST_COMMAND_STRING):
                output = getListInfo();
                break;

            default:
                output = getOutputForNewTask(nextLine);
                break;
            }

            System.out.println(OutputFormatter.formatOutput(output));
        }
    }
}
