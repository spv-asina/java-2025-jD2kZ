public class BuilderExample {
    public static void main(String[] args) {
        WoodenHouseBuilder builder = new WoodenHouseBuilder();
        Director director = new Director();

        // Директор строит дома по стандартным планам
        House simpleHouse = director.buildSimpleHouse(builder);
        System.out.println("Простой дом: " + simpleHouse);

        // Нужно создать нового строителя, так как предыдущий уже использован
        WoodenHouseBuilder builderForLux = new WoodenHouseBuilder();
        House luxuryHouse = director.buildLuxuryHouse(builderForLux);
        System.out.println("Дом люкс: " + luxuryHouse);

        // Клиент может строить и без директора (своя сборка)
        System.out.println("\nCборка без директора:");
        WoodenHouseBuilder customBuilder = new WoodenHouseBuilder();
        customBuilder.buildWalls();
        customBuilder.buildDoors();
        // Пропускаем окна и крышу
        House customHouse = customBuilder.getHouse();
        System.out.println(customHouse);
    }
}