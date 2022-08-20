package duke;

import java.util.Scanner;

import duke.command.CommandHandler;
import duke.command.CommandPair;

import duke.exception.DukeException;

import duke.parser.Parser;

import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private Parser parser;
    private CommandHandler commandHandler;

    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.commandHandler = new CommandHandler(this.ui);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.listenCommand();
    }

    private void listenCommand() {
        this.ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        while(this.parser.getIsListening()) {
            try {
                String currentText = sc.nextLine();
                CommandPair commandPair = this.parser.parseText(currentText);
                this.commandHandler.handleCommand(commandPair);
            } catch (DukeException error) {
                this.ui.printError(error);
            }
        }

        sc.close();
    }

}
