package deku;

import java.nio.file.Path;

/**
 * Main class for start of bot
 */
public class Deku {
    private final BotList botList;
    private final Storage storage;

    private final InputParser parser;

    Deku(Path directoryPath, Path filePath) {
        storage = new Storage(directoryPath, filePath);
        botList = new BotList(storage.load(), storage);
        parser = new InputParser();
    }

    public String getList() {
        return botList.toString();
    }

    /**
     * Outputs message by deku
     *
     * @param input content Deku will say
     * @return Full string of the reply by Deku
     */
    public String getResponse(String input) {
        String dekuReply = parser.parseReply(input, botList);
        return "Deku Responds:" + dekuReply;
    }
}
