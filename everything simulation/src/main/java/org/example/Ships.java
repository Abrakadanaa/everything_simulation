package org.example;

public class Ships {

    public static void main(String[] args) {
        int[][] ocean = ocean_coordinates();
        throw_ships_into_ocean(ocean);
    }

    public static int[] shipyard() {
        //define ship details
        int[] ship = new int[3];
        int length = (int) (Math.random() * 8) + 1;
        int width = (int) (Math.random() * 2) + 1;
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
        boolean aye = true;
        return aye;
    }

    public static void throw_ships_into_ocean(int[][] ocean) {
        boolean keep_throwing_ships_at_the_ocean = true;
        int shipcounter = 1;
        while (keep_throwing_ships_at_the_ocean) {
            int[] ship = shipyard();
            int attempt = 0;
            boolean succesful = false;
            while (attempt < 10 && !succesful) {
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
                                    ocean[ship_coordinates[0] + i][ship_coordinates[1] + j] = shipcounter;
                                }
                                case 1 -> {
                                    ocean[ship_coordinates[0] - j][ship_coordinates[1] + i] = shipcounter;
                                }
                                case 2 -> {
                                    ocean[ship_coordinates[0] - i][ship_coordinates[1] - j] = shipcounter;
                                }
                                case 3 -> {
                                    ocean[ship_coordinates[0] + j][ship_coordinates[1] - i] = shipcounter;
                                }
                            }
                        }
                    }
                    System.out.println(ocean);
                    System.out.println(ship);
                    System.out.println(shipcounter);
                    //succesful = true;
                    //keep_throwing_ships_at_the_ocean = false;
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
