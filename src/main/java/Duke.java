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

            if (cmd.equals("")) {

            }
            if (cmd.equals("list")) {
                ui.list(taskList);
            }

            if (cmd.split(" ")[0].equals("mark")) {
                Commands.mark(cmd, taskList);
            }

            if (cmd.split(" ")[0].equals("unmark")) {
                String number = cmd.split(" ")[1];
                int num = Integer.parseInt(number);
                Task task = taskList.get(num - 1);
                task.unmarkAsDone();
                ui.unmarked(task);
            }

            if (cmd.split(" ")[0].equals("deadline")){
                String taskName = cmd.substring(cmd.indexOf(" ") + 1, cmd.indexOf("/") - 1);
                String by = cmd.substring(cmd.indexOf("/") + 4);
                Task newTask = new Deadline(taskName, by);
                taskList.add(newTask);
                int amountOfTasks = taskList.size();
                ui.addTask(newTask, amountOfTasks);
            }

            if (cmd.split(" ")[0].equals("event")){
                String taskName = cmd.substring(cmd.indexOf(" ") + 1, cmd.indexOf("/") - 1);
                String at = cmd.substring(cmd.indexOf("/") + 4);
                Task newTask = new Event(taskName, at);
                taskList.add(newTask);
                int amountOfTasks = taskList.size();
                ui.addTask(newTask, amountOfTasks);
            }

            if (cmd.split(" ")[0].equals("todo")){
                String taskName = cmd.substring(cmd.indexOf(" ") + 1);
                Task newTask = new ToDo(taskName);
                taskList.add(newTask);
                int amountOfTasks = taskList.size();
                ui.addTask(newTask, amountOfTasks);
            }

            cmd = sc.nextLine();
        }
        ui.exit();
    }
}
