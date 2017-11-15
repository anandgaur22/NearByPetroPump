package anandgaur.com.maptesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anand Gaur
 */

public class PumpPlaceAdapter extends ArrayAdapter<PumpDetail> {

    private final Context context;
    private final ArrayList<PumpDetail> itemsArrayList;

    public PumpPlaceAdapter(Context context, ArrayList<PumpDetail> itemsArrayList) {
        super(context, R.layout.pump_detail_list, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.pump_detail_list, parent, false);

        TextView placename = (TextView) rowView.findViewById(R.id.placename);
        placename.setText(itemsArrayList.get(position).getPlacename());

        double lat=itemsArrayList.get(position).getLat();
        double lng=itemsArrayList.get(position).getLongi();

        TextView address = (TextView) rowView.findViewById(R.id.address);
        address.setText(itemsArrayList.get(position).getPlacevicinity());

        TextView dist=(TextView)rowView.findViewById(R.id.distance);
        dist.setText(""+ itemsArrayList.get(position).getDistance()+" km");

        return rowView;
    }




}