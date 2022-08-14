import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Messages.welcome();

        String in = "";
        TaskList list = new TaskList();
        Scanner sc = new Scanner(System.in);

        while (true) {
            in = sc.nextLine();
            if (in.equals("bye")) {
                break;
            } else if (in.equals("list")) {
                list.printTasks();
            } else if (in.startsWith("mark")) {
                int index = Integer.valueOf(in.split(" ")[1]) - 1;
                list.mark(index);
            } else if (in.startsWith("unmark")) {
                int index = Integer.valueOf(in.split(" ")[1]) - 1;
                list.unmark(index);
            } else {
                list.add(new Task(in));
                System.out.println("added: " + in);
            }
            System.out.println("-------------------------------------------");
        }
        Messages.bye();
    }
}
