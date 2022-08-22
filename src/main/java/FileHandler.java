import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Read and write data files
 * it also parses the files and directly output the parsed information
 */
public class FileHandler {
    private String fileName="./stored/default.dat";

    public FileHandler(){

    }

    public FileHandler(String fileName){
        this.fileName=fileName;
    }

    private CalendarEntry parseEntry(String line) throws Exception{
        //System.out.println("Parsing "+line);
        CalendarEntry ans;
        if (line.substring(0, 3).equals("[T]")){
            ans=new CalendarEntryTodo(line.substring(7));
            if (line.substring(3, 6).equals("[X]")){
                ans.markAsCompleted();
            }
            return ans;
        }
        else if (line.substring(0, 3).equals("[D]") && line.indexOf(" (by: ")!=-1){
            ans=new CalendarEntryDeadline(line.substring(7, line.indexOf(" (by: ")), line.substring(line.indexOf(" (by: ")+6, line.length()-1));
            if (line.substring(3, 6).equals("[X]")){
                ans.markAsCompleted();
            }
            return ans;
        }
        else if (line.substring(0, 3).equals("[E]") && line.indexOf(" (at: ")!=-1 && line.substring(line.indexOf(" (at: ")).indexOf(" - ")!=-1){
            String time=line.substring(line.indexOf(" (at: ")+6);
            String startTime=time.split(" - ")[0];
            String endTime=time.split(" - ")[1];
            endTime=endTime.substring(0, endTime.length()-1);
            ans=new CalendarEntryEvent(line.substring(7, line.indexOf(" (at: ")), startTime, endTime);
            if (line.substring(3, 6).equals("[X]")){
                ans.markAsCompleted();
            }
            return ans;
        }

        throw new IOException("File: "+this.fileName+" unreadable, possibly corrupted");
    }

    public int syncFromFile(Calendar c) throws Exception{
        File fd=new File(this.fileName);
        if (!fd.exists()){
            return 200;
        }
        Scanner file=new Scanner(fd);
        c.clearAllEntries();
        String line="";
        while(file.hasNextLine()){
            line=file.nextLine();
            c.addEntry(this.parseEntry(line));
        }
        return 200;
    }

    public int syncToFile(Calendar c) throws Exception{
        File fd = new File(this.fileName);
        fd.delete();
        fd.getParentFile().mkdir();
        fd.createNewFile();
        FileWriter file=new FileWriter(this.fileName);
        BufferedWriter writer=new BufferedWriter(file);
        for (int i=1; i<=c.size(); i++){
            writer.write(c.getEntry(i).toString()+"\n");
        }
        writer.close();
        return 200;
    }
}
