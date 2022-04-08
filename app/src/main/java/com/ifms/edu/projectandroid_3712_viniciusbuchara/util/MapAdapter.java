package com.ifms.edu.projectandroid_3712_viniciusbuchara.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifms.edu.projectandroid_3712_viniciusbuchara.R;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapAdapter extends BaseAdapter {
    private Context context;
    private LinkedHashMap<String, Integer> spinnerItems;
    private String[] spinnerKeys;
    private LayoutInflater inflater;

    public MapAdapter(Context applicationContext, Map<String, Integer> spinnerItems) {
        this.context = applicationContext;
        this.spinnerItems = (LinkedHashMap) spinnerItems;
        this.spinnerKeys = spinnerItems.keySet().toArray(new String[spinnerItems.size()]);
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return spinnerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return spinnerItems.get(spinnerKeys[i]);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.custom_spinner_layout, null);

        TextView names = (TextView) view.findViewById(R.id.textView);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);

        names.setText(spinnerKeys[i]);
        icon.setImageResource(spinnerItems.get(spinnerKeys[i]));
        return view;
    }
}
