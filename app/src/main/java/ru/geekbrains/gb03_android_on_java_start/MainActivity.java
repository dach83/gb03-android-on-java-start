package ru.geekbrains.gb03_android_on_java_start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<CheckBox> serviceCheckBoxes = new ArrayList<>();
    private TextView selectedServicesTextView;
    private ToggleButton showCalendarButton;
    private CalendarView calendarView;
    private SwitchCompat messageToMasterSwitch;
    private EditText messageToMasterEditText;
    private Button sendOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewFields();
        initServiceCheckBoxes();
        initCalendar();
        initMessageToMaster();
        initSendOrderButton();
    }

    private void initSendOrderButton() {
        sendOrderButton.setOnClickListener((view) -> Toast.makeText(this, R.string.success_order, Toast.LENGTH_SHORT).show());
    }

    private void initMessageToMaster() {
        messageToMasterSwitch.setChecked(false);
        changeMessageToMasterEditTextEnabled(messageToMasterSwitch.isChecked());
        messageToMasterSwitch.setOnClickListener(view -> changeMessageToMasterEditTextEnabled(messageToMasterSwitch.isChecked()));
    }

    private void initCalendar() {
        showCalendarButton.setChecked(false);
        changeCalendarVisibility(showCalendarButton.isChecked());
        showCalendarButton.setOnClickListener(view -> changeCalendarVisibility(showCalendarButton.isChecked()));
    }

    private void initServiceCheckBoxes() {
        for (CheckBox checkBox : serviceCheckBoxes) {
            checkBox.setOnClickListener(this::servicesCheckBoxOnClick);
        }
    }

    private void servicesCheckBoxOnClick(View view) {
        // формируем строчку с выбранными услугами
        StringBuilder sb = new StringBuilder(getText(R.string.selected_services));
        for (CheckBox checkBox : serviceCheckBoxes) {
            if (checkBox.isChecked()) {
                sb.append("\n - ").append(checkBox.getText());
            }
        }
        selectedServicesTextView.setText(sb.toString());
    }

    private void changeCalendarVisibility(boolean show) {
        if (show) {
            calendarView.setVisibility(View.VISIBLE);
        } else {
            calendarView.setVisibility(View.GONE);
        }
    }

    private void changeMessageToMasterEditTextEnabled(boolean enabled) {
        messageToMasterEditText.setEnabled(enabled);
    }

    private void initViewFields() {
        serviceCheckBoxes.add(findViewById(R.id.lash_check_box));
        serviceCheckBoxes.add(findViewById(R.id.epilation_check_box));
        serviceCheckBoxes.add(findViewById(R.id.massage_check_box));
        selectedServicesTextView = findViewById(R.id.selected_services_text_view);
        showCalendarButton = findViewById(R.id.show_calendar_button);
        calendarView = findViewById(R.id.calendar);
        messageToMasterSwitch = findViewById(R.id.message_to_master_switch);
        messageToMasterEditText = findViewById(R.id.message_to_master_edit_text);
        sendOrderButton = findViewById(R.id.send_order_button);
    }
}