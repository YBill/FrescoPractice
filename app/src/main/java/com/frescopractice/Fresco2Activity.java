package com.frescopractice;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class Fresco2Activity extends AppCompatActivity {

    private Uri imageUrl = Uri.parse("http://avatar.csdn.net/4/E/8/1_y1scp.jpg");
    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco2);
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.sdv_image);

        //初始化圆角圆形参数对象
        RoundingParams rp = new RoundingParams();
        //设置图像是否为圆形,同时设置圆角和圆形则显示为圆形
        rp.setRoundAsCircle(true);
        //设置圆角半径
//        rp.setCornersRadius(20);
        //分别设置左上角、右上角、左下角、右下角的圆角半径
//        rp.setCornersRadii(20, 25, 30, 35);
        //分别设置（1,2）左上角、(3,4)右上角、(5,6)左下角、(7,8)右下角的圆角半径
//        rp.setCornersRadii(new float[]{20, 25, 30, 35, 40, 45, 50, 55});
        //设置边框颜色及其宽度
        rp.setBorder(Color.RED, 10);
        //设置叠加颜色,设置后边框也会先出来
        rp.setOverlayColor(Color.GRAY);


        //获取GenericDraweeHierarchy对象
        GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(getResources())
                .setFadeDuration(5000) //设置淡入淡出动画持续时间(单位：毫秒ms)
//                .setRoundingParams(RoundingParams.asCircle()) // //设置圆形圆角参数,RoundingParams.asCircle()是将图像设置成圆形
                .setRoundingParams(rp)
//                .setRoundingParams(RoundingParams.fromCornersRadius(20)) //设置圆角半径
//                .setRoundingParams(RoundingParams.fromCornersRadii(20, 25, 30, 35)) //分别设置左上角、右上角、右下角、左下角的圆角半径
//                .setRoundingParams(RoundingParams.fromCornersRadii(new float[]{20, 50, 30, 35, 40, 45, 50, 55})) // The corners are ordered top-left, top-right, bottom-right, bottom-left.
                .build();//构建

        simpleDraweeView.setHierarchy(hierarchy);//设置Hierarchy
    }

    public void handleClick(View v) {
        //构建Controller
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageUrl)//设置需要下载的图片地址
                .setTapToRetryEnabled(true)//设置点击重试是否开启
                .build(); //构建

        //设置Controller
        simpleDraweeView.setController(controller);
    }

}
