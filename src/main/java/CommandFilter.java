public class CommandFilter {
    private String command;
    private String remainderCommand;
    private String[] commands = {"list", "bye", "mark", "unmark"};

    public void filterCommand(String command) {
        this.command = null;
        this.remainderCommand = null;
        String[] commands = command.split(" ");
        if (isCommand(commands[0])) {
            this.command = commands[0];
            if (commands.length > 1) {
                this.remainderCommand = commands[1];
            }
        } else {
            this.command = "No Command";
            this.remainderCommand = command;
        }
    }

    public String getCommand() {
        return command;
    }

    public String getRemainderCommand() {
        return remainderCommand;
    }

    public Integer getRemainderCommandAsInt() {
        return Integer.parseInt(remainderCommand);
    }

    private boolean isCommand(String command) {
        for (int i = 0; i < commands.length; i++) {
            if (command.equals(commands[i])) {
                return true;
            }
        }
        return false;
    }
}
