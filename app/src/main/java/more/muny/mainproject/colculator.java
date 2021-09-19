package more.muny.mainproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class colculator extends AppCompatActivity {
    Button button_clear;
    Button button_0;
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button  button_8;
    Button button_9;
    Button button_dot;
    Button button_equals;
    TextView input;
    TextView output;
    Intent intent;
    TextView nameValute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colculator);
        button_clear=findViewById(R.id.Button_clear);
        button_0=findViewById(R.id.Button_0);
        button_1=findViewById(R.id.Button_1);
        button_2=findViewById(R.id.Button_2);
        button_3=findViewById(R.id.Button_3);
        button_4=findViewById(R.id.Button_4);
        button_5=findViewById(R.id.Button_5);
        button_6=findViewById(R.id.Button_6);
        button_7=findViewById(R.id.Button_7);
        button_8=findViewById(R.id.Button_8);
        button_9=findViewById(R.id.Button_9);
        button_dot=findViewById(R.id.Button_dot);
        button_equals=findViewById(R.id.Button_equals);
        input = findViewById(R.id.Input);
        output = findViewById(R.id.Output);
        intent = getIntent();
        nameValute = findViewById(R.id.nameValute);

nameValute.setText(intent.getStringExtra("name"));
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                output.setText("");
            }
        });
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(addToInputText("0"));
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(addToInputText("1"));
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(addToInputText("2"));
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText( addToInputText("3"));
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(addToInputText("4"));
            }
        });
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(addToInputText("5"));
            }
        });
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(addToInputText("6"));
            }
        });
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText( addToInputText("7"));
            }
        });
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(addToInputText("8"));

            }
        });
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(addToInputText("9"));
            }

        });
        button_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(addToInputText("."));
            }
        });
        button_equals.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Double inputValue = Double.valueOf(input.getText().toString());
            Double result = inputValue / Double.valueOf(intent.getStringExtra("course"));
            output.setText(String.format("%.2f", result));

    }
});


    }
    private String addToInputText(String buttonValue) {
        return input.getText()+buttonValue;
    }
}