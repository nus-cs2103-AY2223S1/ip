import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";
    private static final String indentedLine = "     " + line;
    private static final String initialMessage = indentedMessage(
            "\n      Hello! I'm Duke\n      What can I do for you?\n");
    private static final String byeMessage = indentedMessage("\n      Bye. This doesn't have to be the end!\n");
    private static final ArrayList<Task> userTasks = new ArrayList<>();

    private static String indentedMessage(String message) {
        return indentedLine + message + indentedLine;
    }

    private static String generateEmptyDescMessage(String taskType) {
        return "\n      ☹ OOPS!!! The description of a " + taskType + " cannot be empty.\n";
    }

    private static String generateEmptyActionMessage(String action) {
        return "\n      ☹ OOPS!!! The action to " + action + " must have the index as an argument.\n";
    }

    private static String generateTasksNumberMessage() {
        return "Now you have " + userTasks.size() + " task" + (userTasks.size() == 1 ? "" : "s") + " in the list.\n";
    }

    public static void main(String[] args) {
        System.out.println(initialMessage);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        whileLoop:
        while (scanner.hasNextLine()) {
            try {
                userInput = scanner.nextLine();
                String[] commands = userInput.split("\\s+");
                Command taskType;
                try {
                    taskType = Command.valueOf(commands[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new CustomMessageException(indentedMessage("\n      ☹ OOPS!!! I'm sorry, but I " +
                            "don't know what that means :-(\n"));
                }
                commands[0] = "";
                int index;
                String toPrint;
                switch (taskType) {
                    case BYE:
                        System.out.println(byeMessage);
                        break whileLoop;
                    case LIST:
                        // Idea below of iterating with indices in streams adapted from
                        // https://stackoverflow.com/a/42616742
                        HashMap<Integer, Task> mappedIndexToUserText = userTasks.stream()
                                .collect(HashMap::new,
                                        (hashMap, streamElement) -> hashMap.put(hashMap.size() + 1, streamElement),
                                        HashMap::putAll);
                        StringBuilder listOfUserText = mappedIndexToUserText.entrySet().stream().reduce(
                                new StringBuilder(), (stringToBuild, currentEntry) -> stringToBuild.append("\n      ")
                                        .append(currentEntry.getKey()).append(".")
                                        .append(currentEntry.getValue().toString()), StringBuilder::append);
                        System.out.println(indentedMessage("\n     Here are the tasks in your list:"
                                + listOfUserText.toString() + "\n"));
                        break;
                    case MARK:
                        if (commands.length == 1) {
                            throw new CustomMessageException(indentedMessage(generateEmptyActionMessage("mark")));
                        }
                        index = Integer.parseInt(commands[1]) - 1;
                        Task taskToMarkDone = userTasks.get(index);
                        taskToMarkDone.setTaskAsDone();
                        toPrint = "\n     Nice! I've marked this task as done:\n       " + taskToMarkDone + "\n";
                        System.out.println(indentedMessage(toPrint));
                        break;
                    case UNMARK:
                        if (commands.length == 1) {
                            throw new CustomMessageException(
                                    indentedMessage(generateEmptyActionMessage("unmark")));
                        }
                        index = Integer.parseInt(commands[1]) - 1;
                        Task taskToMarkUnDone = userTasks.get(index);
                        taskToMarkUnDone.setTaskAsNotDone();
                        toPrint = "\n     OK, I've marked this task as not done yet:\n       "
                                + taskToMarkUnDone + "\n";
                        System.out.println(indentedMessage(toPrint));
                        break;
                    case DELETE:
                        if (commands.length == 1) {
                            throw new CustomMessageException(
                                    indentedMessage(generateEmptyActionMessage("delete")));
                        }
                        index = Integer.parseInt(commands[1]) - 1;
                        String deletedTaskDescription = userTasks.get(index).toString();
                        userTasks.remove(index);
                        toPrint = "\n     Noted. I've removed this task:\n       "
                                + deletedTaskDescription + "\n     " + generateTasksNumberMessage();
                        System.out.println(indentedMessage(toPrint));
                        break;
                    case TODO:
                        parseNewTaskCommand(userInput, commands.length, Command.TODO, "");
                        break;
                    case DEADLINE:
                        parseNewTaskCommand(userInput, commands.length, Command.DEADLINE, " /by ");
                        break;
                    case EVENT:
                        parseNewTaskCommand(userInput, commands.length, Command.EVENT, " /at ");
                        break;
                    default:
                        throw new CustomMessageException(indentedMessage(
                                "\n      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
                }
            } catch (CustomMessageException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void parseNewTaskCommand(String userInput, int commandsLen, Command taskCommand, String toSplitBy)
            throws CustomMessageException {
        if (commandsLen == 1) {
            throw new CustomMessageException(indentedMessage(generateEmptyDescMessage(taskCommand.commandString)));
        }
        if (taskCommand == Command.TODO) {
            userTasks.add(new ToDo(userInput.substring(5).strip()));
        } else if (taskCommand == Command.EVENT || taskCommand == Command.DEADLINE) {
            String[] splitString = userInput.split(toSplitBy);
            String taskDescription = splitString[0].substring(taskCommand.getString().length() + 1).strip();
            if (taskDescription.isEmpty() || taskDescription.isBlank()) {
                throw new CustomMessageException(indentedMessage(generateEmptyDescMessage(taskCommand.getString())));
            }
            String userRequirement = splitString[1].strip();
            Task newTask;
            if (taskCommand == Command.EVENT) {
                newTask = new Event(taskDescription, userRequirement);
            } else {
                newTask = new Deadline(taskDescription, userRequirement);
            }
            userTasks.add(newTask);
        }
        System.out.println(indentedMessage("\n     Got it. I've added this task:\n       "
                + userTasks.get(userTasks.size() - 1) + "\n     " + generateTasksNumberMessage()));
    }

    /**
     * An enum class that contains all the valid user commands.
     */
    public enum Command {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        private final String commandString;

        Command(String commandString) {
            this.commandString = commandString;
        }

        /**
         * Method to get the String representation of the enum
         * @return Returns the String representation of the enum
         */
        public String getString() {
            return this.commandString;
        }
    }
}
