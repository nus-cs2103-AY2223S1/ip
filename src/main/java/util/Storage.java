package util;

import monkeexceptions.MonkeException;
import monkeexceptions.FileReadException;
import monkeexceptions.FileWriteException;
import monkeexceptions.SaveFileException;

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
    private Path fileNamePath = Paths.get("monke.txt");
    private Path filePath = dirPath.resolve(fileNamePath);
    private Path keywordFileNamePath = Paths.get("keywords.txt");
    private Path keywordFilePath = dirPath.resolve(keywordFileNamePath);
    private final File SAVE_DIR;
    private final File SAVE_FILE;
    private final File SAVE_KEYWORDS_FILE;
    private BufferedWriter writer;

    public static Storage getInstance() throws MonkeException {
        if (Storage.instance == null) {
            return new Storage();
        }
        return Storage.instance;
    }

    /**
     * Constructor.
     *
     * @throws MonkeException Exception in case of failure.
     */
    private Storage() throws MonkeException {
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
     * @throws MonkeException Exception in case of failure.
     */
    public void write(String data) throws MonkeException {
        try {
            writer = new BufferedWriter(new FileWriter(filePath.toString()));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new FileWriteException();
        }
    }

    /**
     * Rewrites an existing file.
     *
     * @param data Data to be written.
     * @throws MonkeException Exception in case of failure.
     */
    public void writeKeyword(String data) throws MonkeException {
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
     * @throws MonkeException Exception in case of failure.
     */
    public void append(String data) throws MonkeException {
        try {
            writer = new BufferedWriter(new FileWriter(filePath.toString(), true));
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
     * @throws MonkeException Exception in case of failure.
     */
    public void appendKeyword(String data) throws MonkeException {
        try {
            writer = new BufferedWriter(new FileWriter(keywordFilePath.toString(), true));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new FileWriteException();
        }
    }

    /**
     * Reads a file.
     *
     * @return The data read from the file.
     * @throws MonkeException Exception in case of failure.
     */
    public String read() throws MonkeException {
        String result;
        try {
            result = Files.readString(filePath);
        } catch (IOException e) {
            throw new FileReadException();
        }
        return result;
    }

    /**
     * Reads a file.
     *
     * @return The data read from the file.
     * @throws MonkeException Exception in case of failure.
     */
    public String readKeywords() throws MonkeException {
        String result;
        try {
            result = Files.readString(keywordFilePath);
        } catch (IOException e) {
            throw new FileReadException();
        }
        return result;
    }
}
