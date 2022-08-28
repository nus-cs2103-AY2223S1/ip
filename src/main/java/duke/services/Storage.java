package duke.services;

import duke.tasks.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Arrays;

/** Handles saving and loading data */
public class Storage {

    /** Saved data on the stored tasks */
    private static File saveData;

    /** Is data going to be wiped on exit? */
    public static boolean willWipeData = false;

    /**
     * Loads saved data on stored tasks from Duke_Tasks.txt if it exists, otherwise creates it
     *
     * @throws IOException If an I/O error occurs or the parent directory doesn't exist
     */
    public static void LoadData() throws IOException {
        Path path = Paths.get("Duke_Tasks");
        boolean directoryExists = Files.exists(path);
        if (directoryExists) {
            saveData = path.toFile();
            BufferedReader br = new BufferedReader(new FileReader(saveData));
            String line = br.readLine();
            String[] words;
            Task task;
            while (line != null) {
                //[typeSymbol][1 or 0] [desc] [flag] [timing]
                words = Arrays.stream(line.split(" ")).toArray(String[]::new);
                if (line.charAt(0) == 'T') {
                    task = new Todo(Parser.getDescription(words, null));
                } else if (line.charAt(0) == 'D') {
                    task = new Deadline(Parser.getDescription(words, "/by"), Parser.getTiming(words, "/by"));
                } else if (line.charAt(0) == 'E') {
                    task = new Event(Parser.getDescription(words, "/at"), Parser.getTiming(words, "/at"));
                } else {
                    throw new IllegalArgumentException("OOPS!!! Found a stored task of unknown type");
                }
                if (line.charAt(1) == '1') {
                    task.markAsDone();
                }
                TaskList.tasks.add(task);
                line = br.readLine();
            }
        } else {
            saveData = Files.createFile(path).toFile();
        }
    }

    /**
     * Writes saved data on stored tasks to Duke_Tasks.txt
     *
     * @throws IOException If an I/O error occurs or the parent directory doesn't exist
     */
    public static void SaveData() throws IOException {
        new FileWriter(saveData).close();
        if (!willWipeData) {
            BufferedWriter bf = new BufferedWriter(new FileWriter(saveData));
            StringBuilder sb = new StringBuilder();
            for (Task task : TaskList.tasks) {
                //[typeSymbol][1 or 0] [desc] [flag] [timing]
                sb.append(task.getTypeSymbol()).append(task.getStatusIcon().equals("X") ? '1' : '0').append(" ")
                        .append(task.getDescription());
                if (task instanceof Deadline) {
                    sb.append(" /by ").append(((Deadline) task).getEnteredDeadline());
                } else if (task instanceof Event) {
                    sb.append(" /at ").append(((Event) task).getEnteredTime());
                }
                bf.write(sb.toString());
                bf.newLine();
                sb = new StringBuilder();
            }
            bf.close();
        }
    }
}
