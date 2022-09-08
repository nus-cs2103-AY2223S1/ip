package duke;
import duke.parser.Parser;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.Task;

public class Duke {
    //start with folder no need filepath
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        ui.greetingsPrint();

        //file creation
        try {
            storage.createFiles();
        } catch (Exception e) {
            ui.fileErrorPrint();
        }

        Task task = new Task("", "");
        //file reading
        taskList = storage.readDuke(taskList.getTasks(), taskList.getCurr());

        //main body
        String command = ui.readInput();
        while (!command.split(" ")[0].equals("bye")) {
            taskList = parser.readInput(command, taskList, ui);
            command = ui.readInput();
        }

        //bye block
        storage.writerToDuke(taskList.getTasks(), taskList.getCurr());
        ui.byePrint();

    }
}
