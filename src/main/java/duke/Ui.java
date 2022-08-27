package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Represents UI of application
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructor for class Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a welcome message for the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Fungusta\n" + "Peter's personal chatbot");
    }

    /**
     * Prints a goodbye message for the user.
     */
    public void showGoodbye() {
        System.out.println("Bye Bye See You Next Time!");
    }

    /**
     * Gets first line of user input into system.
     *
     * @return next line in scanner.
     */
    public String takeInput() {
        return sc.nextLine();
    }

    /**
     * Checks if there is another line of user input into system.
     *
     * @return true if scanner has next line, else false.
     */
    public boolean hasInput() {
        return sc.hasNextLine();
    }

    /**
     * Prints a divider line.
     */
    public void showDividerLine() {
        System.out.println("==============================");
    }

    /**
     * Prints an error message due to wrong input by user.
     *
     * @param message error message generated
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints list of the tasks in Duke.
     *
     * @param taskList list of tasks.
     * @throws DukeException thrown if list is empty.
     */
    public void showListOut(TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("You do not have any tasks in the list");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
    }

    /**
     * Prints deleted task and updated number of tasks.
     *
     * @param taskList list of tasks.
     * @param deletedTask deleted task
     */
    public void showDelete(TaskList taskList, Task deletedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        if (taskList.size() == 1) {
            System.out.println("Now you have " + taskList.size() + " task in the list");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list");
        }
    }

    /**
     * Prints added task and updated number of tasks.
     *
     * @param taskList list of tasks.
     * @param newTask new task added.
     */
    public void showAdd(TaskList taskList, Task newTask) {
        System.out.println("Nice! Added this task:");
        System.out.println(newTask);

        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    /**
     * Prints marked task
     *
     * @param taskList list of tasks.
     * @param index index of marked task in list of tasks.
     */
    public void showMark(TaskList taskList, int index) {
        System.out.println("Weeeee! I've marked this task as done:");
        System.out.println(taskList.get(index).toString());
    }

    /**
     * Prints unmarked task.
     *
     * @param taskList list of tasks.
     * @param index index of unmarked task in list of tasks.
     */
    public void showUnmark(TaskList taskList, int index) {
        System.out.println("Aw Mans... I've unmarked this task:");
        System.out.println(taskList.get(index).toString());
    }

    /**
     * Prints list of tasks that contains the inputted keyword.
     *
     * @param taskList list of tasks that contains the inputted keyword.
     * @throws DukeException if no tasks contains the keyword.
     */
    public void showKeywordList(TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("You do not have any tasks in the list that contains the keyword");
        }
        System.out.println("Here are matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
    }
}
