import java.util.Arrays;

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

    // 2D array to hold number of ways to achieve different sums with given coins
    static long[][] numCombos;
    // Static array to hold values of coins
    static int[] coinVals;
    // Static integer to hold target value
    static int theTarget;

    public static long countWays(int target, int[] coins) {
        // Initialize the static 2D array of coin combinations
        numCombos = new long[target + 1][coins.length];
        // Initialize the static array of coin values
        coinVals = coins;
        // Initialize the static target value
        theTarget = target;

        // Start by setting the column of numCombos representing the sum of 0 to 1
        // (1 way to make 0 with each combination of coins available)
        Arrays.fill(numCombos[0], 1);
        // Set the rest of the array to -1 to show that they haven't been visited
        for (int i = 1; i < numCombos.length; i++) {
            for (int j = 0; j < numCombos[0].length; j++)
                numCombos[i][j] = -1;
        }

        // Call the recursive method to get the total number of possible coin combinations
        // Gradually fill out the 2D array numCombos
        // Return the total number of combinations returned by the call to getCombos
        return getCombos(target, coins.length - 1);

    }

    // Recursive method to get the number of combos for each sum
    public static long getCombos(long sum, int index) {
        // Base case
        if (sum == 0) {
            // Add the current combo to the number of combos if the sum has been reduced to 0
            return 1;
        }
        // Otherwise if the sum is less than 0 or the index is out of bounds the current combination is invalid
        else if (sum < 0 || index < 0) {
            return 0;
        }

        // Long representing the number of ways to make the target sum by including the current coin
        long include;
        // Long representing the number of ways to make the target sum by excluding the current coin
        long exclude;

        // Make sure the path of including the current coin can be checked in numCombos
        if (sum - coinVals[index] < 0) {
            include = 0;
        }
        // Otherwise check number of ways to reach the target by including the current coin is already known
        else if (numCombos[(int)sum - coinVals[index]][index] != -1) {
            // Set include to the known value if possible
            include = numCombos[(int)sum - coinVals[index]][index];
        }
        else {
            // Otherwise find the number of combinations by recursively calling getCombos
            include = getCombos(sum - coinVals[index], index);
            // Update numCombos
            numCombos[(int)sum - coinVals[index]][index] = include;
        }

        // Make sure the path of excluding the current coin can be checked in numCombos
        if (index <= 0) {
            exclude = 0;
        }
        // Otherwise check number of ways to reach the target by excluding the current coin is already known
        else if (numCombos[(int)sum][index - 1] != -1) {
            // Set exclude to the known value if possible
            exclude = numCombos[(int)sum][index - 1];
        }
        else {
            // Otherwise find the number of combinations by recursively calling getCombos
            exclude = getCombos(sum, index - 1);
            // Update numCombos
            numCombos[(int)sum][index - 1] = exclude;
        }

        // Return the total coin combinations that give the target sum from including or excluding the current coin
        return include + exclude;
    }
}
