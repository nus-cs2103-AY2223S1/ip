package Qoobee;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private List<Task> list;

    public Storage(String filepath) {
        this.filePath = filepath;
        this.list = new ArrayList<>();
    }

    public void loadFile() throws QoobeeException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            Scanner myReader = new Scanner(reader);
            while (myReader.hasNextLine()) {
                String curr = myReader.nextLine();
                String[] stringArray = curr.split(" \\| ");
                if (stringArray[0].equals("T")) {
                    Task todo = new ToDo(stringArray[2]);
                    if (stringArray[1].equals("1")) {
                        todo.markAsDone();
                    }
                    this.list.add(todo);
                    save(list);
                } else if (stringArray[0].equals("D")) {
                    String[] dateTime = stringArray[3].split(" ", 4);
                    String[] time = dateTime[3].split(":", 2);
                    Task deadline = new Deadline(stringArray[2],
                            LocalDateTime.of(Integer.parseInt(dateTime[2]), Integer.parseInt(dateTime[1])
                                    , Integer.parseInt(dateTime[0]), Integer.parseInt(time[0]),
                                    Integer.parseInt(time[1])));
                    if (stringArray[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    list.add(deadline);
                    save(list);
                } else if (stringArray[0].equals("E")) {
                    Task event = new Event(stringArray[2], stringArray[3]);
                    if (stringArray[1].equals("1")) {
                        event.markAsDone();
                    }
                    list.add(event);
                    save(list);
                }
            }
        } catch (QoobeeException e) {
            System.out.println("I cant find the file!");
        } catch (FileNotFoundException e) {
            // Create the empty file if there is no existing file.
            File file = new File(filePath);
        }
    }

    public void save(List<Task> taskList) throws QoobeeException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                if (task instanceof ToDo) {
                    writer.write("T | ");
                    if (task.getStatusIcon() == "X") {
                        writer.write("1 | ");
                    } else {
                        writer.write("0 | ");
                    }
                    writer.write(task.getDescription());
                } else if (task instanceof Deadline) {
                    writer.write("D | ");
                    if (task.getStatusIcon() == "X") {
                        writer.write("1 | ");
                    } else {
                        writer.write("0 | ");
                    }
                    writer.write(task.getDescription() + " | " + ((Deadline) task).getDateTime());
                } else if (task instanceof Event) {
                    writer.write("D | ");
                    if (task.getStatusIcon() == "X") {
                        writer.write("1 | ");
                    } else {
                        writer.write("0 | ");
                    }
                    writer.write(task.getDescription() + " | " + ((Event) task).getAt());
                }
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new QoobeeException("Something went wrong!!");
        };
    }

    public List<Task> getList() {
        return this.list;
    }

}
