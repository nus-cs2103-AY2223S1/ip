package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveFile(String fileStream) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            //TODO Handle non-existence
        }

        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(fileStream, 0, fileStream.length());
    }
}
