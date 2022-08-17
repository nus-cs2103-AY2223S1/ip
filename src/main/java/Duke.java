import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private boolean isActive = true;
    private String emoji = "<_>";
    private Scanner reader = new Scanner(System.in);
    private Task[] todos = new Task[100];
    private int pointer = 0;

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
            bot.respond();
        }
    }

    public void greet() {
        System.out.print("Yes? I'm Zlimez~~ \nWhat can I possibly do for you?\n >>>^<<<\n\n");
    }

    public void respond() {
        String userInput = reader.nextLine();
        if (userInput.equals("bye")) {
            bye();
        } else if (userInput.equals("list")) {
            listTasks();
        } else if (userInput.startsWith("mark")) {
            markTask(Integer.parseInt(userInput.substring(5)));
        } else if (userInput.startsWith("unmark")) {
            unmarkTask(Integer.parseInt(userInput.substring(7)));
        } else {
            try {
                addTask(userInput);
            } catch (DukeException e) {
                System.out.println(e.toString());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(new DukeException("Please your task lacks the necessary specifications"));
            }
        }

        System.out.println();
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
        todos[pointer] = newTask;
        pointer++;
        System.out.println("\t\t" + newTask);
        System.out.println("\tWala now you have " + pointer + " tasks in the list.");
    }

    private void listTasks() {
        System.out.println("\tReally? If you are so forgetful...");
        for (int i = 1; i <= pointer; i++) {
            System.out.println("\t" + i + ". " + todos[i - 1]);
        }
    }

    private void markTask(int index) {
        try {
            todos[index - 1].mark();
            System.out.println("\tWellz, I've marked this task for YOU:");
            System.out.println("\t\t" + todos[index - 1]);
        } catch (NullPointerException e) {
            System.out.println(new DukeException("Read the index of the existing tasks carefully..."));
        }
    }

    private void unmarkTask(int index) {
        try {
            todos[index - 1].unmark();
            System.out.println("\t-_-, I've unmarked this task for YOU AGAIN:");
            System.out.println("\t\t" + todos[index - 1]);
        } catch (NullPointerException e) {
            System.out.println(new DukeException("Read the index of the existing tasks carefully..."));
        }
    }

    private void bye() {
        this.isActive = false;
        System.out.println("\tBye. zzz FINALLY~~" + " " + emoji);
        reader.close();
    }
}
