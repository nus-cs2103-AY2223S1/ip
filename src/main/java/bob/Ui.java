package bob;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Scanner;

//referenced https://github.com/Donovan9617/ip/blob/master/src/main/java/Duke/Ui.java for structure
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("hey, i'm bob!\ndo you need help?");
        System.out.println("here's what you can do!\n" +
                "    ❤️    ADD A TODO TASK: todo <task>\n" +
                "    🌸    ADD A DEADLINE: deadline <task> /by <yyyy-mm-dd>\n" +
                "    ✨    ADD AN EVENT: event <task /at <yyyy-mm-dd>\n" +
                "    💕    VIEW LIST OF EVENTS: list\n" +
                "    🌼    MARK AS DONE: mark <task number in list>\n" +
                "    ❣️    UNMARK TASK: unmark <task number in list>\n" +
                "    🌟    REMOVE TASK: remove <task number in list>\n" +
                "    💞    TO END THE PROGRAM: bye\n" +
                "hope this helps!");
    }

    public String getReply() {
        String reply = this.sc.nextLine();
        return reply;
    }

    public void sayGoodbye() {
        System.out.println("bye\nsee you again!");
    }

    public void displayTaskList(TaskList taskList) {
        int index = 1;
        String list = "";
        while (index < (taskList.getLength() + 1)) {
            list = list + "\n" + (index) + ". " + taskList.getTask(index).toString();
            index += 1;
        }
        System.out.println("here are all your tasks!" + list);
    }

    public void displayMarked(TaskList tasks, int index) {
        System.out.println("yay! you've completed a task!\n" + tasks.getTask(index).toString());
    }

    public void displayUnMarked(TaskList tasks, int index) {
        System.out.println("aw...i guess there's another task.\n" + tasks.getTask(index).toString());
    }

    public void displayRemoved(TaskList tasks, Task task) {
        System.out.println("that's one less task for you! removed:" + "\n  "
                + task.toString() + "\njust " + (tasks.getLength()) + " tasks left!");
    }

    public void displayFiltered(LocalDate date, String list) {

        System.out.print("here are your tasks on "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "\n" + list);
    }

    public void displayAddedTask (TaskList tasks, Task task) {
        System.out.println("okay! new task:" + "\n  " + task.toString()
                + "\njust " + tasks.getLength() + " tasks left!");
    }

    public void displayError(String error) {
        System.out.println(error);
    }
}
