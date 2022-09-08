package util;

import alanExceptions.AlanException;
import alanExceptions.NoSuchKeywordException;
import alanExceptions.RemovingDefaultKeywordException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Keywords {
    public static Keywords instance;
    private final ArrayList<String> DEFAULT_KEYWORDS = new ArrayList<String>(Arrays.asList("bye", "list", "event", "deadline", "todo",
            "find", "mark", "unmark", "delete", "help", "assign", "remove"));
    private final HashMap<String, String> keywords = new HashMap<>();

    public static Keywords getInstance() {
        if (Keywords.instance == null) {
            Keywords.instance = new Keywords();
        }
        return Keywords.instance;
    }

    public Keywords() {
        for (String keyword : DEFAULT_KEYWORDS) {
            this.keywords.put(keyword, keyword);
        }
    }

    public void assign(String newKW, String commandKW) throws AlanException {
        if (!DEFAULT_KEYWORDS.contains(commandKW)) {
            throw new NoSuchKeywordException();
        }
        this.keywords.put(newKW, commandKW);
    }

    public void remove(String kw) throws AlanException {
        if (DEFAULT_KEYWORDS.contains(kw)) {
            throw new RemovingDefaultKeywordException();
        }
        this.keywords.remove(kw);
    }

    public String getCommand(String kw) {
        String command = this.keywords.get(kw);
        return command == null ? kw : command;
    }

    public String getDefaultKeywords() {
        return String.join(", ", DEFAULT_KEYWORDS);
    }
}
