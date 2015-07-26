# Beam——便捷的MVP开发框架  
MVP介绍参考[Android中的MVP](http://zhengxiaopeng.com/2015/02/06/Android%E4%B8%AD%E7%9A%84MVP/)与Google  
View与Presenter的部分是在[nucleus](https://github.com/konmik/nucleus)之上的修改。
主要对presenter的生命周期进行了补充。并修复部分bug。  
给Model层增加生命周期。
##依赖
`compile 'com.jude:beam:1.0.0'`

##Android的MVP缺陷
Activity，Fragment将view与业务逻辑结合的太紧密。所以将Activity作为Presenter还是View都不很合适。  

作为Presenter，主要是因为activity的生命周期，与各种Intent属于业务逻辑。  
列如某大神的方案[Android MVP - An Alternate Approach](http://blog.cainwong.com/android-mvp-an-alternate-approach/)  

作为View，主要是所有View生成都需要Context，ActionBar与Activity耦合度太高。  
现在的主流，我将生命周期移植到Presenter中来弥补这种方案的不足。

Adapter负有部分界面逻辑责任。但还是建议作为View层的内容。因为他的目的是接收数据解析生成UI展示。而不负责业务逻辑。
这样开发效率也更高。我在[EasyRecyclerView](https://github.com/Jude95/EasyRecyclerView)中将Adapter彻底封装为View。

##Nucleus
Nucleus的主要意图在于Activity在横竖屏切换,activity被回收的情况下，注定会再次启动。所以presenter不需要重新创建。  
Nucleus保持了Presenter的引用，需要手动调用`destroyPresenter()`Presnter才会被销毁。  
Activity在Bundle中存Presenter的Id，并在启动后依此寻找绑定以前的Presenter，没有则新建。  
只需互相写好泛型并给Activity写好注解，就可将Presenter与Activity双向注入。调用`getPresenter()``getView()`就可取得引用。

    @RequiresPresenter(MainPresenter.class)
    public class MainActivity extends NucleusAppCompatActivity<MainPresenter>
    
    public class MainPresenter extends Presenter<MainActivity>
  
不过很遗憾，这样双向注入就耦合的紧紧的。不能使用接口。也是一种缺陷吧。不过在开发中并没有多少影响。

##Presenter生命周期
Presenter增加如下生命周期：  
`void onCreate(Bundle savedState)`——presenter唯一一次初始化,在Activity的onCreate之前执行  
`void onCreateView(ViewType view)`——相当于Activity的onCreate但在它之后调用  
`void onTakeView(ViewType view)`——相当于Activity的onResume  
`void onDropView()`——相当于Activity的onPause  
`void onResult(int requestCode, int resultCode, Intent data)`——相当于Activity的onActivityResult  
`void onDestroy()`——相当于Activity的onDestroy  

建议这样写：

    public class TopicListPresenter extends BasePresenter<TopicListActivity> {
        private Topic[] topics;//持有数据
        
        @Override
        protected void onCreate(Bundle savedState) {
            super.onCreate(savedState);
            //在OnCreate里加载数据并保存一份，再设置给View，
            // 注意里是异步,运行到这里时Activity的onCreate还没有执行。但回调时Activity的onCreate一定已经执行完。View已初始化完毕。
            JobModel.getInstance().getTopicList(new DataCallback<Topic[]>() {
                @Override
                public void success(String info, Topic[] data) {
                    getView().addDataWithRefresh(topics = data);
                }
            });
        }
    
        @Override
        protected void onCreateView(TopicListActivity view) {
            super.onCreateView(view);
            //activity每次被创建就直接把数据设置给它。而不需要再去加载一边。因此不会在横竖屏切换时重复加载。
            getView().addDataWithRefresh(topics);
        }
    }

##Model
Model负责数据处理与提供。  
很多需要在应用启动时初始化。预加载，以提高后面的响应速度。  
本框架中提供了Application启动时的回调。  

先在Application的onCreate中调用：`Beam.init(Context ctx);`  
然后在AndroidManifest.xml中的Application节点下增加如下：

        <meta-data
            android:name="MODEL"
            android:value="
            com.jude.beamdome.PersonModel,
            com.jude.beamdome.CommonModel
            "
            />
这里注册自己的model，value里填完整包名。逗号分隔。启动时便会自动初始化这些model。

      public class CommonModel extends AbsModel {
          @Override
          protected void onAppCreate(Context ctx) {
              Log.i("test","这里是UI线程:"+Thread.currentThread().getName());
          }
      
          @Override
          protected void onAppCreateOnBackThread(Context ctx) {
              Log.i("test","这里是后台线程"+Thread.currentThread().getName());
          }
      }
  
  这里2个回调都是在APP启动时回调。后台线程也是一个Looper线程。在里面用回调也没问题。  
  可以在这2个方法中进行一些初始化。能在后台进行的初始化尽量放在后台，避免启动时间过长。  
  多个model安招注册时排列顺序初始化。并以同样顺序在同一后台线程进行后台线程初始化。  
  
  
