import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract public class StoredFile {
    public static StoredFile from(String filePath) {
        final File file = new File(filePath);
        if (file.exists()) {
            return new ExistentFile(filePath);
        }
        return new NonExistentFile(filePath);
    }

    abstract public boolean fileExists();

    abstract public String getTextContent() throws FileNotFoundException;

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
    }

    private static class NonExistentFile extends StoredFile {

        private NonExistentFile(String filePath) {
            super();
        }

        @Override
        public boolean fileExists() {
            return false;
        }

        @Override
        public String getTextContent() throws FileNotFoundException {
            throw new FileNotFoundException("File does not exist!");
        }
    }
}
