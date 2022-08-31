public class Pikachu {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Pikachu(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void run() {
        ui.intro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PikachuException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        
    }

    public static void main(String[] args) {
        new Pikachu("./././data/pikachu.txt").run();
    }
}
