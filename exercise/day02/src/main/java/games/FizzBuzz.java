package games;

public class FizzBuzz {
    private FizzBuzz() {
    }

    public static String convert(Integer input) {
        if (isOutOfFizzBuzzRange(input)) {
            throw new OutOfRangeException(input+" is not between 0 included and 100 excluded");
        }
        String result = ""; // start with an empty result
        result += appendWhenFizzing(input); // Note that I prefer when those 3 methods are inlined
        result += appendWhenBuzzing(input); // when code grows, it may end up like that incrementally build result in business methods
        result += appendSelfWhenEmpty(input, result); // but here inlined solution is less "clever", and easy to understand
        return result;
    }

    private static String appendWhenFizzing(Integer input) {
        if (input % 3 == 0) {
            return "Fizz";
        }
        return "";
    }

    private static String appendWhenBuzzing(Integer input) {
        if (input % 5 == 0) {
            return "Buzz";
        }
        return "";
    }

    private static String appendSelfWhenEmpty(Integer input, String result) {
        if (result.isBlank()) {
            return input.toString();
        }
        return "";
    }

    private static boolean isOutOfFizzBuzzRange(Integer input) {
        return input <= 0 || input > 100;
    }
}
