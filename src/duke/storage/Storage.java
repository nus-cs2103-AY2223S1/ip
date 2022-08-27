package storage;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;

import task.Event;
import task.ToDo;
import tasklist.TaskList;
import task.Deadline;

public class Storage {

    public static void loadData(TaskList taskList, String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner scanner = new Scanner(f);

        while (scanner.hasNext()) {
            String task = scanner.nextLine();
            if (task.charAt(0) == 'T') {
                Boolean status = task.charAt(4) == '1';
                String content = task.substring(8).trim();
                ToDo toDo = new ToDo(content);
                if (status) {
                    toDo.markComplete();
                }
                taskList.addTask(toDo);

            } else if (task.charAt(0) == 'D') {
                Boolean status = task.charAt(4) == '1';
                String[] split = task.substring(8).split(" \\| ");
                String content = split[0].trim();
                String[] dateTimeSplit = split[1].trim().split(" ");
                LocalDate date = LocalDate.parse(dateTimeSplit[0]);
                LocalTime time = LocalTime.parse(dateTimeSplit[1]);
                Deadline deadline = new Deadline(content, date, time);
                if (status) {
                    deadline.markComplete();
                }
                taskList.addTask(deadline);

            } else if (task.charAt(0) == 'E') {
                Boolean status = task.charAt(4) == '1';
                String[] split = task.substring(8).split(" \\| ");
                String content = split[0].trim();
                String[] dateTimeSplit = split[1].trim().split(" ");
                LocalDate date = LocalDate.parse(dateTimeSplit[0]);
                LocalTime time = LocalTime.parse(dateTimeSplit[1]);
                Event event = new Event(content, date, time);
                if (status) {
                    event.markComplete();
                }
                taskList.addTask(event);
            }
        }
        scanner.close();
    }

    public static void writeData(TaskList taskList, String path) throws IOException {
        FileWriter clearFw = new FileWriter(path, false);
        clearFw.write("");
        clearFw.close();
        FileWriter fw = new FileWriter(path, true);
        ArrayList<String> writeList = taskList.produceWriteList();
        for (int i = 0; i < writeList.size(); i++) {
            fw.write(writeList.get(i));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

}
