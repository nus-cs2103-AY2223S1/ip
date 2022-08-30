package general.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

    abstract public boolean fileExists();

    abstract public String getTextContent() throws FileNotFoundException;

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
