
package Duke;
import Duke.Commands.AddTaskCommand;
import Duke.Commands.MarkDoneCommand;
import Duke.Exceptions.DukeException;
import Duke.Parser.CLIParser;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.TaskList;
import Duke.Commands.UserCommand;
import Duke.Tasks.ToDo;
import Duke.UI.Ui;


import Duke.Storage.FileReader;
import Duke.UserServer.ServerCLI;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * Duke App Main class
 */

public class Duke {

    private static final FileReader storage = new FileReader("Duke");
    ;

    private static final TaskList tasks = storage.load();

    private static final Ui ui = new Ui();

    private void launch() throws FileNotFoundException {
        System.out.println("Hello World!!!!");


//        try {
//            FileInputStream fis = new FileInputStream(String.valueOf(filePath));
//            ObjectInputStream reader = new ObjectInputStream(fis);
//
//        } catch (IOException e){
//            System.out.println("wromg");
//        }


    }


    public void run() {
        ui.showWelcome();
        boolean isExit = false;
//        while (!isExit) {
//            /*
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine();
//                Command c = Parser.parse(fullCommand);
//
//
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//*/
//        }


    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        Event e = new Event("Go to the lecture", LocalDateTime.of(LocalDate.parse("2022-02-22"), LocalTime.parse("11:22")), false);
        Deadline ddl = new Deadline("Go to the lecture", LocalDateTime.of(LocalDate.parse("2022-02-22"), LocalTime.parse("11:22")), true);

        // TaskList
        TaskList tasks = new TaskList();
        // Add Task
        UserCommand A = new AddTaskCommand(ddl, tasks);
        A.execute();
        UserCommand B = new AddTaskCommand(e, tasks);
        B.execute();
        // Mark Task
        UserCommand C = new MarkDoneCommand(1,tasks);
        System.out.println(C.execute());
        System.out.println(tasks.showTasks());


        CLIParser parser = new CLIParser();
        parser.parseCommand("todo hw1", tasks).execute();
        System.out.println("------------");
        System.out.println(tasks.showTasks());


        String input = "todo hw2";
        UserCommand curCommand = parser.parseCommand(input, tasks);
        curCommand.execute();
        System.out.println("------------");
        System.out.println(tasks.showTasks());





        ServerCLI serverCLI = new ServerCLI();
        serverCLI.run();


        // System.out.println(e.toString());

       // new Duke().launch();
    }
}


