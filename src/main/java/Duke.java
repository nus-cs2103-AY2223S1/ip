import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
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
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            try {
                String userInput = scanner.nextLine();

                if (userInput.equals("bye")) {
                    // when user enters bye
                    // exit programme
                    exit();
                    break;
                } else if (userInput.equals("list")) {
                    // when user enters list
                    // display the current list
                    list(tasks);
                } else if (userInput.startsWith("mark")) {
                    // when user enters mark and a number
                    // mark the corresponding task as done
                    if (userInput.split(" ", 2).length == 1) {
                        throw new DukeException("Please specify which task to mark.");
                    }
                    int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                    if (index >= tasks.size()) {
                        throw new DukeException("It seems that there is no corresponding task.");
                    }
                    mark(tasks, index);
                } else if (userInput.startsWith("unmark") && userInput.split(" ", 2).length != 1) {
                    // when user enters unmark and a number
                    // mark the corresponding task as not done
                    int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                    unmark(tasks, index);
                } else {
                    // add user input to the list
                    // check what type of task it is

                    if (userInput.startsWith("todo") && userInput.split(" ", 2).length != 1) {
                        // the task is a todo
                        String description = userInput.split(" ", 2)[1].split("/")[0].trim();

                        ToDo todo = new ToDo(description);
                        tasks.add(todo);

                        say("Got it. I've added this task:", true, false);
                        say("  " + todo.toString(), false, false);
                        say("Now you have " + tasks.size() + " tasks in the list.", false, true);
                    } else if (userInput.startsWith("deadline") && userInput.split(" ", 2).length != 1) {
                        // the task is a deadline
                        String description = userInput.split(" ", 2)[1].split("/")[0].trim();
                        String by = userInput.split(" ", 2)[1].split("/")[1].split(" ", 2)[1];

                        Deadline deadline = new Deadline(description, by);
                        tasks.add(deadline);

                        say("Got it. I've added this task:", true, false);
                        say("  " + deadline.toString(), false, false);
                        say("Now you have " + tasks.size() + " tasks in the list.", false, true);
                    } else if (userInput.startsWith("event") && userInput.split(" ", 2).length != 1) {
                        // the task is an event
                        String description = userInput.split(" ", 2)[1].split("/")[0].trim();
                        String at = userInput.split(" ", 2)[1].split("/")[1].split(" ", 2)[1];

                        Event event = new Event(description, at);
                        tasks.add(event);

                        say("Got it. I've added this task:", true, false);
                        say("  " + event.toString(), false, false);
                        say("Now you have " + tasks.size() + " tasks in the list.", false, true);
                    }
                }
            } catch (DukeException exception) {
                say(exception.getMessage(), true, true);
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

    public static void exit() {
        say("Bye. Hope to see you again soon.", true, true);
    }

    public static void list(ArrayList<Task> tasks) {
        say("Here are the tasks in your list:", true, false);
        for (int i = 0; i < tasks.size(); i++) {
            boolean isFirstLine = false;
            boolean isLastLine = i == tasks.size() - 1;
            say(i + 1 + ". " + tasks.get(i).toString(), isFirstLine, isLastLine);
        }
    }

    public static void mark(ArrayList<Task> tasks, int index) {
        tasks.get(index).setStatus(true);

        say("Nice! I've marked this task as done:", true, false);
        say(tasks.get(index).toString(), false, true);
    }

    public static void unmark(ArrayList<Task> tasks, int index) {
        tasks.get(index).setStatus(false);

        say("OK, I've marked this task as not done yet:", true, false);
        say(tasks.get(index).toString(), false, true);
    }
}
