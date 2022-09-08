package duke;

public class Ui {



    public Ui() {

    }

    //methods
    public String print(int i) {

        switch (i) {
            case 1: //bye
                return "Bye. Hope to see you again soon!";
            case 2: //list
                return "\nHere are the tasks in your list:";
            case 3: //mark
                return "Nice! I've marked this task as done:\n";
            case 4:
            case 5:
            case 6: //todo deadline event
                return "Got it. I've added this task:";
            case 7: //delete
                return "Noted. I've removed this task:";
            default:
                return "";
        }

    }
}
