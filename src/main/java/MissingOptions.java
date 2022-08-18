public class MissingOptions extends DukeException {
    private String cmd;

    public MissingOptions(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "The command " + "\"" + cmd + "\"" + " is missing or has invalid options. (E.g. index, date, description)";
    }
    
}
