#include <jni.h>
#include <string>

extern "C" JNIEXPORT jdouble JNICALL
Java_id_ac_ui_cs_mobileprogramming_farras_pokecarddemo_service_CounterService_increment(
        JNIEnv *env,
        jobject,
        jdouble x) {
    return x+1;
}