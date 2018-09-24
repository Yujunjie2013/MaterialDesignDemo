# MaterialDesignDemo
MaterialDesignDemo使用

正常使用时需要导入Material包

implementation 'com.android.support:design:27.1.1'
CoordinatorLayout

CoordinatorLayout-->AppBarlayout-->CollapsingToolbarLayout

CoordinatorLayout是一个ViewGroup，必须放在布局的最外层，且他的第一个View必须要是AppBarLayout、如果要使用CollapsingToolbarLayout，则它必须是AppBarlayout的直接子View

AppBarLayout

AppBarLayout继承自LinearLayout，是一个垂直布局，AppbarLayout 严重依赖于CoordinatorLayout，必须用于CoordinatorLayout 的直接子View，如果你将AppbarLayout 放在其他的ViewGroup 里面，那么它的这些功能是无效的。

AppBarlayout子View的几种动作一共有5种，可以通过layout_scrollFlags来设置不同的动作

分别是 scroll,enterAlways,enterAlwaysCollapsed,exitUntilCollapsed,snap==。我们来分别看一下这五种动作的含义和效果。

1、scroll:该View跟随滚动View一起滚动

2、enterAlways:必须与scroll一起搭配使用；列如:scroll|enterAlways，当滚动View向下滚动时，该View将直接显示(知乎内容界面用的就是该效果)

3、enterAlwaysCollapsed: 必须与scroll、enterAlways一起配合使用；列如:scroll|enterAlways|enterAlwaysCollapsed，向上滚动时，会跟随View一起滚动并完全隐藏，当滚动View向下滚动时，该View将直接出现设置的最小高度，继续滚动到顶部后显示折叠部分；(知乎内容界面用的就是该效果)

4、exitUntilCollapsed:与enterAlwaysCollapsed最大的区别就是设置的最小高度始终都不会隐藏，滚动到顶部后会固定在顶部；必须与scroll一起配合使用

5、snap：滚动一半时如果停止滚动，折叠的View会根据距离决定是展开还是收缩,必须配合scroll一起使用，也可以搭配enterAlways、enterAlwaysCollapsed、和exitUntilCollapsed等一起使用
AppBarLayout还有几个重要的方法

1、addOnOffsetChangedListener 当appbarlayout的偏移发生改变时会回调

2、getTotalScrollRange 返回AppbarLayout 所有子View的滑动范围

3、removeOnOffsetChangedListener 移除监听器

4、setExpanded 设置appbarlayout是展开还是折叠
CollapsingToolbarLayout

CollapsingToolbarLayout继承自FrameLayout,如果要使用CollapsingToolbarLayout、则必须是AppBarLayout的直接子View

（1） Collapsing title(折叠标题) 当布局全部可见的时候，title 是最大的，当布局开始滑出屏幕，title 将变得越来越小，你可以通过setTitle（CharSequence） 来设置要显示的标题。

（2）Content scrim(内容纱布) 当CollapsingToolbarLayout滑动到一个确定的阀值时将显示或者隐藏内容纱布，可以通过setContentScrim(Drawable)来设置纱布的图片。

提醒：纱布可以是图片也可以是颜色色值，如果要显示颜色，在xml 布局文件中用contentScrim属性添加，代码如下：

app:contentScrim="@color/colorAccent"
Status bar scrim（状态栏纱布） 当CollapsingToolbarLayout滑动到一个确定的阀值时，状态栏显示或隐藏纱布，你可以通过setStatusBarScrim(Drawable)来设置纱布图片。

Parallax scrolling children（有视差地滚动子View） 让CollapsingToolbarLayout 的子View 可以有视差的滚动，需要在xml中用 添加如下代码：

<ImageView
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          android:background="@drawable/food"
          app:layout_collapseMode="parallax" />
完整代码：

<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <android.support.design.widget.CollapsingToolbarLayout
            android:layout_width="match_parent"
            android:layout_height="200dp"
            app:contentScrim="@color/colorAccent"
            app:layout_scrollFlags="scroll|exitUntilCollapsed|snap"
            app:statusBarScrim="@color/green"
            app:title="标题">

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="@drawable/food"
                app:layout_collapseMode="parallax" />

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolBar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:gravity="bottom"
                android:minHeight="?attr/actionBarSize" />
        </android.support.design.widget.CollapsingToolbarLayout>

    </android.support.design.widget.AppBarLayout>


    <android.support.v4.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/app_info"
            android:textSize="20sp" />
    </android.support.v4.widget.NestedScrollView>

