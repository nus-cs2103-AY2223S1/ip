import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;

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
