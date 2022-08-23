package sky;

import sky.command.Command;
import sky.exception.TextNoMeaningException;

import java.util.Scanner;

public class Sky {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Sky(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (TextNoMeaningException e) {
            this.taskList = new TaskList();
        }
    }

    public void run() {
        this.ui.greetUser();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while(!isExit) {
            try {
                String fullCommand = this.ui.readCommand(scanner);
                this.ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (TextNoMeaningException e) {
                System.out.println(e);
            } finally {
                this.ui.showLine(); // show the divider line ("_______")
            }
        }
    }
    
    public static void main(String[] args) {
        new Sky("data/sky.txt").run();
    }
}