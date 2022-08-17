public enum Keyword {
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    /* corresponding input key to Enum value */
    private final String inputKey;

    /**
     * Constructor for Keyword.
     * @param key Input Key.
     */
    Keyword(String key){
        this.inputKey = key;
    }

    /**
     * Retrieve Keyword enum value given input key string.
     * Throws IllegalArgumentException if corresponding value is not found.
     * @param inputKey Input key string.
     * @return Keyword enum value corresponding to input key.
     * @throws IllegalArgumentException
     */
    public static Keyword getKeyword(String inputKey) throws IllegalArgumentException {
        for (Keyword k : Keyword.values()) {
            if (inputKey.equals(k.inputKey)) {
                return k;
            }
        }
        throw new IllegalArgumentException("\tSorry, I don't understand...");
    }

}
