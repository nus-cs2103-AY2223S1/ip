package duke.logic;

import duke.task.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * TaskList stores user tasks and manages them.
 *
 * @author totsukatomofumi
 */
public class TaskList extends ArrayList<Task> {
    /** Storage object for writing task history to a file. */
    private Storage storage;

    /**
     * HashMap to contain set chars representing task types and function to return a new task of that
     * corresponding type pairs.
     */
    private static final HashMap<Character,
            BiFunction<Integer, Integer, Function<String, Task>>> taskMap = new HashMap<>();

    static {
        TaskList.taskMap.put('T', (index, length) -> line -> new ToDo(line.substring(index)));
        TaskList.taskMap.put('D', (index, length) -> line -> new Deadline(line.substring(index, index + length),
                        LocalDate.parse(line.substring(index + length))));
        TaskList.taskMap.put('E', (index, length) -> line -> new Event(line.substring(index, index + length),
                LocalDate.parse(line.substring(index + length))));
    }

    private TaskList() {
        super();
    }

    /**
     * Constructs a task list.
     *
     * @param storage the storage object to be tied to this task list.
     */
    public TaskList(Storage storage) {
        super();
        this.storage = storage;
        //retrieve or else clear the file
        try {
            this.retrieve();
        } catch (IOException e) {   //thrown
            //make sure file is not deleted, else make again
            this.storage.createRequiredFiles();
            this.storage.clear();
        }
    }

    /**
     * Checks if a task exists at that zero-based index.
     * @param query the zero-based index.
     * @return true if a task exists, else false.
     */
    public boolean exists(int query) {
        return query < super.size() && query >= 0;
    }
    
    private void updateStorage() {
        if (this.storage != null) {
            this.storage.update(this);
        }
    }

    /**
     * Removes the task at the specified position from this task list.
     * @param index the index of the task to be removed.
     * @return the task that was removed from the task list.
     */
    @Override
    public Task remove(int index) {
        Task temp = super.remove(index);
        this.updateStorage();
        return temp;
    }

    /**
     * Appends the specified task to the end of the task list.
     *
     * @param task task to be appended to this task list.
     * @return true.
     */
    @Override
    public boolean add(Task task) {
        boolean bool = super.add(task);
        this.updateStorage();
        return bool;
    }

    /**
     * Marks the specified task in the task list.
     *
     * @param index the index of the task to be marked.
     * @return the task that was marked.
     */
    public Task markTask(int index) {
        this.get(index).mark();
        this.updateStorage();
        return this.get(index);
    }

    /**
     * Unmarks the specified task in the task list.
     *
     * @param index the index of the task to be unmarked.
     * @return the task that was unmarked.
     */
    public Task unmarkTask(int index) {
        this.get(index).unmark();
        this.updateStorage();
        return this.get(index);
    }

    /**
     * Retrieves list of task from the task history file via storage.
     *
     * @throws IOException If the file contains invalid contents that cannot be parsed.
     */
    public void retrieve() throws IOException {
        //initialize scanner with task history file
        Scanner retriever;
        try {
            retriever = new Scanner(this.storage.getHistory());
        } catch (FileNotFoundException e) {
            //file or directory was modified during runtime of this program
            throw new RuntimeException(e);
        }
        //iterate through each line
        while (retriever.hasNextLine()) {
            String line = retriever.nextLine();
            StringBuilder strLength = new StringBuilder();
            //starting at first digit of length of task description
            int index = 2;
            while (index < line.length() && line.charAt(index) != '_') {
                strLength.append(line.charAt(index));
                ++index;
            }
            //throw bad file exception
            if (index == line.length()) {   //no '_' encountered
                retriever.close();
                throw new IOException("Text file containing history has invalid formatting for parsing.");
            }
            //now index is index of first '_' encountered
            int length;
            try {
                length = Integer.parseInt(strLength.toString());
            } catch (NumberFormatException e) { //formatting is all messed up
                retriever.close();
                throw new IOException("Text file containing history has invalid formatting for parsing.");
            }
            index++;    //increment to first index of task description
            //retrieve task according to char
            Task toAdd = TaskList.taskMap.get(line.charAt(0)).apply(index, length).apply(line);
            if (toAdd != null) {
                if (line.charAt(1) == '1') {
                    toAdd.mark();
                }
                super.add(toAdd);
            } else {    //if null means no task category was identified
                retriever.close();
                throw new IOException("Unable to identify task type as type found in file was invalid.");
            }
        }
        retriever.close();
    }

    public TaskList search(String keyword) {
        TaskList result = new TaskList();
        for (Task task : this) {
            if (task.contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String list = "";
        int order = 1;
        for (Task task : this) {
            list += order++ + "." + task.toString() + "\n";
        }
        return list;
    }
}
