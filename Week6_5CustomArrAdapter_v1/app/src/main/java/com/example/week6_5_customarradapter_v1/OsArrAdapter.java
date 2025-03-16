/* LayoutInflator:	When we design using XML, all of our UI elements are just tags and
 * 					parameters. Before we can use these UI elements, (e.g., a TextView
 * 					or LinearLayout), we need to create the actual objects corresponding
 * 					to these XML elements. That is what the inflater is for. The inflater,
 * 					uses these tags and their corresponding parameters to create the actual
 * 					objects and set all the parameters. After this, one can get a reference
 * 					to the UI element using findViewById().
 * getView():		getView() is called every time an item in the list is drawn. Now, before
 * 					the item can be drawn, it has to be created. The convertView basically is the
 * 					last used view to draw an item. In getView() we inflate the XML first and
 * 					then use findByViewID() to get the various UI elements of the Listitem.
 * 					When we check for (convertView == null) what we do is check that if a
 * 					view is null(for the first item) then create it, else, if it already
 * 					exists, reuse it, no need to go through the inflate process again.
 * 					It makes it a lot more efficient.
 * parent:			The parent is a ViewGroup to which your view created by getView() is
 * 					finally attached. In our case this would be the ListView.
 */

package com.example.week6_5_customarradapter_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OsArrAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] values;
    private LayoutInflater inflater;

    public OsArrAdapter(@NonNull Context context, String[] values) {
        super(context, R.layout.list_item,values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item,parent,false);
        }
        ImageView iv = (ImageView) rowView.findViewById(R.id.logo);
        TextView tv = (TextView) rowView.findViewById(R.id.label);

        tv.setText(values[position]);
        String str = values[position];

        switch (str) {
            case "Microsoft":
                iv.setImageResource(R.drawable.mic_logo);
                break;
            case "IOS":
                iv.setImageResource(R.drawable.ios_logo);
                break;
            case "HarmonyOS":
                iv.setImageResource(R.drawable.hmhw_logo);
                break;
            default:
                iv.setImageResource(R.drawable.android_logo);
                break;
        }

        return rowView;
    }
}
