import com.google.gson.Gson;

import java.util.*;

public class Sportsball {

    private Team homeTeam;
    private Team awayTeam;
    private String winner;
    private List<GoalInformation> goals;

    private static String myJson = Data.getFileContents("sportsball", "sportsball.txt");
    private static Gson gson = new Gson();
    private static Sportsball sportsball = gson.fromJson(myJson, Sportsball.class); // = gson.fromJson(myJson, Sportsball.class);
    private static String awayteamAsString = "Urbana International";
    private static String hometeamAsString = "Champaign United";

    /**
     * Returns the winner of the game as a string. Simple method to verify correct implementation of JSON file.
     * @return winner of sportsball game.
     */
    public static String getWinner() {
        return sportsball.winner;
    }

    /**
     * helper method for average scorer computation methods.
     * @return a list of the scorers' names as strings.
     */
    public static List<String> getScorers() {
        List<String> scorers = new ArrayList<>();

        for (GoalInformation currentGoal : sportsball.goals) {
            //Do not add a scorer that is already on the scorers list
            if (!scorers.contains(currentGoal.getScorer())) {
                scorers.add(currentGoal.getScorer());
            }
        }

        return scorers;
    }

    /**
     * helper method to determine if the given player exists in either team
     * @param playerName name of player we wish to see if exists
     * @return a boolean, true if the player exists, false if they do not
     */
    public static boolean playerExists(String playerName) {
        if (playerName == null) {
            return false;
        }

        boolean playerFound = false;

        for (Player currentPlayer : sportsball.homeTeam.getPlayers()) {
            if (currentPlayer.getName().equals(playerName)) {
                playerFound = true;
                break;
            }
        }

        for (Player currentPlayer : sportsball.awayTeam.getPlayers()) {
            if (currentPlayer.getName().equals(playerName)) {
                playerFound = true;
                break;
            }
        }

        return playerFound;
    }

    /**
     * helper method for goalsAfterTimestamp test.
     * @param time timestamp of the goal we wish to return
     * @return a goal of the class GoalInformation
     */
    public static GoalInformation goalReturn(int time) {
        if (time < 0) {
            return null;
        }

        GoalInformation returnGoal = new GoalInformation();

        for (GoalInformation currentGoal : sportsball.goals) {
            if (currentGoal.getTimestamp() == time) {
                returnGoal = currentGoal;
                break;
            }
        }

        return returnGoal;
    }

    /**
     * returns a Player object based on a name input. Mainly a helper method.
     * @param playerName name of the player we wish to return as a Player object
     * @return Player object
     */
    public static Player getPlayer(String playerName) {
        if (!playerExists(playerName)) {
            return null;
        }

        Player playerToReturn = null;
        List<Player> homeTeamList = sportsball.homeTeam.getPlayers();
        List<Player> awayTeamList = sportsball.awayTeam.getPlayers();

        for (Player currentPlayer: homeTeamList) {
            if (currentPlayer.getName().equals(playerName)) {
                playerToReturn = currentPlayer;
                break;
            }
        }

        for (Player currentPlayer: awayTeamList) {
            if (currentPlayer.getName().equals(playerName)) {
                playerToReturn = currentPlayer;
                break;
            }
        }

        return playerToReturn;
    }

    /**
     * Returns the age of the player input.
     * @param playerName of the player we are trying to find the age of.
     * @return the age of the input player as an integer.
     */
    public static int returnPlayerAge(String playerName) {
        //ageToReturn is initialized to -1 for debugging purposes.
        int ageToReturn = -1;
        Player returnPlayer = getPlayer(playerName);

        if (returnPlayer == null) {
            ageToReturn = -1;
        } else {
            ageToReturn = returnPlayer.getAge();
        }

        return ageToReturn;
    }

    /**
     * creates a list of timestamps for each goal the player scored.
     * @param playerName of scoring player.
     * @return a list of timestamps corresponding to the goal that each player scored.
     */
    public static List<Integer> timestampsOfGoals(String playerName) {
        //we use null for the return value of a bad playerName input
        if (!playerExists(playerName)) {
            System.out.println("Failed to find player");
            return null;
        }

        List<Integer> timestampsList = new ArrayList<Integer>();

        for (GoalInformation currentGoal : sportsball.goals) {
            if (currentGoal.getScorer().equals(playerName)) {
                timestampsList.add(currentGoal.getTimestamp());
            }
        }

        return timestampsList;
    }

