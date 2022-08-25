package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {
    protected String logo = "     _   _    ______     _____ ____\n"
            + "    | | / \\  |  _ \\ \\   / /_ _/ ___|\n"
            + " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\\n"
            + "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n"
            + " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

    public void greet() {
        System.out.println(logo);
        say("Hello. I'm Jarvis", true, false);
        say("What can I do for you?", false, true);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command;
        command = scanner.nextLine();
        return command;
    }

    public void addMessage(TaskList tasks, Task task) {
        say("Got it. I've added this task:", true, false);
        say("  " + task, false, false);
        say("Now you have " + tasks.size() + " tasks in the list.", false, true);
    }

    public void deleteMessage(TaskList tasks, int index, boolean isDone) {
        if (!isDone) {
            say("Noted. I've removed this task:", true, false);
            say(tasks.get(index).toString(), false, false);
        } else {
            say("Now you have " + tasks.size() + " tasks in the list.", false, true);
        }
    }

    public void listMessage(TaskList tasks) {
        say("Here are the tasks in your list:", true, false);
        for (int i = 0; i < tasks.size(); i++) {
            boolean isFirstLine = false;
            boolean isLastLine = i == tasks.size() - 1;
            say(i + 1 + ". " + tasks.get(i).toString(), isFirstLine, isLastLine);
        }
    }

    public void markMessage(TaskList tasks, int index) {
        say("Nice! I've marked this task as done:", true, false);
        say(tasks.get(index).toString(), false, true);
    }

    public void unmarkMessage(TaskList tasks, int index) {
        say("OK, I've marked this task as not done yet:", true, false);
        say(tasks.get(index).toString(), false, true);
    }

    public void exitMessage() {
        say("Bye. Hope to see you again soon.", true, true);
    }

    public void showLoadingError() {
        say("Encountered error while loading.", true, true);
    }

    public void showError(String errorMessage) {
        say("Encountered error: " + errorMessage, true, true);
    }

    public void say(String message, boolean isFirstLine, boolean isLastLine) {
        String line = "____________________________________________________________";
        if (isFirstLine) {
            System.out.println(line);
        }
        System.out.println(" " + message);
        if (isLastLine) {
            System.out.println(line);
        }
    }
}
