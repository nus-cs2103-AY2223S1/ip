package duke;

import java.util.Scanner;
/**
 *  A class which encapsulates the functionality Duke can do, current functionality is only the to-do list.
 *  @author  Chen Guanzhou
 *  @version v1
 */

public class Actions { //actions that Duke can perform

    public static void toDoList() {
        TaskList currList = new TaskList();
        Storage fileHandler = new Storage(currList);
        fileHandler.readAndProcessFile();
        Ui.greetingMessage();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Parser userInputParser = new Parser(currList);
        while (!input.equals("bye")) {
            userInputParser.parseUserInput(input);
            Storage taskSaver = new Storage(currList);
            taskSaver.writeToFile();
            input = sc.nextLine();
        } //end of while loop, means input is bye
        Ui.endingMessage();
    }
}
