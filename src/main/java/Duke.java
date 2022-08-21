import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private ArrayList<Task> tasks;
    private Storage storage;

    public Duke(String folderPath, String filename) {
        storage = new Storage(folderPath, filename);
        tasks = new ArrayList<Task>();
    }

    private void startChatBot() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Eh hello, my name is Uncle Cheong. \n" +
                    "What you want?\n");
            tasks = storage.readSavedTasks();
            Parser parser = new Parser(sc, tasks);
            parser.parseInputs();
            storage.writeToFile(tasks);
        } catch (DukeException e) {
            System.out.println(e);
        }
        System.out.println("Eh you leaving me so soon?");
    }

    public static void main(String[] args) {
        Duke uncleCheong = new Duke("data", "duke.txt");
        uncleCheong.startChatBot();
    }
}
