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

import io.npay.activity.NPay;
import io.npay.catalog.NPayCatalogItem;
import io.npay.hub.services.ServiceItem;
import io.npay.purchase_payment.NPayPurchasePayment;
import io.npay.purchase_payment.NPayResponseData;
import io.npay.purchase_payment.OnPurchaseDataListener;

/**
 * Created by Naranya on 30/01/2016.
 */
public class CatalogNPayAdapter extends RecyclerView.Adapter<CatalogNPayAdapter.ViewHolder> {
    private Activity context;
    private List<NPayCatalogItem> items;
    private OnPurchaseDataListener listenPurchase;
    private NPay npay;

    public CatalogNPayAdapter(Activity context, List<NPayCatalogItem> items) {
        this.context = context;
        this.items = items;

        npay = new NPay(context);

        listenPurchase = new OnPurchaseDataListener(){
            @Override
            public void onPurchaseDataReceive(NPayResponseData checkout) {

            }
        };

        //Set listener to receive the checkout response
        npay.setOnOnPurchaseDataListener(listenPurchase);

    }

    @Override
    public CatalogNPayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_catalog, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CatalogNPayAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        NPayCatalogItem catalog = items.get(position);

        //set Date
        viewHolder.id.setText( context.getString(R.string.id_item) + " " + catalog.getId_item());
        viewHolder.sku.setText( context.getString(R.string.sku_item) + " " + catalog.getSku());
        viewHolder.type.setText( context.getString(R.string.type_item) + " " + catalog.getI_type());
        viewHolder.name.setText( context.getString(R.string.name_item) + " " + catalog.getName());
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView id;
        public TextView sku;
        public TextView type;
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            id        = (TextView) itemView.findViewById(R.id.item_id_item);
            sku = (TextView) itemView.findViewById(R.id.item_sku);
            type     = (TextView) itemView.findViewById(R.id.item_type);
            name     = (TextView) itemView.findViewById(R.id.item_name);

            id.setTypeface(EasyFonts.robotoLight(context));
            sku.setTypeface(EasyFonts.robotoLight(context));
            type.setTypeface(EasyFonts.robotoLight(context));
            name.setTypeface(EasyFonts.robotoLight(context));

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            npay.requestPurchase( items.get(position).getSku() );
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return items.size();
    }
}