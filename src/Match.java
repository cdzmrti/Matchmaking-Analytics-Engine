public class Match {
    private Player player1;
    private Player player2;

    public Match(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    /**
     * Simulate the end of a match
     * @param result 1.0 if player1 wins, 0.0 if player2 wins, 0.5 for draw
     */

    public void resolveMatch(double result) {
        // 1. get current ratings
        double r1 = player1.getElo();
        double r2 = player2.getElo();

        // 2. use EloCalculator to find the new ratings
        // result for P1 is the 'result' parameter
        // result for P2 is the opposite (1.0 - result)

        double newRating1 = EloCalculator.calculateNewRating(r1, r2, result);
        double newRating2 = EloCalculator.calculateNewRating(r2, r1, 1.0 - result);

        // 3. update the player objects
        player1.setElo(newRating1);
        player1.incrementGames();

        player2.setElo(newRating2);
        player2.incrementGames();

        System.out.println("Match Resolved");
        System.out.println(player1.toString());
        System.out.println(player2.toString());
    }

    public static void main(String[] args) {
        //testing
        Player naru = new Player("Naru");
        Player opponent = new Player("Coldplay");

        Match game1 = new Match(naru, opponent);

        System.out.println("--- Before Match ----");
        System.out.println(naru);
        System.out.println(opponent);

        //let's say naru wins
        game1.resolveMatch(1.0);
    }
}
