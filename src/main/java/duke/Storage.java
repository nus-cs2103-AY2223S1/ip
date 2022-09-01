package duke;

import java.io.*;

public class Storage {
    private File file;

    /**
     *
     * @param file textfile where tasks are written into
     */
    public Storage(File file) {
        this.file = file;
    }

    /**
     *
     * @param tasks tasks from taskList which are written into the textfile
     * @throws IOException
     */
    public void writeToFile(TaskList tasks) throws IOException {
       //file = new File("src/main/java/duke.txt");

        FileWriter fw = new FileWriter(file);

        for (Task task: tasks) {
            fw.write(task.toString() + "\n");
        }
        System.out.println("Auto-saved.");
        fw.close();
    }

    /**
     *
     * @return tasks from textfile are loaded and stored into the TaskList upon restarting the bot
     * @throws IOException
     */
    public TaskList loadFile() throws IOException {
        //file = new File("src/main/java/duke.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/duke.txt"));
        String str;
        TaskList loadedList = new TaskList();

        while((str = reader.readLine()) != null) {

            if(str.charAt(1) == 'T') {
                String task = str.substring(2);
                todo input = new todo(task);
                if (str.charAt(4) == 'X') {
                    input.markAsDone();
                }
                loadedList.add(input);
            }
            else if(str.charAt(1) == 'D') {
                int openBracket = str.indexOf('(');
                int closeBracket = str.indexOf(')');
                String taskName = str.substring(7, openBracket - 1);
                deadline input = new deadline(taskName);

                String result = input.correctDateFormat(str.substring(openBracket + 5, closeBracket));
                input.setDate(new formatDate(result));

                if (str.charAt(4) == 'X') {
                    input.markAsDone();
                }
                loadedList.add(input);
            }
            else if(str.charAt(1) == 'E') {
                int openBracket = str.indexOf('(');
                int closeBracket = str.indexOf(')');
                String taskName = str.substring(7, openBracket - 1);
                event input = new event(taskName);

                String result = input.correctDateFormat(str.substring(openBracket + 5, closeBracket));
                input.setDay(new formatDate(result));

                if (str.charAt(4) == 'X') {
                    input.markAsDone();
                }
                loadedList.add(input);
            }
        }
        return loadedList;
    }
}
