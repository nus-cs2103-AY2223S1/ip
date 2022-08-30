package Duke.DukeHandlers;

import Duke.DukeExceptions.DukeException;

import java.util.Scanner;

public class Interact {
    private String line = "_______________________________________";
    private TasksManager tasksManager;

    /**
     * Greets the user.
     *
     * @return void.
     */
    public String start() {
        tasksManager = new TasksManager();
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    /**
     * Calls the task manager to do certain tasks
     * depending on the command-line argument.
     *
     * @param word  A String read from the command line.
     * @return void.
     * @throws DukeException  If input is not proper.
     */
    public String handle(String word) throws DukeException {
        if (word.startsWith("bye") || word.startsWith("Bye")) {
            bye();
        } else if (word.startsWith("list") || word.startsWith("List")) {
            return tasksManager.showList();
        } else if (word.startsWith("done") || word.startsWith("Done")) {
            return tasksManager.markTaskAsDone(Decoder.handleDone(word, tasksManager.numTasks()));
        } else if (word.startsWith("delete") || word.startsWith("Delete")) {
            return tasksManager.deleteTask(Decoder.handleDelete(word, tasksManager.numTasks()));
        } else if (word.startsWith("todo") || word.startsWith("deadline") || word.startsWith("event")) {
            return tasksManager.addTask(Decoder.handleTasks(word));
        } else if (word.startsWith("date") || word.startsWith("Date")) {
            return tasksManager.showDate(Decoder.parseLD(word));
        } else if (word.startsWith("find") || word.startsWith("Find")) {
            return tasksManager.find(Decoder.parseFind(word));
        } else {
            throw new DukeException("bad input");
        }
        return "";
    }

    /**
     * Shuts down program.
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
     * Creates an Interact, which controls interactions
     * with the user and calls on other classes to do
     * what the user wishes, accordingly. The Scanner
     * takes in these inputs and seeds them to Interact.
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

