package scottie.storage;

import java.util.List;

/**
 * API of a component for storing and retrieving data.
 */
public interface Storage {
    /**
     * Returns a list containing the lines of data stored in this Storage.
     *
     * @return A List of lines of data.
     */
    List<String> loadData();

    /**
     * Saves the given lines into this Storage.
     * The data currently stored is overwritten every time.
     *
     * @param lines The lines of data to store.
     */
    void saveData(Iterable<String> lines);
}
