package doke;

import java.util.*;

public class Doke {

    public static final String dokeFilePath = "src/main/java/data/doke.txt";

    public static void main(String[] args) {

        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();
        Parser parser = new Parser();
        TaskList taskList;

        ui.printOut("full path: " + Storage.dokeFile.getAbsolutePath());

        ui.printOut("hello");

        taskList = new TaskList(ui, storage);
        ui.printOut("The following is your stored task: \n");
        taskList.listOut(ui);

        String str = sc.nextLine();
        while (true) {
            if (!parser.processString(taskList, storage, str, ui)) {
                break;
            }
            str = sc.nextLine();
        }

        ui.printOut("bye");

    }
}