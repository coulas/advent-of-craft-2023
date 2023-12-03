package games;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FizzBuzzTests {
    @ParameterizedTest
    @MethodSource
    void returns_the_given_number_when_not_multiple_of_fizz_nor_buzz(Integer integer) {
        when_convert_input_then_result(integer, integer.toString());
    }

    static Stream<Integer> returns_the_given_number_when_not_multiple_of_fizz_nor_buzz() {
        return Stream.of(1, 2, // 3:fizz
                4, // 5:buzz
                7, 8, 11, 98 // highest in range
        );
    }

    @ParameterizedTest
    @MethodSource
    void returns_Fizz_for_multiple_of_3(Integer fizzer) {
        when_convert_input_then_result(fizzer, "Fizz");
    }


    static Stream<Integer> returns_Fizz_for_multiple_of_3() {
        return Stream.of(3, 2 * 3, 3 * 3, 4 * 3, // 5*3=15: also buzz
                11 * 3 * 3 // = 99 // highest in range
        );
    }

    @ParameterizedTest
    @MethodSource
    void returns_Buzz_for_multiple_of_5(Integer buzzer) {
        when_convert_input_then_result(buzzer, "Buzz");
    }

    static Stream<Integer> returns_Buzz_for_multiple_of_5() {
        return Stream.of(5, 2 * 5, // 3*5=15: also fizz,
                4 * 5, // 5*3=15: also buzz
                20 * 5 // = 2*2*5*5 (no fizz) = 100 highest in range
        );
    }

    @ParameterizedTest
    @MethodSource
    void returns_FizzBuzz_for_multiple_of_both_3_and_5(Integer fizzBuzzer) {
        when_convert_input_then_result(fizzBuzzer, "FizzBuzz");
    }

    static Stream<Integer> returns_FizzBuzz_for_multiple_of_both_3_and_5() {
        return Stream.of(3 * 5, 2 * 3 * 5,
                6 * 3 * 5 // 90: highest in range
        );
    }

    private static void when_convert_input_then_result(Integer input, String result) {
        assertThat(FizzBuzz.convert(input)).isEqualTo(result);
    }

    @ParameterizedTest
    @MethodSource
    void throws_an_exception_when_not_in_range(Integer outOfRange) {
        assertThatThrownBy(() -> FizzBuzz.convert(outOfRange))
                .isInstanceOf(OutOfRangeException.class)
                .hasMessageContaining(outOfRange.toString());
    }

    static Stream<Integer> throws_an_exception_when_not_in_range() {
        return Stream.of(-1, 0, 101);
    }
}
