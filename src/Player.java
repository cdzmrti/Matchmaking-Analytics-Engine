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

    public static double[] loadPlayerData(String targetName) { // search algorithm to find last known elo for a specific player
        try(BufferedReader reader = new BufferedReader(new FileReader("players.txt"))){
            String line;
            double lastElo = 1200.0; // default if not found
            double lastGames = 0.0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(targetName)) { // if parts[0] (contains name of player) matches targetName
                    lastElo = Double.parseDouble(parts[1]); // update, take the elo (string) & convert it into a real number (Double.parseDouble)
                    lastGames = Double.parseDouble(parts[2]); // parts[2]: number of games played
                }
            }
            return new double[] {lastElo, lastGames}; //return both values in an array
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) { // multi-catch syntax
            return new double[] {1200.0, 0.0}; // return default if file doesn't exist yet
        }
    }

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
