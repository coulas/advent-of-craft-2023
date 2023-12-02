package food;

import java.time.LocalDate;
import java.util.UUID;

public record Food(LocalDate expirationDate,
                   Boolean approvedForConsumption,
                   UUID inspectorId) {
    public boolean isEdible(LocalDate now) {
        if (approvedForConsumption) {
            if (hasBeenInspected()) {
                if (now.isBefore(expirationDate)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasBeenInspected() {
        return inspectorId != null;
    }
}