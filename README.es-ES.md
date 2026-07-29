

[![](https://jitpack.io/v/aabolfazl/Native-blur.svg)](https://jitpack.io/#aabolfazl/Native-blur)

# Native-Blur

Native-Blur es una biblioteca C++/Kotlin para aplicar desenfoque (blur) a mapas de bits (bitmaps) y actividades, lista para móviles, compatible con Android, impulsada por la biblioteca Java Native Interface (JNI) para Android.

## Archivo .apk de la aplicación de demostración
[Descargar el archivo .apk desde aquí](https://github.com/aabolfazl/Native-Blur/raw/main/app-release_v1_0_0.apk)

## Características

- Desenfoque de bitmaps y recursos
- Desenfoque de actividades
- Compresión

## Uso

Para aplicar desenfoque a una Actividad:

```kotlin
val overlayBlurView = OverlayBlurView(activity)
overlayBlurView.show()
overlayBlurView.hide()
```

Con vista compatible con XML:

```xml
<me.abolfazl.nativeblur.BlurView
    android:layout_width="match_parent"
    android:layout_height="march_parent"
    android:scaleType="centerCrop"
    android:src="@drawable/spacex"
    app:compress="true"
    app:radius="20" />
```

Usando Bitmap directamente:

```kotlin
val source = BitmapFactory.decodeResource(resources, R.drawable.spacex2)
val bitmap = NativeBlur.blurBitmap(source, 10, false)
imageview.setImageDrawable(BitmapDrawable(resources, bitmap))
```

## Instalación

Agrega lo siguiente en tu archivo `build.gradle` raíz, al final de la sección de repositorios:

```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Añade la dependencia:

```gradle
dependencies { 
    implementation 'com.github.aabolfazl:Native-Blur:1.0.0'
}
```
## Imágenes
<img src="https://github.com/aabolfazl/Native-Blur/blob/main/images/shot1.jpg?raw=true" width=250 title="Shot"> <img src="https://github.com/aabolfazl/Native-Blur/blob/main/images/shot2.jpg?raw=true" width=250 title="Shot"> <img src="https://github.com/aabolfazl/Native-Blur/blob/main/images/shot3.jpg?raw=true" width=250 title="Shot">

## Benchmark
Asignación de memoria y tamaño límite del Heap para una aplicación (en un programa Java se lanza una excepción, pero en C++ se ejecuta correctamente)

<img src="https://github.com/aabolfazl/Native-Blur/blob/main/images/shot4.png?raw=true" width=700 title="Shot">

## Desarrollado por

- Abolfazl Abbasi [@0x40x4](https://twitter.com/0x40x4)

## Referencias
- Algoritmo Stack Blur de Mario Klingemann
- Proyecto de código abierto de Telegram

Imagen de SpaceX de [@Daniel Sanchez Photography](https://twitter.com/Daniel_S_Images)

## Licencia

MIT

**Software gratuito desarrollado para [Teknasyon Üçbüyücü Turnuvası!](https://ucbuyucuturnuvasi.com)**
