package roger;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Files;


/**
 * Encapsulates storage for a text file.
 */
public class Storage {

    private Path path;

    public Storage(Path path) {
        this.path = path;
    }

    public List<String> load() throws IOException {
        return Files.readAllLines(this.path);
    }

    public void write(List<String> lines) throws IOException {
        try {
            Files.delete(path);
        } catch (IOException ignored) {
            // File does not exist yet.
        }

        File file = new File(String.valueOf(path.toAbsolutePath()));
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        for (String line: lines) {
            fw.append(line+ '\n');
        }

        fw.close();
    }

}
