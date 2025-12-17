class CreatorA extends Creator {
    @Override
    public Product createProduct() {
        return new ProductA();
    }
}