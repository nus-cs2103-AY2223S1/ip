package duke;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.nio.file.*;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (Task t : tasks.getTasks()) {
                pw.println(t.toString());
            }
            pw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Task> load() throws DukeException {
        boolean pathExists = Files.exists(Path.of(this.filePath));
        if (pathExists) {
            ArrayList<Task> tasks = new ArrayList<>();
            try {
                FileReader fr = new FileReader(this.filePath);
                BufferedReader br = new BufferedReader(fr);

                String str;
                while ((str = br.readLine()) != null) {
                    if (String.valueOf(str.charAt(0)).equals("T")) {
                        int firstSeparator = str.indexOf("|");
                        int secondSeparator = str.indexOf("|", firstSeparator + 1);
                        Todo t = new Todo(str.substring(secondSeparator + 2));

                        if (String.valueOf(str.charAt(firstSeparator + 2)).equals("X")) {
                            t.markAsDone();
                        }
                        tasks.add(t);
                    } else if (String.valueOf(str.charAt(0)).equals("E")) {
                        int firstSeparator = str.indexOf("|");
                        int secondSeparator = str.indexOf("|", firstSeparator + 1);
                        int thirdSeparator = str.indexOf("|", secondSeparator + 1);
                        int fourthSeparator = str.indexOf("|", thirdSeparator + 1);

                        String date = str.substring(thirdSeparator + 2, fourthSeparator - 1);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                        LocalDate localDate = LocalDate.parse(date, formatter);

                        String time = str.substring(fourthSeparator + 2);
                        LocalTime localTime = LocalTime.parse(time);

                        Event e = new Event(
                                str.substring(secondSeparator + 2, thirdSeparator - 1),
                                localDate,
                                localTime);

                        if (String.valueOf(str.charAt(firstSeparator + 2)).equals("X")) {
                            e.markAsDone();
                        }
                        tasks.add(e);
                    }  else if (String.valueOf(str.charAt(0)).equals("D")) {
                        int firstSeparator = str.indexOf("|");
                        int secondSeparator = str.indexOf("|", firstSeparator + 1);
                        int thirdSeparator = str.indexOf("|", secondSeparator + 1);
                        int fourthSeparator = str.indexOf("|", thirdSeparator + 1);

                        String date = str.substring(thirdSeparator + 2, fourthSeparator - 1);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                        LocalDate localDate = LocalDate.parse(date, formatter);

                        String time = str.substring(fourthSeparator + 2);
                        LocalTime localTime = LocalTime.parse(time);

                        Deadline d = new Deadline(
                                str.substring(secondSeparator + 2, thirdSeparator - 1),
                                localDate,
                                localTime);

                        if (String.valueOf(str.charAt(firstSeparator + 2)).equals("X")) {
                            d.markAsDone();
                        }
                        tasks.add(d);
                    }
                }
                br.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            return tasks;
        } else {
            try {
                File newFile = new File(this.filePath);
                newFile.getParentFile().mkdirs();
                FileWriter writer = new FileWriter(newFile);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            throw new DukeException("File path does not contain a file");
        }
    }
}
