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

    public static void throw_ships_into_ocean(int[][] ocean) {
        boolean keep_throwing_ships_at_the_ocean = true;
        int shipcounter = 1;
        while (keep_throwing_ships_at_the_ocean) {
            Ship ship = shipyard();
            int attempt = 0;
            boolean successful = false;
            while (attempt < 15 && !successful) {
                int[] ship_coordinates = ship_coordinates(); // x,y
                boolean aye = marineControl_says_aye(ship, ship_coordinates, ocean);
                if (aye) {
                    for (int i = 0; i < ship.length; i++) {
                        for (int j = 0; j < ship.width; j++) {
                            drawShipZone(ocean, ship, ship_coordinates, i, j, shipcounter);
                        }
                    }
                    for (int i = -1; i < ship.length + 1; i++) {
                        drawShipZone(ocean, ship, ship_coordinates, i, -1, -1);
                        drawShipZone(ocean, ship, ship_coordinates, i, ship.width, -1);
                    }
                    for (int j = 0; j < ship.width; j++) {
                        drawShipZone(ocean, ship, ship_coordinates, -1, j, -1);
                        drawShipZone(ocean, ship, ship_coordinates, ship.length, j, -1);

                    }

                    successful = true;
                    if (successful) {
                        shipcounter++;
                    }
                }
                attempt++;
                if (attempt >= 15) {
                    keep_throwing_ships_at_the_ocean = false;
                    System.out.println(shipcounter + " " + "ships sailing");
                }
            }
        }
    }

    public static Ship shipyard() {

        //define ship details

        Ship ship = new Ship();
        ship.length = (int) (Math.random() * 15) + 1;
        ship.width = (int) (Math.random() * 4) + 1;
        ship.direction = (int) (Math.random() * 4);

        return ship;
    }

    public static int[] ship_coordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = (int) (Math.random() * 50);
        coordinates[1] = (int) (Math.random() * 50);
        return coordinates;
    }

    public static boolean marineControl_says_aye(Ship ship, int[] ship_coordinates, int[][] ocean_coordinates) {
        final int x = 0;
        final int y = 1;

        for (int i = 0; i < ship.length; i++) {
            for (int j = 0; j < ship.width; j++) {

                int is_x_free = 0;
                int is_y_free = 0;

                switch (ship.direction) {
                    case 0 -> {
                        is_x_free = ship_coordinates[x] + i;
                        is_y_free = ship_coordinates[y] + j;
                    }
                    case 1 -> {
                        is_x_free = ship_coordinates[x] - j;
                        is_y_free = ship_coordinates[y] + i;
                    }
                    case 2 -> {
                        is_x_free = ship_coordinates[x] - i;
                        is_y_free = ship_coordinates[y] - j;
                    }
                    case 3 -> {
                        is_x_free = ship_coordinates[x] + j;
                        is_y_free = ship_coordinates[y] - i;
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

    private static void drawShipZone(int[][] ocean, Ship ship, int[] ship_coordinates, int i, int j, int value) {
        switch (ship.direction) {
            case 0 -> {
                out_of_bounds(ocean, ship_coordinates[0] + i, ship_coordinates[1] + j, value);
            }
            case 1 -> {
                out_of_bounds(ocean, ship_coordinates[0] - j, ship_coordinates[1] + i, value);
            }
            case 2 -> {
                out_of_bounds(ocean, ship_coordinates[0] - i, ship_coordinates[1] - j, value);
            }
            case 3 -> {
                out_of_bounds(ocean, ship_coordinates[0] + j, ship_coordinates[1] - i, value);
            }
        }
    }

    public static void out_of_bounds(int[][] ocean, int x, int y, int value) {
        if (x >= 0 && y >= 0 && x < ocean.length && y < ocean[0].length) {
            ocean[x][y] = value;
        }
    }
}
