package doke;

import java.util.*;

/**
 * A class to represent the Doke program
 */
public class Doke {

    public static final String dokeFilePath = "src/main/java/data/doke.txt";

    /**
     * A main method which runs the Doke program.
     *
     * @param args
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();
        Parser parser = new Parser();
        TaskList taskList;

        ui.printOut("full path: " + Storage.dokeFile.getAbsolutePath());

        ui.printOut("hello");

        taskList = new TaskList(ui, storage);
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