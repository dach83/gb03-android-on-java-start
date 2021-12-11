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
    private SwitchCompat msgToMasterSwitch;
    private EditText msgToMasterEditText;
    private Button sendOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewFields(); // инициализируем ссылки на компоненты активности

        // обработчики chekBox-ов с услугами
        for (CheckBox checkBox : serviceCheckBoxes) {
            checkBox.setOnClickListener(this::servicesCheckBoxOnClick);
        }

        // видимость календаря
        showCalendarButton.setChecked(false);
        setCalendarVisibility(showCalendarButton.isChecked());
        showCalendarButton.setOnClickListener(this::showCalendarButtonOnClick);

        // сообщение мастеру
        msgToMasterSwitch.setChecked(false);
        setMsgToMasterEditTextEnabled(msgToMasterSwitch.isChecked());
        msgToMasterSwitch.setOnClickListener(this::msgToMasterSwitchOnClick);

        // кнопка "Записаться"
        sendOrderButton.setOnClickListener((view) -> Toast.makeText(this, R.string.succes_order, Toast.LENGTH_SHORT).show());
    }

    // Обработчик щелчков по checkBox c услугами
    private void servicesCheckBoxOnClick(View view) {
        // создаем строку с выбранными услугами
        StringBuilder sb = new StringBuilder(getText(R.string.selected_services));
        for (CheckBox checkBox : serviceCheckBoxes) {
            if (checkBox.isChecked()) {
                sb.append("\n - ").append(checkBox.getText());
            }
        }

        selectedServicesTextView.setText(sb.toString());
    }

    // Обработчик щелчков по ToggleButton
    private void showCalendarButtonOnClick(View view) {
        setCalendarVisibility(showCalendarButton.isChecked());
    }

    // Обработчик щелчков по switch
    private void msgToMasterSwitchOnClick(View view) {
        setMsgToMasterEditTextEnabled(msgToMasterSwitch.isChecked());
    }

    // Показываем/прячем календарь
    private void setCalendarVisibility(boolean show) {
        if (show) {
            calendarView.setVisibility(View.VISIBLE);
        } else {
            calendarView.setVisibility(View.GONE);
        }
    }

    // Разрешаем/запрещаем ввод текста
    private void setMsgToMasterEditTextEnabled(boolean enabled) {
        msgToMasterEditText.setEnabled(enabled);
    }

    // Инициализация вьюшек
    private void initViewFields() {
        serviceCheckBoxes.add(findViewById(R.id.lash_check_box));
        serviceCheckBoxes.add(findViewById(R.id.epilation_check_box));
        serviceCheckBoxes.add(findViewById(R.id.massage_check_box));
        selectedServicesTextView = findViewById(R.id.selected_services_text_view);
        showCalendarButton = findViewById(R.id.show_calendar_btn);
        calendarView = findViewById(R.id.calendar);
        msgToMasterSwitch = findViewById(R.id.msg_to_master_switch);
        msgToMasterEditText = findViewById(R.id.msg_to_master_edit_text);
        sendOrderButton = findViewById(R.id.send_order_btn);
    }
}