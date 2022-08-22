import utils.IOUtils;
import utils.Task;

import java.util.ArrayList;
import java.util.Scanner;
public class ConversationHandler {

    private Scanner in;
    private boolean active = true;
    private ArrayList<Task> list = new ArrayList<>();

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
//        We get the first word, since that determines the command
        switch (input.split(" ", 2)[0]) {
            case "Bye":
            case "bye":
                return this.closeCommand(input);

            case "list":
            case "List":
                return this.listCommand(input);

            case "mark":
            case "Mark":
                return this.markCommand(input);

            case "unmark":
            case "Unmark":
                return this.unMarkCommand(input);
            default:
                this.list.add(new Task(input));
                return "added: " + input;
        }
    }

    private String markCommand(String input) {
//        TODO: error handling here
        int index = Integer.parseInt(input.split(" ")[1]);

//        List is 1-indexed in cli
        Task task = this.list.get(index - 1);
        task.setDone();

        return "Nice! I've marked this as done: \n" + task;
    }
    private String unMarkCommand(String input) {
//        TODO: error handling here
        int index = Integer.parseInt(input.split(" ")[1]);

//        List is 1-indexed in cli
        Task task = this.list.get(index - 1);
        task.setNotDone();

        return "OK, I've marked this task as not done yet: \n" + task;
    }

    private String listCommand(String input) {
        String returnMsg = "";
        int index = 1;

        for (Task t: list) {
            returnMsg += index + ". " + t + "\n";
            index ++;
        }
        return returnMsg;
    }

    private String closeCommand(String input) {
        this.in.close();
        this.active = false;
        return "Bye. Hope to see you again!";
    }


}
