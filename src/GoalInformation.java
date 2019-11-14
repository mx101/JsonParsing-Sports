import java.util.List;

public class GoalInformation {
    private String team;
    private int timestamp;
    private String scorer;
    private String assist;
    private List<Passes> passes;

    public String getTeam() {
        return team;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getScorer() {
        return scorer;
    }

    public String getAssist() {
        return assist;
    }

    public List<Passes> getPasses() {
        return passes;
    }
}

