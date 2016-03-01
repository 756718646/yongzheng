###Android M新控件之AppBarLayout，NavigationView，CoordinatorLayout，CollapsingToolbarLayout的使用


######AppBarLayout
AppBarLayout 是继承LinerLayout实现的一个ViewGroup容器组件，它是为了Material Design设计的App Bar，支持手势滑动操作。AppBarLayout是支持手势滑动效果的，不过的跟CoordinatorLayout配合使用


######CoordinatorLayout
从开发文档中可以了解到，CoordinatorLayout是一个增强型的FrameLayout。它的作用有两个

作为一个布局的根布局 最为一个为子视图之间相互协调手势效果的一个协调布局


###Android M新控件之FloatingActionButton，TextInputLayout，Snackbar，TabLayout的使用

资料地址:http://blog.csdn.net/feiduclear_up/article/details/46500865

http://blog.csdn.net/feiduclear_up/article/details/46514791


######FloatingActionButton
    <android.support.design.widget.FloatingActionButton
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@mipmap/emo_im_sad"
        android:clickable="true"
        android:layout_centerInParent="true"
        app:backgroundTint="#ff0000"
        app:elevation="10dp"
        app:fabSize="normal"
        />
        
        需要引用  compile 'com.android.support:design:23.2.0'
        
        elevation设置阴影
        backgroundTint设置背景颜色
        fabSize设置大小
        
######TextInputLayout

    在xml中使用，包裹EditText
    
    <android.support.design.widget.TextInputLayout
        android:id="@+id/textInput"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textColor="@android:color/black"/>

    </android.support.design.widget.TextInputLayout>
    
    代码中使用
    
     private void init() {
        final TextInputLayout inputLayout = (TextInputLayout) findViewById(R.id.textInput);
        inputLayout.setHint("请输入姓名:");

        EditText editText = inputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("onTextChanged:"+s.length());
                if (s.length()>4){
                    inputLayout.setErrorEnabled(true);
                    inputLayout.setError("姓名长度不能超过4个");
                }else{
                    inputLayout.setErrorEnabled(true);
                    inputLayout.setError("正确");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    
    TextInputLayout常用的方法有如下：

    setHint()：设置提示语。
    getEditText()：得到TextInputLayout中的EditView控件。
    setErrorEnabled():设置是否可以显示错误信息。
    setError()：设置当用户输入错误时弹出的错误信息。
    
    TextInputLayout不能单独使用，需要包裹EditView组件。
    
######Snackbar

Snackbar提供了一个介于Toast和AlertDialog之间轻量级控件，它可以很方便的提供消息的提示和动作反馈。


    @Override
    public void onClick(View v) {

        final Snackbar snackbar = Snackbar.make(but,"测试弹出提示",Snackbar.LENGTH_LONG);
        
        snackbar.show();
        
        snackbar.setAction("取消",new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

    }


设置Action行为事件，使用的方法是public Snackbar setAction (CharSequence text, View.OnClickListener listener);

Action的字体颜色默认使用系统主题中的如下颜色

<item name="colorAccent">#ff0000</item>

当然你可以通过代码去改变Action的字体颜色：Snackbar setActionTextColor (int color)；

######TabLayout

在布局上面使用

    TabLayout配合viewpager使用

    <android.support.design.widget.TabLayout
        android:id="@+id/tabs"
        app:tabSelectedTextColor="@android:color/holo_red_light"
        app:tabTextColor="@android:color/black"
        app:tabIndicatorColor="@android:color/holo_red_light"
        android:layout_width="match_parent"
        app:tabIndicatorHeight="1dp"
        android:layout_height="wrap_content"  />
        
    <android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    
    
    代码
    
     private void initTab(){

        viewPager = (ViewPager) super.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) super.findViewById(R.id.tabs);

        List<String> tabList = new ArrayList<>();
        tabList.add("Tab1");
        tabList.add("Tab2");
        tabList.add("Tab3");
        tabList.add("Tab4");
        tabList.add("Tab5");
        tabList.add("Tab6");
        tabList.add("Tab7");
        tabList.add("Tab8");
        tabList.add("Tab9");
        tabList.add("Tab10");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式

        for (int i=0;i<tabList.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(tabList.get(i)));//添加tab选项卡
        }


        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i <tabList.size(); i++) {
            Fragment f1 = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString("content", "xxxxxxx"+i);
            f1.setArguments(bundle);
            fragmentList.add(f1);
        }


        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器
    }
    
    Fragment类
    
    public class TabFragment extends Fragment{

    private String content;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        content = getArguments().getString("content");
        TextView tvContent = (TextView) view.findViewById(R.id.tv_tab_content);
        tvContent.setText(content + "");
    }

}

TabFragmentAdapter适配器类


public class TabFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;
    private List<String> mTitles;

    public TabFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

}


    
        





        
        
        
