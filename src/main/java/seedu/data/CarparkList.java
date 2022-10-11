package seedu.data;

import java.nio.file.Path;
import java.util.List;

import seedu.exception.NoCarparkFoundException;
import seedu.exception.NoFileFoundException;
import seedu.files.FileReader;

/**
 * Container for all the {@link Carpark} classes. Contains method for finding the carpark.
 */
public class CarparkList {
    private final List<Carpark> carparks;

    public CarparkList(Path filepath, Path filepathBackup) throws NoFileFoundException {
        carparks = FileReader.loadLtaJson(filepath, filepathBackup);
    }

    /**
     * Finds carpark based on an exact string (case-insensitive) for the carpark ID.
     *
     * @param searchString string that should be matched to
     * @return returns the carpark with this unique ID
     * @throws NoCarparkFoundException If no carpark was found
     */
    public Carpark findCarpark(String searchString) throws NoCarparkFoundException {
        for (Carpark carpark : carparks) {
            if (carpark.getCarparkId().equalsIgnoreCase(searchString)) {
                return carpark;
            }
        }
        throw new NoCarparkFoundException("No carpark was found!");
    }
}