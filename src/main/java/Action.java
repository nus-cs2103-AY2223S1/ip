public enum Action {
//    Level_1
    GREET, ECHO, EXIT,
//    Level_2
    ADD, LIST,
//    Level_3
    MARK, UNMARK,
//    Level_4
    TODO, EVENT, DEADLINE;

    @Override
    public String toString() {
        switch (this) {
            case GREET:
                return "greet";
            case ECHO:
                return "echo";
            case EXIT:
                return "bye";
            case ADD:
                return "add";
            case LIST:
                return "list";
            case MARK:
                return "mark";
            case UNMARK:
                return "unmark";
            case TODO:
                return "todo";
            case EVENT:
                return "event";
            case DEADLINE:
                return "deadline";
        }
        return null;
    }

    public static Action getAction(String action) {
        action = action.trim().toLowerCase();
        switch (action) {
            case "":
                return null;
            case "greet":
                return Action.GREET;
            case "echo":
                return Action.ECHO;
            case "bye":
                return Action.EXIT;
            case "list":
                return Action.LIST;
            case "mark":
                return Action.MARK;
            case "unmark":
                return Action.UNMARK;
            case "todo":
                return Action.TODO;
            case "event":
                return Action.EVENT;
            case "deadline":
                return Action.DEADLINE;
        }
        return Action.ADD;
    }
}
