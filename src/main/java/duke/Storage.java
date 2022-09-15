package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.expenses.Expense;

import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * The Storage class helps the Duke program store its list of tasks into a file specified by the program.
 * The Storage class also reads from a .txt file and converts the text into an ArrayList of Tasks.
 *  
 */
public class Storage {
    
    /**
     * ArrayList of Tasks to store Tasks loaded from data.txt.
     */
    ArrayList<Task> tasks;
    
    /**
     * The filepath where the data of the Duke program is to be stored to and loaded from.
     */
    String filepath;

    ArrayList<Expense> expenses;

    /**
     * Constructor of Storage class.
     * @param filepath Takes in the filepath to load and store data.
     */
    public Storage(String filepath) {
        this.tasks = new ArrayList<Task>();
        this.expenses = new ArrayList<Expense>();
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the data.txt file and converts each line into Tasks to be added into an 
     * ArrayList of Tasks. 
     * @return Returns an ArrayList of Tasks objects.
     * @throws Exception
     */
    public void load() {
        new File("./data").mkdirs();
        File dataStore = new File(filepath);
        if (dataStore.exists()) {
            BufferedReader fileRead;
            try {
                fileRead = new BufferedReader(new FileReader(dataStore));
                String st;
                while ((st = fileRead.readLine()) != null) {
                    String[] strSplit = st.split(" \\| ");
                    if (strSplit[0].equals("T")) {
                        Todo newTask = new Todo(strSplit[2]);
                        tasks.add(newTask);
                    }
                    else if (strSplit[0].equals("D")) {
                        Deadline newTask;
                        if (strSplit[3].length() > 10) {
                            String[] dlDateAndTime = strSplit[3].split(" ");
                            newTask = new Deadline(strSplit[2], dlDateAndTime[0], dlDateAndTime[1]);
                        } else {
                            newTask = new Deadline(strSplit[2], strSplit[3]);
                        }
                        tasks.add(newTask);
                    } 
                    else if (strSplit[0].equals("E")) {
                        String[] eventDateAndTime = strSplit[3].split(" ");
                        Event newTask = new Event(strSplit[2], eventDateAndTime[0], eventDateAndTime[1]);
                        tasks.add(newTask);
                    }
                    else if (strSplit[0].equals("expense")) {
                        float cost = Float.parseFloat(strSplit[2].replace("$", ""));
                        Expense expense = new Expense(strSplit[1], cost);
                        expenses.add(expense);
                    }
                    if (strSplit[1].equals("1")) {
                        tasks.get(tasks.size() - 1).setStatus(1);
                    }
                }
                fileRead.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        }
    } 

    public ArrayList<Task> loadTasks() {
        return tasks;
    }

    public ArrayList<Expense> loadExpenses() throws Exception {
        return expenses;
    }

    /**
     * Stores tasks into the data.txt file at the specified filepath.
     * @param tasks Takes in the current Tasks returned from TaskList in the form of an ArrayList of Tasks.
     * @throws Exception
     */
    public void store(ArrayList<Task> tasks, ArrayList<Expense> expenses) {
        try {
            FileWriter myWriter = new FileWriter(filepath);
            int numTasks = tasks.size();
            for (int i = 0; i < numTasks; i++) {
                Task t = tasks.get(i);
                if (t instanceof Deadline || t instanceof Event) {
                    myWriter.write(t.getTaskType() + " | " + t.getBinaryStatus() + " | "
                            + t.getTask() + " | " + t.getDue() + "\n");
                } else {
                    myWriter.write(t.getTaskType() + " | " + t.getBinaryStatus() + " | "
                            + t.getTask() + "\n");
                }
            }
            int numExpenses = expenses.size();
            for (int i = 0; i < numExpenses; i++) {
                Expense expense = expenses.get(i);
                myWriter.write("expense | " + expense.getActivity() + " | $" + expense.getCostString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            
        }
    }
}
