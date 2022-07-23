# BANNERED

![Jit](https://img.shields.io/jitpack/v/github/kojofosu/Bannered?style=for-the-badge&color=2F9319) 

![Thumbnail](https://user-images.githubusercontent.com/20203694/180595325-1533e6b6-d8de-420d-adbf-9667206534f1.jpg)

## Setup

Add it in your root `build.gradle` at the end of repositories:

```groovy
allprojects {
    repositories {
        //...omitted for brevity
        maven { url 'https://jitpack.io' }
    }
}
```



Add the dependency

```groovy
dependencies {
   implementation "com.github.kojofosu:bannered:$latest_release"
}
```



## Usage

- Add `Bannered` view in your layout xml file

```xml
    <com.mcdev.bannered.Bannered
        android:id="@+id/banner_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:key="metrics banner"
        app:titleEnabled="true"
        app:title="Complete your profile"
        app:titleTextColor="@android:color/darker_gray"
        app:titleTextSize="20sp"
        app:description="Fill in your profile for a smoother experience. Get 3 reward keys after completing profile"
        app:descriptionTextColor="#2a2a2a"
        app:descriptionTextSize="15sp"
        app:dismissButtonEnabled="true"
        app:dismissButtonText="DISMISS"
        app:dismissButtonTextColor="#21ce99"
        android:layout_marginHorizontal="10dp"
        app:cardElevation="0dp"
        app:cardCornerRadius="15dp"
        app:strokeWidth="1dp"
        app:strokeColor="#21ce99"
        app:cardBackgroundColor="#EBFFF9"/>
```



#### Properties

| Attribute                    | Description                                                  |
| ---------------------------- | ------------------------------------------------------------ |
| `app:key`                    | `Required`: This is a unique string associated with a banner. |
| `app:titleEnabled`           | `true` or `false`: Display banner title                      |
| `app:title`                  | The banner's title                                           |
| `app:titleTextColor`         | The banner's title color                                     |
| `app:titlTextSize`           | The size of the banner's title                               |
| `app:description`            | The description text                                         |
| `app:descriptionTextColor`   | The description text's color                                 |
| `app:descriptionTextSize`    | The size of the description text                             |
| `app:dismissButtonEnabled`   | `true` or `false`: Display the dismiss button                |
| `app:dismissButtonText`      | Change the text displayed as the dismiss button. Default is `"DISMISS"` |
| `app:dismissButtonTextColor` | Dismiss button's text color                                  |
| `app:cardElavation`          | Add elevation to the banner                                  |
| `app:cardCornerRadius`       | The banner's corner radius                                   |
| `app:strokeWidth`            | The banner's border stroke size                              |
| `app:strokeColor`            | The border stroke's color                                    |
| `app:cardBackgroundColor`    | The banner's background color                                |


### Licensed under the [Apache-2.0 License](LICENSE)
