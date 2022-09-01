package duke.main;

import duke.exception.DukeException;

/**
 * Enum values corresponding to the acceptable keywords that Duke can handle.
 */
public enum Keyword {
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find");

    /* corresponding input key to Enum value */
    private final String inputKey;

    /**
     * Constructor for Keyword.
     *
     * @param key Input Key.
     */
    Keyword(String key) {
        this.inputKey = key;
    }

    /**
     * Retrieve Keyword enum value given input key string.
     * Throws DukeException if corresponding value is not found.
     *
     * @param inputKey Input key string.
     * @return Keyword enum value corresponding to input key.
     * @throws DukeException If corresponding keyword value is not found.
     */
    public static Keyword getKeyword(String inputKey) throws DukeException {
        for (Keyword k : Keyword.values()) {
            if (inputKey.equals(k.inputKey)) {
                return k;
            }
        }
        throw new DukeException("Sorry, I don't understand...");
    }
}
