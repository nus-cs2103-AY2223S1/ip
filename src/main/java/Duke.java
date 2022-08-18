import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();

        String divider = "____________________________________________________________";
        System.out.println("  _  __                    \n" +
                " | |/ /__ _ _ __ ___ _ __  \n" +
                " | ' // _` | '__/ _ \\ '_ \\ \n" +
                " | . \\ (_| | | |  __/ | | |\n" +
                " |_|\\_\\__,_|_|  \\___|_| |_|\n");
        System.out.println("Hello I'm Karen. What do you want??\n" + divider);
        Scanner input = new Scanner(System.in);
        while (true) {
            String text = input.nextLine();
            if (text.equals("bye")) {
            System.out.println("Hmm kay...\n" + divider);
                break;
            }
            if (text.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    Task taskI = taskList.get(i);
                    System.out.println((i + 1) + ".[" + taskI.getStatusIcon() + "] " + taskI.description);
                }
                System.out.println(divider);
                continue;
            }
            if (text.startsWith("mark")) {
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                if (index >= taskList.size()) {
                    System.out.println("Umm can you count?" + "\n" + divider);
                    continue;
                }
                Task t = taskList.get(index);
                t.mark();
                System.out.println("Oh you did a task. Congratulations.\n" + "[" + t.getStatusIcon() + "] " + t.description + "\n" + divider);
                continue;
            }
            if (text.startsWith("unmark")) {
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                if (index >= taskList.size()) {
                    System.out.println("Umm can you count?" + "\n" + divider);
                    continue;
                }
                Task t = taskList.get(index);
                t.unmark();
                System.out.println("Ok unmarked. Make up your mind maybe??.\n" + "[" + t.getStatusIcon() + "] " + t.description + "\n" + divider);
                continue;
            }
            if (text != "") {
                taskList.add(new Task(text));
                System.out.println("K. added: " + text + "\n" + divider);
            }
        }
    }
}
