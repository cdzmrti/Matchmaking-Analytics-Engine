public class Player {
    private String username;
    private double elo;
    private int totalGames;

    // Constructor: When a new player joins, they start with a default rating
    public Player(String username) {
        this.username = username;
        this.elo = 1200; // standard starting ELO
        this.totalGames = 0;
    }

    // Getters & Setters
    public String getUsername() { return username; }

    public double getElo() { return elo; }

    public void setElo(double newElo ) { this.elo = newElo; }

    public void incrementGames() { this.totalGames++; }

    @Override
    public String toString() {
        return String.format("Player: %s | ELO: %.2f | Games: %d", username, elo, totalGames);
    }
}
