package chacha;
import java.util.Scanner;


import chacha.commands.Command;
import chacha.tasks.Task;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Chacha {
    public static void main(String[] args) {
        boolean isExit = false;
        Ui ui = new Ui();
        System.out.println(ui.printWelcome());
        
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (CustomException e) {
                ui.printError(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void loadData(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            //load task objects into array
            System.out.println(s.nextLine());
        }
    }
}
