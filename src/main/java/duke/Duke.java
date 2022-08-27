package duke;

import java.io.File;
import java.io.IOException;

/**
 *  duke.Duke Class
 *  The class which stores inputs and interacts with user
 *
 * @author Kang Qiao
 */

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filepath) {
        parser = new Parser();
        taskList = new TaskList();
        ui = new Ui(taskList);
        storage = new Storage(filepath);
    }

    public void run() throws DukeException {
        System.out.println(ui.getHello());
        try{
            File dukeFile = new File("data/duke.txt");
            dukeFile.getParentFile().mkdirs();//create the directory
            if(dukeFile.createNewFile()){
                System.out.println("new file created!");
            } else {
               storage.readAndSaveFile(dukeFile, taskList);
                System.out.println("updated file");
            }
        }catch (IOException e) {
            System.out.println("Error in creating file");
        }
        parser.parseInstruction(storage, taskList);
        System.out.println(ui.goodbye());

    }

    /**
     *The Main function for interaction between the user and DUKE
     */
    public static void main(String[] args) throws DukeException {

        Duke dukeBot = new Duke("data/tasks.txt");
        dukeBot.run();
        System.out.println("hello");
    }
}