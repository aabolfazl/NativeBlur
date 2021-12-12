/*
*
* Copyright (c) 2021 Abolfazl Abbasi
*
* */

#ifndef NATIVE_BLUR_SAMPLE_NATIVEBLUR_H
#define NATIVE_BLUR_SAMPLE_NATIVEBLUR_H

#include <jni.h>
#include <android/bitmap.h>
#include <algorithm>

#define SQUARE(i) ((i)*(i))
#define MAX(x, y) ((x) > (y)) ? (x) : (y)
#define MIN(x, y) ((x) < (y)) ? (x) : (y)

#define SUCCESS 1
#define INVALID_RADIUS -1
#define CAN_NOT_GET_BITMAP_INFO -2
#define INVALID_BITMAP_FORMAT -3
#define BITMAP_CONCURRENCY_ERROR -4

inline static void zeroClearInt(int *p, size_t count) {
    memset(p, 0, sizeof(int) * count);
}

#endif
