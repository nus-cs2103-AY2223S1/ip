package dobby;

/**
 * UserInput is a class that deals with interactions with the user.
 */
public class UserInput {
    private static String mark = "m";
    private static String unmark = "um";
    private static String delete = "del";
    private static String todo = "t";
    private static String find = "f";
    private static String deadline = "d";
    private static String event = "e";
    private static String simplify = "s";
    private static String newCmd;
    private static String oldCmd;
    private String taskType;
    private String rest;
    private int ind;
    private String desc;
    private String date;

    /**
     * Reads the user input
     * and updates all required information
     * and returns a string of the task type.
     *
     * @return String of the task type.
     */
    public String readCommand(String input) {
        try {
            if (!input.contains(" ")) {
                //TODO find another more elegant way to solve this
                if (input.contains("simplify")) {
                    taskType = "simplify";
                    oldCmd = "";
                    newCmd = "";
                } else {
                    taskType = input;
                }
            } else {
                taskType = Parser.getTaskType(input);
                rest = Parser.getRestOfCommand(input);
                if (taskType.equals("mark") | taskType.equals(mark)) {
                    taskType = "mark";
                    ind = Integer.parseInt(rest);
                } else if (taskType.equals("unmark") | taskType.equals(unmark)) {
                    taskType = "unmark";
                    ind = Integer.parseInt(rest);
                } else if (taskType.equals("delete") | taskType.equals(delete)) {
                    taskType = "delete";
                    ind = Integer.parseInt(rest);
                } else if (taskType.equals("todo") | taskType.equals(todo)) {
                    taskType = "todo";
                    desc = rest;
                } else if (taskType.equals("find") | taskType.equals(find)) {
                    taskType = "find";
                    desc = rest;
                } else if (taskType.equals("simplify") | taskType.equals(simplify)) {
                    taskType = "simplify";
                    oldCmd = Parser.getOldCommand(rest);
                    newCmd = Parser.getNewCommand(rest);
                } else if (taskType.equals("deadline") | taskType.equals(deadline)) {
                    taskType = "deadline";
                    String dateType = Parser.getDateType(rest);
                    if (dateType.equals("noDate")) {
                        date = "noDate";
                        desc = Parser.getDesc(rest);
                    } else if (!(dateType.equals("by"))) {
                        date = "wrongDeadline";
                        desc = Parser.getDesc(rest);
                    } else {
                        date = Parser.getDate(rest);
                        desc = Parser.getDesc(rest);
                    }
                } else if (taskType.equals("event") | taskType.equals(event)) {
                    taskType = "event";
                    String dateType = Parser.getDateType(rest);
                    if (dateType.equals("noDate")) {
                        date = "noDate";
                        desc = Parser.getDesc(rest);
                    } else if (!(dateType.equals("at"))) {
                        date = "wrongEvent";
                        desc = Parser.getDesc(rest);
                    } else {
                        date = Parser.getDate(rest);
                        desc = Parser.getDesc(rest);
                    }
                }
            }
        } catch (java.lang.NumberFormatException e) {
            DobbyChat.noNumber();
        }
        return taskType;
    }

    /**
     * Returns the index of UserInput object.
     *
     * @return index of UserInput object.
     */
    public int getInd() {
        return ind;
    }

    /**
     * Returns the description of UserInput object.
     *
     * @return description of UserInput object.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns the date of UserInput object.
     *
     * @return date of UserInput object.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the task type of UserInput object.
     *
     * @return task type of UserInput object.
     */
    public String getTaskType() {
        return taskType;
    }

    public String getOldCmd() {
        return oldCmd;
    }

    public String getNewCmd() {
        return newCmd;
    }

    public static void setSimplifiedCommand() {
        switch (oldCmd) {
        case "mark":
            mark = newCmd;
            break;
        case "unmark":
            unmark = newCmd;
            break;
        case "delete":
            delete = newCmd;
            break;
        case "todo":
            todo = newCmd;
            break;
        case "find":
            find = newCmd;
            break;
        case "deadline":
            deadline = newCmd;
            break;
        case "event":
            event = newCmd;
            break;
        case "simplify":
            simplify = newCmd;
            break;
        default:
            DobbyChat.unknown();
        }
    }
}