</android.support.design.widget.CoordinatorLayout>
Behavior 介绍

看一下官方的介绍：Interaction behavior plugin for child views of CoordinatorLayout. 作用于CoordinatorLayout的子View的交互行为插件。一个Behavior 实现了用户的一个或者多个交互行为，它们可能包括拖拽、滑动、快滑或者其他一些手势。

Behavior 是一个顶层抽象类，其他的一些具体行为的Behavior 都是继承自这个类。它提供了几个重要的方法：

layoutDependsOn
onDependentViewChanged
onStartNestedScroll
onNestedPreScroll
onNestedScroll
onStopNestedScroll
onNestedScrollAccepted
onNestedPreFling
onStartNestedScroll
onLayoutChild
具体调用时机和使用说明如下:

/**
     * 表示是否给应用了Behavior 的View 指定一个依赖的布局，通常，当依赖的View 布局发生变化时
     * 不管被被依赖View 的顺序怎样，被依赖的View也会重新布局
     * @param parent
     * @param child 绑定behavior 的View
     * @param dependency   依赖的view
     * @return 如果child 是依赖的指定的View 返回true,否则返回false
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    /**
     * 当被依赖的View 状态（如：位置、大小）发生变化时，这个方法被调用
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    /**
     *  当coordinatorLayout 的子View试图开始嵌套滑动的时候被调用。当返回值为true的时候表明
     *  coordinatorLayout 充当nested scroll parent 处理这次滑动，需要注意的是只有当返回值为true
     *  的时候，Behavior 才能收到后面的一些nested scroll 事件回调（如：onNestedPreScroll、onNestedScroll等）
     *  这个方法有个重要的参数nestedScrollAxes，表明处理的滑动的方向。
     *
     * @param coordinatorLayout 和Behavior 绑定的View的父CoordinatorLayout
     * @param child  和Behavior 绑定的View
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes 嵌套滑动 应用的滑动方向，看 {@link ViewCompat#SCROLL_AXIS_HORIZONTAL},
     *                         {@link ViewCompat#SCROLL_AXIS_VERTICAL}
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    /**
     * 嵌套滚动发生之前被调用
     * 在nested scroll child 消费掉自己的滚动距离之前，嵌套滚动每次被nested scroll child
     * 更新都会调用onNestedPreScroll。注意有个重要的参数consumed，可以修改这个数组表示你消费
     * 了多少距离。假设用户滑动了100px,child 做了90px 的位移，你需要把 consumed［1］的值改成90，
     * 这样coordinatorLayout就能知道只处理剩下的10px的滚动。
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx  用户水平方向的滚动距离
     * @param dy  用户竖直方向的滚动距离
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    /**
     * 进行嵌套滚动时被调用
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed target 已经消费的x方向的距离
     * @param dyConsumed target 已经消费的y方向的距离
     * @param dxUnconsumed x 方向剩下的滚动距离
     * @param dyUnconsumed y 方向剩下的滚动距离
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    /**
     *  嵌套滚动结束时被调用，这是一个清除滚动状态等的好时机。
     * @param coordinatorLayout
     * @param child
     * @param target
     */
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    /**
     * onStartNestedScroll返回true才会触发这个方法，接受滚动处理后回调，可以在这个
     * 方法里做一些准备工作，如一些状态的重置等。
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     */
    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    /**
     * 用户松开手指并且会发生惯性动作之前调用，参数提供了速度信息，可以根据这些速度信息
     * 决定最终状态，比如滚动Header，是让Header处于展开状态还是折叠状态。返回true 表
     * 示消费了fling.
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param velocityX x 方向的速度
     * @param velocityY y 方向的速度
     * @return
     */
    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    //可以重写这个方法对子View 进行重新布局
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        return super.onLayoutChild(parent, child, layoutDirection);
    }
