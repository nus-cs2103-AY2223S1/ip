import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList tasks) {
            File f = new File(filePath);
            if (!f.exists()) {
                try {
                    f.createNewFile();
                    FileWriter fw = new FileWriter(filePath);
                    for (int i = 0; i < tasks.length(); i++) {
                        fw.write(tasks.getTask(i).savedString() + System.lineSeparator());
                    }
                    fw.close();
                } catch (IOException e) {
                    Ui.printCannotOpenFile();
                }
            } else {
                try {
                    FileWriter fw = new FileWriter(filePath);
                    for (int i = 0; i < tasks.length(); i++) {
                        fw.write(tasks.getTask(i).savedString() + System.lineSeparator());
                    }
                    fw.close();
                } catch (IOException e) {
                    Ui.printUnableToAppend();
                }
            }
    }

    public TaskList load() {
        ArrayList<Task> tasks = new ArrayList<>();

            int index = 1;
            File f = new File(filePath);

            if (!f.exists()) {
                return new TaskList(tasks, new Ui());
            }


        Scanner sc = null;

        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFound();
        }

        while (sc.hasNext()) {

            String input = sc.nextLine();

                if (input.startsWith("T")) {
                    String desc = descStr(input);
                    if (String.valueOf(input.charAt(1)) == "Y") {
                        tasks.add(new ToDo(true, desc, index));
                    } else {
                        tasks.add(new ToDo(false, desc, index));
                    }
                    index++;
                    continue;
                }
                if (input.startsWith("D")) {
                    String desc = descStr(input);
                    String time = timeStr(input);
                    LocalDate date = dateStr(input);
                    if (String.valueOf(input.charAt(1)) == "Y") {
                        tasks.add(new Deadline(true, desc, index, date, time));
                    } else {
                        tasks.add(new Deadline(false, desc, index, date, time));
                    }
                    index++;
                    continue;
                }
                if (input.startsWith("E")) {
                    String desc = descStr(input);
                    String time = timeStr(input);
                    LocalDate date = dateStr(input);
                    if (String.valueOf(input.charAt(1)) == "Y") {
                        tasks.add(new Event(true, desc, index, date, time));
                    } else {
                        tasks.add(new Event(false, desc, index, date, time));
                    }
                        index++;
                        continue;
                    }
                }

                sc.close();
                return new TaskList(tasks, new Ui());
    }

    private String descStr(String str) {
        int endIndex = str.indexOf(">");
        int startIndex = str.indexOf("<");
        return str.substring(startIndex + 1, endIndex);
    }

    private String timeStr(String str) {
        int startIndex = str.indexOf("{");
        int endIndex = str.indexOf("}");
        return str.substring(startIndex + 1, endIndex);
    }

    private LocalDate dateStr(String str) {
        int startIndex = str.indexOf("(");
        int endIndex = str.indexOf(")");
        String date = str.substring(startIndex + 1, endIndex);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMM yyyy");
        return LocalDate.parse(date, df);
    }
}
