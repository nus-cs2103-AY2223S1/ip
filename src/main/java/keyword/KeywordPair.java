package keyword;

public class KeywordPair {
    private String kw;
    private String command;

    public KeywordPair(String kw, String command) {
        this.kw = kw;
        this.command = command;
    }

    public String getKeyword() {
        return this.kw;
    }

    public String getCommand() {
        return this.command;
    }
}
