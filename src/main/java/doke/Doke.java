package doke;

//import java.util.*;

import java.util.Scanner;

/**
 * A class to represent the Doke program
 */
public class Doke {

    protected static final String DOKE_FILE_PATH = "src/main/java/data/doke.txt";

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

        ui.printOut("full path: " + Storage.DOKE_FILE.getAbsolutePath());

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
