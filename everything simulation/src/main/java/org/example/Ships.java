package org.example;

public class Ships {

    public static void main(String[] args) {
        int[][] ocean = ocean_coordinates();
        throw_ships_into_ocean(ocean);
    }

    public static int[] shipyard() {
        //define ship details
        int[] ship = new int[3];
        int length = (int) (Math.random() * 15) + 1;
        int width = (int) (Math.random() * 4) + 1;
        int direction = (int) (Math.random() * 4);

        //fill array:
        ship[0] = length;
        ship[1] = width;
        ship[2] = direction;
        return ship;
    }

    public static int[] ship_coordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = (int) (Math.random() * 50);
        coordinates[1] = (int) (Math.random() * 50);
        return coordinates;
    }

    public static int[][] ocean_coordinates() {
        int[][] ocean = new int[50][50];
        return ocean;
    }

    public static boolean marineControl_says_aye(int[] ship, int[] ship_coordinates, int[][] ocean_coordinates) {
        int openWater = ocean_coordinates[ship_coordinates[0]][ship_coordinates[1]];
        int x = 0;
        int y = 1;
        int length = 0;
        int width = 1;
        int direction = 2;

        if (openWater != 0) {
            return false;
        }

        for (int i = 0; i <= ship[0]; i++) {
            for (int j = 0; j <= ship[1]; j++) {

                int is_x_safe = 0;
                int is_y_free = 0;

                switch (ship[direction]) {
                    case 0 -> {
                        is_x_safe = ship_coordinates[x] + i;
                        is_y_free = ship_coordinates[y] + j;
                    }
                    case 1 -> {
                        is_x_safe = ship_coordinates[x] - j;
                        is_y_free = ship_coordinates[y] + i;
                    }
                    case 2 -> {
                        is_x_safe = ship_coordinates[x] - i;
                        is_y_free = ship_coordinates[y] - j;
                    }
                    case 3 -> {
                        is_x_safe = ship_coordinates[x] + j;
                        is_y_free = ship_coordinates[y] - i;
                    }
                }

                if (is_x_safe < 0 || is_x_safe >= ocean_coordinates.length || is_y_free < 0 || is_y_free >= ocean_coordinates[0].length) {
                    return false;
                }

                if (ocean_coordinates[is_x_safe][is_y_free] != 0) {
                    return false;
                }

            }
        }

        /*

        //check if coordinates and length are okay:


        //0°:
        if (ship_coordinates[x] + ship[length] >= ocean_coordinates.length) {
            return false;
        }

        //90°:
        if (ship_coordinates[y] + ship[length] >= ocean_coordinates[0].length) {
            return false;
        }

        //180°:
        if (ship_coordinates[x] - ship[length] < 0) {
            return false;
        }

        //270°:
        if (ship_coordinates[y] - ship[length] < 0) {
            return false;
        }


        //check if coordinates and width are okay:
        //90°:
        if (ship_coordinates[x] - ship[width] < 0) {
            return false;
        }

        //0°:
        if (ship_coordinates[y] - ship[width] < 0) {
            return false;
        }

        //270°:
        if (ship_coordinates[x] + ship[width] >= ocean_coordinates.length) {
            return false;
        }

        //180°:
        if (ship_coordinates[y] + ship[width] >= ocean_coordinates[0].length) {
            return false;
        }

        switch (ship[direction]) {
            case 0 -> {
                if (ship_coordinates[x] + ship[length] >= ocean_coordinates.length || ship_coordinates[y] - ship[width] < 0) {
                    return false;
                }
            }
            case 1 -> {
                if (ship_coordinates[y] + ship[length] >= ocean_coordinates[0].length && ship_coordinates[x] - ship[width] < 0) {
                    return false;
                }
            }
            case 2 -> {
                if (ship_coordinates[x] - ship[length] < 0 && ship_coordinates[y] + ship[width] >= ocean_coordinates[0].length) {
                    return false;
                }
            }
            case 3 -> {
                if (ship_coordinates[y] - ship[length] < 0 && ship_coordinates[x] + ship[width] >= ocean_coordinates.length) {
                    return false;
                }
            }
        }

        for (int i = 0; i <= ship[0]; i++) {
            for (int j = 0; j <= ship[1]; j++) {
                if (ocean_coordinates[ship_coordinates[x]+i][ship_coordinates[y]+j] != 0) {
                    return false;
                }
            }
        } */ //trashcode
        return true;
    }

    public static void throw_ships_into_ocean(int[][] ocean) {
        boolean keep_throwing_ships_at_the_ocean = true;
        int shipcounter = 1;
        while (keep_throwing_ships_at_the_ocean) {
            int[] ship = shipyard();
            int attempt = 0;
            boolean successful = false;
            while (attempt < 15 && !successful) {
                int[] ship_coordinates = ship_coordinates(); // x,y
                boolean aye = marineControl_says_aye(ship, ship_coordinates, ocean);
                if (aye) {
                    for (int i = -1; i <= ship[0] + 1; i++) {
                        for (int j = -1; j <= ship[1] + 1; j++) {
                            switch (ship[2]) {
                                case 0 -> {
                                    out_of_bounds(ocean, ship_coordinates[0] + i, ship_coordinates[1] + j, -1);
                                }
                                case 1 -> {
                                    out_of_bounds(ocean, ship_coordinates[0] - j, ship_coordinates[1] + i, -1);
                                }
                                case 2 -> {
                                    out_of_bounds(ocean, ship_coordinates[0] - i, ship_coordinates[1] - j, -1);
                                }
                                case 3 -> {
                                    out_of_bounds(ocean, ship_coordinates[0] + j, ship_coordinates[1] - i, -1);
                                }
                            }
                        }
                    }
                    for (int i = 0; i <= ship[0]; i++) {
                        for (int j = 0; j <= ship[1]; j++) {
                            switch (ship[2]) {
                                case 0 -> {
                                    out_of_bounds(ocean, ship_coordinates[0] + i, ship_coordinates[1] + j, shipcounter);
                                }
                                case 1 -> {
                                    out_of_bounds(ocean, ship_coordinates[0] - j, ship_coordinates[1] + i, shipcounter);
                                }
                                case 2 -> {
                                    out_of_bounds(ocean, ship_coordinates[0] - i, ship_coordinates[1] - j, shipcounter);
                                }
                                case 3 -> {
                                    out_of_bounds(ocean, ship_coordinates[0] + j, ship_coordinates[1] - i, shipcounter);
                                }
                            }
                        }
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

    public static void out_of_bounds(int[][] ocean, int x, int y, int value) {
        if (x >= 0 && y >= 0 && x < ocean.length && y < ocean[0].length) {
            ocean[x][y] = value;
        }
    }
}
