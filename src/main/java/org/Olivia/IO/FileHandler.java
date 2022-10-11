package org.Olivia.IO;

import org.Olivia.Dispatchers.GuiEventDispatcher;
import org.Olivia.calendar.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Read and write data files
 * it also parses the files and directly output the parsed information
 *
 * @author ZHANG TONGJUN (albertZhangTJ)
 */
public class FileHandler {
    private String fileName;

    public FileHandler() {
        this.fileName = "./stored/default.dat";
    }

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    private static String parseTitleFromFileLine(String line) {
        line=line.substring(7);
        if (line.indexOf("#") != -1) {
            line = line.substring(0, line.indexOf("#"));
        }
        if (line.indexOf(" (by: ") != -1) {
            line = line.substring(0, line.indexOf(" (by: "));
        }
        if (line.indexOf(" (at: ") != -1) {
            line = line.substring(0, line.indexOf(" (at: "));
        }
        return line;
    }

    private CalendarEntry parseTodoEntry(String line) throws Exception {
        List<String> tags = GuiEventDispatcher.parseTags(line);
        CalendarEntry ans;
        ans = new CalendarEntryTodo(parseTitleFromFileLine(line), tags);
        if (line.substring(3, 6).equals("[X]")) {
            ans.markAsCompleted();
        }
        return ans;
    }

    private CalendarEntry parseDeadlineEntry(String line) throws Exception {
        List<String> tags = GuiEventDispatcher.parseTags(line);
        CalendarEntry ans;
        ans = new CalendarEntryDeadline(parseTitleFromFileLine(line), line.substring(line.indexOf(" (by: ") + 6, line.length() - 1), tags);
        if (line.substring(3, 6).equals("[X]")) {
            ans.markAsCompleted();
        }
        return ans;
    }

    private CalendarEntry parseEventEntry(String line) throws Exception {
        List<String> tags = GuiEventDispatcher.parseTags(line);
        CalendarEntry ans;
        String time = line.substring(line.indexOf(" (at: ") + 6);
        String startTime = time.split(" - ")[0];
        String endTime = time.split(" - ")[1];
        endTime = endTime.substring(0, endTime.length() - 1);
        ans = new CalendarEntryEvent(parseTitleFromFileLine(line), startTime, endTime, tags);
        if (line.substring(3, 6).equals("[X]")) {
            ans.markAsCompleted();
        }
        return ans;
    }

    private CalendarEntry parseEntry(String line) throws Exception {
        if (line.substring(0, 3).equals("[T]")) {
            return this.parseTodoEntry(line);
        } else if (line.substring(0, 3).equals("[D]") && line.indexOf(" (by: ") != -1) {
            return this.parseDeadlineEntry(line);
        } else if (line.substring(0, 3).equals("[E]") && line.indexOf(" (at: ") != -1 && line.substring(line.indexOf(" (at: ")).indexOf(" - ") != -1) {
            return this.parseEventEntry(line);
        }

        throw new IOException("File: " + this.fileName + " unreadable, possibly corrupted");
    }

    public int syncFromFile(Calendar c) throws Exception {
        File fd = new File(this.fileName);
        if (!fd.exists()) {
            return 200;
        }
        Scanner file = new Scanner(fd);
        c.clearAllEntries();
        String line = "";
        while (file.hasNextLine()) {
            line = file.nextLine();
            c.addEntry(this.parseEntry(line));
        }
        return 200;
    }

    public int syncToFile(Calendar c) throws Exception {
        File fd = new File(this.fileName);
        fd.delete();
        fd.getParentFile().mkdir();
        fd.createNewFile();
        FileWriter file = new FileWriter(this.fileName);
        BufferedWriter writer = new BufferedWriter(file);
        for (int i = 1; i <= c.size(); i++) {
            writer.write(c.getEntry(i).toString() + "\n");
        }
        writer.close();
        return 200;
    }
}
