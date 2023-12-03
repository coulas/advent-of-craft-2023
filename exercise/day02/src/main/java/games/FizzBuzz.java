package games;

public class FizzBuzz {
    private FizzBuzz() {
    }

    public static String convert(Integer input) {
        if (isOutOfFizzBuzzRange(input)) {
            throw new OutOfRangeException(input+" is not between 0 included and 100 excluded");
        }
        String result = ""; // start with an empty result
        if (isFizzing(input)) {
            result += "Fizz";
        }
        if (isBuzzing(input)) {
            result += "Buzz";
        }
        if (result.isBlank()) {
            result = input.toString();
        }
        return result;
    }

    private static boolean isFizzing(Integer input) {
        return input % 3 == 0;
    }

    private static boolean isBuzzing(Integer input) {
        return input % 5 == 0;
    }

    private static boolean isOutOfFizzBuzzRange(Integer input) {
        return input <= 0 || input > 100;
    }
}
