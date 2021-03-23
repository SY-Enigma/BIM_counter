package com.example.bim_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button b1,b2;
    private RadioButton man,woman;
    private EditText heightText,weightText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //以下为变量与控件的绑定
        b1 = (Button) findViewById(R.id.b1);
        heightText = (EditText) findViewById(R.id.H);
        weightText = (EditText) findViewById(R.id.W);
        b2 = (Button) findViewById(R.id.b2);
        man = findViewById(R.id.man);
        woman = findViewById(R.id.woman);

        //button2的点击响应，作用为清空所有输入
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //清空实质为把所有输入换成空
                heightText.setText("");
                weightText.setText("");

            }
        });
        //button2的点击事件响应，计算BMI的值，结果用Toast显示
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //得到身高体重
                String height = heightText.getText().toString();
                String weight = weightText.getText().toString();
                double result = 0, heightNum = 0, weightNum = 0;
                if(!height.isEmpty()&&!weight.isEmpty()) {
                    heightNum = Double.parseDouble(height);
                    weightNum = Double.parseDouble(weight);
                    result = weightNum / (heightNum*heightNum);
                }
                if(man.isChecked()){//如果选择的是男性
                    if(result <= 20)
                    {
                        Toast.makeText(MainActivity.this,"体重过轻",Toast.LENGTH_SHORT).show();
                    }
                    else if(result <= 25 && result > 20)
                    {
                        Toast.makeText(MainActivity.this,"体重正常",Toast.LENGTH_SHORT).show();
                    }
                    else if(result <= 27 && result > 25)
                    {
                        Toast.makeText(MainActivity.this,"体重超重",Toast.LENGTH_SHORT).show();
                    }
                    else if(result < 30 && result > 27)
                    {
                        Toast.makeText(MainActivity.this,"轻度肥胖",Toast.LENGTH_SHORT).show();
                    }
                    else if(result < 35 && result > 30)
                    {
                        Toast.makeText(MainActivity.this,"中度肥胖",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"重度肥胖",Toast.LENGTH_SHORT).show();
                    }

                }
                else//选择的是女性
                { if(result <= 19)
                {
                    Toast.makeText(MainActivity.this,"体重过轻",Toast.LENGTH_SHORT).show();
                }
                else if(result <= 24 && result > 19)
                {
                    Toast.makeText(MainActivity.this,"体重正常",Toast.LENGTH_SHORT).show();
                }
                else if(result <= 26 && result > 24)
                {
                    Toast.makeText(MainActivity.this,"体重超重",Toast.LENGTH_SHORT).show();
                }
                else if(result < 29 && result > 26)
                {
                    Toast.makeText(MainActivity.this,"轻度肥胖",Toast.LENGTH_SHORT).show();
                }
                else if(result < 34 && result > 29)
                {
                    Toast.makeText(MainActivity.this,"中度肥胖",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"重度肥胖",Toast.LENGTH_SHORT).show();
                }
                }
            }
        });
    }
}
