package com.naranya.demonpay.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naranya.demonpay.R;
import com.naranya.demonpay.ui.ServiceDetailActivity;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.List;

import io.npay.hub.services.ServiceItem;

/**
 * Created by Naranya on 30/01/2016.
 */
public class ServiceNPayAdapter extends RecyclerView.Adapter<ServiceNPayAdapter.ViewHolder> {
    private Activity context;
    private List<ServiceItem> items;

    public ServiceNPayAdapter(Activity context, List<ServiceItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ServiceNPayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_service, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ServiceNPayAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ServiceItem service = items.get(position);

        //set Date
        viewHolder.name.setText( context.getString(R.string.service_name) + " " + String.valueOf(service.getName()));
        viewHolder.description.setText( context.getString(R.string.service_description) + " " + String.valueOf(service.getDescription()));
        viewHolder.country.setText( context.getString(R.string.service_country) + " " + String.valueOf(service.getHubDetails().getCountry()));
        viewHolder.carrier.setText( context.getString(R.string.service_carrier) + " " + String.valueOf(service.getHubDetails().getCarrier()));
        viewHolder.price.setText( context.getString(R.string.service_price) + " " + String.valueOf(service.getHubDetails().getAmount()));
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;
        public TextView country;
        public TextView carrier;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);

            name        = (TextView) itemView.findViewById(R.id.item_name);
            description = (TextView) itemView.findViewById(R.id.item_description);
            country     = (TextView) itemView.findViewById(R.id.item_country);
            carrier     = (TextView) itemView.findViewById(R.id.item_carrier);
            price       = (TextView) itemView.findViewById(R.id.item_price);
            
            name.setTypeface(EasyFonts.robotoLight(context));
            description.setTypeface(EasyFonts.robotoLight(context));
            country.setTypeface(EasyFonts.robotoLight(context));
            carrier.setTypeface(EasyFonts.robotoLight(context));
            price.setTypeface(EasyFonts.robotoLight(context));

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();

            Intent i = new Intent(context, ServiceDetailActivity.class);
            i.putExtra("id_service", items.get(position).getIdService());
            context.startActivity(i);
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return items.size();
    }
}