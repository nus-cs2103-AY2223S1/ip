import task_classes.Deadline;
import task_classes.Event;
import task_classes.Todo;
import utils.IOUtils;
import task_classes.Task;

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

            case "todo":
            case "Todo":
                return this.addTodoCommand(input);

            case "deadline":
            case "Deadline":
                return this.addDeadlineCommand(input);

            case "event":
            case "Event":
                return this.addEventCommand(input);

            default:
                return input;
        }
    }

    private String addEventCommand(String input) {
//        This is the name + /at + date
//        TODO: Error handling (no /at or date)
        String args = input.split(" ", 2)[1];
        String name = input.split("/at", 2)[0].strip();
        String by = input.split("/at", 2)[1].strip();
        Event e = new Event(name, by);
        list.add(e);
        return "Got it. I've added this task: \n" +
                e.toString() + "\n" +
                "Now you have " + list.size() + " tasks in the list.";
    }

    private String addDeadlineCommand(String input) {
//        This is the name + /by + date
//        TODO: Error handling (no /by or date)
        String args = input.split(" ", 2)[1];
        String name = input.split("/by", 2)[0].strip();
        String by = input.split("/by", 2)[1].strip();
        Deadline d = new Deadline(name, by);
        list.add(d);
        return "Got it. I've added this task: \n" +
                d.toString() + "\n" +
                "Now you have " + list.size() + " tasks in the list.";

    }

    private String addTodoCommand(String input) {
        Todo t = new Todo(input.split(" ", 2)[1]);
        list.add(t);
        return "Got it. I've added this task: \n" +
                t.toString() + "\n" +
                "Now you have " + list.size() + " tasks in the list.";
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
