import java.util.ArrayList;
import java.util.Scanner;

class Task {

    private String taskname;
    private boolean isDone;

    public Task(String taskname) {
        this.taskname = taskname;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "]" + " " + this.taskname;
    }


}

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        ArrayList<Task> arrayList = new ArrayList<>();

        while (!str.equals("bye")) {

            if (str.equals("list")) {
                for (int i = 0; i < arrayList.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + "." + arrayList.get(i));
                }
            } else {

                String[] strArray = str.split(" ");

                if (strArray[0].equals("mark")) {
                    int i = Integer.parseInt(strArray[1]) - 1;
                    arrayList.get(i).markDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(arrayList.get(i));

                } else if (strArray[0].equals("unmark")) {
                    int i = Integer.parseInt(strArray[1]) - 1;
                    arrayList.get(i).markNotDone();

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(arrayList.get(i));

                } else {
                    arrayList.add(new Task(str));
                    System.out.println("added: " + str);
                }
            }
            str = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
