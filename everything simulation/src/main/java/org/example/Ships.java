package org.example;

public class Ships {

    public static void main(String[] args) {
        int[][] ocean = ocean_coordinates();
        throw_ships_into_ocean(ocean);


        ShipsDrawLau.drawOcean(ocean);

    }

    public static int[][] ocean_coordinates() {
        int[][] ocean = new int[50][50];
        return ocean;
    }

    public static boolean try_place_ship(int[][] ocean, Ship ship, int shipID) {
        Vector2D pos = new Vector2D((int) (Math.random() * 50), (int) (Math.random() * 50)); // x,y
        ShipWithCourse shipWithCourse = new ShipWithCourse(ship, BasicCompassDirection.fromInt((int) (Math.random() * 3)), pos);
        boolean aye = marineControl_says_aye(shipWithCourse, ocean);
        if (aye) {
            for (int i = 0; i < ship.length; i++) {
                for (int j = 0; j < ship.width; j++) {
                    drawShipZone(ocean, shipWithCourse.direction, shipWithCourse.position, i, j, shipID);
                }
            }
            for (int i = -1; i < ship.length + 1; i++) {
                drawShipZone(ocean, shipWithCourse.direction, shipWithCourse.position, i, -1, -1);
                drawShipZone(ocean, shipWithCourse.direction, shipWithCourse.position, i, ship.width, -1);
            }
            for (int j = 0; j < ship.width; j++) {
                drawShipZone(ocean, shipWithCourse.direction, shipWithCourse.position, -1, j, -1);
                drawShipZone(ocean, shipWithCourse.direction, shipWithCourse.position, ship.length, j, -1);
            }
        }
        return aye;
    }


    public static void throw_ships_into_ocean(int[][] ocean) {
        boolean keep_throwing_ships_at_the_ocean = true;
        int shipCounter = 1;
        while (keep_throwing_ships_at_the_ocean) {
            Ship ship = shipyard();
            int attempt = 0;
            boolean successful = false;
            while (attempt < 15 && !successful) {
                successful = try_place_ship(ocean, ship, shipCounter);
                if (successful) {
                    shipCounter++;
                }
                attempt++;
            }

            if (attempt >= 15) {
                keep_throwing_ships_at_the_ocean = false;
                System.out.println(shipCounter + " " + "ships sailing");
            }
        }
    }

    public void definitely_throw_ship_into_ocean(int[][] ocean, Ship ship, int shipID) {
        boolean success;
        do {
            success = try_place_ship(ocean, ship, shipID);
        } while (!success);
    }

    public static Ship shipyard() {

        //define ship details

        Ship ship = new Ship(((int) (Math.random() * 15) + 1), ((int) (Math.random() * 4) + 1));

        return ship;
    }

    public static boolean marineControl_says_aye(ShipWithCourse ship, int[][] ocean_coordinates) {
        //final int x = 0;
        //final int y = 1;

        for (int i = 0; i < ship.ship.length; i++) {
            for (int j = 0; j < ship.ship.width; j++) {

                int is_x_free = 0;
                int is_y_free = 0;

                switch (ship.direction) {
                    case EAST -> {
                        is_x_free = ship.position.x + i;
                        is_y_free = ship.position.y + j;
                    }
                    case NORTH -> {
                        is_x_free = ship.position.x - j;
                        is_y_free = ship.position.y + i;
                    }
                    case WEST -> {
                        is_x_free = ship.position.x - i;
                        is_y_free = ship.position.y - j;
                    }
                    case SOUTH -> {
                        is_x_free = ship.position.x + j;
                        is_y_free = ship.position.y - i;
                    }
                }

                if (is_x_free < 0
                        || is_x_free >= ocean_coordinates.length
                        || is_y_free < 0
                        || is_y_free >= ocean_coordinates[0].length) {
                    return false;
                }

                if (ocean_coordinates[is_x_free][is_y_free] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void drawShipZone(int[][] ocean, BasicCompassDirection direction, Vector2D base_coordinates, int i, int j, int value) {
        switch (direction) {
            case EAST -> {
                out_of_bounds(ocean, base_coordinates.x + i, base_coordinates.y + j, value);
            }
            case NORTH -> {
                out_of_bounds(ocean, base_coordinates.x - j, base_coordinates.y + i, value);
            }
            case WEST -> {
                out_of_bounds(ocean, base_coordinates.x - i, base_coordinates.y - j, value);
            }
            case SOUTH -> {
                out_of_bounds(ocean, base_coordinates.x + j, base_coordinates.y - i, value);
            }
        }
    }

    public static void out_of_bounds(int[][] ocean, int x, int y, int value) {
        if (x >= 0 && y >= 0 && x < ocean.length && y < ocean[0].length) {
            ocean[x][y] = value;
        }
    }


}
