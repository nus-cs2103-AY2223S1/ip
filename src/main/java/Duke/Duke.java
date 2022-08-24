package Duke;

import java.util.ArrayList;

public class Duke {

    private TaskList items;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            items = storage.loadFile();
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
            items = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {
        ui.printWelcome();
        String[] input;
        boolean done = false;

        while(!done) {
            try {
                String toParse = ui.getInput();
                input = Parser.parseInput(toParse);
            } catch (DukeException ex) {
                ui.printMsg(ex.getMessage());
                continue;
            }

            if (input[0].equals("list")) {
                // when user request list
                items.printList();
            } else {
                switch(input[0]) {
                    case "mark":
                        // when user wants to mark as done
                        int num = Integer.valueOf(input[1]);
                        if (num > items.getSize()) {
                            ui.printMsg("No such task");
                        } else {
                            Task tsk = items.markTask(num);
                            ui.printMark(tsk);
                        }
                        break;

                    case "unmark":
                        // when user wants to mark as not done
                        int numb = Integer.valueOf(input[1]);
                        if (numb > items.getSize()) {
                            ui.printMsg("No such task");
                        } else {
                            Task tsk = items.unmarkTask(numb);
                            ui.printUnMark(tsk);
                        }
                        break;

                    case "todo":
                        // when user wants to add todo task
                        Task t1 = new Todo(input[1]);
                        items.addTask(t1);
                        ui.printAddTask(t1, items.getSize());
                        break;

                    case "deadline":
                        // when user wants to add deadline task
                        try {
                            Task t2 = new Deadline(input[1], input[2]);
                            items.addTask(t2);
                            ui.printAddTask(t2, items.getSize());
                        } catch (DukeException ex) {
                            ui.printMsg(ex.getMessage());
                        }
                        break;

                    case "event":
                        // when user wants to add event task
                        Task t3 = new Event(input[1], input[2]);
                        items.addTask(t3);
                        ui.printAddTask(t3, items.getSize());
                        break;

                    case "delete":
                        // when user wants to delete task
                        try {
                            Task t4 = items.deleteTask(Integer.valueOf(input[1]));
                            ui.printDeleteTask(t4, items.getSize());
                        } catch (IndexOutOfBoundsException ex) {
                            ui.printMsg("Invalid index");
                        }
                        break;

                    case "bye":
                        done = true;
                        break;
                }
            }
            ui.printMsg("");
            storage.saveFile(items.toStringList());
        }
        ui.printBye();
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
