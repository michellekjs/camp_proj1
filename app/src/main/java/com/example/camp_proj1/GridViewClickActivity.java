package com.example.camp_proj1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.LinkObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.helper.log.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GridViewClickActivity extends AppCompatActivity {

    //private static int pagesNum = 3;
    private ViewPager mPager;
    public ImageButton back_btn;
    ArrayList<Integer> imageIDs = new ArrayList<Integer>();
    ArrayList<String> imageURLs = new ArrayList<>();
    ClickEventSliderAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (TextSetting.theme==1) {
            setTheme(R.style.Theme_Camp_proj1);

        }
        if (TextSetting.theme==2) {
            //Utils.changeToTheme(this,Utils.THEME_ORANGE);
            setTheme(R.style.Theme_Camp_proj3);

        }
        if (TextSetting.theme==3) {
            //Utils.changeToTheme(this,Utils.THEME_PINK);
            setTheme(R.style.Theme_Camp_proj4);

        }
        if (TextSetting.theme==4) {
            //Utils.changeToTheme(this,Utils.THEME_BLACK);
            setTheme(R.style.Theme_Camp_proj5);
        }

        if (TextSetting.theme==5) {
            //Utils.changeToTheme(this,Utils.THEME_RED);
            setTheme(R.style.Theme_Camp_proj6);

        }


        setContentView(R.layout.gridviewclickevent);
        mPager = (ViewPager) findViewById(R.id.pager);

        Intent receivedIntent = getIntent();
        int imageID = (Integer)receivedIntent.getExtras().get("image ID");

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/drawable";


        imageIDs = (ArrayList<Integer>) receivedIntent.getIntegerArrayListExtra("ImageList");
        imageURLs.clear();
        imageURLs.addAll(Image.imageURLs);
        adapter = new ClickEventSliderAdapter(this, imageIDs);
        mPager.setAdapter(adapter);
        mPager.setCurrentItem(imageID);
        mPager.setPageTransformer(true, new ZoomOutPageSlide());
        BottomNavigationView buttomNavigationView = findViewById(R.id.navigationView);
        back_btn = findViewById(R.id.goback_btn);
        back_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                finish();
            }

        });

        buttomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.shareItem:{
                        /*Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        String path = "/drawable/image1.jpg";
                        Uri screenshotUri = Uri.parse(path);	// android image path
                        sharingIntent.setType("image/png");
                        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        startActivity(Intent.createChooser(sharingIntent, "Share image using"));*/
                        Intent intent = new Intent(getApplicationContext(), sharingphoto.class);
                        intent.putExtra("showImage", imageID);

                        kakaolink(imageURLs.get(imageID));
                        return true;
                    }
                    case R.id.delteItem : {

                        Image.imageIDs.remove(imageID);
                        Image.imageURLs.remove(imageID);
                        finish();
                        return true;
                    }
                    default: return false;
                }
            }
        });


    }

    public void kakaolink(String url) {

        FeedTemplate params = FeedTemplate
                .newBuilder(ContentObject.newBuilder("몰입캠프", url,
                        LinkObject.newBuilder().setWebUrl(url)
                                .setMobileWebUrl("https://developers.kakao.com").build())
                        .setDescrption("사진공유해요~")
                        .build())
                .addButton( new ButtonObject("웹에서 보기", LinkObject.newBuilder()
                        .setWebUrl(url)
                        .setMobileWebUrl(url)
                        .build())
                )
                .addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                        .setWebUrl(url)
                        .setMobileWebUrl(url)
                        .setAndroidExecutionParams("key1=value1")
                        .setIosExecutionParams("key1=value1")
                        .build()))
                .build();

        File file = new File("/data/data/com.example.camp_proj1/files/image.txt");
        StringBuffer filecontent = new StringBuffer("");


        if (file.exists()) {

        } else {
            saveDiary( "image.txt", url);
            finish();
        }

        Map<String, String> serverCallbackArgs = new HashMap<String, String>();
        serverCallbackArgs.put("user_id", "${current_user_id}");
        serverCallbackArgs.put("product_id", "${shared_product_id}");

        KakaoLinkService.getInstance().sendDefault(this, params, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.e(errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
                // 템플릿 밸리데이션과 쿼터 체크가 성공적으로 끝남. 톡에서 정상적으로 보내졌는지 보장은 할 수 없다. 전송 성공 유무는 서버콜백 기능을 이용하여야 한다.
            }
        });
    }

    private void setImage(ImageView imageView) {

        Intent receivedIntent = getIntent();
        int imageID = (Integer)receivedIntent.getExtras().get("image ID");
        imageView.setImageResource(imageID);
    }

    public void saveDiary(String readText, String url) {
        try {
            FileOutputStream fos = openFileOutput(readText, MODE_PRIVATE);
            fos.write((url).getBytes());
            fos.close(); //파일에 컨텐츠를 넣고 닫음
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
