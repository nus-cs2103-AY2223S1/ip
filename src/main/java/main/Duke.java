package main;

import java.util.Scanner;

import command.Command;
import exception.DukeException;

public class Duke {

    private TaskList tasks;
    private Parser parser;
    private Storage storage;
    private Ui ui;

    private boolean isEnd = false;

    public Duke() {
        this.parser = new Parser();
        this.tasks = new TaskList();
        this.ui = new Ui(this.tasks);
        this.storage = new Storage(this.tasks, this.ui, this.storage);
    }

    public void run() {
        Scanner userInput = new Scanner(System.in);
        ui.greeting();
            while (!isEnd) {
                try {
                    Command nextCommand = parser.parse(userInput.nextLine());
                    nextCommand.execute(this.tasks, this.ui, this.storage);
                    this.isEnd = nextCommand.isEnd();
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }
            }
        try {
            this.storage.cleanUp();
        } catch (DukeException e) {
            ui.errorEnd();
        }
        userInput.close();
        ui.bye();
    }

    public void end() {
        this.isEnd = true;
    }
         
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }

}
