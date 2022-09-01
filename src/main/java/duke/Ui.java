package duke;

import java.util.stream.Stream;

/**
 * A UI to show outputs, success and error messages to the users.
 */
public class Ui {
    private void print(String s) {
        String seperator = "    ____________________________________________________________\n";
        System.out.println(seperator + Stream.of(s.split("\n")).map(x -> "     " + x + "\n")
                .reduce("", (x, y) -> x + y) + seperator);
    }

    /**
     * Outputs a welcome message.
     */
    public void showGreeting() {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Outputs all tasks or all tasks on a certain date.
     * @param tasks List of tasks.
     * @param date Optional date to find tasks from.
     */
    public void showTasks(TaskList tasks, String... date) {
        String content = "Here are the tasks in your list:\n";
        if (date.length == 0) {
            for (int i = 1; i <= tasks.size(); i++) {
                try {
                    content += String.format("%d.%s\n", i, tasks.get(i));
                } catch (DukeException e) { }
            }
            print(content);
        } else {
            try {
                showTasks(tasks.getTasksByDate(date[0]));
            } catch (DukeException e) {
                showError(e);
            }
        }
    }

    /**
     * Outputs a success message.
     * @param msg Main message. It can be a formatted String.
     * @param args Addition arguments for the formatted message.
     */
    public void showSuccess(String msg, Object... args) {
        print(String.format(msg, args));
    }

    /**
     * Outputs an error message.
     * @param e Error.
     */
    public void showError(DukeException e) {
        print(e.getMessage());
    }

    /**
     * Outputs the closing message.
     */
    public void close() {
        print("Bye. Hope to see you again soon!");
    }
}
