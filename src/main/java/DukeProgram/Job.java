package DukeProgram;

public class Job {
    public enum JobState {
        Done,
        NotDone
    }
    private final String name;
    private JobState jobState;

    public Job(String name) {
        this.name = name;
        this.jobState = JobState.NotDone;
    }

    public Job(String name, boolean done) {
        this(name);
        this.jobState = JobState.Done;
    }

    public void MarkJobState(boolean isComplete) {
        this.jobState = isComplete ? JobState.Done : JobState.NotDone;
    }

    @Override
    public String toString() {
       return String.format("[%s] ", jobState.equals(JobState.Done) ? "X" : " ") + name;
    }
}
