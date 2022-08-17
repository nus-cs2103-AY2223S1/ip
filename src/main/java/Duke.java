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
                try {
                    Commands.mark(cmd, taskList);
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
            } else if (cmd.split(" ")[0].equals("unmark")) {
                try {
                    Commands.unmark(cmd, taskList);
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
            } else if (cmd.split(" ")[0].equals("deadline")){
                try {
                    Commands.deadline(cmd, taskList);
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
            } else if (cmd.split(" ")[0].equals("event")){
                try {
                    Commands.event(cmd, taskList);
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
            } else if (cmd.split(" ")[0].equals("todo")){
                    try {
                        Commands.toDo(cmd, taskList);
                    } catch (DukeException e) {
                        ui.errorMessage(e);
                    }
            } else {
                ui.commandDoesNotExist();
            }

            cmd = sc.nextLine();
        }
        ui.exit();
    }
}
