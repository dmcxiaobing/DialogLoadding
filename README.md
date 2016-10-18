# DialogLoadding

作者：[程序员小冰](http://blog.csdn.net/qq_21376985)  [欢迎点击关注微博](http://weibo.com/mcxiaobing)

长期维护的Android项目，里面包括常用功能实现，以及知识点详解， 当然还有Java中的知识点。

具体请看github：`https://github.com/QQ986945193/DavidAndroidProjectTools`

首先大家都知道，当我们在app开发的时候，一般少不了，比如当请求网络数据时，肯定网速不好的时候，

加载的时候比较慢，所以如果此时已经进入了新的界面，那么肯定不好看，所以一般都会有一个loading等待提示框，

告诉用户正在加载中，给用户更好的体验。因此这就是我教大家实现的效果，先看一下效果图吧:

![这里写图片描述](http://img.blog.csdn.net/20161018111814019)

我这里使用了一个handler延迟两秒钟让它消失不见。当然项目中大家可以监听自己所需要的方法，使加载框消失。

这只是简单的两种实现方式，当然还有更好的其他效果，具体可以自己实现以下，也可以去网上搜索一下。

说主题吧，我这里只是讲一下思路，具体代码可以试着写一下，当然我在最后会给大家放源代码下载地址。

第一步，先写一个自定义dialog，代码也比较详细。就是创建一个dialog，如：

```
  public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v
                .findViewById(R.id.dialog_loading_view);// 加载布局
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        return loadingDialog;
    }
```
然后我们就可以看到返回的是dialog对象，我们在我们的类中调用即可。当然，有显示，就有关闭，我们直接将关闭的方法，也封装在自定义dialog中。

```
 /**
     * 关闭dialog
     *
     * @param mDialogUtils
     */
    public static void closeDialog(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }

```

然后我们进行调用即可。我们这里只是告诉大家实现的思路，因为涉及到了一些图片素材，

所以，我在这里提供一下这两种实现方式的源代码，所以大家可以参考一下。优化一下集中在项目中，

