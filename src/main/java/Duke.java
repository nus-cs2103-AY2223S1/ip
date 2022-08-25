import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
//    /**
//     * This method is a self-introduction for the Luke Bot.
//     */
//    public static void selfIntro() {
//        String logo = " _           _        \n"
//                + "| |    _   _| | _____ \n"
//                + "| |   | | | | |/ / _ \\\n"
//                + "| |___| |_| |   <  __/\n"
//                + "|_____|\\__,_|_|\\_\\___|\n";
//        System.out.println("__________________________________________________");
//        System.out.println(("Hola Amigo! My name is\n" + logo));
//        System.out.println(("How may I assist you today?"));
//        System.out.println("__________________________________________________");
//    }
//
//    public static void main(String[] args) {
//        selfIntro();
//
//        ChatBox chat_feature = new ChatBox();
//
//        chat_feature.Reply();
//    }
}
