public enum KeyPhrases {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    FIND_DATE("find_date"),
    OTHERS("");

    private String commandPhrase;

    KeyPhrases(String commandPhrase) {
        this.commandPhrase = commandPhrase;
    }

    public static KeyPhrases get(String commandPhrase) {
        for (KeyPhrases phrase: KeyPhrases.values()) {
            if (phrase.commandPhrase.equals(commandPhrase)) {
                return phrase;
            }
        }
        return OTHERS;
    }
}
