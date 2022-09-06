package duke;

import duke.task.Task;

public class Ui {
    private static final String LOGO  = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public Ui() {}

    public static void greet() {
        System.out.println("Hello from\n" + LOGO);
    }

    public static void exit() {
        System.out.println("Goodbye! Thank you for visiting\n" + LOGO);
    }

    public static void printList(TaskList list) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("There is nothing in your list yet!");
        } else {
            System.out.println("Here is your to-do list, Master:\n" +
                    list.toString());
        }
    }

    public static void marked(TaskList list, int index, boolean success) throws DukeException {
        Task curr = list.get(index);
        if (success) {
            System.out.println("Well done, Master! I have marked "
                    + curr.toString() +
                    " as done.");
        } else {
            throw new DukeException("This task has already been marked done, Master.");
        }
    }

    public static void unmarked(TaskList list, int index, boolean success) throws DukeException {
        Task curr = list.get(index);
        if (success) {
            System.out.println("Oh no :( I have marked " +
                    curr.toString()
                    + " as undone, Master.");
        } else {
            throw new DukeException("This duke.task was already marked undone, Master.");
        }
    }

    public static void deleted(Task task) {
        System.out.println("Very well, I have deleted " + task.toString() + " from your list, Master.");
    }

    public static void added(Task task) {
        System.out.println("I have added " + task.toString() + " to the list, Master.");
    }

}
