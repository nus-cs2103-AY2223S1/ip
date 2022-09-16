package duke;

/**
 * Represents a Duke exception when the user input contains a forbidden character.
 */
public class BannedDukeCharacterException extends DukeException {
    BannedDukeCharacterException(String character) {
        super(character);
    }
}
