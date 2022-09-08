package duke;

public class Ui {



    public Ui() {

    }

    //methods
    public String print(String s) {

        switch (s) {
            case "bye": //bye
                return "Bye. Hope to see you again soon!";
            case "list": //list
                return "\nHere are the tasks in your list:";
            case "mark": //mark
                return "Nice! I've marked this task as done:\n";
            case "event":
            case "deadline":
            case "todo": //todo deadline event
                return "Got it. I've added this task:";
            case "delete": //delete
                return "Noted. I've removed this task:";
            default:
                return "";
        }

    }
}
