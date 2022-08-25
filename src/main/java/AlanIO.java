import AlanExceptions.AlanException;
import AlanExceptions.FileReadException;
import AlanExceptions.FileWriteException;
import AlanExceptions.SaveFileException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AlanIO {
    private Path dirPath = Paths.get("data");
    private Path fileNamePath = Paths.get("Alan.txt");
    private Path filePath = dirPath.resolve(fileNamePath);
    private final File SAVE_DIR;
    private final File SAVE_FILE;
    private final BufferedWriter writer;
    private final BufferedReader reader;

    public AlanIO() throws AlanException {
        this.SAVE_DIR = new File(dirPath.toString());
        this.SAVE_FILE = new File(filePath.toString());
        try {
            SAVE_DIR.mkdirs();
            SAVE_FILE.createNewFile();
            this.writer = Files.newBufferedWriter(filePath);
            this.reader = Files.newBufferedReader(filePath);
        } catch (IOException | SecurityException e) {
            throw new SaveFileException();
        }
    }

    public AlanIO(Path directory, Path file) throws AlanException {
        this.dirPath = directory;
        this.fileNamePath = file;
        this.filePath = dirPath.resolve(fileNamePath);
        this.SAVE_DIR = new File(directory.toString());
        this.SAVE_FILE = new File(directory.resolve(file).toString());
        try {
            SAVE_DIR.mkdirs();
            SAVE_FILE.createNewFile();
            this.writer = Files.newBufferedWriter(filePath);
            this.reader = Files.newBufferedReader(filePath);
        } catch (IOException | SecurityException e) {
            throw new SaveFileException();
        }
    }

    public void write(String data) throws AlanException {
        try {
            writer.write(data);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new FileWriteException();
        }
    }

    public String read() throws AlanException {
        String result;
        try {
            result = Files.readString(filePath);
        } catch (IOException e) {
            throw new FileReadException();
        }
        return result;
    }
}
