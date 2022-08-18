public enum Action {
    Todo("todo"),
    Deadline("deadline"),
    Event("event"),
    Mark("mark"),
    Unmark("unmark");

    public final String label;
    private Action(String label) {
        this.label = label;
    }

    public static Action parseCommand(String command) {
        for (Action action : values()) {
            if (command.startsWith(action.label)) {
                return action;
            }
        }
        return null;
    }
}
