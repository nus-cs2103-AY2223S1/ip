package models;

import exceptions.DukeException;
import handlers.DukeCommand;
import handlers.DukeCommandMap;

public class Parser {
    public static final DukeCommandMap commandMap = new DukeCommandMap();

    public DukeCommand parseCommand(String userInput) throws DukeException {
        String[] input = userInput.split("\\s+", 2);
        String keyword = input[0].toLowerCase();
        return commandMap.getCommand(keyword);
    }

    public String parseContent(String userInput) throws DukeException {
        String[] input = userInput.split("\\s+", 2);
        return input.length < 2 ? "" : input[1];
    }
}
