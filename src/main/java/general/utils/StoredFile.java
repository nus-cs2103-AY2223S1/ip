package general.utils;

import java.io.*;
import java.util.Scanner;

/**
 * A utility class that wraps abstracts the boilerplate of reading and writing to a file.
 *
 * @author Richard Dominick
 */
abstract public class StoredFile {
    public static StoredFile from(String filePath) {
        final File file = new File(filePath);
        if (file.exists()) {
            return new ExistentFile(filePath);
        }
        return new NonExistentFile(filePath);
    }

    private static void createFileIfNotExists(String filePath) {
        try {
            File directory = new File(filePath.replaceFirst("/[^/\\\\]+$", ""));
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether the {@code StoredFile} points to an existing file.
     *
     * @return {@code true} if the {@code StoredFile} points to an existing file, {@code false} otherwise
     */
    abstract public boolean fileExists();

    /**
     * Returns the content of the {@code StoredFile} as a string.
     *
     * @return The content of the {@code StoredFile}, interpreted as a string
     * @throws FileNotFoundException When the {@code StoredFile} points to a non-existent file
     */
    abstract public String getTextContent() throws FileNotFoundException;

    /**
     * Overwrites the contents of the {@code StoredFile} with the text specified by the {@code text} parameter.
     * If the file does not exist, it will be created.
     *
     * @param text The text to be written to the file
     */
    abstract public void writeText(String text);

    private static class ExistentFile extends StoredFile {
        private final String filePath;

        private ExistentFile(String filePath) {
            super();
            this.filePath = filePath;
        }

        @Override
        public boolean fileExists() {
            return true;
        }

        @Override
        public String getTextContent() throws FileNotFoundException {
            Scanner sc = new Scanner(new File(filePath));
            final StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
                sb.append("\n");
            }
            return sb.toString();
        }

        @Override
        public void writeText(String text) {
            StoredFile.createFileIfNotExists(filePath);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class NonExistentFile extends StoredFile {
        private final String filePath;

        private NonExistentFile(String filePath) {
            super();
            this.filePath = filePath;
        }

        @Override
        public boolean fileExists() {
            return false;
        }

        @Override
        public String getTextContent() throws FileNotFoundException {
            throw new FileNotFoundException("File does not exist!");
        }

        @Override
        public void writeText(String text) {
            StoredFile.createFileIfNotExists(filePath);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
