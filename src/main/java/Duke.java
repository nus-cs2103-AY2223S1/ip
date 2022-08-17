import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greeting();

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")){
            if (cmd.equals("list")) {
                ui.list(taskList);
            } else if (cmd.split(" ")[0].equals("mark")) {
                String number = cmd.split(" ")[1];
                int num = Integer.parseInt(number);
                Task task = taskList.get(num - 1);
                task.markAsDone();
                ui.marked(task);
            } else {
                Task newTask = new Task(cmd);
                taskList.add(newTask);
                ui.addTask(newTask);
            }


            cmd = sc.nextLine();
        }
        ui.exit();
    }
}
