import java.util.ArrayList;
import java.util.Scanner;

public class CmdHandler {
    ArrayList<Task> tasks = new ArrayList<>();
    boolean done = false;

    private void handleList() {
        String out = "";
        for (int i = 1; i <= tasks.size(); i++) {
            out += i + "." + tasks.get(i - 1) + "\n";
        }
        System.out.println(out.trim());
    }


    private void handleBye() {
        System.out.println("Bye");
        done = false;
    }

    private void addTask(String desc) {
        if (desc.substring(0, 8).equals("deadline")) {
            String[] parsed = desc.substring(8).split("/by");
            tasks.add(new Deadline(parsed[0], parsed[1]));
        } else if (desc.substring(0, 5).equals("event")) {
            String[] parsed = desc.substring(5).split("/at");
            tasks.add(new Event(parsed[0], parsed[1]));
        } else if (desc.substring(0, 4).equals("todo"))  {
            tasks.add(new Todo(desc.substring(4)));
        }
        System.out.println("task added" + tasks.get(tasks.size() - 1));
    }

    private void handleMark(int i) {
        tasks.get(i).mark();
        System.out.println("marked as done: " + tasks.get(i));
    }

    private void handleUnMark(int i) {
        tasks.get(i).unMark();
        System.out.println("marked as undone: " + tasks.get(i));
    }

    void handle() {
        Scanner sc = new Scanner(System.in);
        while (!done) {
            String inputStr = sc.nextLine();
            String[] inputArr = inputStr.split(" ");
            if (inputArr[0].equals("list")) {
                handleList();
            } else if (inputArr[0].equals("bye")) {
                handleBye();
            } else if (inputArr[0].equals("unmark")) {
                handleUnMark(Integer.parseInt(inputArr[inputArr.length - 1]) - 1);
            } else if (inputArr[0].equals("mark")) {
                handleMark(Integer.parseInt(inputArr[inputArr.length - 1]) - 1);
            } else {
                addTask(inputStr);
            }
            System.out.println();
        }
    }
}
