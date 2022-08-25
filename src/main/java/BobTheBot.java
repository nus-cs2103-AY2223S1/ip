import java.io.IOException;
import java.util.Scanner;

public class BobTheBot {
    private Storage storage;
    private ToDoList list;
    private Parser parser;

    public BobTheBot(String filePath) throws IOException {
        this.storage = new Storage(filePath);
        this.list = new ToDoList(this.storage.load(), storage);
        this.parser = new Parser();
    }

    public void run() throws BobException {
        Ui.welcome();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.toLowerCase().equals("bye")) {
                Ui.goodbye(list);
                break;
            }
            parser.parseCommand(command, list);
        }

        scanner.close();
    }

    public static void main(String[] args) throws BobException, IOException {
        new BobTheBot("./../../data/data.txt").run();
    }
}
