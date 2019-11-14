import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public String getName() {
        return name;
    }

    /**
     * public method to return an array of the players on the away team.
     * @return an array of players on the away team.
     */
    public List<Player> getPlayers() {
        return players;
    }
}
