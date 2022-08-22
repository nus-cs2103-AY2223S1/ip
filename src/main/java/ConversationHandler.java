import utils.IOUtils;

import java.util.ArrayList;
import java.util.Scanner;
public class ConversationHandler {

    private Scanner in;
    private boolean active = true;
    private ArrayList<String> list = new ArrayList<>();

    public ConversationHandler() {
        IOUtils.printContentWithHR("Hello! I'm " + Main.name + "\n" + "What can I do for you?");
        this.in = new Scanner(System.in);
        this.active = true;

        while(this.active && this.in.hasNext()) {
            String line = this.in.nextLine();
            String output = commandHandler(line);
            IOUtils.printContentWithHR(output);
        }
    }

    public String commandHandler(String input) {
        switch (input) {
            case "Bye":
            case "bye":
                this.close();
                return "Bye. Hope to see you again!";

            case "list":
            case "List":
                return this.enumerateList();

            default:
                this.list.add(input);
                return "added: " + input;
        }
    }

    private String enumerateList() {
        String returnMsg = "";
        int index = 1;

        for (String str: list) {
            returnMsg += index + ". " + str + "\n";
            index ++;
        }
        return returnMsg;
    }

    private void close() {
        this.in.close();
        this.active = false;
    }


}
