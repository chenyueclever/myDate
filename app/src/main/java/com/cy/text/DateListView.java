package com.cy.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;


public class DateListView extends ListView {


    public DateListView(Context context) {
        super(context);
    }

    public DateListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DateListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }






    class DateAdapter extends BaseAdapter {
        ArrayList<DateList> list=new ArrayList();
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
//            ViewHold viewHold = new ViewHold();
//            if (convertView == null) {
//                convertView = View.inflate(MainActivity.this, R.layout.item_date_list, null);
//                viewHold.strName = (TextView) convertView.findViewById(R.id.txt);
//                viewHold.iDrawable = (ImageView) convertView.findViewById(R.id.img);
//                viewHold.button = (Button) convertView.findViewById(R.id.btn_del);
//                convertView.setTag(viewHold);
//            } else {
//                viewHold = (ViewHold) convertView.getTag();
//            }
//            viewHold.iDrawable.setImageResource((Integer) list.get(position).get("id"));
//            viewHold.strName.setText(list.get(position).get("person").toString());
//            viewHold.button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    list.remove(position);
//                    myAdapter.notifyDataSetChanged();
//                }
//            });
//            convertView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_UP:
//                            fSecx = event.getX();
//                            if ((fSecx - fFirstx) > 10) {
//                                ViewHold viewHold = (ViewHold) v.getTag();
//                                viewHold.button.setVisibility(Button.VISIBLE);
//                                viewHold.button.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        list.remove(position);
//                                        myAdapter.notifyDataSetChanged();
//                                    }
//                                });
//                            }
//                            break;
//                        case MotionEvent.ACTION_DOWN:
//                            fFirstx = event.getX();
//                            break;
//                        case MotionEvent.ACTION_MOVE:
//                            break;
//                    }
//                    return true;
//                }
//            });
            return convertView;
        }
    }
}