    /**
     * Get the list of players that assisted a player in their goals.
     * @param playerName of player we are looking for goals with.
     * @return list of assisting players as a list of strings.
     */
    public static List<String> assistingPlayers(String playerName) {
        //we use null for the return value of a bad playerName input
        if (!playerExists(playerName)) {
            System.out.println("Failed to find player");
            return null;
        }

        List<String> assistersList = new ArrayList<>();

        for (GoalInformation currentGoal : sportsball.goals) {
            if (currentGoal.getScorer().equals(playerName)) {
                if (currentGoal.getAssist() == null) {
                    continue;
                }
                if (assistersList.contains(currentGoal.getAssist())) {
                    continue;
                }
                assistersList.add(currentGoal.getAssist());
            }
        }

        return assistersList;
    }

    /**
     * Gives a list of goals made after a certain timestamp during the game.
     * @param time we wish to find the goals after
     * @return as a list of GoalInformation
     */
    public static List<GoalInformation> goalsAfterTimestamp(int time) {
        List<GoalInformation> returnList = new ArrayList<>();

        for (GoalInformation currentGoal : sportsball.goals) {
            if (currentGoal.getTimestamp() > time) {
                returnList.add(currentGoal);
            }
        }

        return returnList;
    }

    /**
     * Find the players that the input player passed to.
     * @param playerName name of player we want to see receivers of
     * @return list of receivers as strings
     */
    public static List<String> getReceivers(String playerName) {

        //we use null for the return value of a bad playerName input
        if (!playerExists(playerName)) {
            System.out.println("Failed to find player");
            return null;
        }

        List<String> receivers = new ArrayList<>();

        for (GoalInformation currentGoal : sportsball.goals) {
            for (Passes currentPass : currentGoal.getPasses()) {
                if (receivers.contains(currentPass.getReceiver())) {
                    continue;
                }
                if (currentPass.getPasser().equals(playerName)) {
                    receivers.add(currentPass.getReceiver());
                }
            }
        }

        return receivers;
    }

    /**
     * Given the name of a team, returns the average timestamp of their goals as an integer
     * @param teamName team we wish to find average timestamp of goals for
     * @return the average timestamp of the goals as a double
     */
    public static int averageTimestampOfGoals(String teamName) {
        //return -1 for bad teamName input
        if (teamName == null) {
            return -1;
        }
        if (!(teamName.equals(hometeamAsString)) && !(teamName.equals(awayteamAsString))) {
            return -1;
        }

        int timestampCounter = 0, totalGoals = 0;

        for (GoalInformation currentGoal : sportsball.goals) {
            if (currentGoal.getTeam().equals(teamName)) {
                timestampCounter += currentGoal.getTimestamp();
                totalGoals++;
            }
        }

        return timestampCounter / totalGoals;
    }

    /**
     * find the average age of the scorers by adding each unique scorer's age and dividing by the total.
     * @return the average age of scorers as an int
     */
    public static int averageScorerAge() {
        List<String> scorers = getScorers();
        int scorerAgeCount = 0;

        for (Player currentPlayer : sportsball.homeTeam.getPlayers()) {
            if (scorers.contains(currentPlayer.getName())) {
                scorerAgeCount += currentPlayer.getAge();
            }
        }

        for (Player currentPlayer : sportsball.awayTeam.getPlayers()) {
            if (scorers.contains(currentPlayer.getName())) {
                scorerAgeCount += currentPlayer.getAge();
            }
        }

        return scorerAgeCount / scorers.size();
    }

    /**
     * Find the average of the scorers' jersey numbers.
     * @return average as an int
     */
    public static int averageScorerJerseyNumber() {
        List<String> scorers = getScorers();
        int scorerJerseyNumberCount = 0;

        for (Player currentPlayer : sportsball.homeTeam.getPlayers()) {
            if (scorers.contains(currentPlayer.getName())) {
                scorerJerseyNumberCount += currentPlayer.getJerseyNumber();
            }
        }

        for (Player currentPlayer : sportsball.awayTeam.getPlayers()) {
            if (scorers.contains(currentPlayer.getName())) {
                scorerJerseyNumberCount += currentPlayer.getJerseyNumber();
            }
        }

        return scorerJerseyNumberCount / scorers.size();
    }

    /**
     * Altered insertion sort method, most of this code was taken from Geoff Challen
     * https://cs125.cs.illinois.edu/learn/2019_04_05_insertion_and_merge_sort/
     * I chose to go with insertion sort since our input array of players is not large enough for
     * efficiency to be a major issue during runtime.
     * @param input array to be sorted
     */
    public static String[] insertionSortAssists(String[] input) {
        for (int index = 0; index < input.length; index++) {
            for (int comparison = 1; comparison > 0; comparison++) {
                int playerOneAssists = getPlayer(input[comparison - 1]).getNumberOfAssists();
                int playerTwoAssists = getPlayer(input[comparison]).getNumberOfAssists();

                if (playerOneAssists < playerTwoAssists) {
                    System.out.println("Switching indices");
                    String temp = input[comparison - 1];
                    input[comparison - 1] = input[comparison];
                    input[comparison] = temp;
                } else {
                    break;
                }
            }
        }

        return input;
    }

