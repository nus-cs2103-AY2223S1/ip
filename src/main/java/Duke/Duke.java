
package Duke;
import Duke.Exceptions.DukeException;
import Duke.Tasks.TaskList;

import Duke.UI.Ui;


import Duke.Storage.FileReader;

import java.io.*;


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
        System.out.println("Hello World!");

        System.out.println(tasks);
        new Duke().launch();
    }
}


