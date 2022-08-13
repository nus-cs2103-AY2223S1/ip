import utils.Constants;
import utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static Utilities util;
    private final List<String> tasks;

    Duke() {
        util = new Utilities();
        tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startDuke();
    }

    public void startDuke() {
        sendGreetings();
        Scanner sc = new Scanner(System.in);

        boolean isDone = false;
        while (!isDone) {
            String input = sc.nextLine();
            switch (input.toUpperCase()) {
                case "BYE":
                    sendExit();
                    isDone = true;
                    break;
                case "LIST":
                    printTasks();
                    break;
                default:
                    tasks.add(input);
                    util.printMsg("added: " + input);
                    break;
            }
        }
        sc.close();
    }

    public void printTasks() {
        util.printStraightLine();
        int counter = 1;
        for (String s : tasks) {
            System.out.println("\t" + counter + ". " + s);
            counter++;
        }
        util.printStraightLine();
    }

    public void sendGreetings() {
        util.printMsg(Constants.MSG_GREETINGS);
    }

    public void sendExit() {
        util.printMsg(Constants.MSG_EXIT);
    }

}