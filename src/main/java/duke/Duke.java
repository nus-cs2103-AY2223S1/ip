package duke;

import duke.command.Command;
import duke.command.CommandParser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        tasks = new TaskList(storage.loadData());
        Parser parser = new Parser(this.ui, tasks);

        while(true) {
            //Parse -> Execute -> Print Result
            String input = sc.nextLine();
            Command c = parser.parse(input);
            c.execute(tasks, this.storage, ui);
        }

//        while (true) {
//            String input = sc.nextLine();
//            parser.parse(input);
//            if (input.equals("bye")) {
//                ui.showByeMessage();
//                sc.close();
//                break;
//            }
//            else if (input.startsWith("mark")) {
//                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
//                Task t = history.get(index - 1);
//                t.markAsDone();
//                ui.showTaskMarkMessage(t);
//            }
//            else if (input.startsWith("unmark")) {
//                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
//                Task t = history.get(index - 1);
//                t.markAsNotDone();
//                ui.showTaskUnmarkMessage(t);
//            }
//            else if (input.startsWith("delete")){
//                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
//                Task t = history.get(index - 1);
//                history.remove(t);
//                storage.rewrite(history);
//                ui.showTaskDeletedMessage(t, history.size());
//
//            } else {
//                throw new DukeException("Invalid command! Please try again");
//            }
//        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("saved.txt").run();
    }
}

