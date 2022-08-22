import java.io.IOException;

public class Duke {
    private final Scan scan;
    private final Storage storage;

    public Duke() {
        TaskList taskList = new TaskList();
        this.storage = new Storage("data/duke.txt", taskList);
        this.scan = new Scan(taskList, storage);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    public void start() {
        try{
            storage.loadFile();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        scan.run();
    }


}
