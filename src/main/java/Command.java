public class Command {
    private final String keyword;
    private final String content;

    enum Keyword {
        bye, list, mark, unmark, todo, deadline, event, delete
    }

    enum TaskKeyword {
        todo, deadline, event
    }

    public Command(String command) {
        this.keyword = getCommandKey(command);
        this.content = getCommandContent(command);
    }

    private String getCommandKey(String command) {
        int index = command.indexOf(' ');
        if (index == -1) {
            return command;
        }
        return command.substring(0, index);
    }

    private String getCommandContent(String command) {
        int index = command.indexOf(' ');
        if (index == -1) {
            return "";
        }
        return command.substring(index).trim();
    }

    protected int getContentId() {
        int index = this.content.indexOf(' ');
        if (index > 0) {
            return Integer.parseInt(this.content.substring(0, index));
        }
        return Integer.parseInt(this.content);
    }

    protected String getKeyword() {
        return this.keyword;
    }

    protected String getContent() {
        return this.content;
    }

    private static boolean checkKeyword(String keyword) {
        for (Keyword k : Keyword.values()) {
            if (k.name().equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkTaskKeyword(String keyword) {
        for (TaskKeyword tk : TaskKeyword.values()) {
            if (tk.name().equals(keyword)) {
                return true;
            }
        }
        return false;
    }
    protected void hasValidTaskDesc() throws DukeException {
        if (!checkTaskKeyword(this.keyword) || this.content.isBlank()) {
            throw new DukeException(String.format("The description of a %s cannot be empty", keyword));
        }
    }
    protected void hasValidKeyword() throws DukeException {
        if (!checkKeyword(this.keyword) || this.keyword.isBlank()) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
