package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Storage {
    /**
     *Read the files into the computer
     * @param reader
     * @param storage
     */
    public static void readFile(Scanner reader, List<Task> storage) {
        while(reader.hasNextLine()) {
            String firsttask = reader.next();
            if (firsttask.equals("T")) {
                int status = reader.nextInt();
                String skipSpaceToDo =reader.next();
                String task = reader.nextLine();
                ToDos t = new ToDos(task);
                if (status == 1){
                    t.setStatus();
                }
                storage.add(t);

            } else if (firsttask.equals("D")) {
                int status = reader.nextInt();
                String skipSpaceDeadline =reader.next();
                String addtask = reader.next().trim();
                String bar = "|";
                String tocheck = "";
                int i = 0;
                while (true) {
                    String toappend = reader.next().trim();
                    tocheck = toappend;
                    addtask = addtask + " " + toappend;
                    i++;
                    if(!tocheck.equals(bar)){
                        break;
                    }
                }
                String skipSpaceDeadline2 = reader.next();
                String deadLineDay =  reader.nextLine().trim();
                Deadlines d = new Deadlines(addtask, deadLineDay);
                if (status == 1){
                    d.setStatus();
                }
                storage.add(d);
            } else {
                int status = reader.nextInt();
                String Skipbar =reader.next();
                String addTask = reader.next().trim();
                String bar = "|";
                String toBeChecked = "";
                while (true) {
                    String toAppend = reader.next().trim();
                    toBeChecked = toAppend;
                    addTask = addTask + " " + toAppend;
                    if(!toBeChecked.equals(bar)){
                        break;
                    }
                }
                String skipbar2 = reader.next();
                String deadLineDay =  reader.nextLine().trim();
                Events t = new Events(addTask, deadLineDay);
                if (status == 1){
                    t.setStatus();
                }
                storage.add(t);
            }
        }
    }

    /**
     *Update the file when requested. E.g When just add an item to the list
     * @param workingFile
     * @param storage
     * @throws IOException
     */
    public static void updateFile(File workingFile, List<Task> storage) throws IOException {

        FileWriter fw = new FileWriter(workingFile);
        PrintWriter pw = new PrintWriter(fw);

        int LengthOfArrayList;
        LengthOfArrayList = storage.size();
        for (int i = 0; i < LengthOfArrayList; i++) {
            if (storage.get(i) instanceof ToDos) {
                String task = (((ToDos) storage.get(i)).getToDoDescirption()).trim();
                int itemStatus = (((ToDos) storage.get(i)).getStatusint());
                pw.println("T " + itemStatus + " | " + task);
            }else if (storage.get(i) instanceof Deadlines) {
                String task = (((Deadlines) storage.get(i)).getDeadLineTask()).trim();
                String date = (((Deadlines) storage.get(i)).getDeadLine());
                int itemStatus = (((Deadlines) storage.get(i)).getStatusint());
                pw.println("D " + itemStatus + " | " + task + " | "+ date);
            }else{
                String task = (((Events) storage.get(i)).getEventsDescription()).trim();
                String date = (((Events) storage.get(i)).getEvent());
                int itemStatus = (((Events) storage.get(i)).getStatusint());
                pw.println("E " + itemStatus +" | " + task +" | " + date );
            }
        }
        pw.close();
    }

    /**
     *Its the same as list comment.Everything will be displayed
     * @param ListofMessages
     */
    public static String displayListOfMessages(List<Task> ListofMessages) {
        int LengthOfArrayList;
        String response = "";
        LengthOfArrayList = ListofMessages.size();
        for (int i = 0; i < LengthOfArrayList; i++) {
            int NumberToDisplay = i + 1;
            if (ListofMessages.get(i) instanceof ToDos) {
                String AdditemMessage = "[T]";
                String finalmessage = NumberToDisplay +   "."  +  AdditemMessage +   "[" + ((ListofMessages.get(i)).getStatusIcon()) + "]" + ((ListofMessages.get(i)).getTask());
                response += finalmessage;
                response += "\n";
            }else if (ListofMessages.get(i) instanceof Deadlines) {
                String ItemType = ((Deadlines) ListofMessages.get(i)).getItem();
                String GetDateLine = ((Deadlines) ListofMessages.get(i)).getDeadLine();//Type casting
                String TaskName = ((Deadlines) ListofMessages.get(i)).getDeadLineTask();
                String finalmessage = NumberToDisplay + "." + ItemType + "[" + ListofMessages.get(i).getStatusIcon() + "] " + TaskName + "(by: " + GetDateLine + ")";
                response += finalmessage;
                response += "\n";

            }else if (ListofMessages.get(i) instanceof Events) {
                String ItemType = ((Events) ListofMessages.get(i)).getItem();
                String GetDateLine = ((Events) ListofMessages.get(i)).getEvent();
                String TaskName = ((Events) ListofMessages.get(i)).getEventsDescription();
                String finalmessage = NumberToDisplay + "." + ItemType + "[" + ListofMessages.get(i).getStatusIcon() + "] " + TaskName + "(at: " + GetDateLine + ")";
                response += finalmessage;
                response += "\n";
            } else {
                String finalmessage = NumberToDisplay + ".[" + ((ListofMessages.get(i)).getStatusIcon()) + "]" + " " + ((ListofMessages.get(i)).getTask());
                response += finalmessage;
                response += "\n";
            }
        }
        return response;
    }
}
