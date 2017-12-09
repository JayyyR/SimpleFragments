# SimpleFragments 
SimpleFragments is a library aimed at making flexible navigation easy on Android while removing unneccessary lifecycle complications. The library makes it easy to create map-based navigation, stack-based navigation or a combination of both. Back presses and rotation are handled.

# Download
Add JitPack repo in your root build.gradle at the end of repositories:
```java
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```
Add the dependency:
```java
dependencies {
	compile 'com.github.JayyyR:SimpleFragments:v0.4-alpha@aar'
}
```

# Usage
The library provides 4 main classes for you to utlize: The FragmentMapActivity, the FragmentMapFragment, the FragmentStackActivity, and the FragmentStackFragment. These are all designed to be "shell" components for you to put your fragments into. The fifth class is SimpleFragment. This class extends the Android Fragment and is what needs to be used for all your Fragments in the app. It provides a few convenience methods that will make your life easier.

## Maps
Your Activities and your Fragments can both host maps of SimpleFragments. If you want a map inside an Activity, then have your Activity extend FragmentMapActivity. If you want a map inside a Fragment, then have your Fragment extend FragmentMapFragment. 

The main method you need to worry about here is `showFragmentInMap`. `showFragmentInMap` takes in 3 arguments: the SimpleFragment you want to show in the map, the container id in your layout where you want to place your Fragment, and a unique tag for your Fragment. 


To show MyFragment1 in your map:
```java
showFragmentInMap(MyFragment1.newInstance(), R.id.fragment_container, UNIQUE_FRAGMENT_TAG_1);
```

To show MyFragment2 in your map:
```java
showFragmentInMap(MyFragment2.newInstance(), R.id.fragment_container, UNIQUE_FRAGMENT_TAG_2);
````

To show the _same_ MyFragment1 in your map that was shown before:
```java
showFragmentInMap(MyFragment1.newInstance(), R.id.fragment_container, UNIQUE_FRAGMENT_TAG_1);
```  
  
  
Because we passed in the same tag : "UNIQUE_FRAGMENT_TAG_1", our new instance of MyFragment1 is ignored and we show the same fragment. If we had passed in a different tag, a new instance of MyFragment1 would have been used.

There are multiple use cases for using maps including a bottom navigation paradigm. You can have your navigation menu below your Fragment container id. Pressing on a menu item will show a different fragment in the map. You can imagine the same use case for a side navigation paradigm.

Your FragmentMapActivities and FragmentMapFragments are always meant to be hosting at least one fragment. There should nothing visible to the user inside the fragment container layout. In other words, nothing visible to the user besides top level things like bottom menus/side navigation/toolbars etc.

## Stacks
Your Activities and Fragments can both host stacks of SimpleFragments. If you want a stack inside an Activity, then have your Activity extend FramentStackActivity. If you want a stack inside a Fragment, then have your Fragment extend FragmentStackFragment.

The main method you need to worry about here is `addFragmentToStack`. `addFragmentToStack` takes 4 arguments: The SimpleFragment you want to add to the stack, the container id in your layout you want to place your fragent and optional tags for the fragment and the backstack.

To add MyFragment1 to your stack:
```java
addFragmentToStack(MyFragment1.newInstance(), R.id.fragment_container, null, null);
```

To add MyFragment2 to your stack:
```java
addFragmentToStack(MyFragment2.newInstance(), R.id.fragment_container, null, null);
```

Now if you were to press back, MyFragment2 would pop and you would be back to MyFragment1.

Your FragmentStackActivities and FragmentStackFragments are always meant to be hosting at least one fragment. There should nothing visible to the user inside the fragment container layout.

## SimpleFragments

So what do you add to your stacks and maps? SimpleFragments! The `SimpleFragment` class extends the regular Android Support Fragment. It must always be a child of a FragmentMapActivity, FragmentMapFragment, FragmentStackActivity, or FragmentStackFragment. The SimpleFragment adds three extra convenience methods:

```java
onShown()
```
`onShown()` is kind of like `onResume()` but better. It will get called when your SimpleFragment gets added to your stack. It will get called when your SimpleFragment gets shown in your map. It will also get called when you resume the fragment from the background.

```java
onHidden()
```
`onHidden()` is kind of like `onPause()` but better. It will get called when your SimpleFragment gets removed from your stack. It will get called when your SimpleFragment gets hidden from your map (you show a different Fragment). It will also get called when you background the app.

```java
onSimpleBackPressed()
```
`onSimpleBackPressed()` is very simple. It gets called when you press the back key. It returns a boolean. Return true if the press was handled, false if it wasn't. If you return false, the back press will be propagated to the SimpleFragment's parent (whatever that may be).

The beauty here is `FragmentMapFragment` and `FragmentStackFragment` extend from `SimpleFragment` themselves. This means you can add FragmentMapFragments and FragmentStackFragments to a `FragmentMapActivity`, a `FragmentStackActivity`, or even a different `FragmentMapFragment` or a different `FragmentStackFragment` - just like you would any other SimpleFragment. You can have stacks within a map. You can have a map inside of a stack. You can have a stack inside a map inside a stack inside a map if you really want to. You don't have to get too complicated with it, but it's very flexible.

### Rotation
Not only are back presses handled for you, but rotation is as well. These are just Fragments after all. When you rotate, the state of your fragments will be saved. You can also pass things to the outstate in `onSaveInstanceState()` and retrieve them in `onCreate()` just like you would any other Fragment.

# Samples
There are two sample apps included here: `samplemapapp` is an example of an app with a map of fragment stacks. `samplestackapp` is an example of an app with a stack of fragments where one of the fragments is a map.

# License
 ```
 The MIT License (MIT)

Copyright (c) 2017 Joseph Acosta

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
