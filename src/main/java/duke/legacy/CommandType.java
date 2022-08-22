public enum CommandType {
    EXIT("bye");





    private String commandString;

    CommandType(String commandString) {
        this.commandString = commandString;
    }

    String getCommandString() {
        return commandString;
    }
}
