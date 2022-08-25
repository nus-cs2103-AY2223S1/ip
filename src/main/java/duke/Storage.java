package duke;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    protected String filePath;
    protected ArrayList<Task> storeLists = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() {
        try {
            File myObj = new File(filePath);
            if (myObj.createNewFile()) {
                System.out.println("No tasks at the moment");
            } else {
                try {
                    printFileContents(filePath);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return storeLists;
    }

    public void printFileContents(String filePath) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                renderStringsAsTasks(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void renderStringsAsTasks(String taskStr) {
        String taskType = String.valueOf(taskStr.charAt(1));
        String taskDescription = taskStr.split("] ", 2)[1];
        if (taskType.equals("T")) {
            Todo t = new Todo(taskDescription);
            storeLists.add(t);
        } else if (taskType.equals("D")) {
            String duration = taskDescription.split("y: ", 2)[1];
            taskDescription = taskDescription.split("\\(", 2)[0];
            Deadline d = new Deadline(taskDescription, duration);
            storeLists.add(d);
        } else {
            String duration = taskDescription.split("t: ", 2)[1];
            taskDescription = taskDescription.split("\\(", 2)[0];
            Event e = new Event(taskDescription, duration);
            storeLists.add(e);
        }
    }

    public void appendToFile(String textToAppend) {
        try {
            File newFile = new File(this.filePath);
            FileWriter fw = new FileWriter(this.filePath, true); // create a FileWriter in append mode
            if(newFile.length() == 0) {
                fw.write(textToAppend);
            } else {
                fw.write("\n" + textToAppend);
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void editTextInFile(String newText, String oldText) {
        try {
            Path path = Paths.get(this.filePath);
            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(oldText)) {
                    fileContent.set(i, newText);
                    break;
                }
            }

            Files.write(path, fileContent, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void removeLineInText(String textToRemove) {
        try {
            File inFile = new File(this.filePath);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals(textToRemove)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
