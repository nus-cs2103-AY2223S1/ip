package Duke.DukeHandlers;

import Duke.DukeExceptions.DukeException;

import java.util.Scanner;

public class Interact {
    private String line = "_______________________________________";
    private TasksManager tasksManager;

    /**
     * Greets the user.
     *
     * @return String.
     */
    public String start() {
        tasksManager = new TasksManager();
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\nHow can I help you";
    }

    /**
     * Calls the task manager to do certain tasks
     * depending on the command-line argument.
     *
     * @param word  A String read from the command line.
     * @return String.
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
}

