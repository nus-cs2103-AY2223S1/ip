package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import commands.*;
import dukeexceptions.*;
import tasks.*;


public class Duke {

    public static void main(String[] args) {
        Statements.entryStatement();
        boolean toExitProgram = false;
        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Statements.initStatement();
        while (!toExitProgram) {
            String userIn = myObj.nextLine();  // Read user input
            try {
                Command comm = Parser.parse(userIn);
                if (comm.equals(new ByeCommand())) {
                    toExitProgram = true;
                }
                comm.run(taskList);


            } catch (DukeException e) {
                System.err.print(e);
            }

        }

    }





}
