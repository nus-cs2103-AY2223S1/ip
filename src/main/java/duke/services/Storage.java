package duke.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/** Handles saving and loading data */
public class Storage {

    /** Saved data on the stored tasks */
    private static File dataSaved;

    /** Is data going to be wiped on exit? */
    private static boolean willWipeData = false;

    /**
     * Loads saved data on stored tasks from Duke_Tasks.txt if it exists, otherwise creates it
     *
     * @throws IOException If an I/O error occurs or the parent directory doesn't exist
     */
    public static void loadData() throws IOException {
        Path path = Paths.get("Duke_Tasks");
        boolean directoryExists = Files.exists(path);
        if (directoryExists) {
            dataSaved = path.toFile();
            BufferedReader br = new BufferedReader(new FileReader(dataSaved));
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
                TaskList.getTasks().add(task);
                line = br.readLine();
            }
        } else {
            dataSaved = Files.createFile(path).toFile();
        }
    }

    /**
     * Tells storage whether to wipe or save data on exit and displays outcome
     *
     * @param willWipe True/false will make data be wiped/saved on exit resp.
     */
    public static void wipeDataOnExit(boolean willWipe) {
        willWipeData = willWipe;
        UI.sayLines(new String[] {
            "Data will be " + (willWipe ? "wiped" : "saved") + " on exit"}
        );
    }

    /**
     * Writes saved data on stored tasks to Duke_Tasks.txt
     *
     * @throws IOException If an I/O error occurs or the parent directory doesn't exist
     */
    public static void saveData() throws IOException {
        new FileWriter(dataSaved).close();
        if (!willWipeData) {
            BufferedWriter bf = new BufferedWriter(new FileWriter(dataSaved));
            StringBuilder lineBuilder = new StringBuilder();
            for (Task task : TaskList.getTasks()) {
                //[typeSymbol][1 or 0] [desc] [flag] [timing]
                lineBuilder.append(task.getTypeSymbol())
                        .append(task.getStatusIcon().equals("X") ? '1' : '0')
                        .append(" ")
                        .append(task.getDescription());
                if (task instanceof Deadline) {
                    lineBuilder.append(" /by ").append(((Deadline) task).getEnteredDeadline());
                } else if (task instanceof Event) {
                    lineBuilder.append(" /at ").append(((Event) task).getEnteredTime());
                }
                bf.write(lineBuilder.toString());
                bf.newLine();
                lineBuilder = new StringBuilder();
            }
            bf.close();
        }
    }
}
