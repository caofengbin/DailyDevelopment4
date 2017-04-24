# DailyDevelopment4
该项目主要记录平时学习过程中的一些零碎知识点(2017年4月)，具体目录如下：

## 1.Android多线程基本使用

&emsp;&emsp;介绍Handle,Looper的基本使用方法。并给出一个**测试代码是否在主线程中运行的方法**，非常的实用：

``` java
boolean isUIThread = Looper.myLooper() == Looper.getMainLooper();
	if(isUIThread) {
		// 运行在UI线程
	} else {
		// 运行在子线程
	}
```

---

## 2.Service的最基本使用

最基本的三个生命周期方法：

> * (1) onCreate():Service创建时调用的；
> * (2) onStartCommand():每次启动服务时调用的；
> * (3) onDestroy():Service销毁时调用的；

&emsp;&emsp;通常情况下，如果我们希望服务一旦启动就立刻去执行某个动作，就可以将逻辑写在 onStartCommand()方法里。而当服务销毁时，我们又应该在onDestroy()方法中去回收那些不再使用的资源。

启动服务的代码：

``` java
Intent startIntent = new Intent(this, MyService.class);
startService(startIntent);
```

停止服务的代码：

``` java
Intent stopIntent = new Intent(this, MyService.class);
stopService(stopIntent);

```

&emsp;&emsp;**startService与stopService都是定义在Context中的方法，在Service的任何一个位置调用stopSelf()方法就能让这个服务停止下 。**


---