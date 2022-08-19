import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private static final String STORAGE_FILEPATH = "./data/duke.txt";
    private static final String SEPARATOR = ":::";

    private File saveFile;

    public Storage() {
        this.saveFile = new File(STORAGE_FILEPATH);
    }

    private void createSaveFile(File saveFile) throws DukeException {
        if (!saveFile.exists()) {
            try {
                saveFile.getParentFile().mkdir();
                saveFile.createNewFile();
            } catch (java.io.IOException exception) {
                throw new DukeException("I/O error occured.");
            } catch (SecurityException exception) {
                throw new DukeException("No write access");
            }
        }
    }

    private Scanner getInputScanner(File saveFile) throws DukeException {
        createSaveFile(saveFile);
        try {
            Scanner scanner = new Scanner(saveFile);
            return scanner;
        } catch (java.io.FileNotFoundException exception) {
            throw new DukeException("Save file not found, even after Duke tries to create one :(");
        }
    }

    private PrintWriter getOutputWriter(File saveFile) throws DukeException {
        createSaveFile(saveFile);
        try {
            PrintWriter writer = new PrintWriter(saveFile);
            return writer;
        } catch (java.io.FileNotFoundException exception) {
            throw new DukeException("Save file not found, even after Duke tries to create one :(");
        }
    }

    public ArrayList<Task> readFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner scanner = getInputScanner(this.saveFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] args = line.split(SEPARATOR);
            System.out.println(line);
            if (args.length != 2) {
                continue;
            }
            Instruction instruction = new Instruction(args[1]);
            InstructionType instructionType = instruction.getInstructionType();
            String[] instructionArgs = instruction.getInstructionArgs();
            boolean isDone = args[0].equals("X");
            switch (instructionType) { // will ignore all lines with invalid format
                case ADD_TODO:
                    tasks.add(new ToDo(isDone, instructionArgs));
                    break;

                case ADD_DEADLINE:
                    tasks.add(new Deadline(isDone, instructionArgs));
                    break;

                case ADD_EVENT:
                    tasks.add(new Event(isDone, instructionArgs));
                    break;
                default:
                    // do nothing
            }
        }
        return tasks;
    }

    public void saveFile(ArrayList<Task> tasks) throws DukeException {
        PrintWriter writer = getOutputWriter(this.saveFile);
        for (int i = 0; i < tasks.size(); i += 1) {
            Task task = tasks.get(i);
            writer.println(task.getStatusIcon() + SEPARATOR + Arrays.stream(task.getCommand()).reduce("", (x, y) -> x + " " + y));
        }
        writer.close();
    }
}
