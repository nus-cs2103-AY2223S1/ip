package stubs;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Stub class representing Storage class.
 */
public class StorageStub {
    private Path path;
    public StorageStub(Path path) {
        this.path = path;
    }

    public List<String> getAllLines() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("[T][ ] Task0");
        lines.add("[T][ ] Task1");
        return lines;
    }
}
