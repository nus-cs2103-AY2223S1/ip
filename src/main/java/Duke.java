import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

class CustomMessageException extends Exception {
    CustomMessageException(String message) {
        super(message);
    }
}

public class Duke {
    private static final String line = "____________________________________________________________";
    private static final String indentedLine = "     " + line;
    private static final String initialMessage = messageWithIndentedLines("\n      Hello! I'm Duke\n      What can I do for you?\n");
    private static final String byeMessage = messageWithIndentedLines("\n      Bye. This doesn't have to be the end!\n");
    private static final ArrayList<Task> userTasks = new ArrayList<>();

    private static String messageWithIndentedLines(String message) {
        return indentedLine + message + indentedLine;
    }

    private static String generateEmptyDescriptionExceptionMessage(String taskType) {
        return "\n      ☹ OOPS!!! The description of a " + taskType + " cannot be empty.\n";
    }

    private static String generateEmptyActionExceptionMessage(String action) {
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
                    throw new CustomMessageException(messageWithIndentedLines("\n      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
                }
                commands[0] = "";
                int index;
                String toPrint;
                switch (taskType) {
                    case BYE:
                        System.out.println(byeMessage);
                        break whileLoop;
                    case LIST:
                        // Idea below of iterating with indices in streams adapted from https://stackoverflow.com/a/42616742
                        HashMap<Integer, Task> mappedIndexToUserText = userTasks.stream()
                                .collect(HashMap::new, (hashMap, streamElement) -> hashMap.put(hashMap.size() + 1, streamElement), HashMap::putAll);
                        StringBuilder listOfUserText = mappedIndexToUserText.entrySet().stream()
                                .reduce(new StringBuilder(), (stringToBuild, currentEntry) -> stringToBuild.append("\n      ").append(currentEntry.getKey()).append(".").append(currentEntry.getValue().toString()), StringBuilder::append);
                        System.out.println(messageWithIndentedLines("\n     Here are the tasks in your list:" + listOfUserText.toString() + "\n"));
                        break;
                    case MARK:
                        if (commands.length == 1) {
                            throw new CustomMessageException(messageWithIndentedLines(generateEmptyActionExceptionMessage("mark")));
                        }
                        index = Integer.parseInt(commands[1]) - 1;
                        Task taskToMarkDone = userTasks.get(index);
                        taskToMarkDone.setTaskAsDone();
                        toPrint = "\n     Nice! I've marked this task as done:\n       " + taskToMarkDone + "\n";
                        System.out.println(messageWithIndentedLines(toPrint));
                        break;
                    case UNMARK:
                        if (commands.length == 1) {
                            throw new CustomMessageException(messageWithIndentedLines(generateEmptyActionExceptionMessage("unmark")));
                        }
                        index = Integer.parseInt(commands[1]) - 1;
                        Task taskToMarkUnDone = userTasks.get(index);
                        taskToMarkUnDone.setTaskAsNotDone();
                        toPrint = "\n     OK, I've marked this task as not done yet:\n       " + taskToMarkUnDone + "\n";
                        System.out.println(messageWithIndentedLines(toPrint));
                        break;
                    case DELETE:
                        if (commands.length == 1) {
                            throw new CustomMessageException(messageWithIndentedLines(generateEmptyActionExceptionMessage("delete")));
                        }
                        index = Integer.parseInt(commands[1]) - 1;
                        String deletedTaskDescription = userTasks.get(index).toString();
                        userTasks.remove(index);
                        toPrint = "\n     Noted. I've removed this task:\n       " + deletedTaskDescription + "\n     " + generateTasksNumberMessage();
                        System.out.println(messageWithIndentedLines(toPrint));
                        break;
                    case TODO:
                        if (commands.length == 1) {
                            throw new CustomMessageException(messageWithIndentedLines(generateEmptyDescriptionExceptionMessage("todo")));
                        }
                        userTasks.add(new ToDo(String.join(" ", commands).strip()));
                        System.out.println(messageWithIndentedLines("\n     Got it. I've added this task:\n       " + userTasks.get(userTasks.size() - 1) + "\n     " + generateTasksNumberMessage()));
                        break;
                    case DEADLINE:
                        if (commands.length == 1) {
                            throw new CustomMessageException(messageWithIndentedLines(generateEmptyDescriptionExceptionMessage("deadline")));
                        }
                        // can refactor this for deadline and event
                        String[] splitByBy = userInput.split(" /by ");
                        String deadlineDescription = splitByBy[0].substring(9).strip();
                        if (deadlineDescription.isEmpty() || deadlineDescription.isBlank()) {
                            throw new CustomMessageException(messageWithIndentedLines(generateEmptyDescriptionExceptionMessage("deadline")));
                        }
                        String deadlineBy = splitByBy[1];
                        userTasks.add(new Deadline(deadlineDescription, deadlineBy.strip()));
                        System.out.println(messageWithIndentedLines("\n     Got it. I've added this task:\n       " + userTasks.get(userTasks.size() - 1) + "\n     " + generateTasksNumberMessage()));
                        break;
                    case EVENT:
                        if (commands.length == 1) {
                            throw new CustomMessageException(messageWithIndentedLines(generateEmptyDescriptionExceptionMessage("event")));
                        }
                        String[] splitByAt = userInput.split(" /at ");
                        String eventDescription = splitByAt[0].substring(6).strip();
                        if (eventDescription.isEmpty() || eventDescription.isBlank()) {
                            throw new CustomMessageException(messageWithIndentedLines(generateEmptyDescriptionExceptionMessage("event")));
                        }
                        String eventAt = splitByAt[1];
                        userTasks.add(new Event(eventDescription, eventAt.strip()));
                        System.out.println(messageWithIndentedLines("\n     Got it. I've added this task:\n       " + userTasks.get(userTasks.size() - 1) + "\n     " + generateTasksNumberMessage()));
                        break;
                    default:
                        throw new CustomMessageException(messageWithIndentedLines("\n      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
                }
            } catch (CustomMessageException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }
}
