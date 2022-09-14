package monkeexceptions;

public class NoSuchKeywordException extends MonkeException {
    public NoSuchKeywordException() throws MonkeException {
        super("No such command keyword exists, choose from the following:\n" +
                    keyword.Keywords.getInstance().getDefaultKeywords());
    }
}
