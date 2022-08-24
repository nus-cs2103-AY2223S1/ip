import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    ArrayList<Task> list;

    ToDoList() {
        list = new ArrayList<>();
    }

    /** 
     * Sets status of task at index to be complete
     *
     * @param index
     * @throws IndexOutOfBoundsException
     */
    public void complete(int index) throws IndexOutOfBoundsException {
        if (index >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        list.get(index).mark();
    }

    /** Sets status of task at index to be incomplete
     *
     * @param index
     * @throws IndexOutOfBoundsException
     */
    public void incomplete(int index) throws IndexOutOfBoundsException {
        if (index >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        list.get(index).unmark();
    }

    /**
     *  Add task to todoList
     *
     * @param task
     */
    public void addTask(Task task) {
        list.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
    }

    // Add task to todoList
    public void listTasks() {
        if (list.size() < 1) {
            System.out.println("\tYou don't have any pending tasks.");
            return;
        }

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null){
                System.out.printf("\t%d. %s\n", i + 1, list.get(i));
            }
        }
    }

    // Returns size of current list
    public int getSize() {
        return list.size();
    }

    /**
     * Deletes a Task
     *
     * @param index index to delete
     * @Throws IndexOutOfBoundsException
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        if (index >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Task tempTask = list.get(index);
            list.remove(index);
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t  " + tempTask);
        }
    }

    /*
     * Loads all the saved tasks into list
     * 
     * @param txtFile list of tasks in String 
     */
    public void addTaskFromFile(List<String> txtFile) {
        if (txtFile.isEmpty()) {
            return;
        }

        for (String line : txtFile) {
            String[] details = line.split(" # ");
            String taskType = details[0];
            boolean status = Boolean.parseBoolean(details[1]);
            String taskName = details[2];
            String eventInfo = details.length > 3 ? details[3] : "";

            Task task;

            switch (taskType) {
            case "T": 
                task = new ToDos(taskName, status);
                list.add(task);
                break;
            case "D":
                task = new Deadline(taskName, eventInfo, status);
                list.add(task);
                break;
            case "E":
                task = new Event(taskName, eventInfo, status);
                list.add(task);
                break;
            }
        }
    }

    
    /* Returns todolist formatted for saving in Duke.txt file in hard disk
     * 
     * @return string of tasks in todolist
     */
    public String createFile() {
        String result = "";
        for (Task t : list) {
            if (t instanceof ToDos) {
                ToDos td = (ToDos) t;
                result += String.format("T # %b # %s\n", td.getStatus(), td.getName());
            } 
            else if (t instanceof Deadline){
                Deadline d = (Deadline) t;
                result += String.format("D # %b # %s # %s\n", d.getStatus(), d.getName(), d.getDeadline());
            }
            else if (t instanceof Event) {
                Event e = (Event) t;
                result += String.format("E # %b # %s # %s\n", e.getStatus(), e.getName(), e.getTime());
            }
        }
        return result;
    }
}
