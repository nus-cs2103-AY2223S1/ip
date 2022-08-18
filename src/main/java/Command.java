public class Command {
    private final String keyword;
    private final String content;

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
}
