# SimpleFragments 
SimpleFragments is a library aimed at making flexible navigation easy on Android while removing unneccessary lifecycle complications. The library makes it easy to create map-based navigation, stack-based navigation or a combination of both. Back presses and rotation are handled.

# Download
Not available yet

# Usage
The library provides 4 main classes for you to utlize: The FragmentMapActivity, the FragmentMapFragment, the FragmentStackActivity, and the FragmentStackFragment. These are all designed to be "shell" components for you to put your fragments into. The fifth class is SimpleFragment. This class extends the Android Fragment and is what needs to be used for all your Fragments in the app. It provides a few convenience methods that will make your life easier.

## Maps
Your Activities and your Fragments can both host maps of SimpleFragments. If you want a map inside an Activity, then have your Activity extend FragmentMapActivity. If you want a map inside a Fragment, then have your Fragment extend FragmentMapActivity. 

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

## Stacks
Your Activities and Fragments can both host stacks of SimpleFragments. If you want a stack inside an Activity, then have your Activity extend FramentStackActivity


