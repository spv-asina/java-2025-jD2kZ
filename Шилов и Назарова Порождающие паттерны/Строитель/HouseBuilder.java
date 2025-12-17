abstract class HouseBuilder {
    protected House house;

    public HouseBuilder() {
        this.house = new House();
    }

    public abstract void buildWalls();
    public abstract void buildDoors();
    public abstract void buildWindows();
    public abstract void buildRoof();

    // Необязательные шаги с реализацией по умолчанию
    public void buildPool() {
        house.setHasPool(false);
    }

    public void buildGarage() {
        house.setHasGarage(false);
    }

    public House getHouse() {
        return house;
    }
}
