import java.io.*; // required for File I/O

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

    public void saveToFile(){
        try (PrintWriter out = new PrintWriter(new FileWriter("players.txt", true))){
            out.println(username + "," + elo + "," + totalGames);
            System.out.println("Data saved for " + username);
        } catch (IOException e) {
            System.err.println("Error saving player: " + e.getMessage());
        }
    }
}
