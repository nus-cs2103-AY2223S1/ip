import java.util.Arrays;
import java.util.Optional;
public enum KeyPhrases {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");

    private String commandPhrase;

    KeyPhrases(String commandPhrase) {
        this.commandPhrase = commandPhrase;
    }

    public static Optional<KeyPhrases> get(String commandPhrase) {
        return Arrays.stream(KeyPhrases.values())
                .filter(phrase -> phrase.commandPhrase.equals(commandPhrase))
                .findFirst();
    }
}
