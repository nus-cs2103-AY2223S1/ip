package keyword;

import monkeExceptions.MonkeException;
import monkeExceptions.NoSuchKeywordException;
import monkeExceptions.RemovingDefaultKeywordException;
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
            "find", "mark", "unmark", "delete", "addkw", "delkw", "help"));
    private final ArrayList<String> DEFAULT_HELP_MSG = new ArrayList<>(Arrays.asList(
            "Say bye to the monkey helper!",
            "List out all your current tasks.",
            "Add an event task.\nFormat: event <task description> /on <time>",
            "Add a deadline task.\nFormat: deadline <task description> /by <time>",
            "Add a todo task.\nFormat: todo <task description>",
            "Search tasks with a keyword.\nFormat: find <keyword>",
            "Mark task as done.\nFormat: mark <task index>",
            "Mark task as undone.\nFormat: unmark <task index>",
            "Delete a task.\nFormat: delete <task index>",
            "Assign a custom keyword to a command.\nFormat: addkw <custom keyword> <command>",
            "Delete a custom keyword.\nFormat: delkw <custom keyword>",
            "Display instructions for commands.\nFormat: help <command>"
    ));
    private final HashMap<String, String> DEFAULT_HELP_MSG_DICT = new HashMap<>();
    private final ArrayList<String> CUSTOM_KEYWORDS = new ArrayList<>();
    private final HashMap<String, String> keywords = new HashMap<>();

    public static Keywords getInstance() throws MonkeException {
        if (Keywords.instance == null) {
            Keywords.instance = new Keywords();
        }
        return Keywords.instance;
    }

    public Keywords() throws MonkeException {
        for (int i = 0; i < DEFAULT_KEYWORDS.size(); i++) {
            this.keywords.put(DEFAULT_KEYWORDS.get(i), DEFAULT_KEYWORDS.get(i));
            this.DEFAULT_HELP_MSG_DICT.put(DEFAULT_KEYWORDS.get(i), DEFAULT_HELP_MSG.get(i));
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

    public void assign(String newKW, String commandKW) throws MonkeException {
        if (!DEFAULT_KEYWORDS.contains(commandKW)) {
            throw new NoSuchKeywordException();
        }
        this.CUSTOM_KEYWORDS.add(newKW);
        storage.appendKeyword(fileFormatter.formatKeyword(new KeywordPair(newKW, commandKW)));
        this.keywords.put(newKW, commandKW);
    }

    public void remove(String kw) throws MonkeException {
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

    public String getHelpMsg(String command) {
        String helpMsg = DEFAULT_HELP_MSG_DICT.get(command);
        return helpMsg == null ? command + " is not a valid command :(" : helpMsg;
    }
}

