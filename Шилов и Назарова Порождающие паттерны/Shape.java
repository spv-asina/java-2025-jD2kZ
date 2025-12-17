interface Shape extends Cloneable {
    Shape clone();
    void setX(int x);
    void setY(int y);
    void setColor(String color);
    String toString();
}