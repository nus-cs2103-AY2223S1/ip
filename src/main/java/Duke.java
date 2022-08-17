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
