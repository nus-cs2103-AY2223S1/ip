public enum Action {
//    Level_1
    GREET, ECHO, EXIT,
//    Level_2
    ADD, LIST,
//    Level_3
    MARK, UNMARK;

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
        }
        return null;
    }

    public static Action getAction(String action) {
        action = action.trim().toLowerCase();
        switch (action) {
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
        }
        return Action.ADD;
    }
}
