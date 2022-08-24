package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws EmptyDescriptionException, OutOfRangeException, UnknownCommandException {

        ArrayList<Task> arr = new ArrayList<>();


        System.out.println("Hello I'm Duke." + "\nWhat can I do for you?");

        //scanner
        Scanner sc = new Scanner(System.in);
        String input = "";

        //duke.duke.Storage
        Storage storage = new Storage("duke.txt");
        String s = storage.reader();

        //duke.duke.Parser
        Parser parser = new Parser();

        //process string
        s = s.replace("[T]", "todo");
        s = s.replace("[D]", "deadline");
        s = s.replace("[E]", "event");
        s = s.replace("[ ]", "");
        s = s.replace("[X]", "");
        if (s.contains(":")) {
            s = s.replace("[ ]", "");
            s = s.replace("(", "/");
            s = s.replace(":", "");
            s = s.replace(")", "");
        }

        //code mechanics
        while (true) {
            if (parser.terminator) {
                break;
            } else if (s.equals("")) {
                parser.setChecker();
                input = sc.nextLine();
                parser.parse(input);
            } else {
                int temp = s.indexOf("%");
                input = s.substring(0, temp);
                parser.parse(input);
                s = s.replaceAll(input + "%", ""); //remove old string
            }

        }

        sc.close();

        // write to file once scanner closes
        storage.writer(parser.getArr());

    }


}

