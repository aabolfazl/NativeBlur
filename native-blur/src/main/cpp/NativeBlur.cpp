//
// Created by AbolfazlAbbasi on 12/11/21.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_me_abolfazl_nativeblur_NativeBlur_checkCPPLinker(JNIEnv *env, jobject) {
    std::string hello = "checkCPPLinker Library Test String";
    return env->NewStringUTF(hello.c_str());
}