package com.example.alligator.listsearchrnd;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Alligator on 3/13/2018.
 */

public class CustomAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<Country> countrylist;
    ArrayList<Country> mStringFilterList;
    ValueFilter valueFilter;

    CustomAdapter(Context context, ArrayList<Country> countrylist) {
        this.context = context;
        this.countrylist = countrylist;
        mStringFilterList = countrylist;
    }

    @Override
    public int getCount() {
        return countrylist.size();
    }

    @Override
    public Object getItem(int position) {
        return countrylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return countrylist.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        convertView = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);

            TextView name_tv = (TextView) convertView.findViewById(R.id.name);
            TextView iso_tv = (TextView) convertView.findViewById(R.id.code);
            ImageView iv = (ImageView) convertView.findViewById(R.id.flag);

            Country country = countrylist.get(position);

            name_tv.setText(country.getName());
            iso_tv.setText(country.getIso_code());
            iv.setImageResource(country.getFlag());
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Country> filterList = new ArrayList<Country>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getName().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        Country country = new Country(mStringFilterList.get(i)
                                .getName(), mStringFilterList.get(i)
                                .getIso_code(), mStringFilterList.get(i)
                                .getFlag());

                        filterList.add(country);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            countrylist = (ArrayList<Country>) results.values;
            notifyDataSetChanged();
        }

    }

}