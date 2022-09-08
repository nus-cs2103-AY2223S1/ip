package alanExceptions;

import util.Keywords;

public class NoSuchKeywordException extends AlanException{
    public NoSuchKeywordException() {
        super("No such command keyword exists, choose from the following:\n" +
                Keywords.getInstance().getDefaultKeywords());
    }
}
