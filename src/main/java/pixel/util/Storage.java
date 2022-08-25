package pixel.util;

// Storage: deals with loading tasks from the file and saving tasks in the file
import pixel.task.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

   /* During the write operation, the characters are written to the internal buffer instead of the disk.
   Once the buffer is filled or the writer is closed, the whole characters in the buffer are
   written to the disk.

   Hence, the number of communication to the disk is reduced.
   */

    public void deleteFileContent() throws IOException {
        new FileWriter(this.filePath, false).close();
    }

    public void appendToFile(Task task) throws IOException {
        String textToAdd = task.formatTaskBeforeSave();
        Writer bufferedFileWriter = new BufferedWriter(new FileWriter(this.filePath, true)); // FileWriter(String fileName, boolean append)
        bufferedFileWriter.append(textToAdd).append("\n");
        bufferedFileWriter.close();
    }

}
