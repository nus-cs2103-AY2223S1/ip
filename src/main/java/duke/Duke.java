package duke;
import java.io.File;
import java.util.*;
import java.io.IOException;


public class Duke {
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    /*
     * Constructor for Class Duke
     * @param storage the storage object to read and write from Duke
     * @param taskList the taskList object
     * @param ui to print to the console
     * @param parser to parse commands from the user. 
     */
    public Duke(Storage storage, TaskList taskList, UI ui, Parser parser) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
        this.parser = parser;
    }

    /*
     * Run method for Duke, which coordinates the storage, taskList, ui and parser
     */
    public void run() {
        this.ui.greet();

        // Read file with tasks if it exists, else create a new one.
        File taskFile = new File("./data/duke.txt");
        if (taskFile.exists()) {
            this.taskList = this.storage.taskListReader();
        } else {
            try {
                File directory = new File("./data/");
                if (!directory.exists()) {
                    directory.mkdir();
                } 
                taskFile.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Takes in inputs and passes them to the Parser if they are neither list or bye
        // Scan for commands
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        
        while (!command.toLowerCase().equals("bye")) {
            if (command.equals("list")){
                this.ui.printList(this.taskList);
            } else {
                try {
                    this.parser.commandParser(command, taskList, ui);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            command = sc.nextLine();
        }

        this.storage.taskListWriter(taskList);
        sc.close();
        this.ui.exit();
    
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        UI ui = new UI();
        TaskList taskList = new TaskList();
        Parser parser = new Parser();

        Duke chatNUS = new Duke(storage, taskList, ui, parser);
        chatNUS.run();
    }

}
