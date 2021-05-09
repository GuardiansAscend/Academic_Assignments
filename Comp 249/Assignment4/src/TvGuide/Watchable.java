// -----------------------------------------------------
// Part: 2
// Written by: Kevin Marnet Scanlan 40175644
// -----------------------------------------------------
package TvGuide;

/**
 * Watchable interface, to make sure that the TVshow class implements an important method.
 * @author Kevin Marnet Scanlan
 */
public interface Watchable {

    /**
     * Method that calculates whether 2 TVshow happen at the same time.
     * @param s The TVshow being compared to
     * @return The result of the time comparison
     */
    public String isOnSameTime(TVshow s);
}
