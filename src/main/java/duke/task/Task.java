package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.dukeexception.DukeException;
public class Task {
    private String description;
    private boolean isDone;
    private LocalDate day;
    private String fullDescription;
    public Task (String description) {
        this.description=description;
        this.isDone=false;
    }
    //return the status of this task
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    //mark this task as Done without reply
    public void taskDone(){
        this.isDone=true;
    }
    public void correctDescrition(String rightDescription){
        this.description=rightDescription;
    }
    //undone this task and print reply
    public void taskUndone(){
        this.isDone=false;

    }
    //print the representation of this task containing status and description
    public String printTask(){
        return ("["+this.getStatusIcon()+"]"+" "+this.description);
    }

    //create a certain kind of task
    public static Task createATask(String s) throws DukeException {
        if (s.split(" ")[0].equals("todo")) {
            return new ToDo(s);
        } else if (s.split(" ")[0].equals("deadline")) {
            return new DeadLine(s);
        } else if (s.split(" ")[0].equals("event")) {
            return new Event(s);
        }
        return null;
    }

    public String getDescription() {
        return this.description;
    }

    public void getFullDescription(String s){
        this.fullDescription = s;
    }
    public String showTime() throws DukeException{
        try {

            String[] s = fullDescription.split(" ");
            String[] s2 = s[s.length - 2].split("/");
            String time = s2[2];
            if (s2[1].length() < 2) {
                time += "-0" + s2[1];
            }else{
                time += "-" + s2[1];
            }
            if(s2[0].length() < 2) {
                time += "-0" + s2[0];
            }else{
                time += "-" + s2[0];
            }
            this.day = LocalDate.parse(time);
            return this.day.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }catch (Exception e){
            throw new DukeException("");
        }

    }
    public LocalDate getDay(){
        return this.day;
    }
}
