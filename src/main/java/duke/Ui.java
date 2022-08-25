package duke;

import java.util.stream.Stream;

public class Ui {
    public void print(String s) {
        String seperator = "    ____________________________________________________________\n";
        System.out.println(seperator + Stream.of(s.split("\n")).map(x -> "     " + x + "\n")
                .reduce("", (x,y) -> x + y) + seperator);
    }

    public void showGreeting() {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

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

    public void showSuccess(String msg, Object... args) {
        print(String.format(msg, args));
    }

    public void showError(DukeException e) {
        print(e.getMessage());
    }

    public void close() {
        print("Bye. Hope to see you again soon!");
    }
}
