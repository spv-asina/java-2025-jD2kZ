public class FactoryMethodExample {
    public static void main(String[] args) {
        Creator exampleA = new CreatorA();
        Creator exampleB = new CreatorB();

        exampleA.someOperation("конкретный продукт А");
        exampleB.someOperation("конкретный продукт В");

        System.out.println("\nДобавление нового продукта:");

        class ProductC implements Product {
            @Override
            public void doStuff(String stuff) {
                System.out.println(stuff);
            }
        }

        class CreatorC extends Creator {
            @Override
            public Product createProduct() {
                return new ProductC();
            }
        }

        Creator exampleC = new CreatorC();
        exampleC.someOperation("конкретный продукт С");
    }
}