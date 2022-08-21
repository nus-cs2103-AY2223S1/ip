import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages all direct interactions between DukeToStorage and file storage.
 */
public class UserInputHistory {
    private Path path;

    public UserInputHistory() {
        createIfDoesntExist();
    }

    /**
     * Gets location of disk storage file.
     * @throws DukeException when Duke program run outside of Duke folder.
     */
    private void getPath() throws DukeException {
        String pathString = System.getProperty("user.dir");
                if (pathString.contains("ip")) {
                    pathString = pathString.substring(0, pathString.indexOf("ip") + 2);
                    this.path = Paths.get(pathString, "src", "main", "java", "userinputhistory.txt");
                } else {
                    throw new DukeException("Cannot run from outside of ip folder of Duke");
                }
    }

    /**
     * Creates file at path for disk storage.
     */
    public void createIfDoesntExist() {
        try {
            getPath();
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (DukeException e) {
            System.out.println("DukeException: " + e);
        }
    }

    /**
     * Appends line to file and returns true if operation completed successfully.
     * @param s line to be appended.
     * @return true if appended successfully.
     */
    public boolean appendLine(String s)  {
        try {
            Files.write(path, s.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return false;
    }

    /**
     * Removes everything from file at path.
     * Rewrites to file from userInputHistory arraylist in DukeToStorage class.
     * @param index index of line to delete (1-indexed).
     * @return true if line deleted successfully.
     */
    public boolean deleteLine(int index) {
        try {
            String temp = "";
            List<String> history = Files.readAllLines(path);
            Files.write(path, temp.getBytes(StandardCharsets.UTF_8));
            int n = history.size(), i = 0;
            for (i = 0; i < n ; i ++) {
                if (i != index - 1) {
                    temp = history.get(i) + "\n";
                    Files.write(path, temp.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return false;
    }

    /**
     * Changes line at index (index - 1) in storage file, by:
     * 1. deleting line at index - 1,
     * 2. appending newString to end of file.
     * @param index line to be changed.
     * @param newString new string to be added instead.
     * @return true if changed successfully.
     */
    public boolean changeLine(int index, String newString) {
        return deleteLine(index) & appendLine(newString);
    }

    /**
     * Return contents of file history.
     * @return arraylist containing all the lines in the file.
     */
    public List<String> getAllLines() {
        List<String> list = new ArrayList<>();
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return list;
    }
}
