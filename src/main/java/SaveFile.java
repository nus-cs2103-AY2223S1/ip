import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    private static boolean isMarked(String toProcess) {
        return !toProcess.startsWith("[ ");
    }

    public ArrayList<Task> retrieveInfo() throws FileNotFoundException, DukeException {
        ArrayList<Task> ls = new ArrayList<>();
        System.out.println("Retrieving files...");
        Scanner reader = new Scanner(this.file);
        while (reader.hasNextLine()) {
            String info = reader.nextLine();
            if (info.startsWith("  [T]")) {
                String[] infoData = info.split("] ");
                ToDo toAdd = new ToDo(infoData[infoData.length - 1]);
                if (isMarked(infoData[infoData.length - 2])) {
                    toAdd.mark();
                }
                ls.add(toAdd);
            } else if (info.startsWith("  [D]")) {
                String[] infoData = info.split("] ");
                String[] descBy = infoData[infoData.length - 1].split("by: ");
                Deadline toAdd = new Deadline(descBy[0].substring(0, descBy[0].length() - 2), descBy[1].substring(0, descBy[1].length() - 1));
                if (isMarked(infoData[infoData.length - 2])) {
                    toAdd.mark();
                }
                ls.add(toAdd);
            } else if (info.startsWith("  [E]")) {
                String[] infoData = info.split("] ");
                String[] descAt = infoData[infoData.length - 1].split("at: ");
                Event toAdd = new Event(descAt[0].substring(0, descAt[0].length() - 2), descAt[1].substring(0, descAt[1].length() - 1));
                if (isMarked(infoData[infoData.length - 2])) {
                    toAdd.mark();
                }
                ls.add(toAdd);
            }
        }
        reader.close();
        System.out.println("Retrieval complete!");
        return ls;
    }

}
