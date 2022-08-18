import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String GREETING_MESSAGE = "Hi there! I' am Duke, your personal time manager."
            + "\nWhat can I help you?";
    private static final String EXIT_OUTPUT_STRING = "Bye! See you next time!";
    private static final String EXIT_COMMAND_STRING = "bye";
    private static final String DISPLAY_LIST_COMMAND_STRING = "list";


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

    private void standBy() {
        Scanner scanner = new Scanner(System.in);
        boolean quited = false;
        String output;

        while (!quited) {
            String nextLine = scanner.nextLine();
            if (nextLine.isEmpty()) {
                continue;
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
