import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(Ui ui, Storage storage) {

        try {
            Scanner s = new Scanner(Storage.dokeFile);
            while (s.hasNext()) {
                String line = s.nextLine();
                String specialString = " [|] ";
                String[] temp = line.split(specialString);
                Task addTask;

                if (temp[0].equals("T")) {
                    addTask = new ToDo(temp[2]);
                } else if (temp[0].equals("D")) {
                    addTask = new Deadline(temp[2], temp[3]);
                } else {
                    if (!temp[0].equals("E")) {
                        continue;
                    }
                    addTask = new Events(temp[2], temp[3]);
                }
                this.taskList.add(addTask);

                try {
                    if (temp[1].equals("1")) {
                        addTask.markDone();
                    } else {
                        addTask.markNotDone();
                    }
                } catch (DokeException e) {
                }
            }
        } catch (FileNotFoundException e) {
            try {
                storage.dokeFile.createNewFile();
                ui.printOut("a new Doke.txt file has been created" +
                        "it is in the path mentioned above");
            } catch (IOException a) {
                ui.printOut("An error occurred. Try again at another time.");
            }
        }
    }

    public void listOut(Ui ui) {
        String message;
        if (this.taskList.isEmpty()) {
            message = "_________________________ \n" + "You have no task! \n" +
            "_________________________";
             ui.printOut(message);
             return;
        }
        int len = this.taskList.size();
        int i=0;
        message = "_________________________ \n";
        while (i < len) {
            message += (i + 1) + "." + this.taskList.get(i).toString() + "\n";
            i++;
        }
        message += "_________________________ \n";
        ui.printOut(message);
    }

    public void delete(int i) {
        this.taskList.remove(i - 1);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task get(int i) {
        return taskList.get(i-1);
    }

    public ArrayList<Task> getList() {
        ArrayList<Task> temp =  this.taskList;
        return temp;
    }

    public int getSize() {
        return taskList.size();
    }

}
