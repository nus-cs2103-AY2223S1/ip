package iana;

import iana.command.Command;
import iana.exception.IanaException;
import iana.main.Parser;
import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.TaskList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents the command line interface Iana used to manage tasks.
 */
public class Iana {

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

    /**
     * Execute the entire program.
     * 
     * @param args the arguments for command line.
     */
    public static void main(String[] args) {
        new Iana().run();
    }
    
    // @Override
    // public void start(Stage stage) {
    //     Label helloWorld = new Label("Hello World!"); // Creating a new Label control
    //     Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

    //     stage.setScene(scene); // Setting the stage to show our screen
    //     stage.show(); // Render the stage.
    // }
}