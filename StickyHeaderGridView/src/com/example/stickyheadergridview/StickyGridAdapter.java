package com.example.stickyheadergridview;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;

public class StickyGridAdapter extends BaseAdapter implements
		StickyGridHeadersSimpleAdapter {

	private List<GridItem> list;
	private LayoutInflater mInflater;
	private GridView mGridView;

	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	boolean isScrollStop = true;

	public StickyGridAdapter(Context context, List<GridItem> list,
			GridView mGridView, ImageLoader imageLoader,
			DisplayImageOptions options) {
		this.list = list;
		mInflater = LayoutInflater.from(context);
		this.mGridView = mGridView;
		this.imageLoader = imageLoader;
		this.options = options;
		this.mGridView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Log.v("onScrollStateChanged", "onScrollStateChanged");
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					isScrollStop = true;
					/** 此处请一定记得要更新，不然图片就没有了哦。。。 - - **/
					notifyDataSetChanged();
				} else {
					isScrollStop = false;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				Log.v("onScroll", "onScroll");
			}
		});
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mViewHolder;
		if (convertView == null) {
			mViewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.grid_item, parent, false);
			mViewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.grid_item);
			convertView.setTag(mViewHolder);

		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}

		String path = list.get(position).getPath();
		disPlayImage("file://" + path, mViewHolder.mImageView);

		return convertView;
	}

	private void disPlayImage(String path, ImageView imageView) {
		Log.v("isScroll", isScrollStop + "");
		imageView.setTag(path);
		/**
		 * 此处设置默认的加载图片还有点问题。正在解决中。。。
		 */
		imageView.setImageResource(R.drawable.ic_launcher);
		imageView.setBackgroundDrawable(new BitmapDrawable());

		if (isScrollStop) {
			imageLoader.displayImage(path, imageView, options);
		}
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		HeaderViewHolder mHeaderHolder;
		if (convertView == null) {
			mHeaderHolder = new HeaderViewHolder();
			convertView = mInflater.inflate(R.layout.header, parent, false);
			mHeaderHolder.mTextView = (TextView) convertView
					.findViewById(R.id.header);
			convertView.setTag(mHeaderHolder);
		} else {
			mHeaderHolder = (HeaderViewHolder) convertView.getTag();
		}

		mHeaderHolder.mTextView.setText(list.get(position).getTime());

		return convertView;
	}

	public static class ViewHolder {
		public ImageView mImageView;
	}

	public static class HeaderViewHolder {
		public TextView mTextView;
	}

	@Override
	public long getHeaderId(int position) {
		return list.get(position).getSection();
	}

}
