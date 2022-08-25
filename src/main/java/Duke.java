import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static int index = 0;
    private final static ArrayList<Task> listOfTasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm SoCCat\nWhat can I do for you?");
        try {
//            new Duke().createNewDirectory();
            new Duke().start();
        } catch (Exception e) {
            
        }
    }
    

    private static String numberOfTasks() {
        return "Now you have " + index +  (index < 2 ? " task" : " tasks") + " in your list.";
    }

    private static void getList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < index; i++) {
            System.out.println(i + 1 + "." + listOfTasks.get(i));
        }
    }
    
    private void start() throws IOException, DukeException {
        loadFromDisk();
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] words = input.split(" ", 2);
            String keyword = words[0];

            try {
                if (keyword.equals("bye")) {
                    bye();
                    return;
                } else if (keyword.equals("list")) {
                    getList();
                } else if (keyword.equals("todo")) {
                    createToDos(words);
                } else if (keyword.equals("deadline")) {
                    createDeadlines(words);
                } else if (keyword.equals("event")) {
                    createEvents(words);
                } else if (keyword.equals("mark")) {
                    mark(words);
                } else if (keyword.equals("unmark")) {
                    unmark(words);
                } else if (keyword.equals("delete")) {
                    deleteTask(words);
                } else {
                    throw new DukeInvalidException();
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } 
        }
    }
    
    private void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    
    private void mark(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            int taskIndex = Integer.parseInt(currInput[1]) - 1;
            System.out.println(listOfTasks.get(taskIndex).markAsDone());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }
    
    private void unmark(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            int taskIndex = Integer.parseInt(currInput[1]) - 1;
            System.out.println(listOfTasks.get(taskIndex).unmarkAsNotDone());
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }
    
    private void newTaskAdded() throws DukeException{
        index++;
        System.out.println("Got it. I've added this task: \n" + listOfTasks.get(index - 1) + "\n" + numberOfTasks());
        saveToDisk();
    }
    
    private void createToDos(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            listOfTasks.add(new ToDos(currInput[1]));
            newTaskAdded();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }
    private void createDeadlines(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            String[] taskDetails = currInput[1].split(" /by ", 2);
            String task = taskDetails[0];
            String deadline = taskDetails[1];
            listOfTasks.add(new Deadlines(task, deadline));
            newTaskAdded();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }

    private void createEvents(String[] currInput) throws DukeException{
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            String[] taskDetails = currInput[1].split(" /at ", 2);
            String task = taskDetails[0];
            String eventTime = taskDetails[1];
            listOfTasks.add(new Events(task, eventTime));
            newTaskAdded();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }
    
    private void deleteTask(String[] currInput) throws DukeException {
        if (currInput.length < 2) {
            throw new DukeEmptyException(currInput[0]);
        }
        try {
            int taskIndex = Integer.parseInt(currInput[1]) - 1;
            Task deletedTask = listOfTasks.remove(taskIndex);
            index--;
            System.out.println("Noted. I've removed this task: \n" + deletedTask + "\n" + numberOfTasks());
            saveToDisk();
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeIndexOutOfBoundsException(listOfTasks.size());
        }
    }
    
    private void loadFromDisk() throws DukeException, IOException {
        Files.createDirectories(Paths.get("data"));
        File file = new File("data/Duke.txt");
        file.createNewFile();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("[|]", 4);
                String keyword = words[0];
                String isDone = words[1];
                String taskDetails = words[2];
                if (keyword.equals("T")) {
                    loadToDos(isDone, taskDetails);
                } else {
                    String dateTime = words[3];
                    if (keyword.equals("D")) {
                        loadDeadlines(isDone, taskDetails, dateTime);
                    } else if (keyword.equals("E")) {
                        loadEvents(isDone, taskDetails, dateTime);
                    }
                }
                index = listOfTasks.size();
            }
            reader.close();
        } catch (IOException ex){
            throw new DukeException(ex.getMessage());
        }
    }
    
    private void saveToDisk() throws DukeException{
        try {
            File file = new File("data/Duke.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : listOfTasks) {
                if (task instanceof ToDos) {
                    writer.write("T|");
                    writer.write(task.isDone ? "1|" : "0|");
                    writer.write(task.description);
                } else if (task instanceof Deadlines) {
                    writer.write("D|");
                    writer.write(task.isDone ? "1|" : "0|");
                    writer.write(task.description + "|");
                    writer.write(((Deadlines) task).by);
                } else if (task instanceof Events) {
                    writer.write("E|");
                    writer.write(task.isDone ? "1|" : "0|");
                    writer.write(task.description + "|");
                    writer.write(((Events) task).duration);
                } 
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }
    
    private void loadToDos(String isDone, String taskDetails) {
        Task newTask = new ToDos(taskDetails);
        if (isDone.equals("1")) newTask.markAsDone();
        listOfTasks.add(newTask);
    }
    
    private void loadDeadlines(String isDone, String taskDetails, String dateTime) {
        Task newTask = new Deadlines(taskDetails, dateTime);
        if (isDone.equals("1")) newTask.markAsDone();
        listOfTasks.add(newTask);
    }
    
    private void loadEvents(String isDone, String taskDetails, String dateTime) {
        Task newTask = new Events(taskDetails, dateTime);
        if (isDone.equals("1")) newTask.markAsDone();
        listOfTasks.add(newTask);
    }
}
