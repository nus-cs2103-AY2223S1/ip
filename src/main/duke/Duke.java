package duke;

import com.sun.tools.javac.Main;
import java.lang.reflect.ParameterizedType;
import java.util.Scanner;
import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
//    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        try {
            this.tasks = Storage.load();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run () {
        this.ui.showWelcomeMessage();
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            System.out.println("Enter your command:");
//            String input = sc.nextLine();
//            this.ui.echo(this.taskList, input);
//        }
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(sc);
//                System.out.println(fullCommand);
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(this.tasks);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }


}
