import java.util.HashMap;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Stevie Halprin
 */

public class MakingChange {
    /**
     * TODO: Complete this function, countWays(), to return the number of ways to make change
     *  for any given total with any given set of coins.
     */

    // HashMap to hold different combinations of coins
    static HashMap<Integer[], Boolean> combos;
    // Static array to hold values of coins
    static int[] coinVals;
    // Static integer to hold target value
    static int theTarget;

    public static long countWays(int target, int[] coins) {
        // Initialize the static HashMap of coin combinations
        combos = new HashMap<>();
        // Initialize the static array of coin values
        coinVals = coins;
        // Initialize the static target value
        theTarget = target;

        // Call BFS to get the total number of possible coin combinations
        // Iterate through the array of coins to get every coin as a starting value
        for (int i = 0; i < coinVals.length; i++) {
            BFS(new Integer[coins.length], 0, i);
        }

        // Return the number of combinations stored in the HashMap
        return combos.size();
    }

    // Breadth first search to get the number of possible coin combinations to achieve the target value
    public static void BFS (Integer[] numCoins, int currentValue, int newCoin) {
        // If the value of the current coin combination exceeds the target value, return and stop recursing
        if (currentValue + coinVals[newCoin] > theTarget) {
            return;
        }
        // Otherwise increment the current sum by the value of the new coin
        else {
            currentValue += coinVals[newCoin];
            // Increment the array keeping track of the number of each coin accordingly
            // Make sure numCoins[newCoin] isn't null
            if (numCoins[newCoin] == null) {
                // If numCoins[newCoin] is null, set it equal to 1
                numCoins[newCoin] = 1;
            }
            else {
                numCoins[newCoin] = numCoins[newCoin] + 1;
            }
        }

        // If the target value has been achieved, add the current combo to the HashMap and return
        if (currentValue == theTarget) {
            combos.put(numCoins, true);
            return;
        }

        // Recursively call BFS by trying to add each of the coins to the current combo
        for (int i = 0; i < coinVals.length; i++) {
            BFS(numCoins, currentValue, i);
        }
    }
}
