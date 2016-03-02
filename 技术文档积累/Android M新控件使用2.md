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

