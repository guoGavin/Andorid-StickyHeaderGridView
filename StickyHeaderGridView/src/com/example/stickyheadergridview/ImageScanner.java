package com.example.stickyheadergridview;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

public class ImageScanner {
	private Context mContext;
	
	public ImageScanner(Context context){
		this.mContext = context;
	}
	
	/**
	 * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中
	 */
	public void scanImages(final ScanCompleteCallBack callback) {
		final Handler mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				callback.scanComplete((Cursor)msg.obj);
			}
		};
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = mContext.getContentResolver();

				// 只查询jpeg和png的图片
//				Cursor mCursor = mContentResolver.query(mImageUri, null,
//						MediaStore.Images.Media.MIME_TYPE + "=? or "
//								+ MediaStore.Images.Media.MIME_TYPE + "=?",
//						new String[] { "image/jpeg", "image/png" },
//						MediaStore.Images.Media.DATE_MODIFIED);
				
				
				Cursor mCursor = mContentResolver.query(mImageUri, null, null, null, null);
				
				//利用Handler通知调用线程
				Message msg = mHandler.obtainMessage();
				msg.obj = mCursor;
				mHandler.sendMessage(msg);
			}
		}).start();

	}
	
	
	public static interface ScanCompleteCallBack{
		public void scanComplete(Cursor cursor);
	}


}
