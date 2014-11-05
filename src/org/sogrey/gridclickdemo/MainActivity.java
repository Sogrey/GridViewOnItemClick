/**
 * 
 */
package org.sogrey.gridclickdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * @author Administrator
 * 
 */
public class MainActivity extends Activity implements OnItemClickListener {

	GridView gv;
	ArrayList<String> mList;
	myAdapter mMyAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initDatas();
	}

	private void initViews() {
		gv = (GridView) findViewById(R.id.gv);
		gv.setOnItemClickListener(this);
	}

	private void initDatas() {
		mList = new ArrayList<String>();
		for (int i = 1; i < 19; i++) {
			String str = "第" + i + "个按钮";
			mList.add(str);
		}
		mMyAdapter = new myAdapter(this, mList);
		gv.setAdapter(mMyAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// 设置适配器
		mMyAdapter.setSeclection(position);
		mMyAdapter.notifyDataSetChanged();
	}

	class myAdapter extends BaseAdapter {
		ArrayList<String> list;
		Context mContext;

		public myAdapter(Context context, ArrayList<String> mList) {
			list = mList;
			mContext = context;
		}

		private int clickTemp = -1;

		// 标识选择的Item
		public void setSeclection(int position) {
			clickTemp = position;
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
			return list.get(position).hashCode();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			Viewholder holder = null;
			if (view != null) {
				holder = (Viewholder) view.getTag();
			} else {
				holder = new Viewholder();
				view = ((Activity) mContext).getLayoutInflater().inflate(
						R.layout.item, null);
				holder.txt = (TextView) view.findViewById(R.id.txt);
				view.setTag(holder);
			}
			holder.txt.setText(list.get(position));
			if (clickTemp == position) {
				view.setBackgroundColor(Color.GREEN);
			} else {
				view.setBackgroundColor(Color.TRANSPARENT);
			}
			return view;
		}

	}

	class Viewholder {
		TextView txt;
	}
}
