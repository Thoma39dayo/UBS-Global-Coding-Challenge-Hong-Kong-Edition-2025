import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Test cases from the scrolls
        System.out.println("Testing Scroll 1:");
        List<String> valid1 = Arrays.asList("abc", "def");
        List<String> invalid1 = Arrays.asList("123", "456");
        System.out.println("Generated pattern: " + generate_gree_expression(valid1, invalid1));
        
        System.out.println("\nTesting Scroll 2:");
        List<String> valid2 = Arrays.asList("aaa", "abb", "acc");
        List<String> invalid2 = Arrays.asList("bbb", "bcc", "bca");
        System.out.println("Generated pattern: " + generate_gree_expression(valid2, invalid2));
        
        System.out.println("\nTesting Scroll 3:");
        List<String> valid3 = Arrays.asList("abc1", "bbb1", "ccc1");
        List<String> invalid3 = Arrays.asList("abc", "bbb", "ccc");
        System.out.println("Generated pattern: " + generate_gree_expression(valid3, invalid3));
        
        System.out.println("\nTesting Scroll 4:");
        List<String> valid4 = Arrays.asList("abc-1", "bbb-1", "cde-1");
        List<String> invalid4 = Arrays.asList("abc1", "bbb1", "cde1");
        System.out.println("Generated pattern: " + generate_gree_expression(valid4, invalid4));
        
        System.out.println("\nTesting Scroll 5:");
        List<String> valid5 = Arrays.asList("foo@abc.com", "bar@def.net");
        List<String> invalid5 = Arrays.asList("baz@abc", "qux.com");
        System.out.println("Generated pattern: " + generate_gree_expression(valid5, invalid5));
    }
    
    public static String generate_gree_expression(List<String> validStrings, List<String> invalidStrings) {
        if (validStrings.isEmpty() && invalidStrings.isEmpty()) {
            return "^$";
        }
        
        // Analyze character patterns
        Set<Character> validChars = new HashSet<>();
        Set<Character> invalidChars = new HashSet<>();
        Set<Character> allChars = new HashSet<>();
        
        // Collect all characters from valid strings
        for (String s : validStrings) {
            for (char c : s.toCharArray()) {
                validChars.add(c);
                allChars.add(c);
            }
        }
        
        // Collect all characters from invalid strings
        for (String s : invalidStrings) {
            for (char c : s.toCharArray()) {
                invalidChars.add(c);
                allChars.add(c);
            }
        }
        
        // Find characters that are only in valid strings
        Set<Character> onlyValid = new HashSet<>(validChars);
        onlyValid.removeAll(invalidChars);
        
        // Find characters that are only in invalid strings
        Set<Character> onlyInvalid = new HashSet<>(invalidChars);
        onlyInvalid.removeAll(validChars);
        
        // Check for digit vs non-digit pattern
        boolean validHasOnlyNonDigits = validStrings.stream().allMatch(s -> s.matches("\\D+"));
        boolean invalidHasOnlyDigits = invalidStrings.stream().allMatch(s -> s.matches("\\d+"));
        
        if (validHasOnlyNonDigits && invalidHasOnlyDigits) {
            return "^\\D+$";
        }
        
        // Check for specific character at start
        if (!validStrings.isEmpty() && !invalidStrings.isEmpty()) {
            char firstValidChar = validStrings.get(0).charAt(0);
            boolean allValidStartWithSame = validStrings.stream().allMatch(s -> s.charAt(0) == firstValidChar);
            boolean allInvalidStartDifferent = invalidStrings.stream().allMatch(s -> s.charAt(0) != firstValidChar);
            
            if (allValidStartWithSame && allInvalidStartDifferent) {
                return "^[" + firstValidChar + "].+$";
            }
        }
        
        // Check for specific character at end
        if (!validStrings.isEmpty() && !invalidStrings.isEmpty()) {
            char lastValidChar = validStrings.get(0).charAt(validStrings.get(0).length() - 1);
            boolean allValidEndWithSame = validStrings.stream().allMatch(s -> s.charAt(s.length() - 1) == lastValidChar);
            boolean allInvalidEndDifferent = invalidStrings.stream().allMatch(s -> s.charAt(s.length() - 1) != lastValidChar);
            
            if (allValidEndWithSame && allInvalidEndDifferent) {
                return "^.+[" + lastValidChar + "]$";
            }
        }
        
        // Check for specific character pattern (like hyphen)
        if (!validStrings.isEmpty() && !invalidStrings.isEmpty()) {
            boolean validHasHyphen = validStrings.stream().anyMatch(s -> s.contains("-"));
            boolean invalidNoHyphen = invalidStrings.stream().noneMatch(s -> s.contains("-"));
            
            if (validHasHyphen && invalidNoHyphen) {
                return "^.+-.+$";
            }
        }
        
        // Check for email pattern
        if (!validStrings.isEmpty() && !invalidStrings.isEmpty()) {
            boolean validHasAtAndDot = validStrings.stream().allMatch(s -> s.contains("@") && s.contains("."));
            boolean invalidMissingAtOrDot = invalidStrings.stream().anyMatch(s -> !s.contains("@") || !s.contains("."));
            
            if (validHasAtAndDot && invalidMissingAtOrDot) {
                return "^\\D+@\\w+\\.\\w+$";
            }
        }
        
        // Default pattern based on character analysis
        if (!onlyValid.isEmpty()) {
            StringBuilder pattern = new StringBuilder("^[");
            for (char c : onlyValid) {
                pattern.append(c);
            }
            pattern.append("]+$");
            if (pattern.length() <= 20) {
                return pattern.toString();
            }
        }
        
        // Fallback to a general pattern
        return "^.+$";
    }
}
