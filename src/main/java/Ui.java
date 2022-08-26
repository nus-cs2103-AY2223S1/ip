import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc;
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETINGS = "Hello! I'm Duke\nWhat can I do for you?\n";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String nextLine() {
        return this.sc.nextLine();
    }

    public void printGreetings() {
        System.out.println("Hello from\n" + logo);
        System.out.println(GREETINGS);
    }

    public void printAddTask(String str) {
        System.out.println("Got it. I've added this task:\n" + str);
    }

    public void printArrAsNumberedList(ArrayList<Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(i+1 + ". " + arr.get(i).toString());
        }
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

}
