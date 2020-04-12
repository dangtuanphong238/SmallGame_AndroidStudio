package com.example.cuocduakithu;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParsePosition;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //khởi tạo
    ImageButton ibtnPlay;
    TextView tvDiem;
    CheckBox cbOne,cbTwo, cbThree;
    SeekBar sbOne,sbTwo, sbThree;
    int soDiem = 100;
    @Override

    //hàm chính
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gọi ánh xạ
        anhXa();

        //chặn không cho người dùng kéo seekbar
        DisableSeekBar();

        tvDiem.setText(soDiem + "");

        //Tạo countdown để nó chạy
        final CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long millisUntilFinished) {
                //random tốc độ
                int number = 5;
                Random random = new Random();
                int one     = random.nextInt(number);
                int two     = random.nextInt(number);
                int three   = random.nextInt(number);

                //kiemtra One Win
                if(sbOne.getProgress() >= sbOne.getMax())
                {
                    //dừng coundowntimer lại
                    this.cancel();

                    //hiện button lên
                    ibtnPlay.setVisibility(View.VISIBLE);

                    //kiem tra đặt cược
                    if(cbOne.isChecked())
                    {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this,"Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this,"Bạn chọn sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    tvDiem.setText(soDiem + "");

                    //nếu con thứ nhất chạy tới nơi rồi
                    EnableCheckbox();

                    //xuất thông báo
                    Toast.makeText(MainActivity.this,"Rùa thắng",Toast.LENGTH_SHORT).show();
                }
                //kiemtra Two Win
                if(sbTwo.getProgress() >= sbTwo.getMax())
                {
                    //dừng countdowntimer
                    this.cancel();

                    //hiện thị nút button
                    ibtnPlay.setVisibility(View.VISIBLE);

                    //kiểm tra đặt cược
                    if(cbTwo.isChecked())
                    {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this,"Bạn đoán chính xác",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this,"Bạn chọn sai rồi",Toast.LENGTH_SHORT).show();
                    }
                    tvDiem.setText(soDiem + "");

                    //nếu con thứ hai chạy tới nơi rồi
                    EnableCheckbox();

                    //Thông báo
                    Toast.makeText(MainActivity.this,"Bò thắng",Toast.LENGTH_SHORT).show();


                }
                //kiemtra Three Win
                if(sbThree.getProgress() >= sbThree.getMax())
                {
                    //dừng countdowntimer
                    this.cancel();

                    //hiện button
                    ibtnPlay.setVisibility(View.VISIBLE);

                    //kiểm tra đặt cược
                    if(cbThree.isChecked())
                    {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this,"Bạn đoán chính xác",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this,"Bạn chọn sai rồi",Toast.LENGTH_SHORT).show();
                    }
                    tvDiem.setText(soDiem + "");

                    //nếu con thứ ba chạy tới nơi rồi
                    EnableCheckbox();

                    //hiện thông báo
                    Toast.makeText(MainActivity.this,"Chim thắng",Toast.LENGTH_SHORT).show();

                }

                //Cộng điểm hiện tại với random để tăng tốc
                sbOne.setProgress(sbOne.getProgress() + one);
                sbTwo.setProgress(sbTwo.getProgress() + two);
                sbThree.setProgress(sbThree.getProgress() + three);

            }
            @Override
            public void onFinish() {

            }
        };

        //Clịck button
        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //neu cho no chay
                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked())
                {
                    //set điểm về 0
                    sbOne.setProgress(0);
                    sbTwo.setProgress(0);
                    sbThree.setProgress(0);

                    //Tắt button
                    ibtnPlay.setVisibility(View.INVISIBLE);

                    //bắt đầu chạy
                    countDownTimer.start();

                    //chặn click checkbox khác
                    DisableCheckbox();
                }
                else {
                    Toast.makeText(MainActivity.this,"Vui lòng đặt cược",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Check One
        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true)
                {
                    cbOne.setButtonDrawable(R.drawable.checked);
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
                else {
                    cbOne.setButtonDrawable(R.drawable.unchecked);
                }
            }
        });

        //Check Two
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true)
                {
                    cbTwo.setButtonDrawable(R.drawable.checked);
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
                else {
                    cbTwo.setButtonDrawable(R.drawable.unchecked);
                }
            }
        });

        //Check Three
        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true)
                {
                    cbThree.setButtonDrawable(R.drawable.checked);
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
                else {
                    cbThree.setButtonDrawable(R.drawable.unchecked);
                }
            }
        });


    }

    //hàm ánh xạ
    private void anhXa()
    {
        ibtnPlay    = (ImageButton) findViewById(R.id.buttonPlay);
        tvDiem      = (TextView) findViewById(R.id.textviewDiemSo);
        cbOne       = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo       = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree     = (CheckBox) findViewById(R.id.checkboxThree);
        sbOne       = (SeekBar) findViewById(R.id.seekbarOne);
        sbTwo       = (SeekBar) findViewById(R.id.seekbarTwo);
        sbThree     = (SeekBar) findViewById(R.id.seekbarThree);
    }

    private void EnableCheckbox()
    {
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    private void DisableCheckbox()
    {
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    private void DisableSeekBar()
    {
        sbOne.setEnabled(false);
        sbTwo.setEnabled(false);
        sbThree.setEnabled(false);
    }
}
