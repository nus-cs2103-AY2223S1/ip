import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Action greet = new Greet();
        System.out.println(greet);
        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        while(!s.equals("bye")) {
            String parts[] = s.split(" ", 0);
            if (s.equals("list")) {
                String listOutput = "Here are the tasks in your list:\n";
                int index = 1;
                for (Task t : taskList) {
                    listOutput += index + ".[" + (t.isMarked() ? "X" : " ") + "] "+ t;
                    index++;
                }
                System.out.println(listOutput);
            }
            else if (parts.length >= 1 && (parts[0].equals("mark") || parts[0].equals("unmark"))) {
                if (parts[0].equals("mark")) {
                    int pos = Integer.parseInt(parts[1]) - 1;
                    Task markedTask = taskList.get(pos).mark();
                    taskList.set(pos, markedTask);
                    System.out.println("Nice! I've marked this task as done:\n[X] " + markedTask);
                }
                else if (parts[0].equals("unmark")) {
                    int pos = Integer.parseInt(parts[1]) - 1;
                    Task unmarkedTask = taskList.get(pos).unmark();
                    taskList.set(pos, unmarkedTask);
                    System.out.println("OK, I've marked this task as not done yet:\n[ ] " + unmarkedTask);
                }
            }
            else {
                Task task = new Task(s);
                taskList.add(task);
                System.out.println("added: " + task);
            }

            s = in.nextLine();
        }
        Action bye = new Bye();
        System.out.println(bye);
    }
}
