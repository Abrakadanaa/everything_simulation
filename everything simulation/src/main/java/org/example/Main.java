package org.example;

import codedraw.CodeDraw;

public class Main {
    public static void main(String[] args) {
        System.out.println("git test");
        int x = 0;
        try (CodeDraw cd = new CodeDraw(400, 400)) {
            cd.show();
            while (!cd.isClosed()) {
                cd.clear();
                cd.drawPoint(x,4);
                x++;
                if (x > 400) {
                    x = 0;
                }
                cd.show(8);
            }
        }
    }
}
