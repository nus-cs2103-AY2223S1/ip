package duke;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    
    static String divider = "____________________________________________________________";
    
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }
    
    public void addTask(Task task) {
        tasks.add(task);
        storage.save(tasks);
    }
    
    public void deleteTask(int index) {
        tasks.delete(index);
        storage.save(tasks);
    }

    public void mark(int index) {
        tasks.mark(index);
        storage.save(tasks);
    }

    public void unmark(int index) {
        tasks.unmark(index);
        storage.save(tasks);
    }
    
    public void run() {
        ui.sendStartMessage();
        Scanner input = new Scanner(System.in);
        while (true) {
            String text = input.nextLine();
            if (text.equals("bye")) {
                ui.sendByeMessage();
                break;
            }
            if (text.equals("list")) {
                ui.sendList(tasks);
                continue;
            }
            if (text.startsWith("mark ")) {
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                if (index < 0 || index >= tasks.size()) {
                    ui.sendWrongIndexMessage();
                    continue;
                }
                mark(index);
                ui.sendMarkedMessage(tasks.get(index));
                continue;
            }
            if (text.startsWith("unmark ")) {
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                if (index < 0 || index >= tasks.size()) {
                    ui.sendWrongIndexMessage();
                    continue;
                }
                unmark(index);
                ui.sendUnmarkedMessage(tasks.get(index));
                continue;
            }
            if (text.startsWith("todo ")) {
                String desc = text.substring(5);
                if (desc.trim().length() == 0) {
                    ui.sendEmptyTaskMessage();
                    continue;
                }
                Todo t = new Todo(desc);
                addTask(t);
                ui.sendAddedMessage(t, tasks.size());
                continue;
            }
            if (text.startsWith("deadline ")) {
                String[] params = text.substring(9).split(" /by ");
                if (params.length < 2) {
                    ui.sendFailureMessage();
                    continue;
                }
                if (params[0].trim().length() == 0) {
                    ui.sendEmptyTaskMessage();
                    continue;
                }

                LocalDateTime dt = Parser.parseDateTime(params[1]);
                Deadline t = new Deadline(params[0], dt);
                addTask(t);
                ui.sendAddedMessage(t, tasks.size());
                continue;
            }
            if (text.startsWith("event ")) {
                String[] params = text.substring(6).split(" /at ");
                if (params[0].trim().length() == 0) {
                    ui.sendEmptyTaskMessage();
                    continue;
                }
                if (params.length < 2) {
                    ui.sendFailureMessage();
                    continue;
                }

                LocalDateTime dt = Parser.parseDateTime(params[1]);
                Event t = new Event(params[0], dt);
                addTask(t);
                ui.sendAddedMessage(t, tasks.size());
                continue;
            }
            if (text.startsWith("delete ")) {
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                if (index < 0 || index >= tasks.size()) {
                    ui.sendWrongIndexMessage();
                    continue;
                }
                Task toRemove = tasks.get(index);
                deleteTask(index);
                ui.sendTaskDeletedMessage(toRemove, tasks.size());
                continue;
            }
            if (text.startsWith("find ")) {
                String keyword = text.substring(5);
                ui.sendList(new TaskList(tasks.filter(keyword)));
                continue;
            }
            ui.sendFailureMessage();
        }
    }
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
