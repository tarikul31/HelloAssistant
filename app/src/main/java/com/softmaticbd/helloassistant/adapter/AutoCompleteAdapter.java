package com.softmaticbd.helloassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.model.CountryItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter extends ArrayAdapter<CountryItem> {

    private List<CountryItem> countryItemList;

    public AutoCompleteAdapter(@NonNull Context context, @NonNull List<CountryItem> countryList) {
        super(context, 0, countryList);
        countryItemList = new ArrayList<>(countryList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return countryFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_auto_complete_row,parent,false
            );
        }
        TextView textView = convertView.findViewById(R.id.tvCountry);
        ImageView imageView = convertView.findViewById(R.id.ivCountry);
        CountryItem country = getItem(position);
        if (country !=  null){
            textView.setText(country.getCountryName());
            imageView.setImageResource(country.getCountryFlag());
        }

        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<CountryItem> suggestions = new ArrayList<>();
            if (charSequence == null || charSequence.length()==0){
                suggestions.addAll(countryItemList);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (CountryItem item : countryItemList){
                    if (item.getCountryName().toLowerCase().contains(filterPattern)){
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((CountryItem) resultValue).getCountryName();
        }
    };

}
