package iana;

import iana.command.Command;
import iana.exception.IanaException;
import iana.parser.Parser;
import iana.storage.Storage;
import iana.tasks.TaskList;
import iana.ui.Ui;
import java.util.Scanner;

/**
 * Represents the command line interface Iana used to manage tasks.
 */
public class Iana {
    private TaskList tasks;
    private Ui ui;

    /**
     * Runs the command line interface.
     */
    public void run() {     
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        try {
            Storage.initialise();   
            tasks = Storage.load();
        } catch (IanaException e) {
            ui.echo(e.getMessage());
        }
        ui.sayHi();
        boolean isActive = true;

        while (isActive) {
            Scanner sc = new Scanner(System.in);

            if (sc.hasNextLine()) {
                try {
                    String input = sc.nextLine();
                    Command command = Parser.parse(input);
                    isActive = !command.isExit();
                    command.execute(tasks, ui);
                } catch(IanaException e) {
                    ui.echo(e.getMessage());
                }
            }
            if (!isActive) {
                sc.close();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui);
        } catch (IanaException e) {
            return e.getMessage();
        }
    }

    /**
     * Execute the entire program.
     * 
     * @param args the arguments for command line.
     */
    public static void main(String[] args) {
        new Iana().run();
    }
}