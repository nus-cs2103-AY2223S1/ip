import java.util.Scanner;
import java.util.ArrayList;

public class Actions { //actions that Duke does
    /**
     * The current chatbot functionality which takes in user input and reacts accordingly to the input.
     */
    public static void toDoList() {
        ArrayList<Task> ls = new ArrayList<>();
        TaskList currList = new TaskList(ls);
        Storage fileHandler = new Storage(currList);
        fileHandler.readAndProcessFile(currList);
        Ui.greetingMessage();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Parser userInputParser = new Parser(currList);
            userInputParser.parseUserInput(input);
            Storage taskSaver = new Storage(currList);
            taskSaver.writeToFile();
            input = sc.nextLine();
        } //end of while loop, means input is bye
        Ui.endingMessage();
    }
}
