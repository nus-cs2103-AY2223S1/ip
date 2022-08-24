package duke;

public class Command {
    
    private Keyword keyword;
    private String[] commandArgs;

    public Command(Keyword keyword, String[] commandArgs) {
        this.keyword = keyword;
        this.commandArgs = commandArgs;
    }

    public Keyword getKeyword() {
        return this.keyword;
    }

    public String[] getCommandArgs() {
        return this.commandArgs;
    }
}
