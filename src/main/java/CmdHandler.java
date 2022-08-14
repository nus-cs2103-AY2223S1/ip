import java.util.ArrayList;
import java.util.Scanner;

public class CmdHandler {
    ArrayList<Task> tasks = new ArrayList<>();
    boolean done = false;

    private void handleList() {
        String out = "";
        for (int i = 1; i <= tasks.size(); i++) {
            out += i + "." + tasks.get(i - 1).toStringWithStatusIcon() + "\n";
        }
        System.out.println(out.trim());
    }


    private void handleBye() {
        System.out.println("Bye");
        done = false;
    }

    private void addTask(String desc) {
        tasks.add(new Task(desc));
        System.out.println("added: " + desc);
    }

    private void handleMark(int i) {
        tasks.get(i).mark();
        System.out.println("marked as done: " + tasks.get(i).toStringWithStatusIcon());
    }

    private void handleUnMark(int i) {
        tasks.get(i).unMark();
        System.out.println("marked as undone: " + tasks.get(i).toStringWithStatusIcon());
    }

    void handle() {
        Scanner sc = new Scanner(System.in);
        while (!done) {
            String[] input = sc.nextLine().split(" ");
            if (input[0].equals("list")) {
                handleList();
            } else if (input[0].equals("bye")) {
                handleBye();
            } else if (input[0].equals("unmark")) {
                handleUnMark(Integer.parseInt(input[input.length - 1]) - 1);
            } else if (input[0].equals("mark")) {
                handleMark(Integer.parseInt(input[input.length - 1]) - 1);
            } else {
                addTask(String.join(" ", input));
            }
            System.out.println();
        }
    }
}
