import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/*
This class encapsulates the idea of a memory/ram of the chatbot
 */
public class Memory {
    //this is the ram of the chatbot
    private ArrayList<Task> ram;
    //this is the physical file saving the items
    private static final File storageFile = new File("C:\\Users\\xudao\\OneDrive" +
            "\\Documents\\NUS FILES\\year 2\\sem 1\\cs2103t\\git\\ip\\storage.txt");

    private BufferedReader reader;
    private BufferedWriter writer;

    public Memory() {
        this.ram = new ArrayList<Task>();
        readData();
    }

    public Task getTask(int index) { return ram.get(index);
    }

    public int getNumOfTask() {
        return ram.size();
    }

    public boolean checkValidIndex(int index) {
        return index > -1 && index < ram.size();
    }

    //reads data in text file and saves it in ram
    private void readData() {
        try {
            this.reader = new BufferedReader(new FileReader(storageFile));
            String currentLine;
            String description;
            boolean status;
            String time;
            while((currentLine = reader.readLine()) != null) {
                String type = currentLine.substring(0, 1);
                status = currentLine.substring(4, 5).equals("T");
                switch(type) {
                    case "T":
                        ram.add(new ToDo(currentLine.substring(8), status));
                        break;
                    case "D":
                        break;
                    case "E":
                        break;
                }
            }
            reader.close();
        }
        catch(IOException e) {
            System.out.print("Invalid Path for storage file");
        }
    }

    //copies data stored in ram into external file
    private void transferData() {
        try {
            for (int i = 0; i < ram.size(); i++) {
                this.writer = new BufferedWriter(new FileWriter(storageFile));
                writer.write(ram.get(i).getDescription());
            }
            writer.close();
        }
        catch(IOException e) {
            System.out.print("Invalid Path for storage file");
        }
    }

    public void saveTask(Task task) {
        ram.add(task);
        try {
            this.writer = new BufferedWriter(new FileWriter(storageFile, true));
            writer.write(task.getDescription());
            writer.close();
        }
        catch(IOException e) {
            System.out.print("Invalid Path for storage file");
        }
    }

    public void markTask(int index) throws DukeException {
        if (checkValidIndex(index)) {
            Task current = ram.get(index);
            if (current.getStatus()) {
                throw new DukeException("☹ OOPS!!! The task you want to mark is already marked.");
            }
            current.markasDone();
            transferData();
            String content = "Nice! I've marked this task as done:\n" + current.toString();
            System.out.println(Duke.wrapper(content));
        } else {
            throw new DukeException("☹ OOPS!!! The task you want to mark is not here.");
        }
    }

    public void unMarkTask(int index) throws DukeException {
        if (index > -1 && index < ram.size()) {
            Task current = ram.get(index);
            if (!current.getStatus()) {
                throw new DukeException("☹ OOPS!!! The task you want to mark is already marked.");
            }
            current.markasNotDone();
            transferData();
            String content = "OK, I've marked this task as not done yet:\n" + current.toString();
            System.out.println(Duke.wrapper(content));
        } else {
            throw new DukeException("☹ OOPS!!! The task you want to mark is not here.");
        }
    }

    public void deleteTask(int index) throws DukeException {
        if (checkValidIndex(index)) {
            Task deletedTask = ram.get(index);
            ram.remove(index);
            transferData();
            String content = "Noted. I've removed this task:\n" + deletedTask.toString()
                    + numOfTaskToString();
            System.out.println(Duke.wrapper(content));
        } else {
            throw new DukeException("☹ OOPS!!! The The task you want to delete is not here.");
        }

    }

    public String toString() {
        String output = "";
        for (int i = 0; i < ram.size(); i ++) {
            Task current = ram.get(i);
            output = output + String.valueOf(i + 1) + "." + current.toString();
            if (i != ram.size() - 1) {
                output = output + "\n";
            }
        }
        return output;
    }

    public String numOfTaskToString() {
        return "\n" + "Now you have " + String.valueOf(ram.size()) + " tasks in the list.";
    }
}
