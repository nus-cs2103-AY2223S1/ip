import java.util.Arrays;
import java.util.Scanner;

public class Mia {
    private static final TaskManager tasksManager = new TaskManager();

    public static void main(String[] args) {
        final String logo = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                            "┃ You are talking to MIA... ┃\n" +
                            "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";
        System.out.println(logo);

        final int windowWidth = 50;
        printResponse(new Span("Hello there!"), windowWidth);
        final Scanner sc = new Scanner(System.in);

        System.out.print("Enter a command: ");
        while (sc.hasNextLine()) {
            final String line = sc.nextLine().strip();

            // Replaces the entered command (previous line) with a bubble
            System.out.print("\u001B[1A\u001B[K");
            printCommand(new Span(line));

            if (line.equals("bye")) {
                printResponse(new Span("See you!"), windowWidth);
                break;
            } else if (line.equals("list")) {
                printResponse(new Span(tasksManager.toString()), windowWidth);
                System.out.print("Enter a command: ");
                continue;
            } else if (line.startsWith("delete ")) {
                final int number = Integer.parseInt(line.substring(7));
                if (tasksManager.deleteTask(number)) {
                    printResponse(new Span("Task has been deleted!"), windowWidth);
                } else {
                    printResponse(new Span(String.format(
                            "Something went wrong when deleting task %d! Likely, you specified an invalid task number.",
                            number)), windowWidth);
                }
                System.out.print("Enter a command: ");
                continue;
            } else if (line.startsWith("mark ")) {
                final int number = Integer.parseInt(line.substring(5));
                if (tasksManager.checkTask(number)) {
                    printResponse(new Span("Task has been marked as done!"), windowWidth);
                } else {
                    printResponse(new Span(String.format(
                            "Task not modified! Either the task is already done, or you specified an invalid task number %d.",
                            number)), windowWidth);
                }
                System.out.print("Enter a command: ");
                continue;
            } else if (line.startsWith("unmark ")) {
                final int number = Integer.parseInt(line.substring(7));
                if (tasksManager.uncheckTask(number)) {
                    printResponse(new Span("Task has been marked as not done!"), windowWidth);
                } else {
                    printResponse(new Span(String.format(
                            "Task not modified! Either the task is still not done, or you specified an invalid task number %d.",
                            number)), windowWidth);
                }
                System.out.print("Enter a command: ");
                continue;
            } else if (line.startsWith("todo ")) {
                final Task todo = new Todo(line.substring(5));
                tasksManager.addTask(todo);
                printResponse(new Span(String.format("Added todo \"%s\" to tasks list!", todo.getTitle())), windowWidth);
                continue;
            } else if (line.startsWith("deadline ")) {
                final String[] data = line.substring(9).split("/by", 2);
                if (data.length == 2) {
                    final Task deadline = new Deadline(data[0], data[1]);
                    tasksManager.addTask(deadline);
                    printResponse(new Span(String.format("Added \"%s\" (task with deadline) to tasks list!", deadline.getTitle())), windowWidth);
                } else {
                    printResponse(new Span("Incorrect format of deadline command!"), windowWidth);
                }
                continue;
            } else if (line.startsWith("event ")) {
                final String[] data = line.substring(6).split("/at", 2);
                if (data.length == 2) {
                    final Task event = new Event(data[0], data[1]);
                    tasksManager.addTask(event);
                    printResponse(new Span(String.format("Added new event \"%s\" to tasks list!", event.getTitle())), windowWidth);
                } else {
                    printResponse(new Span("Incorrect format of event command!"), windowWidth);
                }
                continue;
            }
            printResponse(new Span("Sorry boss, I don't know what you are trying to say 😟"), windowWidth);
            System.out.print("Enter a command: ");
        }
        sc.close();
    }

    private static Text[] breakLines(Text data) {
        // Break strings at the next word after line length hits 30 characters
        return data.lines()
                .flatMap((s) -> Arrays.stream(s.split("(?<=\\G\\b?.{30,}\\s)")))
                .map(Text::strip)
                .toArray(Text[]::new);
    }

    private static void printResponse(Text response, int windowWidth) {
        final Text[] lines = breakLines(response);
        int maxLength = 3; // prevents negative count
        for (int i = 0; i < lines.length; i++) {
            if (maxLength < lines[i].length()) {
                maxLength = lines[i].length();
            }
        }
        final int paddingLength = windowWidth - maxLength - 4;
        StringBuilder sb = new StringBuilder(" ".repeat(paddingLength))
                .append("╭").append("─".repeat(maxLength + 2)).append("╮\n");
        // Pad lines right
        final String formatString = "%-" + maxLength + "s";
        for (int i = 0; i < lines.length; i++) {
            sb.append(" ".repeat(paddingLength))
                    .append("│ ").append(String.format(formatString, lines[i])).append(" │\n");
        }
        sb.append(" ".repeat(paddingLength))
                .append("╰").append("─".repeat(maxLength)).append("╮┬╯\n")
                .append(" ".repeat(windowWidth-7)).append("MIA ╰╯ \n");
        System.out.printf(sb.toString());
    }

    private static void printCommand(Text command) {
        final Text[] lines = breakLines(command);
        int maxLength = 3; // prevents negative count
        for (int i = 0; i < lines.length; i++) {
            if (maxLength < lines[i].length()) {
                maxLength = lines[i].length();
            }
        }
        StringBuilder sb = new StringBuilder("╭").append("─".repeat(maxLength + 2)).append("╮\n");
        // Pad lines right
        final String formatString = "%-" + maxLength + "s";
        for (int i = 0; i < lines.length; i++) {
            sb.append("│ ").append(String.format(formatString, lines[i])).append(" │\n");
        }
        sb.append("╰┬╭").append("─".repeat(maxLength)).append("╯\n")
                .append(" ╰╯ You").append(" ".repeat(maxLength-3)).append("\n");
        System.out.printf(sb.toString());
    }
}
