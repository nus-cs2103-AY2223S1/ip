package util;

import alanExceptions.AlanException;
import alanExceptions.FileReadException;
import alanExceptions.FileWriteException;
import alanExceptions.SaveFileException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private Path dirPath = Paths.get("data");
    private Path fileNamePath = Paths.get("alan.txt");
    private Path filePath = dirPath.resolve(fileNamePath);
    private final File SAVE_DIR;
    private final File SAVE_FILE;
    private BufferedWriter writer;

    public Storage() throws AlanException {
        this.SAVE_DIR = new File(dirPath.toString());
        this.SAVE_FILE = new File(filePath.toString());
        try {
            SAVE_DIR.mkdirs();
            SAVE_FILE.createNewFile();
        } catch (IOException | SecurityException e) {
            throw new SaveFileException();
        }
    }

    public Storage(Path directory, Path file) throws AlanException {
        this.dirPath = directory;
        this.fileNamePath = file;
        this.filePath = dirPath.resolve(fileNamePath);
        this.SAVE_DIR = new File(directory.toString());
        this.SAVE_FILE = new File(directory.resolve(file).toString());
        try {
            SAVE_DIR.mkdirs();
            SAVE_FILE.createNewFile();
        } catch (IOException | SecurityException e) {
            throw new SaveFileException();
        }
    }

    public void write(String data) throws AlanException {
        try {
            writer = new BufferedWriter(new FileWriter(filePath.toString()));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new FileWriteException();
        }
    }

    public void append(String data) throws AlanException {
        try {
            writer = new BufferedWriter(new FileWriter(filePath.toString(), true));
            writer.write(data);
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
