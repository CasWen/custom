package com.zwj.testandroidserver;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zwj.testandroidserver.utils.PieData;
import com.zwj.testandroidserver.view.PieView;

import java.util.ArrayList;

public class PieViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pie_view);

        ConstraintLayout rootview=findViewById(R.id.rootview);

        /**
         *第二种是通过view找到对应的id,不需要new一个PieView
         */
        PieView view = findViewById(R.id.pieview);
//        PieView view = new PieView(this);
        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("sloop", 60);
        PieData pieData2 = new PieData("sloop", 30);
        PieData pieData3 = new PieData("sloop", 40);
        PieData pieData4 = new PieData("sloop", 20);
        PieData pieData5 = new PieData("sloop", 20);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        view.setData(datas);

/**
 *第三种方法是把view加进去
 */
//        rootview.addView(view);


    }
}
