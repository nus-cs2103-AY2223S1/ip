public class CommandFilter {
    private String firstWord;
    private String remainderWords;
    private String[] commands = {"list", "bye", "mark", "unmark", "todo", "deadline", "event"};

    public void filterCommand(String command) {
        firstWord = null;
        remainderWords = null;
        int firstWhiteSpaceIndex = command.indexOf(' ');
        if (firstWhiteSpaceIndex == -1) { //no whitespace found
            firstWord = command;
        } else {
            firstWord = command.substring(0,firstWhiteSpaceIndex);

        }
        firstWord = firstWord.toLowerCase();
        if (isCommand(firstWord)) {
            if (!firstWord.equals(command)) {
                remainderWords = command.substring(firstWhiteSpaceIndex+1);
            }
        } else {
            firstWord = "No Command";
            remainderWords = command;
        }
    }

    public String getCommand() {
        return firstWord;
    }

    public String getRemainderCommand() {
        return remainderWords;
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
