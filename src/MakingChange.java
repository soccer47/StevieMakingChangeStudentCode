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

    // 2D array to hold number of ways to achieve different sums with given coins
    static int[][] numCombos;
    // Static array to hold values of coins
    static int[] coinVals;
    // Static integer to hold target value
    static int theTarget;

    public static long countWays(int target, int[] coins) {
        // Initialize the static 2D array of coin combinations
        numCombos = new int[target + 1][coins.length];
        // Initialize the static array of coin values
        coinVals = coins;
        // Initialize the static target value
        theTarget = target;

        // Start by setting the column of numCombos representing the sum of 0 to 1
        // (1 way to make 0 with each combination of coins available)
        for (int i = 0; i < numCombos[0].length; i++) {
            numCombos[0][i] = 1;
        }
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
    public static int getCombos (int sum, int index) {
        // Base case
        if (sum == 0) {
            // Add the current combo to the number fo combos if the sum has been reduced to 0
            return 1;
        }
        // Otherwise if the sum is less than 0 or the index is out of bounds the current combination is invalid
        else if (sum < 0 || index < 0) {
            return 0;
        }

        // Int representing the number of ways to make the target sum by including the current coin
        int include;
        // Int representing the number of ways to make the target sum by excluding the current coin
        int exclude;

        // Check if the path of including the current coin has already been visited
        if (sum - coinVals[index] < 0) {
            include = 0;
        }
        else if (numCombos[sum - coinVals[index]][index] != -1) {
            include = numCombos[sum - coinVals[index]][index];
        }
        else {
            // Otherwise visit the path by recursively calling getCombos
            include = getCombos(sum - coinVals[index], index);
            // Update numCombos
            numCombos[sum - coinVals[index]][index] = include;
        }

        // Check if the path of excluding the current coin has already been visited
        if (index <= 0) {
            exclude = 0;
        }
        else if (numCombos[sum][index - 1] != -1) {
            exclude = numCombos[sum][index - 1];
        }
        else {
            // Otherwise visit the path by recursively calling getCombos
            exclude = getCombos(sum, index - 1);
            // Update numCombos
            numCombos[sum][index - 1] = exclude;
        }

        // Return the sum of the combinations that you can get from either including or excluding the current coin
        return include + exclude;
    }
}
