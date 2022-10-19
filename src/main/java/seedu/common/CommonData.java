package seedu.common;

import java.util.HashMap;

import seedu.data.LotType;

/**
 * Common shared data.
 */
public class CommonData {
    public static final String API_KEY_DEFAULT = "1B+7tBxzRNOtFbTxGcCiYA==";
    public static final HashMap<LotType, String> LOT_TYPE_TO_STRING = new HashMap<>() { {
            put(LotType.CAR, "Cars");
            put(LotType.MOTORCYCLE, "Motorcycles");
            put(LotType.HEAVY_VEHICLE, "Heavy Vehicles");
        }};
}