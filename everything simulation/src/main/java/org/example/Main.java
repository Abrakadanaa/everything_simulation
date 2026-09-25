package org.example;

import codedraw.CodeDraw;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("git gut test");
        int x = 0;
        int y = 0;
        try (CodeDraw cd = new CodeDraw(400, 400)) {
            cd.show();
            cd.setColor(new Color(0,0,100));
            cd.drawLine(10, 20, 20, 10);
            while (!cd.isClosed()) {
                cd.drawPoint(x,y);
                x++;
                if (x > 400) {
                    x = 0;
                    y++;
                }
                cd.show();
            }
        }
    }
}
