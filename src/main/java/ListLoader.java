import java.io.*;

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
        BufferedReader reader = null;
        try {
            if (!new File("data").exists()) {
                new File("data").mkdir();
            }
            if (!listText.exists()) {
                listText.createNewFile();
            }
            this.taskList = taskList;
            reader = new BufferedReader(new FileReader(listText));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.isBlank()) {
                    continue;
                }

                String[] inputArray = currentLine.split(" \\| ", 4);
                String command = inputArray[0];
                Boolean isCompleted = inputArray[1].equals("1") ? true : false;
                String description = inputArray[2];

                switch (Command.valueOf(command)) {
                case T:
                    taskList.addTodo(description, isCompleted, false);
                    break;
                case D:
                    taskList.addDeadline(description, inputArray[3], isCompleted, false);
                    break;
                case E:
                    taskList.addEvent(description, inputArray[3], isCompleted, false);
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("The saved tasklist is corrupted. Please inspect taskList.");
            System.exit(1);
            //listText.delete();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Appends task of given parameters to saved task list.
     *
     * @param summary String representing summarised description of task.
     */
    public void appendToList(String summary) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(listText, true));
            reader = new BufferedReader(new FileReader(listText));
            String firstLine;
            if ((firstLine = reader.readLine()) != null && !firstLine.isBlank()) {
                writer.newLine();
            }
            writer.write(summary);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Marks the task represented by the given summary as complete in the saved list.
     *
     * @param summary String representing summarised description of task.
     */
    public void markTask(String summary) {
        String oldContent = "";
        String newContent = "";
        String[] strArray = summary.trim().split(" \\| ", 3);
        String newString = strArray[0] + " | " + 1 + " | " + strArray[2];
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(listText));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.isBlank()) {
                    continue;
                }
                oldContent += currentLine + "\n";
            }

            newContent = oldContent.replaceFirst("\\Q" + summary + "\\E", newString).trim();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer = new BufferedWriter(new FileWriter(listText, false));
            writer.write(newContent);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Marks the task represented by the given summary as incomplete in the saved list.
     *
     * @param summary String representing summarised description of task.
     */
    public void unmarkTask(String summary) {
        String oldContent = "";
        String newContent = "";
        String[] strArray = summary.trim().split(" \\| ", 3);
        String newString = strArray[0] + " | " + 0 + " | " + strArray[2];
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(listText));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.isBlank()) {
                    continue;
                }
                oldContent += currentLine + "\n";
            }

            newContent = oldContent.replaceFirst("\\Q" + summary + "\\E", newString).trim();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer = new BufferedWriter(new FileWriter(listText, false));
            writer.write(newContent);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Delete the task represented by the given summary in the saved list.
     *
     * @param summary String representing summarised description of task.
     */
    public void deleteTask(String summary) {
        String newContent = "";
        String[] strArray = summary.trim().split(" \\| ", 3);
        String newString = strArray[0] + " | " + 1 + " | " + strArray[2];
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(listText));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(summary) || currentLine.isBlank()) {
                    continue;
                }
                newContent += currentLine + "\n";
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer = new BufferedWriter(new FileWriter(listText, false));
            writer.write(newContent.trim());
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
