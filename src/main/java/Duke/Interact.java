package Duke;

import Duke.DukeExceptions.DukeException;

import java.util.Scanner;

public class Interact {
    private String line = "_______________________________________";
    private TasksManager tasksManager;

    public void start() {
        tasksManager = new TasksManager();
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
    }
    public void handle(String word) throws DukeException {
        if (word.startsWith("bye") || word.startsWith("Bye")) {
            bye();
        } else if (word.startsWith("list") || word.startsWith("List")) {
            tasksManager.showList();
        } else if (word.startsWith("done") || word.startsWith("Done")) {
            tasksManager.markTaskAsDone(Decoder.handleDone(word));
        } else if (word.startsWith("delete") || word.startsWith("Delete")) {
            tasksManager.deleteTask(Decoder.handleDelete(word));
        } else if (word.startsWith("todo") || word.startsWith("deadline") || word.startsWith("event")) {
            tasksManager.addTask(Decoder.handleTasks(word));
        } else if (word.startsWith("date") || word.startsWith("Date")) {
            tasksManager.showDate(Decoder.parseLD(word));
        } else {
            throw new DukeException("bad input");
        }
    }

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void bye() {
        tasksManager.closePW();
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(0);
    }

    public static void main(String[] args) {
        Interact interact = new Interact();
        interact.start();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String nextWord = sc.nextLine();
            if (!nextWord.equals("")) {
                try {
                    interact.handle(nextWord);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
        }
    }
}

