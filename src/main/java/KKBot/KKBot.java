package KKBot;

import KKBot.commands.Command;
import KKBot.exceptions.KKBotException;
import KKBot.parser.Parser;
import KKBot.storage.Storage;
import KKBot.tasklist.TaskList;
import KKBot.ui.Ui;

/**
 * KKBot.KKBot is a chatbot that helps keep track of your to-do list!
 *
 * @author AkkFiros
 */
public class KKBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public KKBot() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (KKBotException e) {
            this.ui.show(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.ui.readInput();
                Command command = Parser.initialParse(input);
                command.execute(this.tasks, this.ui, this.storage);
                this.storage.save(this.tasks);
            } catch (KKBotException e) {
                this.ui.show(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        KKBot bot = new KKBot();
        bot.run();
    }
}
