DividerLinerLayout
==================
Android3.0(API 11) 中 LinearLayout 增加了 divider ，可以设置分割线，但是不能在 3.0 以下使用。
此库中的 LinearLayout 兼容 3.0 以下，可以设置divider。属性名称与官方的一致。
<p>
   <img src="https://raw.githubusercontent.com/baoyongzhang/DividerLinearLayout/master/screenshot-1.png" width="320" alt="Screenshot"/>
</p>

```xml
<com.baoyz.widget.LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    app:divider="@drawable/divider"
    app:showDividers="middle|end" >

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="20dp"
        android:text="LenarLayout Divider"
        android:textSize="22sp" />

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="20dp"
        android:text="LenarLayout Divider"
        android:textSize="22sp" />

    ...

</com.baoyz.widget.LinearLayout>
```
