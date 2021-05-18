# <p align="center"> SliderPickerLibrary </p>

<img src="https://img.shields.io/badge/language-kotlin-orange.svg"/> <img src="https://img.shields.io/badge/platform-android-lightgrey.svg"/> [![](https://jitpack.io/v/tim91G/SliderPickerLibrary.svg)](https://jitpack.io/#tim91G/SliderPickerLibrary)

This library includes a custom `LayoutManager` for Android's Recyclerview to provide a slider-picker style user interface with a center focus. I took inspiration from [this](https://medium.com/@nbtk123/create-your-own-horizontal-vertical-slider-picker-android-94b6ee32b3ff) blogpost to develop this Layoutmanager library for Android's Recyclerview. This library provides some usefull features like:

* Snapping to a position when scrolling or flinging the recyclerview and put the item center in the recyclerview center
* Use both in horizontally or vertically orientation
* optional scaling effect to increase the size of centered items
* callback method which is called at a customizable interval to update your UI
* prevent an overscrolling issue when using smoothscroll by implementing a custom `LinearSmoothScroller`


## Download

**add jitpack**

Add the following code to the `build.gradle` file at the Project level

```groovy

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**add dependency**

Add the following code to the module level `build.gradle` file

```groovy

dependencies {
    implementation 'com.github.tim91G:SliderPickerLibrary:1.0.0'
}
```

## How to use

Using the library is fairly easy. add `import com.mittylabs.sliderpickerlibrary.SliderLayoutManager` to your Activity or fragment and then call the `SliderLayoutManager.Builder`. When you're done with the builder, call `.build()` and assign the result to your `recyclerView.layoutManager`. 

* Support for both horizontal or vertical orientation
    * `SliderLayoutManager.Builder(this, RecyclerView.HORIZONTAL)`
* add setOnScrollListener to provide a callback for the selected position with optional custom update interval
    * `.setOnScrollListener { index -> textView.text = index.toString() }`
    * `.setOnScrollListener(100) { ... }`
* This will set the initial index. (You need to handle rotations yourself)
    * `.setInitialIndex(0)`
* This will smoothscroll to the corresponding index without overshooting with smaller itemviews
    * `sliderLayoutManager.smoothScroll(20) `
* The library provides two center scalling effects. You can apply this by calling `setScaling` and provide either
    * `SliderLayoutManager.Scaling.Logarithmic(0.5f)` 
    * `SliderLayoutManager.Scaling.Linear`

## Example usage

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    ...

    val sliderLayoutManager = SliderLayoutManager.Builder(this, RecyclerView.HORIZONTAL)
        .setInitialIndex(0)
        .setOnScrollListener { index -> textView.text = index.toString() }
        .setScaling(SliderLayoutManager.Scaling.Logarithmic(0.5f))
        .build()

    recyclerView.adapter = MainAdapter().apply {
        onItemClick = { index ->
            sliderLayoutManager.smoothScroll(index) 
        }
    }
    
    recyclerView.layoutManager = sliderLayoutManager

    ...
}
```
