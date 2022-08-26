package seedu.duke;

import java.util.ArrayList;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;


// for level 9, use contains and check each task, appending to the temp array result

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }


    // taken from Week 3 topics



    public void run() {
        try {
            File file = new File("data/duke.txt");
            System.out.println("Current Tasks:");
            printFileContents("data/duke.txt");
            // no need to get tasks here, alr done in load at constructor
//            System.out.println(this.tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, added file");
        }

        // put as attributes in ui
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        ArrayList<Boolean> isOpen = new ArrayList<>();
        isOpen.add(true);

        try {
            while (scanner.hasNextLine() && isOpen.get(0)) {
                String input = scanner.nextLine();
                String command = this.ui.getUserCommand(input);
                Parser.parse(command, input, tasks, isOpen, scanner);
//                System.out.println(isOpen.get(0));
            }
        } catch (IllegalStateException e) {
            // just catching error
        }
        scanner.close();
    }

    // dealt with getting from file initially and saving, but need to call function for rewrite somewhere
    public static void main(String[] args) throws DukeException{
        new Duke("data/tasks.txt").run();
    }
}


