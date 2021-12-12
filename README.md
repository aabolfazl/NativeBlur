# Native-Blur

The Native-Blur is a C++/Kotlin libraray for blur bitmaps and activity, mobile-ready, android compatible,
powered by Java Native Interface(JNI) library for Android.
## Features
- Blur bitmap and resourses
- Blure activty
- Compress

## Usage
For blur the Activty:
```kotlin
val overlayBlurView = OverlayBlurView(activity)
overlayBlurView.show()
overlayBlurView.hide()
```
With Xml support view:
```xml
<me.abolfazl.nativeblur.BlurView
    android:layout_width="match_parent"
    android:layout_height="march_parent"
    android:scaleType="centerCrop"
    android:src="@drawable/spacex"
    app:compress="true"
    app:radius="20" />
```
Directly Bitmap:
```kotlin
val source = BitmapFactory.decodeResource(resources, R.drawable.spacex2)
val bitmap = NativeBlur.blurBitmap(source, 10, false)
imageview.setImageDrawable(BitmapDrawable(resources, bitmap))
```
<img src="./images/shot1.jpg" width=250 title="Shot">
<img src="./images/shot2.jpg" width=250 title="Shot">
<img src="./images/shot3.jpg" width=250 title="Shot">

## Developed By
- Abolfazl Abbasi [@0x40x4](https://twitter.com/0x40x4)
- Stack Blur Algorithm by Mario Klingemann
- Telegram open source project

SpaceX Image from [@Daniel Sanchez Photography](https://twitter.com/Daniel_S_Images)

## License
MIT

**Free Software developed for [Teknasyon Üçbüyücü Turnuvası!](https://ucbuyucuturnuvasi.com)**

