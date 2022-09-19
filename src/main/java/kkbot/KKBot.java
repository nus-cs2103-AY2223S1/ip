package kkbot;

import kkbot.commands.Command;
import kkbot.exceptions.KKBotException;
import kkbot.parser.Parser;
import kkbot.storage.Storage;
import kkbot.tasklist.TaskList;
import kkbot.ui.Ui;

/**
 * kkbot.kkbot is a chatbot that helps keep track of your to-do list!
 *
 * @author AkkFiros
 */
public class KKBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public KKBot() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(this.storage.load());
        } catch (KKBotException e) {
            ui.show(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String input = ui.readInput();
                Command command = Parser.initialParse(input);
                command.execute(tasks, ui, storage);
                storage.save(tasks);
            } catch (KKBotException e) {
                ui.show(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        KKBot bot = new KKBot();
        bot.run();
    }
}
