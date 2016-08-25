package com.frescopractice;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class Fresco1Activity extends AppCompatActivity {

    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco1);
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.sdv_image);

//        Drawable drawable = getResources().getDrawable(R.drawable.icon_placeholder); // 已过期
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.icon_placeholder); // v4包新增方法
        Drawable rotateDrawable = new AutoRotateDrawable(ContextCompat.getDrawable(this, R.drawable.icon_progress_bar), 5000);

        // 获取GenericDraweeHierarchy对象
        GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(getResources())
                .setPlaceholderImage(R.drawable.icon_placeholder) //设置占位图，它默认的缩放类型是CENTER_INSIDE
                .setProgressBarImage(rotateDrawable) // 设置进度条
                .setFailureImage(R.drawable.icon_failure) // 设置加载失败图
                .setRetryImage(R.drawable.icon_retry) // 设置重试图
                .setFadeDuration(3000) // 设置淡入淡出动画持续时间
                .setBackground(ContextCompat.getDrawable(this, R.drawable.bg_zero)) //设置单张背景图
                .setOverlay(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)) //设置单张叠加图
                .build();
        simpleDraweeView.setHierarchy(hierarchy);
    }

    public void handleClick(View v){
        Uri uri = Uri.parse("http://img.my.csdn.net/uploads/201407/26/1406383291_8239_.jpg");

        // 注意setImageURI()必须在setController()之前,否者没有重试，一般在设置重试时建议不要设置simpleDraweeView.setImageURI(uri)了，直接通过builder的.setUri(uri)设置
//        simpleDraweeView.setImageURI(uri);

        //构建Controller
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true) // 允许重试
                .build();
        simpleDraweeView.setController(controller);

    }
}
