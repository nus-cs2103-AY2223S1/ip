package duke;
import java.util.Scanner;
import java.util.ArrayList;

public class Actions { //actions that Duke can perform
    /**
     * The current chatbot functionality which takes in user input and reacts accordingly to the input.
     */
    public static void toDoList() {
        TaskList currList = new TaskList();
        Storage fileHandler = new Storage(currList);
        fileHandler.readAndProcessFile(currList);
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
