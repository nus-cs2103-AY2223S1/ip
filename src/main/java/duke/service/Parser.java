package duke.service;

import duke.handlers.HandlerFactory;
import duke.handlers.IHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern COMMAND_REGEX = Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: (.*))?$");

    public static IHandler parse(String s) {
        try {
            Matcher m = COMMAND_REGEX.matcher(s);
            m.find();
            String command = m.group(1);
            String value = m.group(2);
            String flag = m.group(3);
            String options = m.group(4);

            HandlerFactory handlerFactory = new HandlerFactory(command);
            return handlerFactory.taskName(value).flag(flag).flagOption(options).build();
        } catch (IllegalStateException ex) {
            // catch when no match found, returns unknown handler
            return (new HandlerFactory("")).build();
        }
    }
}
