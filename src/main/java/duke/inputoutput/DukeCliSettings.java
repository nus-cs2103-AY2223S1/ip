package duke.inputoutput;

/**
 * Enum to represent the binary flags used for string parsing
 */
public enum DukeCliSettings {
    WRAP_INDENT(3), WRAPPER(1), INDENT(2), NOFORMATTING(0);

    final int value;

    private DukeCliSettings(int value) {
        this.value = value;
    }
}
