package duke;


public class Ui {

    public static String printEntryStatement() {
        String l1 = "hihi!\n";
        String l2 = "what can i do for you?\n";
        return l1 + l2;
    }

    public static String printByeStatement() {
        String l1 = "bye bye!\n";
        return l1;
    }

    public static String printAddStatement(String res, int len) {
        String s = "ok!\n" + "  " + res + "\nhas been added.\nnow you have " + len
                + " tasks in the list.\n";
        return s;
    }

    public static String printDeleteStatement(String res, int len) {
        String s = "okie!\n" + res + "\nhas been deleted forever.\n"
                + "you have " + len + " task" + ((len!=1)?"s ":" ") + "left!\n";
        return s;
    }

    public String printHelp() {
        String s = "need help? try these commands!\n" + "todo [description]: adds a new todo\n"
                + "deadline [description] /by DD/MM/YYYY HH:mm: adds a new task with a deadline\n"
                + "event [description] /at DD/MM/YYYY HH:mm: adds a new event\n"
                + "list: lists out all the current tasks\n" + "mark [index]: marks the task at index as completed\n"
                + "unmark [index]: marks the task at index as incomplete\n"
                + "delete [index]: deletes task at specified index\n"
                + "find [keyword]: finds the tasks containing specified keyword\n"
                + "statistics: shows you your task statistics.\n" + "bye: exits program!\n";
        return s;
    }
}
