package com.aei.mazkekacontroller;

import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.LayoutDirection;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button commandsBtn;
    private Button connectBtn;
    private Switch distillSwitch;
    private EditText idText;
    private TextView tempText;
    private TextView sitText;
    private TextView textView;
    private FireBase fireBase;

    private int methanolCount = 2;
    private int ethanolCount = 2;
    private int tailsCount = 2;
    private int finishCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireBase = new FireBase(new FireBase.Listener() {
            @Override
            public void onPowerChange(boolean sit) {
                handlePower(sit);
            }

            @Override
            public void tempUpdate(double temp) {
                handleTemp(temp);
            }

            @Override
            public void sitUpdate(String sit) {
                handleSit(sit);
            }

            @Override
            public void onDistChange(boolean sit){
                distillSwitch.setChecked(sit);
            }
        });
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        NotificationsManage.init(this,(NotificationManager) getSystemService(NOTIFICATION_SERVICE));
        commandsBtn = findViewById(R.id.commandBtn);
        connectBtn = findViewById(R.id.connect);
        distillSwitch = findViewById(R.id.distillingSwitch);
        idText = findViewById(R.id.idText);
        tempText = findViewById(R.id.tempLabel);
        sitText = findViewById(R.id.sitLabel);
        textView = findViewById(R.id.textView);
        setCallbacks();
    }

    private void setCallbacks(){
        commandsBtn.setOnClickListener(e -> {

        });
        connectBtn.setOnClickListener(e -> {
            fireBase.setName(idText.getText().toString());
        });
        distillSwitch.setOnCheckedChangeListener((e,v) -> {
            if(distillSwitch.isChecked()){
                fireBase.setDistiling(true);
            }else{
                Alert.createDialog(this,Alert.STOP, () -> {
                    fireBase.setDistiling(false);
                }).show();
            }
        });
        idText.setOnKeyListener((e,ke,k) -> {
            if(ke == KeyEvent.ACTION_DOWN && k.getKeyCode() == KeyEvent.KEYCODE_ENTER && !idText.getText().toString().isEmpty()){
                fireBase.setName(idText.getText().toString());
                return true;
            }
            return false;
        });
    }

    private void handlePower(boolean sit){
        if(sit){
            connectBtn.setEnabled(false);
            idText.setEnabled(false);
            commandsBtn.setEnabled(true);
            textView.setTextColor(Color.GREEN);
            textView.setText("ON");
            distillSwitch.setEnabled(true);
        }else{
            Alert.createDialog(this,Alert.CANT_CONNECT);
            commandsBtn.setEnabled(true);
            connectBtn.setEnabled(true);
            idText.setEnabled(true);
            commandsBtn.setEnabled(false);
            distillSwitch.setEnabled(false);
            textView.setText("OFF");
            textView.setTextColor(Color.RED);
        }
    }

    private void handleSit(String sit){
        sitText.setText(sit);
        String temp = tempText.getText().toString();
        int color = Color.BLACK;
        if(sit.equalsIgnoreCase("Methanol"))
            color = Color.BLUE;
        else if(sit.equalsIgnoreCase("Ethanol")) {
            color = Color.GREEN;
            if(ethanolCount-- > 0)
                NotificationsManage.noti(temp,sit);
        }
        else if(sit.equalsIgnoreCase("Tails")) {
            color = Color.YELLOW;
            if(tailsCount-- > 0)
                NotificationsManage.noti(temp,sit);
        }
        else if(sit.equalsIgnoreCase("Finish")) {
            color = Color.RED;
            if(methanolCount-- > 0)
                NotificationsManage.noti(temp,sit);
        }
        sitText.setTextColor(color);
        tempText.setTextColor(color);
    }

    private void handleTemp(double tmp){
        String temp = String.format(Locale.ENGLISH,"%.2f",tmp);
        tempText.setText(temp);
        String sit = sitText.getText().toString();
        NotificationsManage.unoti(temp, sit);
        if(sit.equalsIgnoreCase("Methanol") && methanolCount-- > 0)
            NotificationsManage.noti(temp,sit);
        else if(sit.equalsIgnoreCase("Ethanol") && ethanolCount-- > 0)
            NotificationsManage.noti(temp,sit);
        else if(sit.equalsIgnoreCase("Tails") && tailsCount-- > 0)
            NotificationsManage.noti(temp,sit);
        else if(sit.equalsIgnoreCase("Finish") && finishCount-- > 0)
            NotificationsManage.noti(temp,sit);
    }
}
