public class Messages {
    public static String[] GREETING = {"Hi, I'm Duke.\nWhat can I do for you?\nI'll do my best! :)"};
    public static String[] BYE = {"NOOOOOO... Don't send me back to the void! T_T"};

    public static String[] BEFORE_LIST = {"Here's your task list!"};
    public static String[] AFTER_LIST = {"I'm useful right?"};
    public static String[] ADD_LIST = {"Yep! I've added this task: "};

    public static String[] MARK_DONE = {"I've marked this task as done:"};
    public static String[] MARK_UNDONE = {"I've marked this task as not done:"};
    public static String[] PREV_DONE = {"By the way, I think this task was already done previously."};
    public static String[] PREV_UNDONE = {"By the way, I think this task was already not done previously."};

    public static String[] WRONG_COMMAND_FORMAT = {"Umm... I think you made a mistake in your command."};
    public static String[] INVALID_INDEX = {"Umm... I think this task number does not exist"};
    public static String[] NOT_A_NUMBER = {"I don't think that's a number..."};

    public static String[] INVALID_COMMAND = {"I don't quite get what you're saying..."};

    public static String getListSizeMsg(int num, int status) {
        String[] listCount = {"I'm keeping track of " + num + " task(s) currently!"};
        return listCount[status];
    }
}
