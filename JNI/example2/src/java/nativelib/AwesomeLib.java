package nativelib;

public class AwesomeLib {

    static {
        System.loadLibrary("nativeLib");
    }

    public native void nativeMethod();

	public native int getRandom();

	public native void printMatrix(float[][] matrix);


	 public void printNativeResult(float value1, int value2) {
        System.out.println(
          "Java code: value1: " + value1 + " value2: " + value2
        );
    }

	public native void runJavaCodeNative();

}
