package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import commands.*;
import dukeexceptions.*;
import tasks.*;


public class Duke {
    ArrayList<Task> taskList;
    String filePath;
    private Storage s;

    public Duke(String filePath) {
        this.filePath = filePath;
        this.s = new Storage(this.filePath);
        this.taskList = s.open();
    }

    public void run() {
        boolean toExitProgram = false;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Statements.initStatement();
        while (!toExitProgram) {
            String userIn = myObj.nextLine();  // Read user input
            try {
                Command comm = Parser.parse(userIn);
                if (comm instanceof ByeCommand) {
                    toExitProgram = true;
                    this.s.end(this.taskList);
                }
                comm.run(this.taskList);
            } catch (DukeException e) {
                System.err.print(e);
            }
        }
    }

    public static void main(String[] args) {
        Statements.entryStatement();
        new Duke("data/task.txt").run();
    }

}






