package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import commands.*;
import dukeexceptions.*;
import tasks.*;


public class Duke {
    TaskList taskList;
    String filePath;
    private Storage s;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.filePath = filePath;
        this.s = new Storage(this.filePath);
        this.taskList = s.open();
    }

    public void run() {
        Ui.entryStatement();
        boolean toExitProgram = false;
        Ui.initStatement();
        while (!toExitProgram) {
            String userIn = ui.readCommand();  // Read user input
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
        new Duke("data/task.txt").run();
    }

}






