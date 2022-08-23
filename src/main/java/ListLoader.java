import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Reads and writes to a skeleton of the current task list while being updated as the list changes.
 *
 * @author WR3nd3
 */
public class ListLoader {
    public enum Command {
        T, D, E
    }
    private File listText = new File("data/duke.txt");
    private TaskList taskList;

    /**
     * Constructor for ListLoader object which ensures the necessary files are in place.
     * Updates TaskList with saved data in file.
     *
     * @param taskList The TaskList to be updated with the saved data.
     */
    public ListLoader(TaskList taskList) {
        try {
            if (!new File("data").exists()) {
                new File("data").mkdir();
            }
            if (!listText.exists()) {
                listText.createNewFile();
            }
            this.taskList = taskList;
            BufferedReader reader = new BufferedReader(new FileReader(listText));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                //String trimmedLine = currentLine.trim();
                //if(trimmedLine.equals(lineToRemove)) continue;
                //writer.write(currentLine + System.getProperty("line.separator"));
                String[] inputArray = currentLine.split(" \\| ", 4);
                String command = inputArray[0];
                //System.out.println("command: " + command);
                Boolean isCompleted = inputArray[1].equals("1") ? true : false;
                //System.out.println("isCompleted: " + isCompleted);
                String description = inputArray[2];
                //System.out.println("description: " + inputArray[2]);

                switch (Command.valueOf(command)) {
                case T:
                    taskList.addTodo(description, isCompleted);
                    break;
                case D:
                    taskList.addDeadline(description, inputArray[3], isCompleted);
                    break;
                case E:
                    taskList.addEvent(description, inputArray[3], isCompleted);
                    break;
                }

            }
            reader.close();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("The saved tasklist is corrupted. Please inspect taskList.");
            //listText.delete();
//            try {
//                System.out.println(listText.delete());
//            } catch (Exception ex){
//                System.out.println(ex.getMessage());
//            }
            //new ListLoader(taskList);
        }
    }

}
