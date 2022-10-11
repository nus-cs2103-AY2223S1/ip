package stubs;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Stub class representing Storage class.
 */
public class StorageStub {
    public StorageStub(Path path) {

    }

    public List<String> getAllLines() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("[T][1] buildCage");
        return lines;
    }
}
