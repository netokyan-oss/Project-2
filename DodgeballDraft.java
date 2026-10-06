import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Runs the dodgeball draft using the Gale-Shapley
 * stable matching algorithm.
 */
public class DodgeballDraft {

    /**
     * Loads candidates and their preference lists from a file.
     *
     * @param filename name of the input file
     * @return array of candidates
     */
    public static Candidate[] loadCandidates(String filename) {

        try {
            Scanner file = new Scanner(new File(filename));

            int count = 0;

            while (file.hasNextLine()) {

                String line = file.nextLine().trim();

                if (!line.isEmpty() && !line.equals(".")) {
                    count++;

                    while (file.hasNextLine()) {
                        line = file.nextLine().trim();

                        if (line.equals(".")) {
                            break;
                        }
                    }
                }
            }

            file.close();

            Candidate[] candidates = new Candidate[count];

            file = new Scanner(new File(filename));

            int index = 0;

            while (file.hasNextLine() && index < count) {

                String name = file.nextLine().trim();

                if (name.isEmpty() || name.equals(".")) {
                    continue;
                }

                Candidate candidate = new Candidate(name);

                int priority = 1;

                while (file.hasNextLine()) {

                    String choice = file.nextLine().trim();

                    if (choice.equals(".")) {
                        break;
                    }

                    if (!choice.isEmpty()) {
                        candidate.addChoice(priority, choice);
                        priority++;
                    }
                }

                candidates[index] = candidate;
                index++;
            }

            file.close();

            return candidates;

        } catch (FileNotFoundException e) {

            System.out.println("Could not open file: " + filename);

            return new Candidate[0];
        }
    }

    /**
     * Finds a candidate with the specified name.
     *
     * @param candidates array of candidates
     * @param name name to search for
     * @return matching candidate, or null if not found
     */
    private static Candidate findCandidate(
            Candidate[] candidates, String name) {

        for (Candidate candidate : candidates) {

            if (candidate != null
                    && candidate.getName().equals(name)) {
                return candidate;
            }
        }

        return null;
    }

    /**
     * Determines whether a candidate prefers a proposed
     * match over their current match.
     *
     * @param candidate candidate receiving the proposal
     * @param proposed proposed match
     * @return true if the candidate prefers the proposed match
     */
    public static boolean preferCandidate(
            Candidate candidate, String proposed) {

        String current = candidate.getCurrentMatch();

        if (current == null) {
            return true;
        }

        int proposedPriority =
                candidate.getPriorityOf(proposed);

        int currentPriority =
                candidate.getPriorityOf(current);

        if (proposedPriority == -1) {
            return false;
        }

        if (currentPriority == -1) {
            return true;
        }

        return proposedPriority < currentPriority;
    }

    /**
     * Matches a team with a player.
     *
     * @param team team being matched
     * @param player player being matched
     */
    public static void match(
            Candidate team, Candidate player) {

        team.setCurrentMatch(player.getName());
        player.setCurrentMatch(team.getName());
    }

    /**
     * Finds a stable matching using the Gale-Shapley algorithm.
     *
     * @param teams array of teams
     * @param players array of players
     */
    public static void findStableMatch(
            Candidate[] teams, Candidate[] players) {

        int[] nextChoice = new int[teams.length];

        boolean finished = false;

        while (!finished) {

            finished = true;

            for (int i = 0; i < teams.length; i++) {

                Candidate team = teams[i];

                if (team.getCurrentMatch() != null) {
                    continue;
                }

                int numberOfChoices =
                        getNumberOfChoices(team);

                if (nextChoice[i] >= numberOfChoices) {
                    continue;
                }

                finished = false;

                String playerName =
                        team.getChoice(nextChoice[i] + 1);

                nextChoice[i]++;

                Candidate player =
                        findCandidate(players, playerName);

                if (player == null) {
                    continue;
                }

                if (player.getCurrentMatch() == null) {

                    match(team, player);

                } else if (preferCandidate(
                        player, team.getName())) {

                    Candidate oldTeam =
                            findCandidate(
                                    teams,
                                    player.getCurrentMatch());

                    if (oldTeam != null) {
                        oldTeam.setCurrentMatch(null);
                    }

                    match(team, player);
                }
            }
        }
    }

    /**
     * Gets the number of choices in a candidate's
     * preference list.
     *
     * @param candidate candidate whose choices are counted
     * @return number of choices
     */
    private static int getNumberOfChoices(
            Candidate candidate) {

        int count = 0;

        while (true) {

            try {

                candidate.getChoice(count + 1);
                count++;

            } catch (IndexOutOfBoundsException e) {

                break;
            }
        }

        return count;
    }

    /**
     * Prints the matches to the screen.
     *
     * @param candidates candidates whose matches are printed
     */
    public static void printMatches(
            Candidate[] candidates) {

        System.out.println();
        System.out.println(" --------- Matches ----------");

        for (int i = 0; i < candidates.length; i++) {

            System.out.println(
                    " [" + (i + 1) + "] "
                    + candidates[i]);
        }
    }

    /**
     * Saves the matches to a file.
     *
     * @param candidates candidates whose matches are saved
     * @param filename output filename
     */
    public static void saveMatches(
            Candidate[] candidates,
            String filename) {

        try {

            PrintWriter output =
                    new PrintWriter(filename);

            output.println();
            output.println(" --------- Matches ----------");

            for (int i = 0; i < candidates.length; i++) {

                output.println(
                        " [" + (i + 1) + "] "
                        + candidates[i]);
            }

            output.close();

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Could not create output file: "
                    + filename);
        }
    }

    /**
     * Runs the dodgeball draft program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter teams file: ");
        String teamsFile = input.nextLine();

        System.out.print("Enter players file: ");
        String playersFile = input.nextLine();

        System.out.print("Enter output file: ");
        String outputFile = input.nextLine();

        Candidate[] teams =
                loadCandidates(teamsFile);

        Candidate[] players =
                loadCandidates(playersFile);

        findStableMatch(teams, players);

        printMatches(players);

        saveMatches(players, outputFile);

        input.close();
    }
}