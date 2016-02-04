package com.naranya.demonpay.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naranya.demonpay.R;
import com.naranya.demonpay.adapters.ServiceNPayAdapter;
import com.naranya.demonpay.ui.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.npay.activity.NPay;
import io.npay.hub.services.OnServiceItemReceivedListener;
import io.npay.hub.services.ServiceItem;

/**
 * Created by anselmo on 2/3/16.
 */
public class ServiceFragment extends Fragment {
    private RecyclerView recycler;

    private ArrayList<ServiceItem> items;
    private ServiceNPayAdapter adapter;
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

        adapter = new ServiceNPayAdapter(getActivity(), items);
        recycler.setAdapter(adapter);

        OnServiceItemReceivedListener listen5 = new OnServiceItemReceivedListener() {
            @Override
            public void onServiceItemReceivedListener(List<ServiceItem> result) {
                try {
                    for (ServiceItem item : result) {
                        items.add( item );
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        };

        npay.setOnServiceItemReceivedListener(listen5);
        npay.getServices().getServices();

        return rootView;
    }
}