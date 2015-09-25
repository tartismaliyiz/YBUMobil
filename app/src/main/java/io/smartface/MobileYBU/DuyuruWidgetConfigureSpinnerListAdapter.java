package io.smartface.MobileYBU;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YILDIZ on 06.08.2015.
 */
public class DuyuruWidgetConfigureSpinnerListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;
    private int rowViewID;

    public DuyuruWidgetConfigureSpinnerListAdapter(Context context, int rowView, int textView, ArrayList<String> values) {
        super(context, rowView, textView, values);
        this.context = context;
        this.rowViewID = rowView;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(rowViewID, parent, false);
        TextView line1 = (TextView) rowView.findViewById(R.id.intervalDesc);
        line1.setText(values.get(position));
        return rowView;
    }

    @Override
    public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
        return getView(position, cnvtView, prnt);
    }


}