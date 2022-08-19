public class Tasklist {
    private String[] taskArray;
    private int count = 0;

    public Tasklist(int size){
        this.taskArray = new String[size];
    }

    public void add(Task task){
        this.taskArray[this.count] = task.toString();
        this.count += 1;
    }

    @Override
    public String toString(){
        String stringedList = "";
        for (int i = 0; i < this.count; i++) {
            stringedList += (i + 1) + ". " + this.taskArray[i] + "\n";
        }
        return stringedList;
    }

}
