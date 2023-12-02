package food;

import java.time.LocalDate;
import java.util.UUID;

public record Food(LocalDate expirationDate, boolean approvedForConsumption, UUID inspectorId) {
    public boolean isEdible(LocalDate now) {
        if (hasBeenInspected()) {
            if (approvedForConsumption) {
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