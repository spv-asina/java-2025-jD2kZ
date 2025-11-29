package nativelib;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hey! This is Java!");

        AwesomeLib nativeLib = new AwesomeLib();

        nativeLib.nativeMethod();

        int r = nativeLib.getRandom();           
		System.out.println(r);


        float[][] matrix = {{1.1f, 2.2f}, {3.3f, 4.4f}};
		nativeLib.printMatrix(matrix);

		nativeLib.runJavaCodeNative();
    }
}
