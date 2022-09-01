import task_classes.Deadline;
import task_classes.Event;
import task_classes.Task;
import task_classes.Todo;
import utils.FileIO;
import utils.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Duke {

    private boolean open = true;
    private ArrayList<Task> list;

    private Ui ui;

    public Duke() {
        this.ui = new Ui();

    }

    public void run() {
        this.ui.start();
        this.open = true;
        list = new ArrayList<>();
        for (Task t: FileIO.getInstance().readTaskList()) {
            list.add(t);
        }

        Scanner in = new Scanner(System.in);

        while (this.isOpen() && in.hasNext()) {
            String line = in.nextLine();
            String output = this.handleCommand(ui.read(line));
            ui.printWithHorizontalRule(output);
        }
    }

    public String handleCommand(HashMap<String, String> command) {
        String reply = "";
        try {

        } catch (IllegalArgumentException e) {
            return "Something went wrong 😔: " + e.getMessage();
        }

        FileIO.getInstance().saveList(list);
        return reply;
    }

    private String deleteCommand(HashMap<String, String> commandObj) {
        if (!commandObj.containsKey("args") || !Parser.IsInteger(commandObj.get("args"))) {
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
            throw new IllegalArgumentException("Event must have a Name and a date!");
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
            throw new IllegalArgumentException("Deadline must have a Name and a deadline!");
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
            throw new IllegalArgumentException("TODO must have a Name!");
        }

        String name = commandObj.get("args");

        Todo t = new Todo(name);
        list.add(t);
        return "Got it. I've added this task: \n" +
                t.toString() + "\n" +
                "Now you have " + list.size() + " tasks in the list.";
    }

    private String markCommand(HashMap<String, String> commandObj) {
        if (!commandObj.containsKey("args") || !Parser.IsInteger(commandObj.get("args"))) {
            throw new IllegalArgumentException("Mark needs an index");
        }

        int index = Integer.parseInt(commandObj.get("args"));

//        List is 1-indexed in cli
        Task task = this.list.get(index - 1);
        task.setDone();

        return "Nice! I've marked this as done: \n" + task;
    }

    private String unMarkCommand(HashMap<String, String> commandObj) {
        if (!commandObj.containsKey("args") || !Parser.IsInteger(commandObj.get("args"))) {
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


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
