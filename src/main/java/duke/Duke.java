package duke;
import java.util.Scanner;
import java.io.IOException;


public class Duke {
    Scanner sc;
    public Storage storage;
    public TaskList tasklist;
    public Ui ui =  new Ui();

    public Duke(String filePath) {
        storage = new Storage("src/filestorage/dummylist.txt");
        tasklist = new TaskList();
        try {
            this.tasklist = new TaskList();
            storage.readData();
        } catch (DukeException e) {
            this.tasklist = new TaskList();
            ui.errorMsg(e.getMessage());
        }
    }

    public void run() {
        TaskHandler taskHandler = new TaskHandler(tasklist);
        ui.welcomeMsg();
        boolean bye = false;
        Scanner sc = new Scanner(System.in);
        while (!bye) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                bye = true;
                ui.farewellMsg();
            } else if (input.equals("list")) {
                TaskList.showList();
            } else if (input.startsWith("mark ")) {
                TaskHandler.markChild(input);
            } else if (input.startsWith("unmark ")) {
                TaskHandler.unmarkChild(input);
            } else if (input.startsWith("delete ")) {
                TaskHandler.deleteTask(input);
            } else {
                TaskHandler.addTask(input);
            }
        }
        storage.writeData();
    }

    public static void main(String[] args) {
        new Duke("src/filestorage/dummylist.txt").run();
    }
}
