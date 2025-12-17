class Circle implements Shape {
    private int x;
    private int y;
    private String color;
    private int radius;

    public Circle(int x, int y, String color, int radius) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.radius = radius;
    }

    private Circle(Circle source) {
        this.x = source.x;
        this.y = source.y;
        this.color = source.color;
        this.radius = source.radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle(x=" + x + ", y=" + y +
                ", color=" + color + ", radius=" + radius + ")";
    }
}
