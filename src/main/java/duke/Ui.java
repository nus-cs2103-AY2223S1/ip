package duke;

/**
 * Creates a Ui object.
 */
public class Ui {

    private static final String LINE_DIVIDER = "____________________________________________________________";

    /**
     * Prints intro interface.
     */
    public void printIntro() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Wassup la I'm Duke\nWhat you want?\n" + LINE_DIVIDER);
    }

    /**
     * Prints goodbye message.
     */
    public void goodByeMessage() {
        System.out.println(LINE_DIVIDER + "\nBye. Zai Jian!\n" + LINE_DIVIDER);
    }

    /**
     * Prints task list.
     * @param tl TaskList to be printed
     */
    public void printList(TaskList tl) {
        System.out.println(LINE_DIVIDER);
        if (tl.size() == 0) {
            System.out.println("List is empty la");
            System.out.println(LINE_DIVIDER);
            return;
        }
        System.out.println("Here are your tasks la:");
        for (int j = 0; j < tl.size(); j++) {
            System.out.println(j + 1 + ":" + tl.get(j).toString());
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Ui for marking tasks.
     * @param task to be marked
     */
    public void printMarkedMsg(Task task) {
        System.out.println(LINE_DIVIDER);
        System.out.println("Ok ticked this already");
        System.out.println(task.toString());
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Ui for unmarking tasks.
     * @param task to be unmarked
     */
    public void printUnmarkedMsg(Task task) {
        System.out.println(LINE_DIVIDER);
        System.out.println("Ok not done yet ah");
        System.out.println(task.toString());
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Ui for deleting tasks.
     * @param removedTask String of removed task
     * @param size of TaskList
     */
    public void printDeleteMsg(String removedTask, int size) {
        System.out.println(LINE_DIVIDER);
        System.out.println("I remove this ah:");
        System.out.println();
        System.out.println("Now " + size + " tasks only");
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Ui for index inputs that are out of bounds.
     */
    public void printOutOfBoundsMsg() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Out of bounds lah, try again");
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Ui for no task input errors.
     */
    public void printNoTaskInputMsg() {
        System.out.println("☹ OOPS!!! Why empty");
    }

    /**
     * Ui for successful task additions.
     * @param task that was added
     * @param size of TaskList
     */
    public void printTaskAddedMsg(Task task, int size) {
        System.out.println(LINE_DIVIDER);
        System.out.println("Ok I add your task already:");
        System.out.println(task + "\nNow " + size + " tasks already");
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Ui for general errors.
     */
    public void printError() {
        System.out.println(LINE_DIVIDER);
        System.out.println("☹ Walao what do you mean");
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Ui for clearing whole TaskList.
     */
    public void printClearMsg() {
        System.out.println(LINE_DIVIDER);
        System.out.println("CLEAR ALL LIAO");
        System.out.println(LINE_DIVIDER);
    }
}
