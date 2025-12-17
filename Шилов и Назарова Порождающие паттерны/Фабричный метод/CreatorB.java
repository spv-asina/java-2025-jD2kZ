class CreatorB extends Creator {
    @Override
    public Product createProduct() {
        return new ProductB();
    }
}