    /**
     * Computation method that returns an ordered list of the players with the most assists.
     * @return as a string list
     */
    public static List<String> mostAssists() {
        List<String> orderedAssists = new ArrayList<>();

        //adds every assister to the return list
        for (GoalInformation currentGoal : sportsball.goals) {
            Player currentPlayer = getPlayer(currentGoal.getAssist());

            if (currentPlayer == null) {
                continue;
            }

            if (orderedAssists.contains(currentGoal.getAssist())) {
                //increment assister's assist count and continue if we have already counted this assister
                currentPlayer.setNumberOfAssists(currentPlayer.getNumberOfAssists() + 1);
                continue;
            } else {
                //otherwise, set number of assists to 1 as we have not yet counted them
                currentPlayer.setNumberOfAssists(1);
                orderedAssists.add(currentGoal.getAssist());
            }
        }

        String[] unorderedArray = new String[orderedAssists.size()];

        for (int index = 0; index < orderedAssists.size(); index++) {
            unorderedArray[index] = orderedAssists.get(index);
        }

        String[] orderedAssistsArr = insertionSortAssists(unorderedArray);
        List<String> returnList = new ArrayList<>();

        for (String assister : orderedAssistsArr) {
            returnList.add(assister);
        }
        return returnList;
    }

    /**
     * Helper method for any method that requires checking which team to work with.
     * Note that some methods may have their own initial test for null or other bad inputs
     * to keep in line with the method's return declaration
     * @param teamName name of team we are trying to return.
     * @return name of team as a member of the Team class
     */
    public static Team helperReturnTeam(String teamName) {
        if (teamName == null) {
            return null;
        }

        Team returnTeam = new Team();

        if (teamName.equals(hometeamAsString)) {
            returnTeam = sportsball.homeTeam;
        } else if (teamName.equals(awayteamAsString)) {
            returnTeam = sportsball.awayTeam;
        }

        return returnTeam;
    }

    /**
     * Find the average age of a team's players given a team name.
     * @param teamName we want the average player age of
     * @return average age as an int
     */
    public static int averagePlayerAge(String teamName) {
        //use zero for bad input
        if (teamName == null) {
            return 0;
        }

        //returnAge initialized to -1 for debugging purposes
        int returnAge = -1;
        Team team = helperReturnTeam(teamName);

        for (Player currentPlayer : team.getPlayers()) {
            returnAge += currentPlayer.getAge();
        }

        returnAge = returnAge / team.getPlayers().size();

        return returnAge;
    }

    /**
     * Finds the most frequent hometown of a team
     * @param teamName we want the most popular hometown of
     * @return
     */
    public static String mostPopularHometown(String teamName) {
        if (teamName == null) {
            return null;
        }

        String returnTown = "";
        int uniqueTownIndex = 0;
        Team team = helperReturnTeam(teamName);
        List<String> hometownsSeen = new ArrayList<>();
        List<String> hometownsSeenDoubleCounting = new ArrayList<>();
        List<Integer> uniqueTownIndexKeeper = new ArrayList<>();

        //loops through all players on the team and records their hometowns in two separate lists
        //one list counting cities uniquely, the other counting cities multiple times if they appear so
        for (Player currentPlayer : team.getPlayers()) {
            if (!hometownsSeen.contains(currentPlayer.getHometown())) {
                hometownsSeen.add(currentPlayer.getHometown());
                uniqueTownIndexKeeper.add(uniqueTownIndex);
            }
            hometownsSeenDoubleCounting.add(currentPlayer.getHometown());
            uniqueTownIndex++;
        }

        int[] timesSeenTown = new int[hometownsSeen.size()];

        if (hometownsSeenDoubleCounting.size() == hometownsSeen.size()) {
            return "No most frequent hometown found";
        } else {
            for (int doubleCount = 0; doubleCount < hometownsSeenDoubleCounting.size(); doubleCount++) {
                for (int town = 0; town < hometownsSeen.size(); town++) {
                    if (hometownsSeenDoubleCounting.get(doubleCount).equals(hometownsSeen.get(town))) {
                        timesSeenTown[town]++;
                        break;
                    }
                }
            }
        }

        int positionToReturn = 0;

        for (int town = 0; town < timesSeenTown.length; town++) {
            if (timesSeenTown[town] > timesSeenTown[positionToReturn]) {
                positionToReturn = town;
            }
        }

        returnTown = hometownsSeenDoubleCounting.get(uniqueTownIndexKeeper.get(positionToReturn));

        return returnTown;
    }
}
