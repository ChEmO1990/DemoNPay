package com.naranya.demonpay.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.naranya.demonpay.R;
import com.naranya.demonpay.adapters.ServiceNPayAdapter;
import com.naranya.demonpay.db.DataBaseHelper;
import com.naranya.demonpay.db.Querys;
import com.naranya.demonpay.models.CatalogNPay;
import com.naranya.demonpay.ui.widget.DividerItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.npay.activity.NPay;
import io.npay.hub.services.OnServiceItemReceivedListener;
import io.npay.hub.services.ServiceItem;

public class HomeActivity extends BaseActivity {
    @Bind(R.id.recyclerHome)
    RecyclerView recyclerHome;

    private ArrayList<ServiceItem> items;
    private ServiceNPayAdapter adapter;
    private NPay npay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        npay = new NPay(this);

        Toolbar toolbar = getToolbar();
        toolbar.setTitle(getString(R.string.activity_home));

        items = new ArrayList<>();

        recyclerHome.setLayoutManager(new LinearLayoutManager(this));
        recyclerHome.setHasFixedSize(true);
        recyclerHome.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        adapter = new ServiceNPayAdapter(this, items);
        recyclerHome.setAdapter(adapter);

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
    }
}