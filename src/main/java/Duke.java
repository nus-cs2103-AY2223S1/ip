import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class Duke {
    private final Task[] tasks;
    private int id = 0;

    public Duke(String name) {
        this.tasks = new Task[100];
        speak("Hello! I'm %s\nWhat do you need to do?", name);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("Duke");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.next();
            String arguments = sc.nextLine();
            if (!duke.callback(command, arguments)) {
                break;
            }
        }
    }

    /**
     * Callback for all commands.
     *
     * @param command First word of user input
     * @param input   Remaining words of user input
     * @return Whether the program should continue running.
     */
    private boolean callback(String command, String input) {
        input = input.strip();
        switch (command) {
            case "bye":
                goodbye();
                return false;
            case "list":
                listHistory();
                break;
            case "todo":
                addTodo(input);
                break;
            case "event":
                addEvent(input);
                break;
            case "deadline":
                addDeadline(input);
                break;
            case "mark":
                setTaskCompletionStatus(input, true);
                break;
            case "unmark":
                setTaskCompletionStatus(input, false);
                break;
            default:
                speak("I don't understand that command");
                break;
        }
        return true;
    }

    /**
     * Handle the {@literal todo} command
     *
     * @param input the input to be handled
     */
    private void addTodo(String input) {
        tasks[id++] = new ToDo(input);
        speak("Got it. I've added this task:\n%s\nNow you have %d tasks in your list", tasks[id - 1], id);
    }

    /**
     * Handle the event command
     *
     * @param input the input to be handled
     */
    private void addEvent(String input) {
        if (input.matches("^.* /at .*$")) {
            String[] parts = input.split(" /at ");
            tasks[id++] = new Event(parts[0].strip(), parts[1].strip());
            speak("Got it. I've added this task:\n%s\nNow you have %d tasks in your list", tasks[id - 1], id);
        } else {
            speak("Invalid event format");
        }
    }

    /**
     * Handle the deadline command
     *
     * @param input the input to be handled
     */
    private void addDeadline(String input) {
        if (input.matches("^.* /by .*$")) {
            String[] parts = input.split(" /by ");
            tasks[id++] = new Deadline(parts[0].strip(), parts[1].strip());
            speak("Got it. I've added this task:\n%s\nNow you have %d tasks in your list", tasks[id - 1], id);
        } else {
            speak("Invalid event format");
        }
    }

    /**
     * Handle the input for the mark and unmark commands.
     *
     * @param input     the input to be handled
     * @param completed the status to be set
     */
    private void setTaskCompletionStatus(String input, boolean completed) {
        boolean isValid = false;
        if (input.matches("^[0-9]+$")) {
            int task = parseInt(input) - 1;
            if (task < id && task >= 0) {
                isValid = true;
            }
        }
        if (!isValid) {
            speak("Invalid task number");
            return;
        }
        if (input.matches("^[0-9]+$")) {
            int task = parseInt(input) - 1;
            if (task < id && task >= 0) {
                tasks[task].setDone(completed);
                if (completed) {
                    speak("Nice! I've marked this task as done:\n%s", tasks[task]);
                } else {
                    speak("Ok, I've marked this task as not done yet:\n%s", tasks[task]);
                }
            } else {
                speak("Invalid task number. You only have %d tasks.", id);
            }
        } else {
            speak("Invalid task number: %s", input);
        }
    }

    /**
     * Get Duke to speak the given text.
     *
     * @param text The text to speak.
     */
    private void speak(String text) {
        String line = "_".repeat(20) + '\n';
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    /**
     * Speaks the specified text defined by it's format and arguments.
     *
     * @param format A format string
     * @param args   Arguments referenced by the format specifiers in the format string.
     */
    private void speak(String format, Object... args) {
        speak(String.format(format, args));
    }

    /**
     * Displays all current tasks.
     */
    public void listHistory() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        IntStream.range(0, this.id).forEach(i -> output.append(String.format("%d. %s%n", i + 1, tasks[i])));
        speak(output.toString());
    }

    private void goodbye() {
        speak("Bye. Hope to see you again soon!");
    }
}
