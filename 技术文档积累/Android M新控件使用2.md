###Android M新控件之AppBarLayout，NavigationView，CoordinatorLayout，CollapsingToolbarLayout的使用

资料:http://www.jcodecraeer.com/a/anzhuokaifa/developer/2015/0531/2958.html?mType=Group


CoordinatorLayout与app bar
CoordinatorLayout的另一个用例是 app bar（之前的actionbar）与 滚动技巧。你可能已经在自己的布局中使用了Toolbar ，它允许你更加自由的自定义其外观与布局的其余部分融为一体。Design library把这种设计带到了更高的水平：使用AppBarLayout可以让你的Toolbar与其他view（比如TabLayout的选项卡）能响应被标记了ScrollingViewBehavior的View的滚动事件。因此，你可以创建一个如下的布局：


CoordinatorLayout使用新的思路通过协调调度子布局的形式实现触摸影响布局的形式产生动画效果。

CoordinatorLayout通过设置子View的 Behaviors来调度子View。 

系统（Support V7）提供了AppBarLayout.Behavior, AppBarLayout.ScrollingViewBehavior, FloatingActionButton.Behavior, SwipeDismissBehavior<V extends View> 等。


######简单例子

<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">


    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end|bottom"
        android:layout_margin="16dp"
        android:src="@drawable/ic_done" />

</android.support.design.widget.CoordinatorLayout>


CoordinatorLayout作为“super-powered FrameLayout”，设置子视图的android:layout_gravity属性控制位置。

         
          

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view,"FAB", Snackbar.LENGTH_LONG)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件
                            }
                        })
                        .show();
            }
        });

FloatingActionButton是最简单的使用CoordinatorLayout的例子，FloatingActionButton默认使用FloatingActionButton.Behavior。



######列表滚到隐藏toolbar

效果显示，视图滚动时，Toolbar会隐藏，这个效果是Android Support Library里面,新增的CoordinatorLayout, AppBarLayout实现的。通过AppBarLayout的子视图的属性控制。观察AppBarLayout的子布局，Toobar有app:layout_scrollFlags属性，这就是控制滑动时视图效果的属性。app:layout_scrollFlags有四个值：

<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main_content"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
            app:layout_scrollFlags="scroll|enterAlways" />

        <android.support.design.widget.TabLayout
            android:id="@+id/tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </android.support.design.widget.AppBarLayout>

    <android.support.v4.view.ViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end|bottom"
        android:layout_margin="@dimen/fab_margin"
        android:src="@drawable/ic_done" />

</android.support.design.widget.CoordinatorLayout>

为了使得Toolbar有滑动效果，必须做到如下三点: 
1. CoordinatorLayout作为布局的父布局容器。 
2. 给需要滑动的组件设置 app:layout_scrollFlags=”scroll|enterAlways” 属性。 
3. 给滑动的组件设置app:layout_behavior属性
4. 那个viewpager里面的fragment是RecyclerView,listview不行


### 滚动新控件使用

参考资料:http://blog.csdn.net/huachao1001/article/details/51558835

对应的布局文件

内部的子View通过在布局中加app:layout_scrollFlags设置执行的动作，那么app:layout_scrollFlags可以设置哪些动作呢？分别如下：
（1） scroll:值设为scroll的View会跟随滚动事件一起发生移动。
什么意思呢？简单的说，就是当指定的ScrollView发生滚动时，该View也跟随一起滚动，就好像这个View也是属于这个ScrollView一样。

<android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?android:attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:layout_scrollFlags="scroll" />
</android.support.design.widget.AppBarLayout>


