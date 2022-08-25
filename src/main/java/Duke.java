import java.util.Scanner;
import java.util.Arrays;
import java.time.LocalDate;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private static final String filePath = "./data/duke.txt";
    private void addTask(Task task) {
        tasks.add(task);
        ui.addTaskMessage(task, tasks);
    }
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            try {
                storage.createFile();
            } catch (DukeException ex) {
                ui.showError(e.getMessage());
            }
        }
    }
    public void run() {
        ui.lineBreak();
        ui.greetings();
        ui.lineBreak();
        Scanner sc = new Scanner(System.in);
        String exit_command = "bye";
        String list_command = "list";
        String mark_command = "mark";
        String unmark_command = "unmark";
        String todo_command = "todo";
        String deadline_command = "deadline";
        String event_command = "event";
        String delete_command = "delete";
        boolean flag = true;
        while (flag) {
            try {
                String response = sc.nextLine();
                String[] split_slash = response.split("/");
                String[] cmd_descp = split_slash[0].split(" ");
                String task_description = String.join(" ", Arrays.copyOfRange(cmd_descp, 1, cmd_descp.length));
                String command = cmd_descp[0];
                ui.lineBreak();
                if (command.toLowerCase().equals(exit_command)) {
                    flag = false;
                    ui.exit();
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(list_command)) {
                    ui.displayTasks(tasks);
                } else if (command.toLowerCase().equals(mark_command)) {
                    String ind = cmd_descp[1];
                    Task current = tasks.get(Integer.parseInt(ind) - 1);
                    current.doing();
                    ui.markDone(current);
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(unmark_command)) {
                    String ind = cmd_descp[1];
                    Task current = tasks.get(Integer.parseInt(ind) - 1);
                    current.undo();
                    ui.markUndone(current);
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(delete_command)) {
                    String ind = cmd_descp[1];
                    ui.delTaskMessage(tasks.del(Integer.parseInt(ind) - 1),tasks);
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(todo_command)) {
                    addTask(new Todo(task_description));
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(deadline_command)) {
                    String[] time_part = split_slash[1].split(" ");
                    String time = String.join(" ", Arrays.copyOfRange(time_part, 1, time_part.length));
                    LocalDate date = LocalDate.parse(time);
                    addTask(new Deadline(task_description, date));
                    storage.save(tasks.toString());
                } else if (command.toLowerCase().equals(event_command)) {
                    String[] time_part = split_slash[1].split(" ");
                    String time = String.join(" ", Arrays.copyOfRange(time_part, 1, time_part.length));
                    LocalDate date = LocalDate.parse(time);
                    addTask(new Event(task_description, date));
                    storage.save(tasks.toString());
                } else {
                    ui.invalidCommand();
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.lineBreak();
            }
        }
    }
    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
