package deku;

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

    /**
    * Method to start the chat-bot
    */
    private void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ui.introduction());
        boolean active = true;
        while (active && scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                active = false;
            }
            System.out.println(parser.parseReply(userInput, ui, botList));
        }
    }

    String getResponse(String input) {
        return "Deku Responds:" + input;
    }

    /**
    * Default main method
    */
    //Solution below adapted and modified from https://stackoverflow.com/questions/24709769/
    public static void main(String[] args) throws IOException {
        Path directoryPath = Paths.get(System.getProperty("user.dir"), "data");
        Path filePath = directoryPath.resolve("save.txt");
        new Deku(directoryPath, filePath).start();
    }
}
