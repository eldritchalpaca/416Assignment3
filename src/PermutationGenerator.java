import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for generating all permutations of a given String.
 */
public class PermutationGenerator {
    public static void main(String[] args) {

        // Change this to your input string or input it as an argument
        String input = "ABC";

        // Get user input if available
        if (args.length >= 1) {
            input = args[0];
        }

        // Validate input
        if (!validateInput(input)) {
            System.out.println("Invalid input. Input must be a non-null, non-empty string.");
            return;
        }

        List<String> permutations = generatePermutations(input);
        System.out.println("Permutations of " + input + ":");
        for (String perm : permutations) {
            System.out.println(perm);
        }
    }

    /**
     * Returns a list of all permutations of the given string
     * @param str The given string
     * @return A list of all permutations of the given string
     * @throws IllegalArgumentException If the given string is not valid
     */
    public static List<String> generatePermutations(String str) throws IllegalArgumentException {

        if (!validateInput(str)) {
            throw new IllegalArgumentException();
        }

        // Initialize list with an empty string
        List<String> permutations = new ArrayList<>();
        permutations.add("");

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            List<String> newPermutations = new ArrayList<>();

            // For each permutation generated so far
            for (String perm : permutations) {
                // Insert currentChar into every possible position
                for (int j = 0; j <= perm.length(); j++) {
                    String newPerm = insertCharAt(perm, currentChar, j);
                    newPermutations.add(newPerm);
                }
            }
            // Update permutations list
            permutations = newPermutations;
        }
        return permutations;
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
     * Inserts a character into a string at a given index
     * @param str The given string
     * @param ch The character to be inserted
     * @param index The position the character should be inserted at
     * @return The string after the character has been inserted
     */
    private static String insertCharAt(String str, char ch, int index) {

        if (!validateInput(str)) {
            return "";
        }

        StringBuilder prefix = new StringBuilder();
        StringBuilder suffix = new StringBuilder();

        // Build prefix (substring before index)
        for (int i = 0; i < index; i++) {
            prefix.append(str.charAt(i));
        }
        // Build suffix (substring after index)
        for (int i = index; i < str.length(); i++) {
            suffix.append(str.charAt(i));
        }

        // Concatenate prefix + ch + suffix
        return prefix + Character.toString(ch) + suffix;
    }
}