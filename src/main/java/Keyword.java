public enum Keyword {
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String inputKey;

    Keyword(String key){
        this.inputKey = key;
    }

    public static Keyword getKeyword(String inputKey) throws IllegalArgumentException {
        for (Keyword k : Keyword.values()) {
            if (inputKey.equals(k.inputKey)) {
                return k;
            }
        }
        throw new IllegalArgumentException("\t Sorry, I don't understand...");
    }

}
