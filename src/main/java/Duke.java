import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private boolean isActive = true;
    private String emoji = "<_>";
    private Scanner reader = new Scanner(System.in);
    private List<Task> todos = new ArrayList<>();

    private enum Action {
        MARK (5),
        UNMARK (7),
        DELETE (7);

        private int parseIndex;

        Action(int parseIndex) {
            this.parseIndex = parseIndex;
        }

        int process(String userInput) throws DukeException {
            try {
                return Integer.parseInt(userInput.substring(parseIndex));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Do tell me the index...");
            } catch (NumberFormatException e) {
                throw new DukeException("HELLO do you know index is a number");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke bot = new Duke();
        bot.greet();
        while (bot.isActive) {
            try {
                bot.respond();
            } catch (DukeException e) {
                System.out.println("\t" + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    public void greet() {
        System.out.print("Yes? I'm Zlimez~~ \nWhat can I possibly do for you?\n >>>^<<<\n\n");
    }

    public void respond() throws DukeException {
        String userInput = reader.nextLine();
        if (userInput.equals("bye")) {
            bye();
        } else if (userInput.equals("list")) {
            listTasks();
        } else if (userInput.startsWith("mark")) {
            markTask(Action.MARK.process(userInput));
        } else if (userInput.startsWith("unmark")) {
            unmarkTask(Action.UNMARK.process(userInput));
        } else if (userInput.startsWith("delete")) {
            deleteTask(Action.DELETE.process(userInput));
        } else {
            try {
                addTask(userInput);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please your task lacks the necessary specifications");
            }
        }
    }

    private void addTask(String task) throws DukeException {
        Task newTask;
        if (task.startsWith("todo")) {
            newTask = new Todo(task);
        } else if (task.startsWith("deadline")) {
            newTask = new Deadline(task);
        } else if (task.startsWith("event")) {
            newTask= new Event(task);
        } else {
            // Fallback should not occur
            throw new DukeException("Your command lacks the keyword for me to act upon");
        }

        System.out.println("\tLazily added this task for you " + emoji);
        todos.add(newTask);
        System.out.println("\t\t" + newTask);
        System.out.println("\tWala now you have " + todos.size() + " tasks in the list.");
    }

    private void deleteTask(int index) throws DukeException {
        try {
            Task removed = todos.remove(index - 1);
            System.out.println("\tYES, I've removed this task for YOU:");
            System.out.println("\t\t" + removed);
            System.out.println("\tWala now you have " + todos.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Read the index of the existing tasks carefully...");
        }
    }

    private void listTasks() {
        System.out.println("\tReally? If you are so forgetful...");
        for (int i = 1; i <= todos.size(); i++) {
            System.out.println("\t" + i + ". " + todos.get(i - 1));
        }
    }

    private void markTask(int index) throws DukeException {
        try {
            todos.get(index - 1).mark();
            System.out.println("\tWellz, I've marked this task for YOU:");
            System.out.println("\t\t" + todos.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Read the index of the existing tasks carefully...");
        }
    }

    private void unmarkTask(int index) throws DukeException {
        try {
            todos.get(index - 1).unmark();
            System.out.println("\t-_-, I've unmarked this task for YOU AGAIN:");
            System.out.println("\t\t" + todos.get(index - 1));
        } catch (NullPointerException e) {
            throw new DukeException("Read the index of the existing tasks carefully...");
        }
    }

    private void bye() {
        this.isActive = false;
        System.out.println("\tBye. zzz FINALLY~~" + " " + emoji);
        reader.close();
    }
}
