###Android M新控件之AppBarLayout，NavigationView，CoordinatorLayout，CollapsingToolbarLayout的使用

资料:http://www.jcodecraeer.com/a/anzhuokaifa/developer/2015/0531/2958.html?mType=Group


CoordinatorLayout与app bar
CoordinatorLayout的另一个用例是 app bar（之前的actionbar）与 滚动技巧。你可能已经在自己的布局中使用了Toolbar ，它允许你更加自由的自定义其外观与布局的其余部分融为一体。Design library把这种设计带到了更高的水平：使用AppBarLayout可以让你的Toolbar与其他view（比如TabLayout的选项卡）能响应被标记了ScrollingViewBehavior的View的滚动事件。因此，你可以创建一个如下的布局：


CoordinatorLayout使用新的思路通过协调调度子布局的形式实现触摸影响布局的形式产生动画效果。

CoordinatorLayout通过设置子View的 Behaviors来调度子View。 

系统（Support V7）提供了AppBarLayout.Behavior, AppBarLayout.ScrollingViewBehavior, FloatingActionButton.Behavior, SwipeDismissBehavior<V extends View> 等。
