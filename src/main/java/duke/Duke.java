package duke;

import java.util.Scanner;


public class Duke {
    public  Duke(){

    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        parser = new Parser();
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(ui));
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            tasks.createFile();
        }
    }

    private void run() {
        ui.welcomeMessage();
        Scanner userCommand = new Scanner(System.in);
        String input = userCommand.nextLine();
        while(!input.equals("bye")){
            parser.commandParser(input,tasks,ui);
            input = userCommand.nextLine();
        }
        tasks.addTasksToFile();
        ui.exitMessage();
    }




    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
