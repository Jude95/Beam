# Beam——便捷的MVP开发框架  

Beam 是一套基于MVP模式的快速开发框架。定义了一套开发规范。并提供了基于这套规范的Activity，Fragment，Presenter，Model等父类及控件和API等，完成APP开发过程中大量繁琐工作。  
主要包含3部分：  
ui — Presenter与View层的双向注入。管理了Activity与Presenter的引用关系。让Presenter来控制Activity的显示。  
expansion — 包含了对ui层的一系列拓展功能。并提供了数据展示及数据列表展示的开发模版。  
model — 数据层，在APP启动时初始化所有model，并提供一个处理数据用的后台Looper线程。    

##使用  
`compile 'com.jude:beam:2.1.3'`

在你的自定义Application中加入 `Beam.init(this);`  
让你的Activity都继承于  
[`BeamAppCompatActivity`](https://github.com/Jude95/Beam/wiki/BeamAppCompatActivity&BeamFragment)  
[`BeamBaseActivity`](https://github.com/Jude95/Beam/wiki/BeamBaseActivity)  
[`BeamListActivity`](https://github.com/Jude95/Beam/wiki/BeamList)  
[`BeamDataActivity`](https://github.com/Jude95/Beam/wiki/BeamData)  
  

Fragment都继承于  
[`BeamFragment`](https://github.com/Jude95/Beam/wiki/BeamAppCompatActivity&BeamFragment)    
[`BeamListFragment`](https://github.com/Jude95/Beam/wiki/BeamList)  
中的一个。  

Presenter都继承于[`Presenter`](https://github.com/Jude95/Beam/wiki/Presenter)  
Model都继承于[`AbsModel`](https://github.com/Jude95/Beam/wiki/Model)  


##重复依赖
本库已经依赖了下面的库，请注意重复依赖的问题  
>
    compile 'com.android.support:appcompat-v7:22.2.1'
    compile 'com.jude:easyrecyclerview:3.0.6'
    compile 'com.afollestad:material-dialogs:0.7.5.5'
    compile 'io.reactivex:rxandroid:0.25.0'

##MVP模式
MVP模式在Android开发中的使用越来越流行，它十分适合Android。最好先看看[本框架对MVP的理解](https://github.com/Jude95/Beam/wiki/MVP%E6%A8%A1%E5%BC%8F)。
