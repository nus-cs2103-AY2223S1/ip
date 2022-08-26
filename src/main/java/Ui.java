import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static String NAME = "DoiMoiBot: ";

    /**
     * Constructor to create instance of Ui.
     */
    public Ui() {
         this.sc = new Scanner(System.in);
    }

    /**
     * Method prints out Duke's default greeting.
     */
    public void greet() {
        System.out.println(NAME + "Hello! I'm doimoibot\n" + "What can I do you for?");
    }

    /**
     * Method to encapsulate the printing of seperator lines.
     */
    public void line() {
        System.out.println("------------------------------------------------------------------------------------");
    }

    /**
     * Method prints Duke's default farewell.
     */
    public void farewell() {
        System.out.println(NAME + "Goodbye! See you soon!");
    }

    /**
     * Method reads the user input.
     *
     * @return String representation of user input.
     */
    public String readInput() {
        return this.sc.nextLine();
    }

    /**
     * Method prints out notification that specified task has been added to task list.
     *
     * @param task Task to be added to task list.
     */
    public void notifyAdded(Task task) {
        System.out.println("Successfully added Task!\n" + task);
    }

    /**
     * Method prints notification that specified task has been marked done or undone according to boolean parameter.
     *
     * @param task The task that has been marked.
     * @param isDone The status of the task has been marked as.
     */
    public void notifyMarked(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Marked as done!\n" + task);
        } else {
            System.out.println("Marked as not done!\n" + task);
        }
    }

    /**
     * Method prints notification that specified task has been deleted from task list.
     *
     * @param task Task to be deleted from task list.
     */
    public void notifyDeleted(Task task) {
        System.out.println("Deleted task!\n" + task);
    }

    /**
     * Method prints out the information currently stored in the task list.
     * @param tasks TaskList object whose information will be printed.
     */
    public void printList(TaskList tasks) {
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(i + ": " + tasks.get(i - 1));
        }
    }
}
