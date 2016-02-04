package com.naranya.demonpay.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naranya.demonpay.R;
import com.naranya.demonpay.adapters.CatalogNPayAdapter;
import com.naranya.demonpay.adapters.ServiceNPayAdapter;
import com.naranya.demonpay.ui.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import io.npay.activity.NPay;
import io.npay.catalog.NPayCatalogItem;
import io.npay.catalog.OnCatalogReceivedListener;
import io.npay.hub.services.ServiceItem;
import io.npay.resources.NPayConstants;

/**
 * Created by anselmo on 2/3/16.
 */
public class CatalogFragment extends Fragment {
    private RecyclerView recycler;

    private ArrayList<NPayCatalogItem> items;
    private CatalogNPayAdapter adapter;
    private NPay npay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout resource that'll be returned
        View rootView = inflater.inflate(R.layout.fragment_demo, container, false);

        npay = new NPay(getActivity());

        items = new ArrayList<>();

        recycler = (RecyclerView) rootView.findViewById(R.id.recycler);


        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        adapter = new CatalogNPayAdapter(getActivity(), items);
        recycler.setAdapter(adapter);

        //Implementation of the Listener that will get the items
        OnCatalogReceivedListener listen = new OnCatalogReceivedListener(){

            //Method that has to be overridden in order to use the received items
            @Override
            public void OnCatalogReceived(int responseType, List<NPayCatalogItem> result) {

                //We iterate all the received items
                for (NPayCatalogItem item : result) {
                    items.add( item );
                }

                adapter.notifyDataSetChanged();
            }
        };

        npay.setOnCatalogReceivedListener(listen);
        //Get the configured catalog
        npay.getNpayCatalog().getCatalog();

        return rootView;
    }
}