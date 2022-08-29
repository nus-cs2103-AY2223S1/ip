package duke;

import command.ByeCommand;
import command.Command;
import task.DukeTask;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A chat bot that tracks your tasks!
 */
public class Duke {
    private Storage storage;
    private ArrayList<DukeTask> tasklist;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasklist = new ArrayList<DukeTask>();
        Storage.setOnce(tasklist, filePath);
    }

    /**
     * Run the program
     */
    public void run() {
        storage.read();
        Scanner input = new Scanner(System.in);
        System.out.println("What are your commands sir:");
        boolean isRunning = true;
        while(isRunning) {
            if (input.hasNext()) {
                String str = input.nextLine();
                Command cmd = Parser.parse(str);
                if (cmd instanceof ByeCommand) {
                    System.out.println("Goodbye, hope to see you again");
                    isRunning = false;
                    input.close();
                    break;
                }
                try {
                    cmd.deconstruct(tasklist, ui, storage);
                } catch (Exception e) {
                    System.out.println(e);
                }
                
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/list.txt").run();
    }
}
