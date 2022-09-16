package zupey;

import zupey.exceptions.ZupeyException;
import zupey.handlers.HandlerFactory;
import zupey.handlers.IHandler;
import zupey.service.Parser;
import zupey.service.Service;

/**
 * Main entrypoint for zupey Application
 */
public class Zupey {

    private static final String LOGO =
            "  ______                      \n"
            + " |___  /                      \n"
            + "    / /_   _ _ __   ___ _   _ \n"
            + "   / /| | | | '_ \\ / _ \\ | | |\n"
            + "  / /_| |_| | |_) |  __/ |_| |\n"
            + " /_____\\__,_| .__/ \\___|\\__, |\n"
            + "            | |          __/ |\n"
            + "            |_|         |___/ \n";
    private Service service;

    /**
     * Constructs a new zupey instance.
     * @param s service context in which zupey operates.
     */
    public Zupey(Service s) {
        this.service = s;
        s.message(LOGO + "Hello! I'm Zupey\nWhat can I do for you?");
    }

    /**
     * Main Entrypoint for CLI tool
     * @param args arguments passed by user in CLI
     */
    public static void main(String[] args) {
    }

    /**
     * Handles the command given to zupey.
     * @param str user input.
     * @throws ZupeyException
     */
    public String handleCommand(String str) {
        try {
            String[] tokens = Parser.parse(str);
            HandlerFactory handlerFactory = new HandlerFactory(tokens[0]);
            IHandler handler = handlerFactory.taskName(tokens[1]).flag(tokens[2]).flagOption(tokens[3]).build();
            String message = handler.handle(this.service);
            this.service.updateStorage();
            return message;
        } catch (ZupeyException ex) {
            return " ☹ OOPS!!! " + ex.getMessage();
        }
    }
}
