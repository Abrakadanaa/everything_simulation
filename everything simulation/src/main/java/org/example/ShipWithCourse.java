package org.example;

class ShipWithCourse {
    Ship ship;
    BasicCompassDirection direction;
    Vector2D position;

    ShipWithCourse(Ship ship, BasicCompassDirection direction, Vector2D position) {
        this.ship = ship;
        this.direction = direction;
        this.position = position;
    }

}