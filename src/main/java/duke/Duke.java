package duke;


import parser.Parser;

import storage.Storage;

import ui.UI;

import task.TaskList;



/**
 * Duke is a Personal ChatBot to help keep track of your Tasks.
 *
 * @author joelwong
 */

public class Duke {

    private UI ui;
    private Storage storage;
    private TaskList tasks;


    /**
     * Creates a new Duke.
     *
     * @param filePath the file path of stored tasks in the text file.
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
            assert !this.tasks.getTaskList().isEmpty() : "Task should not be empty";
            //this.tasks.printContent();
            //ui.showGotTask();
        } catch (DukeException e) {
            ui.showNoTask();
            //does nothing but instantiate a object
            this.tasks = new TaskList();
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt");
    }


    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            String returnOut = parser.parse(tasks, input);
            if (parser.isBye()) {
                //Do nothing
            }
            return returnOut;
        } catch (DukeException e) {
            return ui.showInaccurateInput();
        }
    }





}


