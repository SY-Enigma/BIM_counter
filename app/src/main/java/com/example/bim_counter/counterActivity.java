package com.example.bim_counter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class counterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;

    private Button btn_point;// 小数点
    private Button btn_divide;// 除以
    private Button btn_multiply;// 乘以
    private Button btn_minus;// 减去
    private Button btn_pluse;// 加
    private Button btn_equal;// 等于

    private Button btn_clear;  //清空
    private Button btn_del;  //取消

    private EditText et_showview;  //输入框
    private double dou = 0;  //接收结果

    /**
     * 加一个标识
     * 需求就是我们运算出结果的时候，再次点击数字及你选哪个下一次运算，这个时候，输入框应该自动为空
     */
    private boolean flag;  //清空标识


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_point = findViewById(R.id.btn_point);// 小数点
        btn_divide = findViewById(R.id.btn_divide);// 除以
        btn_multiply = findViewById(R.id.btn_multiply);// 乘以
        btn_minus = findViewById(R.id.btn_minus);// 减去
        btn_pluse = findViewById(R.id.btn_pluse);// 加
        btn_equal = findViewById(R.id.btn_equal);// 等于

        btn_clear = findViewById(R.id.btn_clear);
        btn_del = findViewById(R.id.btn_del);
        et_showview = (EditText) findViewById(R.id.et_showview);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_point.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_pluse.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //我们在这里实现业务逻辑
        String str = et_showview.getText().toString();
        switch (v.getId()) {
            //我们点击数字键，输入框就收到我们的计算数字
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                //再次输入的时候判断，把前面的清空，再把点击的按钮放在输入框上
                if (flag) {
                    flag = false;
                    str = "";
                    et_showview.setText("");
                }
                //我们只要点击键盘，相应的数字添加在EditText上
                et_showview.setText(str + ((Button)v).getText());
                break;
            //运算符也是一样需要添加的
            case R.id.btn_pluse:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                //再次输入的时候判断，把前面的清空，再把点击的按钮放在输入框上
                if (flag) {
                    flag = false;
                    str = "";
                    et_showview.setText("");
                }
                //为了计算方便，我们可以在前后添加空格
                et_showview.setText(str + " " + ((Button)v).getText() + " ");
                break;
            case R.id.btn_del:
                //再次输入的时候判断，把前面的清空，再把点击的按钮放在输入框上
                if (flag) {
                    flag = false;
                    str = "";
                    et_showview.setText("");
                } else if (str != null && !str.equals(" ")) {//判断，因为我们需要一个个删，首先确定是不是空或者""
                    //用substring()截取字符长度-1 再设置回去 形成删一个的效果
                    et_showview.setText(str.substring(0, str.length() - 1));
                }
                break;
            //清除按钮，直接把输入框设置成空
            case R.id.btn_clear:
                flag = false;
                str = "";
                et_showview.setText(" ");
                break;
            //计算结果
            case R.id.btn_equal:
                getResult();
                break;
        }
    }

    //等号计算结果
    private void getResult() {
        //首先取一下你输入完后现在输入框的内容
        String result = et_showview.getText().toString();
        //当我们的输入框是null或者""时我们不进行操作
        if (result == null || result.equals(" ")) {
            return;
        }
        //我们要计算结果，必须知道输入框是否有操作符，判断条件就是前后是否在空格，否则不进行操作
        if (!result.contains(" ")) {
            //没有运算符，所以不用运算
            return;
        }
        //当我点击等号的时候，清空标识设置为true,但是如果之前有的话，设置成false
        if (flag) {
            flag = false;
            return;
        }
        flag = true;
        //如果有空格，我们就截取前后段再获取运算符进行计算
        String str1 = result.substring(0, result.indexOf(" "));  //运算符前面字段
        String op = result.substring(result.indexOf(" ") + 1, result.indexOf(" ") + 2);  //截取运算符
        String str2 = result.substring(result.indexOf(" ") + 3);  //运算符后面字段

        //这里我们还是要判断str1和str2都不是空才
        if (!str1.equals("") && !str2.equals("")) {  //第一种情况：都不是空
            //强转
            double d1 = Double.parseDouble(str1);
            double d2 = Double.parseDouble(str2);
            //开始计算，定义一个double变量接收结果，定义为全局
            if (op.equals("＋")) {  //加
                dou = d1 + d2;
            } else if (op.equals("－")) { //减
                dou = d1 - d2;
            } else if (op.equals("×")) { //乘
                dou = d1 * d2;
            } else if (op.equals("÷")) {  //除
                //除数为0不计算
                if (d2 == 0) {
                    dou = 0;
                } else {
                    dou = d1 / d2;
                }
            }
            //我们之前把他强转为double类型了，但是如果没有小数点，我们就是int类型
            if (!str1.contains(".") && !str2.contains(".") && !op.equals("÷")) {
                int i = (int) dou;
                et_showview.setText(i + "");
            } else {  //如果有小数点
                et_showview.setText(dou + "");
            }
        } else if (!str1.equals("") && str2.equals("")) {  //第二种情况:str2是空
            //这种情况就不运算了
            et_showview.setText(result);
        } else if (str1.equals("") && !str2.equals("")) {  //第三种情况:str1是空
            //这种情况我们就需要判断了，因为此时str1 = 0;
            double d3 = Double.parseDouble(str2);
            if (op.equals("+")) {  //加
                dou = 0 + d3;
            } else if (op.equals("-")) { //减
                dou = 0 - d3;
            } else if (op.equals("×")) { //乘
                dou = 0;
            } else if (op.equals("÷")) {  //除
                //除数为0不计算
                dou = 0;
            }
            //我们之前把他强转为double类型了，但是如果没有小数点，我们就是int类型
            if (!str2.contains(".")) {
                int i = (int) dou;
                et_showview.setText(i + "");
            } else {  //如果有小数点
                et_showview.setText(dou + "");
            }
        } else {  //最后一种情况，他们两个都是空
            //我们清空掉
            et_showview.setText("");
        }
    }}
