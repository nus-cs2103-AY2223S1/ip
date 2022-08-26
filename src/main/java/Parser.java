public class Parser {
    private Command command;
    private String args;

    public boolean parse(String s) {
        String[] words = s.split(" ", 2);
        args = words.length == 2 ? words[1] : "";
        try {
            command = Command.valueOf(words[0].toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            command = null;
            return false;
        }
    }

    public void runCommand(Duke duke) {
        if (command != null) {
            command.run(args, duke);
        }
    }
}
