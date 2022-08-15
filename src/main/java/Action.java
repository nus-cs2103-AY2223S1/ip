import java.util.HashMap;
import java.util.Optional;

public enum Action {
//    Level_1
    GREET, ECHO, EXIT,
//    Level_2
    ADD, LIST,
//    Level_3
    MARK, UNMARK,
//    Level_4
    TODO, EVENT, DEADLINE;

    private static HashMap<Action, Integer> actionParamMap = new HashMap<>();
    static {
        HashMap<Action, Integer> map = actionParamMap;
//        Level_1
        map.put(Action.GREET, 0);
        map.put(Action.EXIT, 0);
        map.put(Action.ECHO, 1);
//        Level_2
        map.put(Action.ADD, 1);
        map.put(Action.LIST, 0);
//        Level_3
        map.put(Action.MARK, 1);
        map.put(Action.UNMARK, 1);
//        Level_4
        map.put(Action.TODO, 1);
        map.put(Action.EVENT, 2);
        map.put(Action.DEADLINE, 2);
    }

    private static HashMap<String, Action> stringActionMap = new HashMap<>();
    static {
        HashMap<String, Action> map = stringActionMap;
//        Level_1
        map.put("greet", Action.GREET);
        map.put("bye", Action.EXIT);
        map.put("echo", Action.ECHO);
//        Level_2
        map.put("add", Action.ADD);
        map.put("list", Action.LIST);
//        Level_3
        map.put("mark", Action.MARK);
        map.put("unmark", Action.UNMARK);
//        Level_4
        map.put("todo", Action.TODO);
        map.put("event", Action.EVENT);
        map.put("deadline", Action.DEADLINE);
    }

    public static Action getAction(String action) {
        return Optional.ofNullable(action).map(x -> Action.stringActionMap.get(x.trim())).orElse(null);
    }
}
