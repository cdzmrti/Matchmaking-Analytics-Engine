public class Tournament {
    public static void main(String[] args) {
        // call initialize so db is ready before using it
        DatabaseManager.initialize();

        // initialize player w/ persistence
        String name = "Naru";
        double[] savedData = Player.loadPlayerData(name);
        Player naru = new Player(name);

        naru.setElo(savedData[0]);
        naru.setTotalGames((int)savedData[1]);

        System.out.println("--- Tournament Start ---");
        System.out.println("Welcome back, " + name + "!");
        System.out.println("Current Rating: " + naru.getElo());
        System.out.println("Total Lifetime Games: " + (int)savedData[1]);
        System.out.println("-------------------------------");

        // create an array of opponents w/ different skill levels
        double[] opponentRatings = {1000, 1100, 1200, 1300, 1500};

        // loop: naru plays each opponent
        for (int i = 0; i < opponentRatings.length; i++) {
            // create a unique opponent for each round
            Player opponent = new Player("Opponent_" + (i + 1));
            opponent.setElo(opponentRatings[i]);

            System.out.println("Round " + (i + 1) + ": Naru vs " + opponent.getUsername() + " (Rating: " + opponentRatings[i] + ")" );

            // create match
            Match match = new Match(naru, opponent);

            // randomized logic: simulate game outcome
            double chance = Math.random();
            double result;

            if (chance > 0.8) {
                result = 0.0; // Naru lost (20% chance)
                System.out.println("Result: Naru Lost!");
            } else if (chance > 0.7) {
                result = 0.5; // it's a draw (10% chance)
                System.out.println("Result: It's a Draw!");
            } else {
                result = 1.0; //Naru Won (70% chance)
                System.out.println("Result: Naru Won!");
            }

            // resolve match and update player ratings
            match.resolveMatch(result);
            System.out.println("------------------------");
        }

        // output final results
        System.out.println("--- Tournament End ---");
        System.out.println("Final Stats for Naru: " + naru);

        // save new rating back to file
        // ensures next time prorgam is ran, Naru picks up where they left off
        naru.saveToFile();
    }
}
