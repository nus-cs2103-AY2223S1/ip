import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    private File file = Path.of("data/duke.txt").toFile();

    private ArrayList<Task> listOfActions = new ArrayList<Task>(100);

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
            //Ok got shit in the task
            this.tasks.printContent();
            ui.showGotTask();
        } catch (DukeException e) {
            ui.showNoTask();
            //does nothing but instantiate a object
            this.tasks = new TaskList();
        }

    }

    public void run() {
        String type;
        int currentAction = this.storage.getSize();
        int end = 0;
        ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (end != 1) {
            //If user wants to check the list
            String output = list(tasks.taskArray, currentAction);
            String[] input = userInput.split(" ");
            Parser parse = new Parser(this.tasks, userInput);
            if (parse.isErreneous()) {
                type = parse.getType();
                ui.showInaccurateInput();
            } else  {
                if (parse.isAction) {
                    currentAction++;
                }
                type = parse.getType();
            }
            if (type.equals("bye")) {
                ui.goodByeMessage();
                break;
            } else if (type.equals("list")) {
                ui.showList(tasks.getList());
            } else if (type.equals("delete")) {
                currentAction--;
            }

            userInput = sc.nextLine();
        }
    }


    public static void main(String[] args) {

        new Duke("data/duke.txt").run();

    }

    public static String list(ArrayList<Task> listOfActions, int currentAction) {
        String o = "";
        for (int i = 0; i < currentAction; i++) {
            o = o + String.format("%d", i + 1) + "." + listOfActions.get(i) + "\n";
        }
        return o;
    }





}


