public class Player {
    private String name;
    private int age;
    private int jerseyNumber;
    private String position;
    private String hometown;
    private int numberOfAssists;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getHometown() {
        return hometown;
    }

    public int getNumberOfAssists() {
        return numberOfAssists;
    }

    public void setNumberOfAssists(int assists) {
        this.numberOfAssists = assists;
    }
}