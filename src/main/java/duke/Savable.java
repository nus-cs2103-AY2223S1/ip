package duke;

import duke.exception.ReadAttributeException;

public interface Savable<T> {
    String toFormattedString();

    T parse(String formattedString) throws ReadAttributeException;
}
