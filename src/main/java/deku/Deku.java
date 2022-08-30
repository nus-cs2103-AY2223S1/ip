package deku;

import deku.Ui.MainWindow;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main class for start of bot
 */
public class Deku {
    private final BotList botList;
    private final Storage storage;
    private final UI ui;
    private final InputParser parser;

    Deku(Path directoryPath, Path filePath) {
        ui = new UI();
        storage = new Storage(directoryPath, filePath);
        botList = new BotList(storage.load(), storage);
        parser = new InputParser();
    }

    public String getList() {
        return botList.toString();
    }

    /**
     * Output message by deku
     *
     * @param input content Deku will say
     * @return Full string of the reply by Deku
     */
    public String getResponse(String input) {
        return "Deku Responds:" + parser.parseReply(input, ui, botList);
    }
}
