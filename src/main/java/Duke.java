import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";
    private static final String indentedLine = "     " + line;
    private static final String initialMessage = messageWithIndentedLines("\n      Hello! I'm Duke\n      What can I do for you?\n");
    private static final String byeMessage = messageWithIndentedLines("\n      Bye. This doesn't have to be the end!\n");
    private static final ArrayList<Task> userTasks = new ArrayList<>();

    private static String messageWithIndentedLines(String message) {
        return indentedLine + message + indentedLine;
    }

    public static void main(String[] args) {
        System.out.println(initialMessage);
        Scanner scanner = new Scanner(System.in);
        // the user has to at least input bye to exit
        String userInput = scanner.nextLine();
        while (true) {
            if (userInput.equals("bye")) {
                System.out.println(byeMessage);
                break;
            } else if (userInput.equals("list")) {
                // Idea below of iterating with indices in streams adapted from https://stackoverflow.com/a/42616742
                HashMap<Integer, Task> mappedIndexToUserText = userTasks.stream()
                        .collect(HashMap::new, (hashMap, streamElement) -> hashMap.put(hashMap.size() + 1, streamElement), HashMap::putAll);
                StringBuilder listOfUserText = mappedIndexToUserText.entrySet().stream()
                        .reduce(new StringBuilder(), (stringToBuild, currentEntry) -> stringToBuild.append("\n      ").append(currentEntry.getKey()).append(".").append(currentEntry.getValue().toString()), StringBuilder::append);
                System.out.println(messageWithIndentedLines(listOfUserText.toString() + "\n"));
            } else if (userInput.startsWith("mark")) {
                int index = Character.getNumericValue(userInput.charAt(5)) - 1;
                Task taskToMarkDone = userTasks.get(index);
                taskToMarkDone.setTaskAsDone();
                String toPrint = "\n     Nice! I've marked this task as done:\n       " + taskToMarkDone + "\n";
                System.out.println(messageWithIndentedLines("     Here are the tasks in your list:\n" + toPrint));
            } else if (userInput.startsWith("unmark")) {
                int index = Character.getNumericValue(userInput.charAt(7)) - 1;
                Task taskToMarkDone = userTasks.get(index);
                taskToMarkDone.setTaskAsNotDone();
                String toPrint = "\n     OK, I've marked this task as not done yet:\n       "  + taskToMarkDone + "\n";
                System.out.println(messageWithIndentedLines(toPrint));
            } else { // it must be a task
                String[] commands = userInput.split("\\s+");
                String taskType = commands[0];
                commands[0] = "";
                switch (taskType) {
                    case "todo":
                        userTasks.add(new ToDo(String.join(" ", commands)));
                        break;
                    case "deadline":
                        StringBuilder deadlineDescription = new StringBuilder();
                        int i = 1;
                        while (!commands[i].matches("^/by")) {
                            deadlineDescription.append(commands[i]).append(" ");
                            i++;
                        }
                        StringBuilder deadlineBy = new StringBuilder();
                        for (int k = i + 1; k < commands.length; k++) {
                            deadlineBy.append(commands[k]).append(" ");
                        }
                        userTasks.add(new Deadline(deadlineDescription.toString().strip(), deadlineBy.toString().strip()));
                        break;
                    case "event":
                        StringBuilder eventDescription = new StringBuilder();
                        int j = 1;
                        while (!commands[j].matches("^/at")) {
                            eventDescription.append(commands[j]).append(" ");
                            j++;
                        }
                        StringBuilder eventAt = new StringBuilder();
                        for (int k = j + 1; k < commands.length; k++) {
                            eventAt.append(commands[k]).append(" ");
                        }
                        userTasks.add(new Event(eventDescription.toString().strip(), eventAt.toString().strip()));
                        break;
                }
                System.out.println(messageWithIndentedLines("\n     Got it. I've added this task:\n       " + userTasks.get(userTasks.size() - 1) + "\n     Now you have " + userTasks.size() + " task" + (userTasks.size() == 1 ? "" : "s") + " in the list.\n"));
            }
            userInput = scanner.nextLine();
        }
    }
}
