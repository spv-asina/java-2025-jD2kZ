public class PrototypeExample {
    public static void main(String[] args) {

        ShapeRegistry registry = new ShapeRegistry();

        registry.register("red_small", new Circle(0, 0, "red", 10));
        registry.register("blue_big", new Circle(0, 0, "blue", 50));

        Circle c1 = (Circle) registry.create("red_small");
        c1.setX(10);
        c1.setY(20);

        Circle c2 = (Circle) registry.create("red_small");
        c2.setX(100);
        c2.setColor("green");

        Circle c3 = (Circle) registry.create("blue_big");

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}