import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Duke(String filepath){
        this.ui = new Ui();
        try {
            this.storage = new Storage(filepath);
            this.taskList = this.storage.loadTasks();
        }catch (IOException e){
            this.ui.showFileCreatingError();
        }
    }
    public void run(){
        this.ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList,this.storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                ui.showDukeException(e.getMessage());
            }
        }
        ui.showGoodbyeMessage();
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
