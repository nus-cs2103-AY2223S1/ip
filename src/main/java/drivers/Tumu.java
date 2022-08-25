import exceptions.TumuException;

/**
 * Chatbot driver code. Receives the input from the user
 * and responds accordingly.
 */
public class Tumu {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Tumu() {
        ui = new UI();
        storage = new Storage("data/Tumu.txt");
        tasks = new TaskList(storage.loadData());
    }

    public static void main(String[] args) {
        new Tumu().run();
    }

    private void run() {
        ui.greeting();
        response();
    }

    private void response() {
        String command = "";
        do {
            String fullCommand = ui.readCommand();
            try {
                ui.showLine();
                Command c = Parser.parse(fullCommand, ui);
                command = Parser.getCommand(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (TumuException e) {
                ui.notifyUser(e.toString());
            } catch (NumberFormatException e) {
                ui.notifyUser("Please (un)mark or delete a task by " +
                        "its list position (must be an integer)!");
                ui.readCommand(); //flush rest of the commands
            } finally {
                ui.showLine();
            }
        } while (command.equalsIgnoreCase("bye"));

        ui.closeReader();
    }
}
