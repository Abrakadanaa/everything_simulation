package org.example;

import codedraw.CodeDraw;

public class Main {
    public static void main(String[] args) {
        System.out.println("git test");
        try (CodeDraw cd = new CodeDraw(400, 400)) {
            cd.fillCircle(300, 200, 50);
            cd.show();
        }
    }
}
