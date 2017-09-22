package com.accenture.accenturetairningdemo.adepter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.accenture.accenturetairningdemo.R;
import com.accenture.accenturetairningdemo.model.Person;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/21.
 */

public class ContactAdepter extends BaseAdapter {
    Context context;
    ArrayList<Person> personList;
    Handler handler;
    private LayoutInflater mInflater;

    public ContactAdepter(Context context, ArrayList<Person> personList, Handler handler) {
        this.context = context;
        this.personList = personList;
        this.handler = handler;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int i) {
        return personList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.contact_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.photo.setImageBitmap(returnBitMap(personList.get(i).getImg()));
        viewHolder.name.setText(this.personList.get(i).getName());
        viewHolder.number.setText(this.personList.get(i).getPhone());
        String url = personList.get(i).getImg();
        Glide.with(this.context).load(url).into(viewHolder.photo);
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "button click", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    public Bitmap returnBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
            Log.e("url", url);
        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return bitmap;
    }

    static class ViewHolder {
        @BindView(R.id.photo)
        ImageView photo;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.btn)
        Button btn;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
