package top.helwor.zhangruilong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class StartGame extends BaseActivity {

    private EditText input;
    private Button submit;
    private Button restart;
    private TextView condition;
    private AlertDialog.Builder builder;
    private int random;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        random = new Random().nextInt(10) + 1;

        condition = findViewById(R.id.start_txt_condition);
        input = findViewById(R.id.start_ed_input);
        submit = findViewById(R.id.start_btn_submit);
        restart = findViewById(R.id.start_btn_restart);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                condition.setBackgroundResource(R.drawable.microsoft_card_red);
                String s = input.getText().toString();
                try {
                    num = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    condition.setText(R.string.error);
                    showToast("请输入数字");
                    return;
                }

                if (num > random){
                    setFail(true);
                } else if (num < random){
                    setFail(false);
                } else {
                    setSuccess();
                }

            }
        });

        //restart
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmLogout();
            }
        });
    }

    private void setFail(boolean isTrue){
        if (isTrue){
            condition.setText(R.string.big);
        } else {
            condition.setText(R.string.small);
        }
    }

    private void setSuccess(){
        condition.setBackgroundResource(R.drawable.microsoft_card_green);
        condition.setText(R.string.success);
    }

    private void reSet(){
        condition.setBackgroundResource(R.drawable.microsoft_card);
        condition.setText(R.string.defaultText);
        input.setText("");
    }

    private void confirmLogout() {
        builder = new AlertDialog.Builder(this)
                .setMessage("确定重新开始吗")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        random = new Random().nextInt(10) + 1;
                        reSet();
                        showToast("已重置");
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }
}