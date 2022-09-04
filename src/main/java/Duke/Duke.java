
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

    private static final ServerCLI serverCLI = new ServerCLI();

    public static void main(String[] args) { //throws DukeException, FileNotFoundException {

        serverCLI.run();


//        System.out.println("Testing Tasks: Deadline, Event");
//        TaskList tasks = new TaskList();
//        Event e = new Event("Go to the lecture", LocalDateTime.of(LocalDate.parse("2022-02-22"), LocalTime.parse("11:22")), false);
//        Deadline ddl = new Deadline("Go to the lecture", LocalDateTime.of(LocalDate.parse("2022-02-22"), LocalTime.parse("11:22")), true);
//        System.out.println("    Event " + e.toString() + "\n" + "    Deadline " + ddl.toString());
//
//        System.out.println("Testing Commands:  AddTaskCommand, MarkDoneCommand");
//        UserCommand A = new AddTaskCommand(ddl, tasks);
//        A.execute();
//        UserCommand B = new AddTaskCommand(e, tasks);
//        B.execute();
//        // Mark Task
//        UserCommand C = new MarkDoneCommand(1,tasks);
//        System.out.println("    " + C.execute());
//        System.out.println("    " + tasks.showTasks());
//
//        System.out.println("Testing CLIParser");
//        CLIParser parser = new CLIParser();
//        String input = "todo hw1";
//        System.out.println("    Input:" + input);
//        parser.parseCommand(input, tasks).execute();
//        System.out.println("    " + tasks.showTasks());
//
//        System.out.println("Testing CLIParser.run()");
//


    }

}


