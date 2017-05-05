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

## 3.Service的高级使用

本示例中给出了如下的几种使用方式：

> * bindService()的方法
> * unBindService()的方法
> * 使用前台进程的方法
> * **使用IntentService**的方法

---

## 4.OkHttp的基本使用

本示例中给出了如下的常见方式：

> * 使用OkHttp发送get()请求；
> * 使用OkHttp发送post键值对的请求；
> * 使用OkHttp发送post json数据的请求；
> * 使用OkHttp下载与长传文件；

通用的使用流程如下：

``` java
// 第一步；
OkHttpClient okHttpClient = new OkHttpClient();

// 2.构造request
Request.Builder builder = new Request.Builder();

final Request request = builder.get().url("https://api.douban.com/v2/book/search?q=%E7%A8%8B%E5%BA%8F%E5%91%98%E4%BF%AE%E7%82%BC%E4%B9%8B%E9%81%93--%E4%BB%8E%E5%B0%8F%E5%B7%A5%E5%88%B0%E4%B8%93%E5%AE%B6&fields=id,title,url").build();

// 3.将request封装为call,类似runnable,非常重要的一个步骤
        Call call = okHttpClient.newCall(request);

// 4.请求
//call.execute();

call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
          
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
      		
      });
}
```