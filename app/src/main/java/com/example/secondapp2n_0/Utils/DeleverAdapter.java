package  com.example.secondapp2n_0.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.secondapp2n_0.R;
import java.util.ArrayList;

import com.example.secondapp2n_0.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;


public class DeleverAdapter extends ArrayAdapter<String> {
    public DeleverAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
    /*ArrayList<String> arrayList;
    ArrayList<String> delevers;
    Context context;
    public DeleverAdapter(@NonNull Context context1, int resource, @NonNull List<String> objects, ArrayList<String> hashMap) {
        super(context1, resource, objects);
        arrayList=(ArrayList<String>) objects;
        delevers=hashMap;
        context=context1;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;


        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cheack_box, null);
            viewHolder.name = (CheckBox) convertView.findViewById(R.id.CheckBox);
            convertView.setTag(viewHolder);

            viewHolder.name.setOnClickListener( new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Toast.makeText(context,"Clicked on Row:sddfsdfffsfsddf ", Toast.LENGTH_LONG).show();
                }
            });

        }
        else {

            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.name.setText(arrayList.get(position));
        if (delevers.contains(arrayList.get(position)))
            viewHolder.name.setChecked(true);
        else viewHolder.name.setChecked(false);
        /*convertView.setClickable(true);
        convertView.setFocusable(true);
        convertView.setBackgroundResource(android.R.drawable.menuitem_background);
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int x;
            }

        });

        return convertView;

    }
    private class ViewHolder {
        CheckBox name;
    }
*/
}