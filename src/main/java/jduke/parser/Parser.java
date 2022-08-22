package jduke.parser;

import jduke.Jduke;
import jduke.data.exception.JdukeException;

public class Parser {
    public static Jduke.Command parseMainCommand(String input) throws JdukeException {
        Jduke.Command mainCmd;
        try {
            mainCmd = Jduke.Command.valueOf(input.split(" ", 2)[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JdukeException("cannot understand command");
        }
        return mainCmd;
    }

    public static String parseCommandParams(String input) {
        if (input.split(" ", 2).length == 1) {
            return "";
        }
        return input.split(" ", 2)[1];
    }
}
