import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     _   _    ______     _____ ____\n"
                + "    | | / \\  |  _ \\ \\   / /_ _/ ___|\n"
                + " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\\n"
                + "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n"
                + " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";
        System.out.println(logo);

        // greeting messages
        say("Hello. I'm Jarvis", true, false);
        say("What can I do for you?", false, true);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> task = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                // when user enters bye
                // exit programme
                say("Bye. Hope to see you again soon.", true, true);
                break;
            }
            else if (userInput.equals("list")) {
                // when user enters list
                // display the current list
                say("Here are the tasks in your list:", true, false);
                for (int i = 0; i < task.size(); i++) {
                    boolean isFirstLine = false;
                    boolean isLastLine = false;
                    if (i == task.size() - 1) {
                        isLastLine = true;
                    }
                    say(i + 1 + ". [" + task.get(i).getStatusIcon() + "] "
                            + task.get(i).getDescription(), isFirstLine, isLastLine);
                }
            }
            else if (userInput.startsWith("mark") && userInput.split(" ", 2).length != 1) {
                // when user enters mark and a number
                // mark the corresponding task as done
                int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                task.get(index).setStatus(true);
                say("Nice! I've marked this task as done:", true, false);
                say("  [" + task.get(index).getStatusIcon() + "] "
                        + task.get(index).getDescription(), false, true);
            }
            else if (userInput.startsWith("unmark") && userInput.split(" ", 2).length != 1) {
                // when user enters unmark and a number
                // mark the corresponding task as not done
                int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                task.get(index).setStatus(false);
                say("OK, I've marked this task as not done yet:", true, false);
                say("  [" + task.get(index).getStatusIcon() + "] "
                        + task.get(index).getDescription(), false, true);
            }
            else {
                // add user input to the list
                task.add(new Task(userInput));
                say("added: " + userInput, true, true);
            }
        }
    }

    public static void say(String message, boolean firstLine, boolean lastLine) {
        String line = "____________________________________________________________";
        if (firstLine) {
            System.out.println(line);
        }
        System.out.println(" " + message);
        if (lastLine) {
            System.out.println(line);
        }
    }
}
