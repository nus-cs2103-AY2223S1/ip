import task_classes.Deadline;
import task_classes.Event;
import task_classes.Task;
import task_classes.Todo;
import utils.FileIO;
import utils.IOUtils;
import utils.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConversationHandler {

    private boolean open = true;
    private ArrayList<Task> list = new ArrayList<>();

    public ConversationHandler() {
        IOUtils.printContentWithHR("Hello! I'm " + Main.name + "\n" + "What can I do for you?");
        this.open = true;
        list = new ArrayList<>();
        for (Task t: FileIO.getInstance().readTaskList()) {
            list.add(t);
        }
    }

    public String handleCommand(String input) {
//        We get the first word, since that determines the command
        HashMap<String, String> command = InputParser.getInputArguments(input);
        String reply = "";
        try {
            switch (command.get("keyword")) {
                case "Bye":
                case "bye":
                    reply = this.closeCommand(command);
                    break;

                case "list":
                case "List":
                    reply = this.listCommand(command);
                    break;

                case "mark":
                case "Mark":
                    reply = this.markCommand(command);
                    break;

                case "unmark":
                case "Unmark":
                    reply = this.unMarkCommand(command);
                    break;

                case "todo":
                case "Todo":
                    reply = this.addTodoCommand(command);
                    break;

                case "deadline":
                case "Deadline":
                    reply = this.addDeadlineCommand(command);
                    break;

                case "event":
                case "Event":
                    reply = this.addEventCommand(command);
                    break;

                case "delete":
                case "Delete":
                case "remove":
                case "Remove":
                    reply = this.deleteCommand(command);
                    break;


                default:
                    reply = input;
                    break;
            }
        } catch (IllegalArgumentException e) {
            return "Something went wrong 😔: " + e.getMessage();
        }

        FileIO.getInstance().saveList(list);
        return reply;
    }

    private String deleteCommand(HashMap<String, String> commandObj) {
        if (!commandObj.containsKey("args") || !InputParser.IsInteger(commandObj.get("args"))) {
            throw new IllegalArgumentException("Delete command must contain a numeric argument!");
        }

        int index = Integer.parseInt(commandObj.get("args")) - 1;
        Task task = this.list.get(index);
        this.list.remove(index);
        return "Noted. I've removed this task: \n" +
                task.toString() + "\n" +
                "Now you have " + this.list.size() + " tasks in the list.";
    }

    private String addEventCommand(HashMap<String, String> commandObj) {
        if (!commandObj.containsKey("args") || !commandObj.containsKey("/at")) {
            throw new IllegalArgumentException("Event must have a name and a date!");
        }

        String name = commandObj.get("args");
        String at = commandObj.get("/at");
        Event e = new Event(name, at);
        list.add(e);

        return "Got it. I've added this task: \n" +
                e.toString() + "\n" +
                "Now you have " + list.size() + " tasks in the list.";
    }

    private String addDeadlineCommand(HashMap<String, String> commandObj) {
        if (!commandObj.containsKey("args") || !commandObj.containsKey("args")) {
            throw new IllegalArgumentException("Deadline must have a name and a deadline!");
        }

        String name = commandObj.get("args");
        String by = commandObj.get("/by");
        Deadline d = new Deadline(name, by);
        list.add(d);
        return "Got it. I've added this task: \n" +
                d.toString() + "\n" +
                "Now you have " + list.size() + " tasks in the list.";

    }

    private String addTodoCommand(HashMap<String, String> commandObj) {
        if (!commandObj.containsKey("args")) {
            throw new IllegalArgumentException("TODO must have a name!");
        }

        String name = commandObj.get("args");

        Todo t = new Todo(name);
        list.add(t);
        return "Got it. I've added this task: \n" +
                t.toString() + "\n" +
                "Now you have " + list.size() + " tasks in the list.";
    }

    private String markCommand(HashMap<String, String> commandObj) {
        if (!commandObj.containsKey("args") || !InputParser.IsInteger(commandObj.get("args"))) {
            throw new IllegalArgumentException("Mark needs an index");
        }

        int index = Integer.parseInt(commandObj.get("args"));

//        List is 1-indexed in cli
        Task task = this.list.get(index - 1);
        task.setDone();

        return "Nice! I've marked this as done: \n" + task;
    }

    private String unMarkCommand(HashMap<String, String> commandObj) {
        if (!commandObj.containsKey("args") || !InputParser.IsInteger(commandObj.get("args"))) {
            throw new IllegalArgumentException("Unmark needs an index");
        }

        int index = Integer.parseInt(commandObj.get("args"));

//        List is 1-indexed in cli
        Task task = this.list.get(index - 1);
        task.setNotDone();

        return "OK, I've marked this task as not done yet: \n" + task;
    }

    private String listCommand(HashMap<String, String> commandObj) {
        String returnMsg = "";
        int index = 1;

        for (Task t : list) {
            returnMsg += index + ". " + t + "\n";
            index++;
        }
        return returnMsg;
    }

    private String closeCommand(HashMap<String, String> commandObj) {
        this.open = false;
        return "Bye. Hope to see you again!";
    }

    public boolean isOpen() {
        return this.open;
    }


}
