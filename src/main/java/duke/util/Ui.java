package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    public void showWelcome() {
        System.out.println("Hello! I'm Botson\n"
                + "What can I help you with? :-)\n"
                + "--------------------------");
    }

    public void exit() {
        System.out.println("Goodbye! Hope to see you again soon!\n"
                + "--------------------------");
    }

    public void noSuchTaskError() {
        System.out.println("OOPS!!! Error: No Such Task :-(\n"
                + "--------------------------");
    }

    public void showError() {
        System.out.println("An error occurred.");
    }

    public void cannotUnderstandError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "--------------------------");
    }

    public void removeTask(String removedTask, int size) {
        System.out.println("Noted. I've removed this task:\n"
                + removedTask);
        System.out.println("Now you have " + size + " tasks in the list.\n"
                + "--------------------------");
    }

    public void addedTask(String task, int size) {
        System.out.println("--------------------------\n"
                + "I've added to the list:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.\n"
                + "--------------------------");
    }

    /**
     * prints out the list of tasks added
     * @param list
     */
    public void getList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println("--------------------------");
    }

    public void markedTask(boolean mark, String task) {
        String output = mark ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
        System.out.println(output);
        System.out.println(task);
        System.out.println("--------------------------");
    }

    public void showFoundTasks(ArrayList<Task> list) {
        System.out.println("I found some matching tasks!");
        this.getList(list);
        System.out.println("--------------------------");
    }
}
