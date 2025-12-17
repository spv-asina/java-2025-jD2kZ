class WoodenHouseBuilder extends HouseBuilder {
    @Override
    public void buildWalls() {
        house.setWalls("деревянные");
    }

    @Override
    public void buildDoors() {
        house.setDoors("деревянные");
    }

    @Override
    public void buildWindows() {
        house.setWindows("деревянные рамы");
    }

    @Override
    public void buildRoof() {
        house.setRoof("деревянная черепица");
    }

    @Override
    public void buildPool() {
        house.setHasPool(true); // В деревянных домах всегда есть бассейн
    }
}