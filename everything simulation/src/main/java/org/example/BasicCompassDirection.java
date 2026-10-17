package org.example;
// (ship length+width, position, direction)
public enum BasicCompassDirection {
    EAST,
    NORTH,
    WEST,
    SOUTH;

    public static BasicCompassDirection fromInt(int d) {
        return switch (d) {
            case 0 -> BasicCompassDirection.EAST;
            case 1 -> BasicCompassDirection.NORTH;
            case 2 -> BasicCompassDirection.WEST;
            case 3 -> BasicCompassDirection.SOUTH;
            default -> throw new IllegalArgumentException();
        };
    }
}
