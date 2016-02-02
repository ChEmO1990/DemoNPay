package com.naranya.demonpay.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.naranya.demonpay.R;
import com.naranya.demonpay.db.Querys;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.npay.activity.NPay;
import io.npay.hub.language.LanguageHelper;
import io.npay.hub_service_information.OnServiceInformationReceivedListener;
import io.npay.hub_service_information.ServiceInformationItem;

public class DetailSubscriptionActivity extends BaseActivity {
    @Bind(R.id.txtStatus)
    TextView textStatus;

    @Bind(R.id.lbl_detail_subscription)
    TextView textDetail;

    private Toolbar toolbar;
    private NPay npay;
    private String id_service;

    private OnServiceInformationReceivedListener listenInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_subscription);
        ButterKnife.bind(this);

        toolbar = getToolbar();
        toolbar.setTitle(R.string.activity_subscription);
        toolbar.setNavigationIcon(R.mipmap.ic_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_service = extras.getString("id_service");
            Log.i("ID_SERVICE", id_service);
        }

        npay = new NPay(this);


        listenInfo = new OnServiceInformationReceivedListener() {
            @Override
            public void onServiceInformationReceivedListener(ServiceInformationItem result) {
                try {
                    String information =
                            "Información detallada" + "\n" + "\n" +
                                    "Objeto: " + result.getObject() + "\n" +
                                    "Creado: " + result.getCreated() + "\n" +
                                    "Primer cargo: " + result.getFirstCharge() + "\n" +
                                    "Siguiente cargo: " + result.getNextCharge() + "\n" +
                                    "Cliente ID: " + result.getIdCustomer() + "\n" +
                                    "Servicio ID: " + result.getIDService() + "\n" +
                                    "Cancelado: " + result.getCancelled() + "\n" +
                                    "Estatus: " + result.getStatus();

                    textStatus.setText("Estatus suscripción: " + result.getStatus());
                    textDetail.setText(information);

                } catch(Exception e){
                    e.printStackTrace();
                    textStatus.setText("Información no disponible");
                    textDetail.setText("Para mostrar el estatus de la suscripción, primero se debe de generar la alta previamente.");
                }
            }
        };

        npay.setOnServiceInformationReceivedListener(listenInfo);
        npay.getServiceInformation().getServiceInformation(Querys.getIDSubscription(id_service), id_service);
    }
}
