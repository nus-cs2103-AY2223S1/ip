package alpha;

import alpha.command.Command;
import alpha.command.Exit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.Scanner;

public class Alpha {
    Ui uI = new Ui();
    Parser parser = new Parser();
    TaskList taskList;
    final String FILE_PATH;
    FileOperations fileOperations;

     public Alpha(String filePath) {
         FILE_PATH = filePath;
         fileOperations = new FileOperations(FILE_PATH);
         try {
             fileOperations.createFile();
             taskList = new TaskList(fileOperations.readFile());
         } catch(AlphaException e) {
             uI.colouredPrint(uI.ANSI_RED, e.getMessage());
         }
     }
    public void run() {
        Scanner in = new Scanner(System.in);
        uI.welcomeMessage();
        boolean isExit = false;
        while(!isExit) {
            String userInput = uI.takeUserInput(in);
            try {
                Command c = parser.interpretMessage(userInput);
                c.execute(taskList, uI, fileOperations);
                if (c instanceof Exit) {
                    isExit = true;
                }
            } catch (AlphaException | DateTimeException e) {
                uI.colouredPrint(uI.ANSI_RED, e.getMessage());
            }
        }
        uI.colouredPrint(uI.ANSI_BLUE, ">> Bye, see you soon!");
        System.exit(0);
    }
    public static void main(String[] args) {
        new Alpha("./alpha.Alpha.txt").run();
    }
}
