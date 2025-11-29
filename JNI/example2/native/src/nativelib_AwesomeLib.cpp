#include "nativelib_AwesomeLib.h"
#include <iostream>
#include <ctime> 

JNIEXPORT void JNICALL Java_nativelib_AwesomeLib_nativeMethod
  (JNIEnv *, jobject){
  	std::cout << "This is C++ code!!" << std::endl;
  }


JNIEXPORT jint JNICALL Java_nativelib_AwesomeLib_getRandom
  (JNIEnv *, jobject){
  	std::srand(std::time(nullptr));
  	int randomValue = std::rand();
  	return (jint) randomValue; 
  }


JNIEXPORT void JNICALL Java_nativelib_AwesomeLib_printMatrix
  (JNIEnv* env, jobject, jobjectArray matrix) {

    std::cout << "C++ code: print jobjectArray:" << std::endl;

    jsize rows = env->GetArrayLength(matrix);

    for (jsize i = 0; i < rows; ++i) {

        jfloatArray row = (jfloatArray) env->GetObjectArrayElement(matrix, i);
        jsize cols = env->GetArrayLength(row);

        jfloat* data = env->GetFloatArrayElements(row, nullptr);

        for (jsize j = 0; j < cols; ++j) {
            std::cout << data[j] << (j + 1 < cols ? ", " : "");
        }
        std::cout << std::endl;

        env->ReleaseFloatArrayElements(row, data, 0);
        env->DeleteLocalRef(row);
    }
}


JNIEXPORT void JNICALL Java_nativelib_AwesomeLib_runJavaCodeNative
  (JNIEnv * env, jobject thisObject)
{

std::cout << "This is C++ code!!" << std::endl;

    jclass cls_awesome_lib = env -> GetObjectClass(thisObject);
    jmethodID mid_compare = env->GetMethodID(
        cls_awesome_lib,
        "printNativeResult",
        "(FI)V"
    );
    
    // call method
    env->CallVoidMethod(
        thisObject,
        mid_compare,
        2.0,
        3
    );

}


