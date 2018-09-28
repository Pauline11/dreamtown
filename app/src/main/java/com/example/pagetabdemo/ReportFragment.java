package com.example.pagetabdemo;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

/**
 * 拍照
 */
public class ReportFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "MainActivity";
    private ImageButton cameraButton;
    private ImageView photoImageView;
    private static final int REQUEST_CODE = 1;
    private String strImgPath = "";//照片保存路径
    private File imageFile = null;//照片文件
    /** 定义相片的最大尺寸 **/
    private final int IMAGE_MAX_WIDTH = 540;
    private final int IMAGE_MAX_HEIGHT = 960;

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_report, container,
                    false);
            initView(rootView);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cameraButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                strImgPath = Environment.getExternalStorageDirectory()
                        .toString() + "/2/Report_PIC/";
                String fileName = new SimpleDateFormat("yyyyMMddHHmmss")
                        .format(new Date()) + ".jpg";// 照片以格式化日期方式命名
                File output = new File(strImgPath);
                if (!output.exists()) {
                    output.mkdirs();
                }
                output = new File(strImgPath, fileName);
                strImgPath = strImgPath + fileName;// 该照片的绝对路径
                Uri uri = Uri.fromFile(output);
                getPhoto.putExtra(MediaStore.EXTRA_OUTPUT, uri);//根据uri保存照片
                getPhoto.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);//保存照片的质量
                startActivityForResult(getPhoto, REQUEST_CODE);//启动相机拍照
            }
        });
    }

    /**
     * 视图初始化
     */
    private void initView(View rootView) {
        cameraButton = (ImageButton) rootView.findViewById(R.id.take_photo);
        photoImageView = (ImageView) rootView.findViewById(R.id.photo_view);
    }

    /**
     * 返回照片结果处理
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            imageFile = new File(strImgPath);
            int scale = 0;
            scale = getZoomScale(imageFile);//得到缩放倍数
            Log.i(TAG, "scale = "+scale);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = scale;
            photoImageView.setImageBitmap(BitmapFactory.decodeFile(strImgPath,options));//按指定options显示图片防止OOM
        }else {
            Toast.makeText(getActivity(),"failed", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 图片缩放处理
     * @param imageFile 照片文件
     * @return 缩放的倍数
     */
    private int getZoomScale(File imageFile) {
        int scale = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(strImgPath, options);
        while (options.outWidth / scale >= IMAGE_MAX_WIDTH
                || options.outHeight / scale >= IMAGE_MAX_HEIGHT) {
            scale *= 2;
        }
        return scale;
    }


}