package duke;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;

public class Ui {
    static final String CHATBOX_NAME = "Ado";
    static final String PARTITION = "<><><><><><><><><><><><><><><><><><><><><><><><><><><><>";

    public Ui() {
    }

    void showWelcome() {
        String startMessage = PARTITION + "\n  /\\_/\\\n"
                + " ( o.o ) < Yo! I'm " + CHATBOX_NAME + "!\n"
                + "  > ^ <    What can I do for you? :)\n" + PARTITION + "\n";
        printMessage(startMessage);
    }

    String readCommand() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        validate(input.toLowerCase());
        return input;
    }

    public void showLine() {
        System.out.println(PARTITION);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    //todo: add javaDoc
    public String listToString(List<Task> list) {
        if (list.size() == 0) {
            return "List is empty ~\n";
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list: \n");
        for (int i = 0; i < list.size(); i++) {
            output.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return output.toString();
    }

    public String listToStringWithWord(List<Task> list, String word) {
        boolean foundMatchingTask = false;
        if (list.size() == 0) {
            return "List is empty ~\n";
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks containing \"" + word + "\":\n");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDescription().contains(word)) {
                output.append(i + 1).append(". ").append(list.get(i)).append("\n");
                foundMatchingTask = true;
            }
        }
        if (!foundMatchingTask) {
            return "No matching tasks with \"" + word + "\" :(";
        }
        return output.toString();
    }

    void showLoadingError() {
        System.out.println("Error in loading task :( New task list created!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    void validate(String input) throws DukeException {
        String[] commandSegments = input.split(" ", 2);
        String mainCommand = commandSegments[0].toLowerCase().trim();

        String[] allCommands = {"list", "bye", "todo", "deadline", "event", "mark", "unmark", "delete", "find"};
        if (!Arrays.asList(allCommands).contains(mainCommand)) {
            //handles invalid commands
            throw new DukeException(mainCommand + "? (´･_･`) I don't know what that means\n");
        }
        String[] commandsWithDescription = {"todo", "deadline", "event", "find"};
        if (Arrays.asList(commandsWithDescription).contains(mainCommand)) {

            if (commandSegments.length <= 1) {
                throw new DukeException("The description of a " + mainCommand + " cannot be empty. （ﾟДﾟ ）\n");
            }

            switch (mainCommand) {
            case "todo":
            case "find":
                break;
            case "deadline":
                String[] deadlineSegments = commandSegments[1].split("/by", 2);
                if (deadlineSegments.length < 2) {
                    throw new DukeException("The date of deadline cannot be empty. （ﾟДﾟ ）\n");
                }
                String description = deadlineSegments[0];
                String by = deadlineSegments[1].trim();
                try {
                    LocalDate date = LocalDate.parse(by);
                } catch (Exception ex) {
                    throw new DukeException("Put date after /by in terms of yyyy-MM-dd");
                }
                break;
            case "event":
                String[] eventSegments = commandSegments[1].split("/at", 2);
                if (eventSegments.length < 2) {
                    throw new DukeException("The date of event cannot be empty. （ﾟДﾟ ）\n");
                }
                break;
            default:
                throw new DukeException(mainCommand + "? (´･_･`) I don't know what that means\n");
            }
        }
    }
}
