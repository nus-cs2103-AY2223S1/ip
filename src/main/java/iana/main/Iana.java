package iana.main;


import iana.command.Command;
import iana.exception.IanaException;
import iana.tasks.TaskList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Iana {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Iana(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = storage.load();
        } catch (IanaException e) {
            this.ui.echo(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {     
        ui.sayHi();
        boolean isActive = true;

        while (isActive) {
            Scanner sc = new Scanner(System.in);

            if (sc.hasNextLine()) {
                try {
                    String input = sc.nextLine();
                    Command command = Parser.parse(input);
                    isActive = !command.isExit();
                    command.execute(tasks, ui, storage);
                } catch(IanaException e) {
                    ui.echo(e.getMessage());
                }
            }
            if (!isActive) {
                sc.close();
            }
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("src", "main/data", "DataStorage.txt");
        String filePath = path.toAbsolutePath().toString();
        new Iana(filePath).run();
    }
}