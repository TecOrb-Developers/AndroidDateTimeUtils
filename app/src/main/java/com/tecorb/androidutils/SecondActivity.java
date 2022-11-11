package com.tecorb.androidutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tecorb.androidutils.databinding.ActivitySecondBinding;
import com.vkp.utilitylibrary.datetime.DateTimeUtils;

import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvDate.setText("Get Previous Month date :\n "+ DateTimeUtils.getPreviousMonthDate(new Date(),-1));
    }
}