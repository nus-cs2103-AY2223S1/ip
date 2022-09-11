package roger.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Testing stub for Storage class.
 */
public class StorageStub extends Storage {

    public StorageStub(Path path) {
        super(path);
    }

    public List<String> load() throws IOException {
        return new ArrayList<String>();
    }

    public void write(List<String> lines) throws IOException {
    }

}
