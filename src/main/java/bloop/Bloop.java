package bloop;

import java.io.IOException;
import java.util.Scanner;

/**
 * Chatbot to keep track of tasks.
 */
public class Bloop {

    private static Ui ui;

    private static Storage storage;

    private static TaskList tasks;

    private static Parser parser;

    /**
     * Constructor for Bloop.
     */
    public Bloop() {
        ui = new Ui();
        storage = new Storage("BloopData.txt", ui);
        tasks = new TaskList(ui, storage);
    }

    private static void chat() {
        Bloop bloop = new Bloop();
        ui.startMessage();
        storage.makeFile(tasks.getList());
        parser = new Parser(tasks);
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        while(text.compareTo("bye") != 0) {
            try {
                parser.parse(text);
            } catch(BloopException be) {
                ui.print(be.getMessage());
            } catch(IOException e) {
                ui.print(e.getMessage());
            }
            text = sc.nextLine();
        }
        ui.endMessage();
    }

    public static void main(String[] args) {
        chat();
    }
}
