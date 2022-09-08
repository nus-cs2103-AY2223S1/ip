package alanExceptions;

public class NoSuchKeywordException extends AlanException{
    public NoSuchKeywordException() throws AlanException {
        super("No such command keyword exists, choose from the following:\n" +
                    keyword.Keywords.getInstance().getDefaultKeywords());
    }
}
