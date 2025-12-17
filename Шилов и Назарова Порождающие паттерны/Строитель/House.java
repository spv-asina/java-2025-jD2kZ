import java.util.*;
class House {
    private String walls;
    private String doors;
    private String windows;
    private String roof;
    private boolean hasPool;
    private boolean hasGarage;

    public House() {
        this.hasPool = false;
        this.hasGarage = false;
    }

    // Setters (обычно package-private, чтобы их вызывал только Builder)
    void setWalls(String walls) { this.walls = walls; }
    void setDoors(String doors) { this.doors = doors; }
    void setWindows(String windows) { this.windows = windows; }
    void setRoof(String roof) { this.roof = roof; }
    void setHasPool(boolean hasPool) { this.hasPool = hasPool; }
    void setHasGarage(boolean hasGarage) { this.hasGarage = hasGarage; }

    @Override
    public String toString() {
        List<String> features = new ArrayList<>();
        if (walls != null) features.add("стены: " + walls);
        if (doors != null) features.add("двери: " + doors);
        if (windows != null) features.add("окна: " + windows);
        if (roof != null) features.add("крыша: " + roof);
        features.add("бассейн: " + (hasPool ? "есть" : "нет"));
        features.add("гараж: " + (hasGarage ? "есть" : "нет"));
        return "Дом(" + String.join(", ", features) + ")";
    }
}
