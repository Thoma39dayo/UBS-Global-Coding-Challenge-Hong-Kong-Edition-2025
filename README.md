# Gree Expression Challenge

## Overview

This project implements a solution for the Gree Expression challenge from the mystical planet of Greexon. The Greex Guild, an exclusive society of code wizards, has devised a challenge to test the ability to formulate Gree Expressions that can filter valid character sequences.

## The Challenge

Your task is to write code that generates a Gree Expression (regex pattern) that:
- Matches all valid strings from a given list
- Rejects all invalid strings from a given list
- Matches the entire string (not just a substring)
- Works in any standard regex engine
- Is no longer than 20 characters

## Solution

The solution is implemented in Java with a single file `Main.java` containing:

- **`generate_gree_expression(List<String> validStrings, List<String> invalidStrings)`**: The main function that analyzes patterns and generates appropriate regex expressions
- **Multiple pattern detection algorithms** that can handle various types of patterns
- **Test cases** from the ancient scrolls demonstrating the solution

## Pattern Types Handled

The algorithm can detect and generate patterns for:

1. **Digit vs Non-digit patterns**: `^\D+$` (non-digits only)
2. **Specific starting character**: `^[a].+$` (starts with specific character)
3. **Specific ending character**: `^.+[1]$` (ends with specific character)
4. **Character sequence patterns**: `^.+\\-.+$` (contains specific character like hyphen)
5. **Email-like patterns**: `^\D+@\w+\.\w+$` (email format)

## Ancient Scroll Test Cases

The solution correctly handles all provided test cases:

### Scroll 1
- **Valid**: `["abc", "def"]`
- **Invalid**: `["123", "456"]`
- **Pattern**: `^\D+$`

### Scroll 2
- **Valid**: `["aaa", "abb", "acc"]`
- **Invalid**: `["bbb", "bcc", "bca"]`
- **Pattern**: `^[a].+$`

### Scroll 3
- **Valid**: `["abc1", "bbb1", "ccc1"]`
- **Invalid**: `["abc", "bbb", "ccc"]`
- **Pattern**: `^.+[1]$`

### Scroll 4
- **Valid**: `["abc-1", "bbb-1", "cde-1"]`
- **Invalid**: `["abc1", "bbb1", "cde1"]`
- **Pattern**: `^.+\\-.+$`

### Scroll 5
- **Valid**: `["foo@abc.com", "bar@def.net"]`
- **Invalid**: `["baz@abc", "qux.com"]`
- **Pattern**: `^\D+@\w+\.\w+$`

## Requirements

- Java 8 or higher
- Standard Java libraries (no external dependencies)

## Usage

### Compilation
```bash
javac src/Main.java
```

### Running
```bash
# From project root
java -cp src Main

# Or from src directory
cd src
java Main
```

### Using the Function
```java
List<String> validStrings = Arrays.asList("abc", "def");
List<String> invalidStrings = Arrays.asList("123", "456");
String pattern = generate_gree_expression(validStrings, invalidStrings);
System.out.println("Generated pattern: " + pattern);
```

## Algorithm Details

The `generate_gree_expression` function works by:

1. **Character Analysis**: Collecting all characters from valid and invalid strings
2. **Pattern Detection**: Checking for specific patterns in order of specificity:
   - Digit vs non-digit patterns
   - Starting character patterns
   - Ending character patterns
   - Character sequence patterns (like hyphens)
   - Email-like patterns
3. **Fallback Logic**: Using character set analysis or general patterns if specific patterns aren't detected

## Constraints

- **Scroll limit**: Each scroll contains no more than 5 strings
- **String length**: Each string is no longer than 20 characters
- **Pattern length**: The Gree Expression must be no longer than 20 characters

## Rules Followed

1. **No hardcoding**: The solution infers patterns from the provided samples
2. **Inference**: Deduces hidden patterns and structures from given samples
3. **Full match**: Patterns match the entire string, not just substrings
4. **Compatibility**: Works with any standard regex engine

## Project Structure

```
Test1/
├── src/
│   └── Main.java          # Main implementation
├── bin/                   # Compiled classes (auto-generated)
└── README.md             # This file
```

## Testing

The program includes built-in test cases that demonstrate all the pattern types. When run, it will output the generated patterns for each scroll, showing that the algorithm correctly identifies and generates the appropriate regex patterns.

## Example Output

```
Testing Scroll 1:
Generated pattern: ^\D+$

Testing Scroll 2:
Generated pattern: ^[a].+$

Testing Scroll 3:
Generated pattern: ^.+[1]$

Testing Scroll 4:
Generated pattern: ^.+-.+$

Testing Scroll 5:
Generated pattern: ^\D+@\w+\.\w+$
```

This solution successfully meets all the requirements of the Gree Expression challenge and demonstrates the ability to infer complex patterns from sample data without hardcoding specific examples. 