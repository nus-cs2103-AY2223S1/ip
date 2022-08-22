package Duke;

import Tasks.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        File file = new File(this.filePath);
        BufferedReader reader = null;
        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
        try {
            reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                String[] components = currentLine.split(",");
                Task task = null;
                switch (components[0]) {
                case "T":
                    task = new ToDo(components[2]);
                    break;
                case "D":
                    LocalDate deadlineDate = LocalDate.parse(components[3], format);
                    task = new Deadline(components[2], deadlineDate.format(formatter));
                    break;
                case "E":
                    LocalDate eventDate = LocalDate.parse(components[3], format);
                    task = new Event(components[2], eventDate.format(formatter));
                    break;
                }

                task.setIsDone(components[1].equals("true"));
                tasks.add(task);
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return tasks;
    }

    private String convertToString(Task task) {
        String taskType = task.getTaskType();
        boolean isDone = task.getisDone();
        String description = task.getDescription();
        String sep = System.getProperty("line.separator");

        if (taskType.equals("T")) {
            return String.format("T,%s,%s,%s", isDone, description, sep);
        } else {
            LocalDate date = task.getDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            String formattedDate = date.format(formatter);
            return String.format("%s,%s,%s,%s,%s", taskType, isDone, description, formattedDate, sep);
        }
    }

    public void save(TaskList tasks) {
        File file = new File(this.filePath);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            // StringBuilder stringBuilder = new StringBuilder();
            int len = tasks.getSize();
            for (int i = 0; i < len; i++) {
                // stringBuilder.append(this.convertToString(tasks.getTask(i)));
                fileWriter.write(this.convertToString(tasks.getTask(i)));
            }

            // fileWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
