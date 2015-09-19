package io.smartface.MobileYBU;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by YILDIZ on 06.08.2015.
 */
public class DuyuruWidgetConfigureListAdapter extends ArrayAdapter<JSONObject> {
    private final Context context;
    private final ArrayList<JSONObject> values;
    private int rowViewID;
    Boolean[] cbChecked = {false};
    int nOfElements;

    public DuyuruWidgetConfigureListAdapter(Context context, int rowView, ArrayList<JSONObject> values, String[] preCheck) {
        super(context, rowView, values);
        this.context = context;
        this.rowViewID = rowView;
        this.values = values;
        nOfElements = values.size();
        cbChecked = new Boolean[ nOfElements ];
        for(int i = 0;i<nOfElements;i++){
            try {
                if(preCheck != null && (preCheck[i] != null || preCheck[i] != "") && values.get(i).getString("sourceLink").equalsIgnoreCase(preCheck[i])){
                    cbChecked[i] = true;
                }else{
                    cbChecked[i] = false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }catch(ArrayIndexOutOfBoundsException e){
                cbChecked[i] = false;
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(rowViewID, parent, false);
        TextView line1 = (TextView) rowView.findViewById(R.id.line1);
        TextView line2 = (TextView) rowView.findViewById(R.id.line2);

        CheckBox cb = (CheckBox) rowView.findViewById(R.id.checkBox1);
        cb.setTag(position);
        cb.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v ;
                int position = (Integer) cb.getTag();
                cbChecked[position] = cb.isChecked();
                //Toast.makeText(getContext(),"Click on setOnClickListener() received",Toast.LENGTH_SHORT).show();
            }
        });
        rowView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) rowView.findViewById(R.id.checkBox1);
                cb.performClick();
            }
        });

        try {
            line1.setText(values.get(position).getString("line1"));
            line2.setText(values.get(position).getString("line2"));
            line2.setMovementMethod(new ScrollingMovementMethod());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cb.setChecked(cbChecked[position]);

        return rowView;
    }

    public int countSelectedCB() {
        int j = 0;
        for(int i = 0;i<nOfElements;i++) if(cbChecked[i]) j++;
        return j;
    }

    public String[] getCheckedItems() {
        String[] returnV = new String[this.countSelectedCB()];
        int j = 0;
        for(int i=0;i<this.countSelectedCB();i++){
            if(cbChecked[i]){
                try {
                    returnV[j] = values.get(i).getString("sourceLink");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                j++;
            }
        }
        return returnV;
    }
}