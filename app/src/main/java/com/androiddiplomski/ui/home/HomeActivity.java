package com.androiddiplomski.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;

import com.androiddiplomski.R;
import com.androiddiplomski.data.api.models.response.MovieApiResponse;
import com.androiddiplomski.device.ForegroundService;
import com.androiddiplomski.injection.component.ActivityComponent;
import com.androiddiplomski.ui.base.activities.BaseActivity;
import com.androiddiplomski.util.Constants;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;


public class HomeActivity extends BaseActivity implements HomeView {

    @Inject
    HomePresenter presenter;

    @BindView(R.id.text_millis)
    TextView textView;

    @BindView(R.id.button_zapocni)
    Button buttonStartStop;

    BroadcastReceiver broadcastReceiver = null;

    boolean started = false;

    public static Intent createIntent(final Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        checkLocationPermission();

    }

    private void checkLocationPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            isLocationEnabled();
                        }

                        if (!report.getDeniedPermissionResponses().isEmpty()) {
                            finish();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();

    }

    public void isLocationEnabled() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("GPS not enabled");
            builder.setMessage("GPS is not enabled. Enable GPS?");
            builder.setPositiveButton("YES", (dialogInterface, i) -> this.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)));
            builder.setNegativeButton("NO", (dialogInterface, i) ->  this.finish());
            builder.create().show();
            return;
        }
    }

    @OnClick(R.id.button_zapocni)
    public void startFollow() {
        if(!started) {
            started = true;
            buttonStartStop.setText("Zavrsi");
            startService(new Intent(HomeActivity.this, ForegroundService.class)
                    .setAction(Constants.ACTION.STARTFOREGROUND_ACTION));

            registerBroadCastReceiver();

        }
        else{
            started = false;
            buttonStartStop.setText("Zapocni");
            if (broadcastReceiver != null) {
                unregisterReceiver(broadcastReceiver);
                startService(new Intent(HomeActivity.this, ForegroundService.class).setAction(Constants.ACTION.STOPFOREGROUND_ACTION));
            }
        }
    }

    private void registerBroadCastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long timeInMilliseconds = intent.getLongExtra("timeInMilliseconds", 0);
                textView.setText(String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeInMilliseconds),
                        TimeUnit.MILLISECONDS.toMinutes(timeInMilliseconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeInMilliseconds)),
                        TimeUnit.MILLISECONDS.toSeconds(timeInMilliseconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMilliseconds))));
                /*float distance= intent.getFloatExtra("distanceInMeters",0);
                txtDistance.setText(String.format("%.2f", distance)+"m");
                double elevationGain = intent.getDoubleExtra("elevationGainInMeters",0);
                txtElevation.setText(String.format("%.2f", elevationGain)+"m");
                double calorie = intent.getDoubleExtra("calories", 0);
                txtKcal.setText(String.format("%.1f", calorie));*/
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter(Constants.ACTION.BROADCAST_ACTION));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        //presenter.getMovieInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            startService(new Intent(HomeActivity.this, ForegroundService.class).setAction(Constants.ACTION.STOPFOREGROUND_ACTION));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dispose();
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void showData(final MovieApiResponse movieInfo) {
        if (movieInfo != null) {
            Timber.e(movieInfo.getAccuracy() + " " + movieInfo.getLocation().getLat() + " " + movieInfo.getLocation().getLng());
        } else {
            Timber.e("NULL");
        }
    }
}
