package skyler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {

    private String filePathChosen;

    public Storage(String filePathChosen) {
        this.filePathChosen = filePathChosen;
    }

    public static LocalDateTime processDateTime(String strDateTime) {
        String[] timeInfo = strDateTime.split(" ", 2);

        String unformattedTime = timeInfo[1];
        String hour = unformattedTime.substring(0, 2);
        String minute = unformattedTime.substring(2, 4);
        String formattedTime = String.format("%s:%s", hour, minute);

        LocalDate date = LocalDate.parse(timeInfo[0]);
        LocalTime time = LocalTime.parse(formattedTime);
        return LocalDateTime.of(date, time);
    }

    // load from file and return arraylist of tasks
    // throws exception if folder / file does not exist (will create if so)

    //method below adapted from
    //https://www.geeksforgeeks.org/java-program-to-search-for-a-file-in-a-directory/ ,
    //https://www.educba.com/java-directories/ and
    //https://www.tutorialspoint.com/determine-if-file-or-directory-exists-in-java
    public ArrayList<Task> load() throws SkylerException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        String[] dir = filePathChosen.split("/");
        String file = dir[dir.length - 1];
        String currPath = "";
        for (int i = 0; i < dir.length - 1; i++) {
            currPath += dir[i];
            String name = new File(currPath).getAbsolutePath();
            Path path = Paths.get(name);
            // if folder does not exist yet
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            currPath += "/";
        }

        String filePath = new File(currPath).getAbsolutePath();
        File directory = new File(filePath);

        String[] flist = directory.list();
        int flag = 0;
        // directory is empty
        if (flist == null) {
            filePath = new File(currPath + file).getAbsolutePath();
            directory = new File(filePath);
            directory.createNewFile();
            throw new SkylerException();
        }

        for (int i = 0; i < flist.length; i++) {
            String filename = flist[i];
            if (filename.equals(file)) {
                filePath = new File(currPath + file).getAbsolutePath();
                directory = new File(filePath);
                flag = 1;
            }
        }

        // directory is not empty but specified file not found
        if (flag == 0) {
            filePath = new File(currPath + file).getAbsolutePath();
            directory = new File(filePath);
            directory.createNewFile();
            throw new SkylerException();
        }

        Scanner sc = new Scanner(directory);
        while (sc.hasNextLine()) {
            String taskStr = sc.nextLine();
            String taskDescription = taskStr.substring(8);

            char type = taskStr.charAt(0);
            int taskStatus = Character.getNumericValue(taskStr.charAt(4));

            switch(type) {
            case 'T':
                Todo todo = new Todo(taskDescription);
                if (taskStatus == 1) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case 'D':
                Pattern p = Pattern.compile(" [|] ");
                String[] descriptors = p.split(taskDescription);

                LocalDateTime dt = processDateTime(descriptors[1]);
                Deadline deadline = new Deadline(descriptors[0], dt);
                if (taskStatus == 1) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case 'E':
                Pattern p1 = Pattern.compile(" [|] ");
                String[] descriptors1 = p1.split(taskDescription);

                LocalDateTime dt1 = processDateTime(descriptors1[1]);
                Event event = new Event(descriptors1[0], dt1);
                if (taskStatus == 1) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
            }
        }

        return tasks;
    }

    public void saveTask(ArrayList<Task> task) throws java.io.IOException {
        String filepath = new File(filePathChosen).getAbsolutePath();
        FileWriter fw = new FileWriter(filepath);

        for (Task currTask : task) {
            String taskStr = currTask.toStringUnformatted();
            String taskDescription = taskStr.substring(7);
            int strLen = taskDescription.length();

            char taskType = taskStr.charAt(1);
            int taskStatus = taskStr.charAt(4) == 'X' ? 1 : 0;

            StringBuilder output = new StringBuilder();
            String str = String.format("%c | %d | ", taskType, taskStatus);
            output.append(str);

            switch (taskType) {
            case 'T':
                output.append(taskDescription).append(System.lineSeparator());
                break;
            case 'D':
                Pattern p = Pattern.compile(" [(]by: ");
                String[] descriptors = p.split(taskDescription.substring(0, strLen - 1));
                String str1 = String.format("%s | %s", descriptors[0], descriptors[1]);
                output.append(str1).append(System.lineSeparator());
                break;
            case 'E':
                Pattern p1 = Pattern.compile(" [(]at: ");
                String[] descriptors1 = p1.split(taskDescription.substring(0, strLen - 1));
                String str2 = String.format("%s | %s", descriptors1[0], descriptors1[1]);
                output.append(str2).append(System.lineSeparator());
                break;
            default:
            }
            fw.write(output.toString());
            fw.flush();
        }

        fw.close();
    }

    public ArrayList<Integer> findTask(String keyword) {
        // return arraylist of task indices
        ArrayList<Integer> taskIndices = new ArrayList<>();
        File file = new File(filePathChosen);
        try {
            Scanner sc = new Scanner(file);
            int task = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains(keyword)) {
                    taskIndices.add(task);
                }
                task++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return taskIndices;
    }

}
