class Director {
    public House buildSimpleHouse(HouseBuilder builder) {
        builder.buildWalls();
        builder.buildDoors();
        builder.buildWindows();
        builder.buildRoof();
        return builder.getHouse();
    }

    public House buildLuxuryHouse(HouseBuilder builder) {
        builder.buildWalls();
        builder.buildDoors();
        builder.buildWindows();
        builder.buildRoof();
        builder.buildPool();   // Дополнительная опция
        builder.buildGarage(); // Дополнительная опция
        return builder.getHouse();
    }
}
