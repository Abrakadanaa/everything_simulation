package org.example;

class Ship {
    int length;
    int width;

    Ship(int length, int width) {
        this.length = length;
        this.width = width;
    }

    static Ship carrier () {
        return new Ship(5,1);
    }
    static Ship battleShip () {
        return new Ship(4,1);
    }

    static Ship cruiser () {
        return new Ship(3,1);
    }

    static Ship submarine () {
        return new Ship(3,1);
    }

    static Ship destroyer () {
        return new Ship(2,1);
    }


}