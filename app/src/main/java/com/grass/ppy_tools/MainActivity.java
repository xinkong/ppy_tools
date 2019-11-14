package com.grass.ppy_tools;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.grass.ppy_tools.image.GlideImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView open_album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open_album = findViewById(R.id.open_album);
        open_album.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open_album:
//               openAlbun();
               takePhoto();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                ArrayList<ImageItem> items = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                selPicPath.addAll(items);
                File file = new File(items.get(0).path);
                Log.i("tag",items.get(0).path);
//                luban(new File(items.get(0).path),mTakeCode);
//                mWriteBackInfoPresenter.upImgFile(BitmapUtils.superCorrectCompress(items.get(0).path, WriteBackInfoActivity.this));
            }
        }
    }

    private void takePhoto() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setCrop(false);
        Intent intent = new Intent(this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, 100);
    }

    private void openAlbun() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setCrop(false);
        imagePicker.setSelectLimit(1);
        imagePicker.setMultiMode(true);
        imagePicker.setShowCamera(false);

        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, 100);

    }
}
