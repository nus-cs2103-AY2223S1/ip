package stubs;
import java.util.List;
import java.util.ArrayList;

import java.nio.file.Path;

public class StorageStub {
    Path path;
    public StorageStub(Path path) {
        this.path =  path;
    }

    public List<String> getAllLines() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("[T][ ] Task0");
        lines.add("[T][ ] Task1");
        return lines;
    }
}
