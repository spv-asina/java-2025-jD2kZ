abstract class Creator {
    public abstract Product createProduct();
    public void someOperation(String stuff) {
        Product product = createProduct();
        product.doStuff(stuff);
    }
}