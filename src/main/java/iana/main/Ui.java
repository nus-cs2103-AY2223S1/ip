package iana.main;
import java.util.Scanner;

import iana.tasks.Task;

public class Ui {
    private static String LINEBLOCK = "\t---------------------------------------------\n";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINEBLOCK);
    }

    public void echo(String msg) {
        System.out.println(LINEBLOCK);
        System.out.println("\t" + msg + "\n");
        System.out.println(LINEBLOCK);
    }

    public void list(TaskList tasks) {
        String listMessage = "\t> Here are the tasks in your list:\n" + tasks.toString();
        showLine();
        System.out.println(listMessage);
        showLine();
    }

    public void sayBye() {
        echo("> Goodbye! :P");
    }

    public void sayHi() {
        echo("> Hello there~ I'm IANA.\n\tWhat can I do for you today? : )");
    }

    public void askNewCommand() {
        echo("\t> Try another action ><");
    }

    public void sayTaskAdded(Task task) {
        echo(String.format("> Nice! I have added the task to the list:\n\t   %s", task.toString()));
    }

    public void sayTaskDeleted(Task task, int listSize) {
        echo(String.format("> Noted. I've removed this task:\n\t   %s\n\tNow you have %d tasks in the list.", 
        task.toString(), listSize));
    }

    public String readCommand() {
        return this.sc.nextLine();
    }
}
