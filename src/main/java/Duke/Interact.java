package Duke;

import Duke.DukeExceptions.DukeException;

import java.util.Scanner;

public class Interact {
    private String line = "_______________________________________";
    private TasksManager tasksManager;

    /**
     * Initializes the user interface and greets the user.
     *
     * @return void.
     */
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

    /**
     * Calls the task manager to do certain tasks
     * depending on the command-line argument.
     *
     * @param word  A String read from the command line.
     * @return void.
     * @throws DukeException  If input is not proper.
     */
    public void handle(String word) throws DukeException {
        if (word.startsWith("bye") || word.startsWith("Bye")) {
            bye();
        } else if (word.startsWith("list") || word.startsWith("List")) {
            tasksManager.showList();
        } else if (word.startsWith("done") || word.startsWith("Done")) {
            tasksManager.markTaskAsDone(Decoder.handleDone(word, tasksManager.numTasks()));
        } else if (word.startsWith("delete") || word.startsWith("Delete")) {
            tasksManager.deleteTask(Decoder.handleDelete(word, tasksManager.numTasks()));
        } else if (word.startsWith("todo") || word.startsWith("deadline") || word.startsWith("event")) {
            tasksManager.addTask(Decoder.handleTasks(word));
        } else if (word.startsWith("date") || word.startsWith("Date")) {
            tasksManager.showDate(Decoder.parseLD(word));
        } else if (word.startsWith("find") || word.startsWith("Find")) {
            tasksManager.find(Decoder.parseFind(word));
        } else {
            throw new DukeException("bad input");
        }
    }

    /**
     * Greets the user.
     *
     * @return void.
     */
    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Says bye to the user and calls the taskmanager
     * to shut down and write files to tasklist.txt.
     *
     * @return void.
     */
    public void bye() {
        tasksManager.closePW();
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(0);
    }

    /**
     * The main class creates an interact object,
     * which controls interactions with the user
     * and calls on other classes to do what the
     * user wishes, accordingly. The Scanner takes in
     * these inputs and seeds them to Interact.
     *
     * @param args user input.
     * @return void.
     */
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

