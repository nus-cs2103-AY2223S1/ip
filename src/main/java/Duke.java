import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> LIST_OF_THINGS = new ArrayList<>();

    private static class Task {
        private final String task_name;
        private boolean isCompleted;

        public Task(String name) {
            task_name = name;
            isCompleted = false;
        }

        public void markComplete() {
            isCompleted = true;
        }

        public void markIncomplete() {
            isCompleted = false;
        }

        @Override
        public String toString() {
            if (isCompleted) {
                return String.format("[X] %s", task_name);
            } else {
                return String.format("[ ] %s", task_name);
            }
        }
    }

    public static boolean isMarkCommand(String str) {
        if (str.length() < 6) {
            return false;
        }
        if (!str.startsWith("mark")) {
            return false;
        }
        try {
            Integer.parseInt(str.substring(5));
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isUnmarkCommand(String str) {
        if (str.length() < 8) {
            return false;
        }
        if (!str.startsWith("unmark")) {
            return false;
        }
        try {
            Integer.parseInt(str.substring(7));
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }


    public static void main(String[] args) {
        System.out.println("Hi, I'm Duke. What can I do for you?");
        Scanner keyboard = new Scanner(System.in);
        String message = keyboard.nextLine();
        while (true) {
            if (message.equals("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            } else if (message.equals("list")) {
                StringBuilder output_message = new StringBuilder();
                for (int i = 0; i < LIST_OF_THINGS.size(); i++) {
                    output_message.append(String.format("%d. %s", i + 1, LIST_OF_THINGS.get(i)));
                    output_message.append("\n");
                }
                System.out.println(output_message);
            } else if (isMarkCommand(message)) {
                int index = Integer.parseInt(message.substring(5));
                if (index > LIST_OF_THINGS.size() || index < 1) {
                    System.out.printf("Sorry, task %d does not exist!%n", index);
                } else {
                    Task task = LIST_OF_THINGS.get(index - 1);
                    task.markComplete();
                    System.out.printf("Good Job! This task has been marked as done:%n%s%n", task);
                }
            } else if (isUnmarkCommand(message)) {
                int index = Integer.parseInt(message.substring(7));
                if (index > LIST_OF_THINGS.size() || index < 1) {
                    System.out.printf("Sorry, task %d does not exist!%n", index);
                } else {
                    Task task = LIST_OF_THINGS.get(index - 1);
                    task.markIncomplete();
                    System.out.printf("Get it done! This task has been marked as undone:%n%s%n", task);
                }
            } else {
                LIST_OF_THINGS.add(new Task(message));
                System.out.printf("Added: %s%n", message);
            }
            message = keyboard.nextLine();
        }
    }
}
