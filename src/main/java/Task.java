 public abstract class Task {

     private final String task;

     private boolean done = false;

     public Task(String task) {
         this.task = task;
     }

     public String getTask() {
         return this.task;
     }

     public boolean getDone() {
         return this.done;
     }

     public String markdone() {
         this.done = true;
         return "Nice! I've marked this task as done:\n" + this;
     }

     public String unmarkdone() {
         this.done = false;
         return "OK, I've marked this task as not done yet:\n" + this;
     }
 }