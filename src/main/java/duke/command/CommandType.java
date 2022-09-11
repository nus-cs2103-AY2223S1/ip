package duke.command;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    TODO, DEADLINE, FIND, GET, BYE, EVENT, MARK, UNMARK, DELETE, LIST;
    public static final HashMap< String,CommandType> commandMap = new HashMap<>()
    {{
        put("t", TODO);
        put("Todo", TODO);
        put("TODO", TODO);
        put("todo", TODO);
        put("T", TODO);
        put("deadline", DEADLINE);
        put("ddl", DEADLINE);
        put("Deadline", DEADLINE);
        put("DEADLINE", DEADLINE);
        put("d", DEADLINE);
        put("D", DEADLINE);
        put("event", EVENT);
        put("Event", EVENT);
        put("e", EVENT);
        put("E", EVENT);
        put("EVENT", EVENT);
        put("Find", FIND);
        put("FIND", FIND);
        put("F", FIND);
        put("f", FIND);
        put("find", FIND);
        put("Unmark", UNMARK);
        put("u", UNMARK);
        put("UNMARK", UNMARK);
        put("U", UNMARK);
        put("unmark", UNMARK);
        put("mark", MARK);
        put("m", MARK);
        put("MARK", MARK);
        put("M", MARK);
        put("Mark", MARK);
        put("Bye", BYE);
        put("BYE", BYE);
        put("bye", BYE);
        put("List", LIST);
        put("list", LIST);
        put("l", LIST);
        put("L", LIST);
        put("LIST", LIST);
        put("get", GET);
        put("GET", GET);
        put("G", GET);
        put("Get", GET);
        put("g", GET);
        put("delete", DELETE);
        put("DELETE", DELETE);
        put("Delete", DELETE);
    }};
}
