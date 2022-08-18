import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Duke {

    private static class OutputFormatter {
        private static final String HORIZONTAL_BAR = "-------------------------";

        public static String formatOutput(String output) {
            return HORIZONTAL_BAR + '\n' + output + '\n' + HORIZONTAL_BAR + '\n' + '\n';
        }
    }

    private interface Actionable {
        public void takeAction();
    }

    private static class Task {

        private String taskTitle;

        Task(String taskTitle) {
            this.taskTitle = taskTitle;
        }
    }

     private static class CommandParser {

        private Map<String, CommandType> commandTypeMap;

        CommandParser() {
            commandTypeMap = new HashMap<>();
            CommandType[] commandTypes = CommandType.class.getEnumConstants();
            for (CommandType type: commandTypes) {
                commandTypeMap.put(type.getCommandString(), type);
            }
        }

        Actionable parse(String commandString) {
            return new Command(CommandType.EXIT) {
                @Override
                public void takeAction() {

                }
            };
        }
    }

    private static final String GREETING_MESSAGE = "Hi there! I' am Duke, your personal time manager."
            + "\nWhat can I help you?";
    private static final String EXIT_OUTPUT_STRING = "Bye! See you next time!";
    private static final String EXIT_COMMAND_STRING = "bye";


    private CommandParser commandParser;
    private List<Task> taskList;

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

    private void standBy() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String nextLine = scanner.nextLine();
            Actionable = commandParser.parse(nextLine);
            if (EXIT_COMMAND_STRING.equals(nextLine)) {
                System.out.println(OutputFormatter.formatOutput(EXIT_OUTPUT_STRING));
                break;
            } else {
                System.out.println(OutputFormatter.formatOutput(nextLine));
            }
        }
    }
}
