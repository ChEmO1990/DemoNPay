package com.naranya.demonpay.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.naranya.demonpay.R;
import com.naranya.demonpay.db.Querys;
import com.naranya.demonpay.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.npay.activity.NPay;
import io.npay.hub.language.LanguageHelper;
import io.npay.hub.service_detail.OnServiceDetailReceivedListener;
import io.npay.hub.service_detail.ServiceDetailItem;
import io.npay.hub_cancel_subscription.CancelResponse;
import io.npay.hub_cancel_subscription.OnSubscriptionCancelledListener;
import io.npay.hub_service_information.OnServiceInformationReceivedListener;
import io.npay.hub_service_information.ServiceInformationItem;
import io.npay.hub_subscriptions.OnSubscriptionCreatedListener;
import io.npay.hub_subscriptions.SubscriptionResponse;
import io.npay.hub_verify_subscription.OnSubscriptionReceivedListener;
import io.npay.hub_verify_subscription.SubscriptionItem;
import io.npay.subscriptions.OnSubscriptionStatusReceivedListener;

/**
 * Created by Naranya on 31/01/2016.
 */
public class ServiceDetailActivity extends BaseActivity implements OnServiceDetailReceivedListener {
    @Bind(R.id.lbl_detail_service)
    TextView textDetail;

    @Bind(R.id.btnSubscribir)
    Button btnSubscripcion;

    @Bind(R.id.btnCancel)
    Button btnCancel;

    @Bind(R.id.btnInfo)
    Button btnInfo;

    private Toolbar toolbar;
    private NPay npay;
    private String id_service;

    private OnSubscriptionCreatedListener listenCreate;
    private OnSubscriptionCancelledListener listenCancel;
    private OnServiceInformationReceivedListener listenInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_service);
        ButterKnife.bind(this);

        toolbar = getToolbar();
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

        listenCreate = new OnSubscriptionCreatedListener() {
            @Override
            public void onSubscriptionCreatedListener(SubscriptionResponse result) {
                try {
                    Log.e("---CREATE---", "*--------------------*");
                    Log.v("Object", result.getObject());
                    Log.v("Created", result.getCreated());
                    Log.v("First Charge", result.getFirstCharge());
                    Log.v("Last Charge", result.getLastCharge());
                    Log.v("Next Charge", result.getNextCharge());
                    Log.v("Customer ID", result.getIdCustomer());
                    Log.v("Service ID", result.getIDService());
                    Log.v("Cancelled", result.getCancelled());
                    Log.v("Status", result.getStatus());
                    Querys.addSubscription(result.getIdSubscription(), result.getIDService());
                    Toast toast = Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG);

                } catch(Exception e){
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(), "Toast del lado de la app - Object is empty", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };

        listenCancel = new OnSubscriptionCancelledListener() {

            @Override
            public void onSubscriptionCancelledListener(CancelResponse resultItem) {
                try {
                    Log.e("---CANCEL---", "*--------------------*");
                    Log.v("Object", resultItem.getObject());
                    Log.v("Created", resultItem.getCreated());
                    Log.v("First Charge", resultItem.getFirstCharge());
                    Log.v("Last Charge", resultItem.getLastCharge());
                    Log.v("Next Charge", resultItem.getNextCharge());
                    Log.v("Customer ID", resultItem.getIdCustomer());
                    Log.v("Service ID", resultItem.getIDService());
                    Log.v("Cancelled", resultItem.getCancelled());
                    Log.v("Status", resultItem.getStatus());
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        };

        listenInfo = new OnServiceInformationReceivedListener() {
            @Override
            public void onServiceInformationReceivedListener(ServiceInformationItem result) {
                try {
                    Log.e("---INFORMATION---", "*----------------------*");

                    String information =
                                    "Objeto: " + result.getObject() + "\n" +
                                    "Creado: " + result.getCreated() + "\n" +
                                    "Primer cargo: " + result.getFirstCharge() + "\n" +
                                    "Siguiente cargo: " + result.getNextCharge() + "\n" +
                                    "Cliente ID: " + result.getIdCustomer() + "\n" +
                                    "Servicio ID: " + result.getIDService() + "\n" +
                                    "Cancelado: " + result.getCancelled() + "\n" +
                                    "Estatus: " + result.getStatus();

                    new AlertDialog.Builder(ServiceDetailActivity.this)
                            .setTitle("Información ")
                            .setMessage(information)
                            .setPositiveButton(new LanguageHelper().getOk(), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        };

        npay.setOnServiceDetailReceivedListener(this);

        npay.setOnSubscriptionCreatedListener(listenCreate);
        npay.setOnSubscriptionCancelledListener(listenCancel);
        npay.setOnServiceInformationReceivedListener(listenInfo);


        npay.getServiceDetails().getServiceDetails(id_service);
    }

    @Override
    public void onServiceDetailReceivedListener(ServiceDetailItem result) {
        try {

            toolbar.setTitle(result.getName());

            String information =
                    "Información detallada" + "\n" + "\n" +
                    "Nombre: " + result.getName() + "\n" +
                            "Descripción: " + result.getDescription() + "\n" +
                            "Pais: " + result.getCountry() + "\n" +
                            "Carrier: " + result.getCarrier() + "\n" +
                            "Cantidad: " + result.getAmount() + "\n" +
                            "SubTotal: " + result.getSubTotal() + "\n" +
                            "Impuesto: " + result.getTax() + "\n" +
                            "Intervalo: " + result.getInterval() + "\n" +
                            "Intervalo contador: " + result.getIntervalCount() + "\n" +
                            "Intervalo caducado: " + result.getIntervalExpire() + "\n" +
                            "Moneda: " + result.getCurrency() + "\n" +
                            "Codigo referencia: " + result.getReferenceCode() + "\n" +
                            "Doble Opt In: " + result.getDoubleOptIn() + "\n" +
                            "Tipo: " + result.getType();
            textDetail.setText(information);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btnSubscribir) void makeSubscription() {
        npay.CreateSubscription(id_service, Constants.KEYWORD, Constants.MEDIA);
    }

    @OnClick(R.id.btnCancel) void cancelSubscription() {
        npay.CancelSubscription().CancelSubscription(Querys.getIDSubscription(id_service), Constants.KEYWORD, id_service);
    }

    @OnClick(R.id.btnInfo) void infoSubscription() {
        Intent i = new Intent(this, DetailSubscriptionActivity.class);
        i.putExtra("id_service", id_service);
        startActivity(i);
    }
}