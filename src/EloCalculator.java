public class EloCalculator {
    // The K-factor determines how much a rating changes after a game.
    //32 is standard for most competitive games
    private static final int K_FACTOR = 32;

    /**
     * Calculates the new rating for a player based on their performance
     * @param ratingA the current rating of the player we are updating
     * @param ratingB the rating of the opponent they played against
     * @param actualScore 1.0 for a win, 0.5 for a draw, 0.0 for a loss
     * @return the updated rating as a double
     */
    public static double calculateNewRating(double ratingA, double ratingB, double actualScore) {
        // calculate expected score for Player A (the probability they should win)
        // formula: 1 / (1 + 10^((RatingB - RatingA) / 400))
        // if expected score is 0.5, players are perfectly matched
        // if 0.9, system is 90% sure player A will win
        double expectedScore = 1.0 / (1.0 + Math.pow(10, (ratingB - ratingA) / 400.0)); // 400 is the standard scaling factor for ELO
        
    
        //calculate new rating
        //formula: CurrentRating + K * (ActualResult - ExpectedResult)
        return ratingA + K_FACTOR * (actualScore - expectedScore);
    }

    public static void main(String[] args) {
        // TEST CASE 1: A strong player beats a weaker player
        double proPlayer = 2000;
        double newbiePlayer = 1000;
        double proNewRating = calculateNewRating(proPlayer, newbiePlayer, 1.0);

        System.out.println("Scenario: Pro (2000) beats Newbie (1000)");
        System.out.println("Pro's New Rating: " + proNewRating);
        System.out.println("Points Gained: " + (proNewRating - proPlayer));
        System.out.println("---");

        // TEST CASE 2: A weaker player beats a strong player
        double newbieWinsRating = calculateNewRating(newbiePlayer, proPlayer, 1.0);

        System.out.println("Scenario: Newbie (1000) beats Pro (2000)");
        System.out.println("Newbie's New Rating: " + newbieWinsRating);
        System.out.println("Points Gained: " + (newbieWinsRating + newbiePlayer));
    }
}
