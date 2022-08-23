import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Duke(String filePath){
      this.storage = new Storage(filePath);
      this.taskList = new TaskList(storage.load());
      this.ui = new Ui();
    }

    public void run() {
      this.taskList.printList();
      Scanner sc = new Scanner(System.in);
      ui.greet();
      ui.start(sc, this.storage, this.taskList);
    }
    public static void main(String[] args) {
      new Duke("tasks.txt").run();
    }



}
