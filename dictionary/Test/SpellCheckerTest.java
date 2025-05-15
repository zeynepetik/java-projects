package src.test;

import java.io.IOException;
import src.spellChecker.SpellChecker;
import src.structures.MyArrayList;

/**
 * A JUnit-style test framework for the SpellChecker
 */
public class SpellCheckerTest {
    private SpellChecker spellChecker;
    private int passedTests = 0;
    private int totalTests = 0;

    public static void main(String[] args) {
        try {
            SpellCheckerTest test = new SpellCheckerTest();
            test.setup();
            test.runAllTests();
            test.printSummary();
        } catch (Exception e) {
            System.err.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setup() throws IOException {
        System.out.println("Setting up test environment...");
        spellChecker = new SpellChecker("dictionary.txt");
        System.out.println("Test environment setup complete.");
    }

    public void runAllTests() {
        testSpellCheckCorrectWords();
        testSpellCheckIncorrectWords();
        testSuggestions();
        testPerformance();
        testEdgeCases();
    }

    public void testSpellCheckCorrectWords() {
        System.out.println("\n=== Testing correct words ===");
        
        String[] correctWords = {"hello", "world", "computer", "programming", "algorithm"};
        
        for (String word : correctWords) {
            totalTests++;
            boolean result = spellChecker.check(word);
            if (result) {
                System.out.println("✓ Correctly identified '" + word + "' as correctly spelled");
                passedTests++;
            } else {
                System.out.println("✗ Failed to identify '" + word + "' as correctly spelled");
            }
        }
    }

    public void testSpellCheckIncorrectWords() {
        System.out.println("\n=== Testing incorrect words ===");
        
        String[] incorrectWords = {"helo", "wrld", "compuuter", "programing", "algorthm"};
        
        for (String word : incorrectWords) {
            totalTests++;
            boolean result = spellChecker.check(word);
            if (!result) {
                System.out.println("✓ Correctly identified '" + word + "' as misspelled");
                passedTests++;
            } else {
                System.out.println("✗ Failed to identify '" + word + "' as misspelled");
            }
        }
    }

    public void testSuggestions() {
        System.out.println("\n=== Testing suggestions ===");
        
        // Test cases with expected suggestions
        String[][] testCases = {
            {"0e3o", "hello", "help", "held"}, // expected suggestions may vary based on dictionary
            {"pr9gr7mming", "programming"},
            {"compuuter", "computer"},
            {"algorthm", "algorithm"}
        };
        
        for (String[] testCase : testCases) {
            String misspelledWord = testCase[0];
            totalTests++;
            
            MyArrayList<String> suggestions = spellChecker.getSuggestions(misspelledWord);
            System.out.println("For misspelled word '" + misspelledWord + "', got suggestions:");
            
            boolean foundExpectedSuggestion = false;
            for (int i = 0; i < suggestions.size(); i++) {
                String suggestion = suggestions.get(i);
                System.out.println("  - " + suggestion);
                
                // Check if any of the expected suggestions is found
                for (int j = 1; j < testCase.length; j++) {
                    if (suggestion.equals(testCase[j])) {
                        foundExpectedSuggestion = true;
                        break;
                    }
                }
            }
            
            if (foundExpectedSuggestion) {
                System.out.println("✓ Found at least one expected suggestion for '" + misspelledWord + "'");
                passedTests++;
            } else {
                System.out.println("✗ No expected suggestions found for '" + misspelledWord + "'");
            }
        }
    }

    public void testPerformance() {
        System.out.println("\n=== Testing performance ===");
        
        String[] testWords = {
            "0e3o", "progr7mmming", "computr", "algritm", "documentation",
            "internationalization", "misisipi", "encyclopedia"
        };
        
        for (String word : testWords) {
            totalTests++;
            
            long startTime = System.nanoTime();
            boolean correct = spellChecker.check(word);
            
            if (!correct) {
                spellChecker.getSuggestions(word);
            }
            
            long endTime = System.nanoTime();
            double durationMs = (endTime - startTime) / 1000000.0;
            
            if (durationMs < 100) {
                System.out.printf("✓ Processing '%s' took %.2f ms (under 100ms requirement)\n", word, durationMs);
                passedTests++;
            } else {
                System.out.printf("✗ Processing '%s' took %.2f ms (exceeds 100ms requirement)\n", word, durationMs);
            }
        }
    }

    public void testEdgeCases() {
        System.out.println("\n=== Testing edge cases ===");
        
        // Test case 1: Empty string
        totalTests++;
        boolean emptyResult = spellChecker.check("");
        if (!emptyResult) {
            System.out.println("✓ Correctly handled empty string");
            passedTests++;
        } else {
            System.out.println("✗ Failed to handle empty string correctly");
        }
        
        // Test case 2: Null input
        totalTests++;
        try {
            spellChecker.check(null);
            System.out.println("✗ Failed to throw exception for null input");
        } catch (Exception e) {
            System.out.println("✓ Correctly threw exception for null input");
            passedTests++;
        }
        
        // Test case 3: Very long word
        totalTests++;
        String veryLongWord = "supercalifragilisticexpialidocious";
        long startTime = System.nanoTime();
        spellChecker.getSuggestions(veryLongWord);
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1000000.0;
        
        if (durationMs < 100) {
            System.out.printf("✓ Processing very long word took %.2f ms (under 100ms requirement)\n", durationMs);
            passedTests++;
        } else {
            System.out.printf("✗ Processing very long word took %.2f ms (exceeds 100ms requirement)\n", durationMs);
        }
    }

    public void printSummary() {
        System.out.println("\n=== Test Summary ===");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed tests: " + passedTests);
        System.out.println("Failed tests: " + (totalTests - passedTests));
        
        double passRate = (double) passedTests / totalTests * 100;
        System.out.printf("Pass rate: %.2f%%\n", passRate);
    }
}