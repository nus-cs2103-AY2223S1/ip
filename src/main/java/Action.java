public class Action {
    private final String action;

    Action(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return this.action + "\n";
    }
}
