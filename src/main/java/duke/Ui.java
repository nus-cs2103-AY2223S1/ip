package duke;
import duke.Task.Deadline;
import duke.Task.Event;
import duke.Task.Task;
import duke.TaskList;
public class Ui {
    public Ui() {

    }
    private void lineBreak() {
        System.out.println("\n");
    }

    /**
     * Shows the greeting message
     */
    protected void showGreeting() {
        System.out.println("Howwwwwdyyyyyy");
        this.lineBreak();
        String image = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣶⣿⣿⣷⣶⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣾⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⡟⠁⣰⣿⣿⣿⡿⠿⠻⠿⣿⣿⣿⣿⣧⠀⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⠏⠀⣴⣿⣿⣿⠉⠀⠀⠀⠀⠀⠈⢻⣿⣿⣇⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⢀⣠⣼⣿⣿⡏⠀⢠⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⡀⠀\n⠀"
                      +"⠀⠀⠀⣰⣿⣿⣿⣿⣿⡇⠀⢸⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⡇⠀\n⠀"
                      +"⠀⠀⢰⣿⣿⡿⣿⣿⣿⡇⠀⠘⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⢀⣸⣿⣿⣿⠁⠀\n⠀"
                      +"⠀⠀⣿⣿⣿⠁⣿⣿⣿⡇⠀⠀⠻⣿⣿⣿⣷⣶⣶⣶⣶⣶⣿⣿⣿⣿⠃⠀⠀\n⠀"
                      +"⠀⢰⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀\n⠀"
                      +"⠀⢸⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⠛⠉⢉⣿⣿⠀⠀⠀⠀⠀\n⠀"
                      +"⠀⢸⣿⣿⣇⠀⣿⣿⣿⠀⠀⠀⠀⠀⢀⣤⣤⣤⡀⠀⠀⢸⣿⣿⣿⣷⣦⠀⠀\n⠀"
                      +"⠀⠀⢻⣿⣿⣶⣿⣿⣿⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣦⡀⠀⠉⠉⠻⣿⣿⡇⠀\n⠀"
                      +"⠀⠀⠀⠛⠿⣿⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠈⠹⣿⣿⣇⣀⠀⣠⣾⣿⣿⡇⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣦⣤⣤⣤⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣿⣿⣿⣿⣿⣿⠿⠋⠉⠛⠋⠉⠉⠁⠀⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠁";
        System.out.println(image);
        this.lineBreak();
        System.out.println("What can i do for you? :)");
        this.lineBreak();
    }

    /**
     * Shows the goodbye message
     */
    protected void showBye() {
        System.out.println("You dont want me anymore... sigh");
        this.lineBreak();
    }

    /**
     * Shows all the task
     * @param taskList the list of task
     */
    protected void showAllTask(TaskList taskList) {
        System.out.println("Look at me being useful\n");
        System.out.println(taskList);
    }

    /**
     * Shows the add task message
     * @param taskList the list of task
     * @param task the task that was added
     */
    protected void showAddTask(TaskList taskList, Task task) {
        System.out.println("Danm you have another task");
        this.lineBreak();
        System.out.println(task);
        this.lineBreak();
        System.out.println(String.format("oh god you have %s tasks now", taskList.length()));
        this.lineBreak();
    }

    /**
     * Shows the remove task message
     * @param taskList the list of task
     * @param task the task to be removed
     */
    protected void showRemoveTask(TaskList taskList, Task task) {
        System.out.println("Thats right work is just a concept");
        this.lineBreak();
        System.out.println(task);
        this.lineBreak();
        System.out.println(String.format("oh god you have %s tasks now", taskList.length()));
        this.lineBreak();
    }

    /**
     * Shows the marked task message
     * @param task the task that was marked
     */

    protected void showMarkTask(Task task) {
        System.out.println("Noice you are done w a task. EFFISHUN!!");
        this.lineBreak();
        System.out.println(task);
        this.lineBreak();
    }
    /**
     * Shows the unmarked task message
     * @param task the task that was unmarked
     */
    protected void showUnmarkTask(Task task) {
        System.out.println("IMPOSTERRRRRRRR!!! I WILL UNMARK IT ");
        this.lineBreak();
        System.out.println(task);
        this.lineBreak();
    }

    protected void showFindTask(TaskList taskList) {
        System.out.println("WOW I AM SO USEFUL !!");
        this.lineBreak();
        System.out.println("Here are all the matching tasks in your list:");
        this.lineBreak();
        System.out.println(taskList);
    }
}
