import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws IOException {
        Storage storage = new Storage();
        TaskList tasks = storage.loadFile();
        Parser parser = new Parser(tasks, storage);
        UI ui = new UI();
        ui.welcomeUser();
        String input = ui.readInput();  // Read user input
        while (!input.equals("bye")) {
            parser.parse(input);
            input = ui.readInput(); // Read next user input
        }
        ui.sayBye();
    }
}