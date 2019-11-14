import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DataTest {

    private Gson gson;

    @Test
    public void canReadFileFromFolder() throws Exception {
        String testFileContents = Data.getFileContents("test_resources", "test_file_contents.txt");
        assertEquals("Gone, reduced to atoms", testFileContents);
    }

    @Before
    public void setup() {
        gson = new Gson();
    }

    @Test
    public void simpleGetWinnerTest() throws Exception {
        assertEquals("Urbana International", Sportsball.getWinner());
    }

    @Test
    public void getPlayerAgeTest() throws Exception {
        assertEquals(20, Sportsball.returnPlayerAge("David Cole"));
    }

    @Test
    public void fakePlayerAgeTest() throws Exception {
        assertEquals(-1, Sportsball.returnPlayerAge("Josh Sanchez"));
    }

    @Test
    public void goalTimeStampsTest() throws Exception {
        List<Integer> timestamps = new ArrayList<Integer>();
        timestamps.add(80);
        //System.out.println(timestamps.toString());
        assertEquals(timestamps, Sportsball.timestampsOfGoals("Robert Good"));
    }

    @Test
    public void nullGoalTimestampsTest() throws Exception {
        assertEquals(null, Sportsball.timestampsOfGoals(null));
    }

    @Test
    public void assistingPlayersTest() throws Exception {
        List<String> assists = new ArrayList<>();
        assists.add("Mercedes Jackson");
        assertEquals(assists, Sportsball.assistingPlayers("Robert Good"));
    }

    @Test
    public void noGoalsAssistingPlayersTest() throws Exception {
        List<String> compareList = new ArrayList<>();
        assertEquals(compareList, Sportsball.assistingPlayers("Gary Moore"));
    }

    @Test
    public void getReceiversTest() throws Exception {
        List<String> receiversConnie = new ArrayList<>();
        receiversConnie.add("Patricia Jones");
        assertEquals(receiversConnie, Sportsball.getReceivers("Connie Marquez"));
    }

    @Test
    public void truePlayerFinderTest() throws Exception {
        assertEquals(true, Sportsball.playerExists("Robert Good"));
    }

    @Test
    public void falsePlayerFinderTest() throws Exception {
        assertEquals(false, Sportsball.playerExists("Josh Sanchez"));
    }

    @Test
    public void badPlayerNameInput() throws Exception {
        assertEquals(-1, Sportsball.returnPlayerAge("Josh Sanchez"));
    }

    @Test
    public void averageTimestampTest() throws Exception {
        assertEquals(46, Sportsball.averageTimestampOfGoals("Urbana International"));
    }

    @Test
    public void averageScorerAgeTest() throws Exception {
        assertEquals(24, Sportsball.averageScorerAge());
    }

    @Test
    public void goalsAfterTimeTest() throws Exception {
        List<GoalInformation> compareGoals = new ArrayList<>();
        compareGoals.add(Sportsball.goalReturn(85));
        assertEquals(compareGoals, Sportsball.goalsAfterTimestamp(80));
    }

    @Test
    public void averageScorerJerseyTest() throws Exception {
        assertEquals(12, Sportsball.averageScorerJerseyNumber());
    }

    @Test
    public void mostAssistsTest() throws Exception {
        List<String> assistList = new ArrayList<>();
        assistList.add("Guadalupe Nelson");
        assistList.add("Patricia Jones");
        assistList.add("Steven White");
        assistList.add("Chad Sizemore");
        assistList.add("Scott Smiley");
        assistList.add("Stephen Robertson");
        assistList.add("John Yeiser");
        assistList.add("Mercedes Jackson");
        assertEquals(assistList, Sportsball.mostAssists());
    }

    @Test
    public void helperReturnTeamTestC() throws  Exception {
        assertEquals("Champaign United", Sportsball.helperReturnTeam("Champaign United").getName());
    }

    @Test
    public void helperReturnTeamTestU() throws  Exception {
        assertEquals("Urbana International",
                Sportsball.helperReturnTeam("Urbana International").getName());
    }

    @Test
    public void averagePlayerAgeTest() throws Exception {
        assertEquals(23, Sportsball.averagePlayerAge("Champaign United"));
    }

    @Test
    public void mostPopularHometownTestU() throws Exception {
        assertEquals("Bloomington", Sportsball.mostPopularHometown("Urbana International"));
    }

    @Test
    public void mostPopularHometownTestC() throws Exception {
        assertEquals("Champaign", Sportsball.mostPopularHometown("Champaign United"));
    }
}
