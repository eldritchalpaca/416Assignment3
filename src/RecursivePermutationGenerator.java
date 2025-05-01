import java.util.ArrayList;
import java.util.List;

public class RecursivePermutationGenerator {

    /**
     * Main method to run the permutation generator.
     * Accepts a default string; can be modified for user input if needed.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String input = "ABC";

        // Get user input if available
        if (args.length >= 1) {
            input = args[0];
        }

        // Validate input
        if (!validateInput(input)) {
            System.err.println("Invalid input. Input must be a non-null, non-empty string.");
            return;
        }

        List<String> permutations = generatePermutations(input);
        System.out.println("Permutations of " + input + ":");
        for (String permutation : permutations) {
            System.out.println(permutation);
        }
    }

    /**
     * Validates a string input to ensure it is not null and the String is not empty
     * @param str The input to be validated
     * @return true if the input is valid, false otherwise
     */
    private static boolean validateInput(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * Generates all permutations of the input string using recursion.
     *
     * @param str the input string (assumed to be validated)
     * @return list of all permutations
     */
    public static List<String> generatePermutations(String str) {

        if (!validateInput(str)) {
            return new ArrayList<>();
        }

        List<String> permutations = new ArrayList<>();
        // Base case: if string length is 1, only one permutation
        if (str.length() == 1) {
            permutations.add(str);
            return permutations;
        }

        // Recursive case: for each character, fix it and permute the remaining substring
        for (int i = 0; i < str.length(); i++) {
            char fixedChar = str.charAt(i);
            String remaining = removeCharAt(str, i);
            // Recursively generate permutations of the remaining string
            List<String> subPermutations = generatePermutations(remaining);
            // Prepend the fixed character to each sub-permutation
            for (String subPerm : subPermutations) {
                permutations.add(fixedChar + subPerm);
            }
        }
        return permutations;
    }

    /**
     * Removes the character at a specified index from a string.
     * Avoids using String.substring() directly, using manual operations.
     *
     * @param str the source string
     * @param index position of character to remove
     * @return new string with the character at index removed
     */
    private static String removeCharAt(String str, int index) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i != index) {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }
}
