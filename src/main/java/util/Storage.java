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

/**
 * This class is used to read and write files.
 */
public class Storage {
    public static Storage instance;
    private Path dirPath = Paths.get("data");
    private Path fileNamePath = Paths.get("alan.txt");
    private Path filePath = dirPath.resolve(fileNamePath);
    private Path keywordFileNamePath = Paths.get("keywords.txt");
    private Path keywordFilePath = dirPath.resolve(keywordFileNamePath);
    private final File SAVE_DIR;
    private final File SAVE_FILE;
    private final File SAVE_KEYWORDS_FILE;
    private BufferedWriter writer;

    public static Storage getInstance() throws AlanException {
        if (Storage.instance == null) {
            return new Storage();
        }
        return Storage.instance;
    }

    /**
     * Constructor
     *
     * @throws AlanException Exception in case of failure.
     */
    private Storage() throws AlanException {
        this.SAVE_DIR = new File(dirPath.toString());
        this.SAVE_FILE = new File(filePath.toString());
        this.SAVE_KEYWORDS_FILE = new File(keywordFilePath.toString());
        try {
            SAVE_DIR.mkdirs();
            SAVE_FILE.createNewFile();
            SAVE_KEYWORDS_FILE.createNewFile();
        } catch (IOException | SecurityException e) {
            throw new SaveFileException();
        }
    }

    /**
     * Rewrites an existing file.
     *
     * @param data Data to be written.
     * @throws AlanException Exception in case of failure.
     */
    public void write(String data) throws AlanException {
        try {
            writer = new BufferedWriter(new FileWriter(filePath.toString()));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new FileWriteException();
        }
    }

    public void writeKeyword(String data) throws AlanException {
        try {
            writer = new BufferedWriter(new FileWriter(keywordFilePath.toString()));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new FileWriteException();
        }
    }

    /**
     * Appends to an existing file.
     *
     * @param data Data to be appended.
     * @throws AlanException Exception in case of failure.
     */
    public void append(String data) throws AlanException {
        try {
            writer = new BufferedWriter(new FileWriter(filePath.toString(), true));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new FileWriteException();
        }
    }
    public void appendKeyword(String data) throws AlanException {
        try {
            writer = new BufferedWriter(new FileWriter(keywordFilePath.toString(), true));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new FileWriteException();
        }
    }

    /**
     * Reads a file
     *
     * @return The data read from the file.
     * @throws AlanException Exception in case of failure.
     */
    public String read() throws AlanException {
        String result;
        try {
            result = Files.readString(filePath);
        } catch (IOException e) {
            throw new FileReadException();
        }
        return result;
    }

    public String readKeywords() throws AlanException {
        String result;
        try {
            result = Files.readString(keywordFilePath);
        } catch (IOException e) {
            throw new FileReadException();
        }
        return result;
    }
}
