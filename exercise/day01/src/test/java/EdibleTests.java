import food.Food;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

class EdibleTests {
    private static final LocalDate expirationDate = LocalDate.of(2023, 12, 1);
    private static final UUID inspector = randomUUID();
    private static final LocalDate notFreshDate = expirationDate.plusDays(7);
    private static final LocalDate freshDate = expirationDate.minusDays(7);

    public static Stream<Arguments> notEdibleFood() {
        return Stream.of(
                Arguments.of("too late", true, inspector, notFreshDate),
                Arguments.of("not approved", false, inspector, freshDate),
                Arguments.of("not inspected", true, null, freshDate),
                Arguments.of("both not approved and not inspected", false, null, freshDate),
                Arguments.of("both not approved and too late", false, inspector, notFreshDate),
                Arguments.of("both not inspected and too late", true, null, notFreshDate),
                Arguments.of("all three not approved, not inspected and too late", false, null, notFreshDate)
        );
    }

    @ParameterizedTest
    @MethodSource("notEdibleFood")
    void not_edible_if_not_fresh(String cause, boolean approvedForConsumption, UUID inspectorId, LocalDate now) {
        var food = new Food(
                expirationDate,
                approvedForConsumption,
                inspectorId);
        assertThat(food.isEdible(now)).isFalse();
    }

    @Test
    void edible_food() {
        var food = new Food(
                expirationDate,
                true,
                inspector);
        assertThat(food.isEdible(freshDate)).isTrue();
    }
}