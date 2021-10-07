package unsw.crown;

import java.util.List;

/**
 * @author Braedon Wooding, and @Bunny DOng
 */
public interface CheckerStrategy {
    // what about rendering?
    // maybe some sort of 'renderSpecial' to add the crowns/duck icons.

    // this is more of an aggregation idea...
    void addValidPositions(Checkerboard board, Position position,
                                  List<Position> positions, Position nextPosition);

    // what about something like?
    // public void isValid(...);

    // is one better?  up to you how you wish to approach this...
}
