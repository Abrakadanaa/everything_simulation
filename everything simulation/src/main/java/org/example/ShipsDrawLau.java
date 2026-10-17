package org.example;

import codedraw.CodeDraw;

import java.awt.*;

public class ShipsDrawLau {
    public static void drawOcean(int[][] ocean) {
        try (CodeDraw codeDraw = new CodeDraw(400, 400)) {
            for (int i = 0; i < ocean.length; i++) {
                for (int j = 0; j < ocean[0].length; j++) {
                    int shipValue = ocean[i][j];
                    codeDraw.setColor(getColor(shipValue));
                    codeDraw.fillRectangle(
                            translateCoordinate(i),
                            translateCoordinate(j),
                            translateCoordinate(i+1)-1,
                            translateCoordinate(j+1)-1);
                    codeDraw.drawPoint(
                            translateCoordinate(i),
                            translateCoordinate(j));
                    if (shipValue > 0) {
                        codeDraw.setColor(Color.WHITE);
                        codeDraw.drawText(translateCoordinate(i), translateCoordinate(j), shipValue+"");
                    }
                }
            }
            codeDraw.show(100000);
        }
    }

    private static Color getColor(int shipValue) {
        return switch (shipValue) {
            case 0 -> Color.CYAN;
            case -1 -> Color.BLUE;
            default -> Color.RED;
        };
    }

    static double translateCoordinate(int v) {
        return v * 400.0 / 50;
    }
}
