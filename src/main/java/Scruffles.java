import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scruffles {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    public Scruffles(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            }
            try {
                Parser.parse(input, tasks);
            } catch (UnknownArgumentException | DescriptionEmptyException e) {
                ui.printMessage(e.getMessage());
            }
        }
        storage.save(tasks);
        ui.bye();
    }
    public static void main(String[] args) {
        new Scruffles("/Users/shamustan/Desktop/University/AY22:23 S1/CS2103T/scruffles.txt").run();
    }
}