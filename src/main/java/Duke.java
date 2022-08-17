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
            if (s.equals("list")) {
                String listOutput = "";
                int index = 1;
                for (Task t : taskList) {
                    listOutput += index + ". " + t;
                    index++;
                }
                System.out.println(listOutput);
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
