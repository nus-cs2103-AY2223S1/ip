package duke;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    private String command;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\n" +
                "What can I do for you?");
    }

    public void showTaskAdded(Task t, TaskList list) {
        String size = Integer.toString(list.size());
        System.out.println("Got it. I've added this task:\n" + "  " + t);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskDeleted(int taskNum, TaskList list) {
        System.out.println("Noted. I've removed this task:\n" +
                "  " + list.getTask(taskNum));
        String size = Integer.toString(list.size() - 1);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskMarked(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" +
                "  " + t);
    }

    public String promptUserCommand() {
        System.out.println("Enter command:");
        this.command = this.sc.nextLine();
        return this.command;
    }

    public void showUnknownInputError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(TaskList list) {
        int count = 1;
        for (Task t : list.getList()) {
            String s = Integer.toString(count);
            System.out.println(s + ". " + t);
            count++;
        }
    }
}
