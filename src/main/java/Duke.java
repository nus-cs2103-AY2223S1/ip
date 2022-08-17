import java.util.Scanner;

public class Duke {
    static String[] taskList = new String[100];
    static int counter = 0;
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
                String taskName = newTask.toString();
                taskList[counter] = taskName;
                counter++;

                ui.addTask(cmd);
            }

            cmd = sc.nextLine();
        }
        ui.exit();
    }
}
