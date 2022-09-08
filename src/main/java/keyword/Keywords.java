package keyword;

import alanExceptions.AlanException;
import alanExceptions.NoSuchKeywordException;
import alanExceptions.RemovingDefaultKeywordException;
import util.FileFormatter;
import util.FileParser;
import util.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Keywords {
    private Storage storage;
    private final FileParser fileParser;
    private final FileFormatter fileFormatter;
    public static Keywords instance;
    private final ArrayList<String> DEFAULT_KEYWORDS = new ArrayList<>(Arrays.asList("bye", "list", "event", "deadline", "todo",
            "find", "mark", "unmark", "delete", "help", "addkw", "delkw"));
    private final ArrayList<String> CUSTOM_KEYWORDS = new ArrayList<>();
    private final HashMap<String, String> keywords = new HashMap<>();

    public static Keywords getInstance() throws AlanException {
        if (Keywords.instance == null) {
            Keywords.instance = new Keywords();
        }
        return Keywords.instance;
    }

    public Keywords() throws AlanException {
        for (String keyword : DEFAULT_KEYWORDS) {
            this.keywords.put(keyword, keyword);
        }
        this.storage = Storage.getInstance();
        this.fileParser = new FileParser();
        this.fileFormatter = new FileFormatter();

        List<KeywordPair> keywordPairs = fileParser.parseKeywords(storage.readKeywords());
        for (KeywordPair pair : keywordPairs) {
            this.CUSTOM_KEYWORDS.add(pair.getKeyword());
            this.keywords.put(pair.getKeyword(), pair.getCommand());
        }
    }

    public void assign(String newKW, String commandKW) throws AlanException {
        if (!DEFAULT_KEYWORDS.contains(commandKW)) {
            throw new NoSuchKeywordException();
        }
        this.CUSTOM_KEYWORDS.add(newKW);
        storage.appendKeyword(fileFormatter.formatKeyword(new KeywordPair(newKW, commandKW)));
        this.keywords.put(newKW, commandKW);
    }

    public void remove(String kw) throws AlanException {
        if (DEFAULT_KEYWORDS.contains(kw)) {
            throw new RemovingDefaultKeywordException();
        }
        this.CUSTOM_KEYWORDS.remove(kw);
        this.keywords.remove(kw);

        List<KeywordPair> keywordPairs = new ArrayList<>();
        for (String customkw : this.CUSTOM_KEYWORDS) {
            keywordPairs.add(new KeywordPair(customkw, this.keywords.get(customkw)));
        }
        storage.writeKeyword(fileFormatter.formatKeywordList(keywordPairs));
    }

    public String getCommand(String kw) {
        String command = this.keywords.get(kw);
        return command == null ? kw : command;
    }

    public String getDefaultKeywords() {
        return String.join(", ", this.DEFAULT_KEYWORDS);
    }

    public String getCUSTOM_KEYWORDS() {
        return String.join(", ", this.CUSTOM_KEYWORDS);
    }

    public List<KeywordPair> getKeywordPairs() {
        ArrayList<KeywordPair> keywordPairs = new ArrayList<>();
        for (String keyword : CUSTOM_KEYWORDS) {
            keywordPairs.add(new KeywordPair(keyword, this.keywords.get(keyword)));
        }
        return keywordPairs;
    }
}

