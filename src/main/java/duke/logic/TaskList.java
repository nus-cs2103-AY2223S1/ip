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

public class TaskList extends ArrayList<Task> {
    private Storage storage;
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


    public boolean exists(int query) {
        return query < super.size() && query >= 0;
    }

    private void updateStorage() {
        if (this.storage != null) {
            this.storage.update(this);
        }
    }

    @Override
    public Task remove(int index) {
        Task temp = super.remove(index);
        this.updateStorage();
        return temp;
    }

    @Override
    public boolean add(Task task) {
        boolean bool = super.add(task);
        this.updateStorage();
        return bool;
    }

    public Task markTask(int index) {
        this.get(index).mark();
        this.updateStorage();
        return this.get(index);
    }

    public Task unmarkTask(int index) {
        this.get(index).unmark();
        this.updateStorage();
        return this.get(index);
    }

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
