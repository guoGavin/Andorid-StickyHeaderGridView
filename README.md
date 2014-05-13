Andorid-StickyHeaderGridView
============================
异步加载图片，gridview和listview同样适用，本例用的是GridView来呈现。  
  在滑动过程成不会去加载，可用于网络加载，本地的加载。当滑动停止，则开启异步加载图片。
  
  

引用StickyListHeaders，git地址： [https://github.com/emilsjolander/StickyListHeaders](https://github.com/emilsjolander/StickyListHeaders)  
  
  引用ImageLoader ，git地址：[https://github.com/nostra13/Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader)  
  
  
    
      
        
##先看效果图：  
###-----滑动过程中的样式：  
![滑动中](http://1.valuesgithubimages.sinaapp.com/images/ss1.png)  
            
###-----滑动后的样式：  
![滑动后](http://1.valuesgithubimages.sinaapp.com/images/ss2.png)  
          
###-----挤压的样式：  
![挤压上面textview](http://1.valuesgithubimages.sinaapp.com/images/ss3.png)  
  
  
  
      public StickyGridAdapter(Context context, List<GridItem> list,
			    GridView mGridView, ImageLoader imageLoader,
		     	DisplayImageOptions options)   
      传入options，imageLoader。记得cacheInMemory(true)，要缓存在内存里哦。不然可能会出现闪屏的现象。
      
      
ImageScanner类用来获取手机上所有的图片信息，用ContentProvider来获取。  

具体的代码可以load下来看。注释都已经添加好了。  
  
  
分享给你的小伙伴吧。 - -   
