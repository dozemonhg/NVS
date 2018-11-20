package quyen.nguyenvustore.View.DangNhap_DangKy;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import quyen.nguyenvustore.Adapter.ViewPagerAdapterDangNhap;
import quyen.nguyenvustore.R;

public class DangNhapAcivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.layout_dangnhap);

        tabLayout = (TabLayout) findViewById(R.id.tabDangNhap);
        viewPager = (ViewPager) findViewById(R.id.viewPagerDangNhap);
        toolbar = (Toolbar) findViewById(R.id.toolBarDangNhap);

        setSupportActionBar(toolbar);

        ViewPagerAdapterDangNhap viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);
    }


}
