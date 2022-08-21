package jude;

import java.io.IOException;

/**
 * Test stub in Storage class.
 */
public class StorageStub extends Storage {

    /**
     * Creates an instance of the StorageStub test class.
     *
     * @param filePath The specified filepath, parameter not actually used.
     */
    public StorageStub(String filePath) throws IOException {
        super(filePath);
    }

    /**
     * This is a mock method which returns a {@code TaskListStub}.<br>
     * The superclass method does the following:<br>
     * {@inheritDoc}
     *
     * @return A dummy {@code TaskListStub}.
     */
    public TaskListStub load() {
        return new TaskListStub();
    }
}
