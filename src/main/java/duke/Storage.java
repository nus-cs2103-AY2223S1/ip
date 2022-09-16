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
     * @param readfile
     * @param storage
     */
    public static void readfilez(Scanner readfile, List<Task> storage) {
        while(readfile.hasNextLine()) {
            String firsttask = readfile.next();
            if (firsttask.equals("T")) {
                int status = readfile.nextInt();
                String Skipbar =readfile.next();
                String task = readfile.nextLine();

                ToDos t = new ToDos(task);
                if (status == 1){
                    t.setStatus();
                }
                storage.add(t);

            } else if (firsttask.equals("D")) {
                int status = readfile.nextInt();
                String Skipbar =readfile.next();
                String addtask = readfile.next().trim();
                String bar = "|";
                String tocheck = "";
                int i = 0;
                while (true) {
                    String toappend = readfile.next().trim();
                    tocheck = toappend;
                    addtask = addtask + " " + toappend;
                    i++;
                    if(!tocheck.equals(bar)){
                        break;
                    }
                }
                String skipbar2 = readfile.next();
                String deadlineday =  readfile.nextLine().trim();
                Deadlines d = new Deadlines(addtask, deadlineday);
                if (status == 1){
                    d.setStatus();
                }
                storage.add(d);
            } else {
                int status = readfile.nextInt();
                String Skipbar =readfile.next();
                String addtask = readfile.next().trim();
                String bar = "|";
                String tocheck = "";
                while (true) {
                    String toappend = readfile.next().trim();
                    tocheck = toappend;
                    addtask = addtask + " " + toappend;
                    if(!tocheck.equals(bar)){
                        break;
                    }
                }
                String skipbar2 = readfile.next();
                String deadlineday =  readfile.nextLine().trim();
                Events t = new Events(addtask, deadlineday);
                if (status == 1){
                    t.setStatus();
                }
                storage.add(t);
            }
        }
    }

    /**
     *Update the file when requested. E.g When just add an item to the list
     * @param thefile
     * @param storage
     * @throws IOException
     */
    public static void UpdateFile (File thefile,List<Task> storage) throws IOException {

        FileWriter fw = new FileWriter(thefile);
        PrintWriter pw = new PrintWriter(fw);

        int LengthOfArrayList;
        LengthOfArrayList = storage.size();
        for (int i = 0; i < LengthOfArrayList; i++) {
            if (storage.get(i) instanceof ToDos) {
                String task = (((ToDos) storage.get(i)).getToDoDescirption()).trim();
                int statusofITEM = (((ToDos) storage.get(i)).getStatusint());
                pw.println("T " + statusofITEM + " | " + task);
            }else if (storage.get(i) instanceof Deadlines) {
                String task = (((Deadlines) storage.get(i)).getDeadLineTask()).trim();
                String date = (((Deadlines) storage.get(i)).getDeadLine());
                int statusofITEM = (((Deadlines) storage.get(i)).getStatusint());
                pw.println("D " + statusofITEM + " | " + task + " | "+ date);
            }else{
                String task = (((Events) storage.get(i)).getEventsDescription()).trim();
                String date = (((Events) storage.get(i)).getEvent());
                int statusofITEM = (((Events) storage.get(i)).getStatusint());
                pw.println("E " + statusofITEM +" | " + task +" | " + date );
            }

        }

        pw.close();
    }

    /**
     *Its the same as list comment.Everything will be displayed
     * @param ListofMessages
     */
    public static void DisplayListOfMessages(List<Task> ListofMessages) {
        int LengthOfArrayList;
        LengthOfArrayList = ListofMessages.size();
        for (int i = 0; i < LengthOfArrayList; i++) {
            int NumberToDisplay = i + 1;
            if (ListofMessages.get(i) instanceof ToDos) {
                String AdditemMessage = "[T]";
                String finalmessage = NumberToDisplay +   "."  +  AdditemMessage +   "[" + ((ListofMessages.get(i)).getStatusIcon()) + "]" + ((ListofMessages.get(i)).getTask());
                System.out.println(finalmessage);
            }else if (ListofMessages.get(i) instanceof Deadlines) {
                String ToManipulate = ListofMessages.get(i).getStatusIcon();
                String ItemType = ((Deadlines) ListofMessages.get(i)).getItem();
                String GetDateLine = ((Deadlines) ListofMessages.get(i)).getDeadLine();//Type casting
                String TaskName = ((Deadlines) ListofMessages.get(i)).getDeadLineTask();
                String finalmessage = NumberToDisplay + "." + ItemType + "[" + ListofMessages.get(i).getStatusIcon() + "] " + TaskName + "(by: " + GetDateLine + ")";
                System.out.println(finalmessage);
            }else if (ListofMessages.get(i) instanceof Events) {
                String ToManipulate = ListofMessages.get(i).getStatusIcon();
                String ItemType = ((Events) ListofMessages.get(i)).getItem();
                String GetDateLine = ((Events) ListofMessages.get(i)).getEvent();
                String TaskName = ((Events) ListofMessages.get(i)).getEventsDescription();
                String finalmessage = NumberToDisplay + "." + ItemType + "[" + ListofMessages.get(i).getStatusIcon() + "] " + TaskName + "(at: " + GetDateLine + ")";
                System.out.println(finalmessage);
            } else {
                String finalmessage = NumberToDisplay + ".[" + ((ListofMessages.get(i)).getStatusIcon()) + "]" + " " + ((ListofMessages.get(i)).getTask());
                System.out.println(finalmessage);
            }
        }
    }





}
