package duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ui {

    public void line() {
        System.out.println("----------------------");
    }

    public void printFarewell() {
        line();
        System.out.println("Bye, hope to see you again!");
        line();
    }

    public void printException(Exception e) {
        line();
        System.out.println(e.toString());
        line();
    }

    public void printErrorMessage(String s) {
        line();
        System.out.println(s);
        line();

    }

    public void printTaskAdded(Task a, TaskList tList) {
        line();
        System.out.println("added: " + a.toString());
        System.out.println(String.format("Now you have %d tasks in the list", tList.getCount()));
        line();

    }

    public void printList(TaskList tList) {
        line();
        for (int i = 0; i < tList.getCount(); i++) {
            String display = String.format("%d.%s", i + 1, tList.getTask(i).toString());
            System.out.println(display);
        }
        line();
    }

    public void printFind(ArrayList<Task> t) {
        if (t.size() == 0) {
            line();
            System.out.println("Opps! No matching tasks");
            line();
        } else {
            line();
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < t.size(); i++) {
                String display = String.format("%d.%s", i + 1, t.get(i).toString());
                System.out.println(display);
            }
            line();
        }
    }

    public void printMarkTestUndone(Task a) {
        line();
        System.out.println("Ok! I've marked this task as undone");
        System.out.println(a.toString());
        line();
    }

    public void printDelete(Task a, TaskList tList) {
        line();
        System.out.println("Noted! I've removed this task");
        System.out.println(a.toString());
        System.out.println("Now you have " + tList.getCount() + " tasks!");
        line();
    }

    public void printMarkTaskDone(Task a) {
        line();
        System.out.println("Ok! I've marked this task as done");
        System.out.println(a.toString());
        line();
    }

    public void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        line();

    }
}
