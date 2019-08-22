package com.RNColorManager;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager.TaskDescription;
import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.view.WindowManager;

import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;

import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = RNColorManagerModule.NAME)
public class RNColorManagerModule extends ReactContextBaseJavaModule {
    public static final String NAME = "RNColorManager";

    public RNColorManagerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void setNavigationBarColor(final String colorString, final boolean animated, final int duration) {
        final Activity activity = getCurrentActivity();
        final int color = Color.parseColor(colorString);

        if (activity == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            UiThreadUtil.runOnUiThread(
                    new GuardedRunnable(getReactApplicationContext()) {
                        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void runGuarded() {
                            activity
                                    .getWindow()
                                    .addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            if (animated) {
                                int curColor = activity.getWindow().getNavigationBarColor();
                                ValueAnimator colorAnimation =
                                        ValueAnimator.ofObject(new ArgbEvaluator(), curColor, color);

                                colorAnimation.addUpdateListener(
                                        new ValueAnimator.AnimatorUpdateListener() {
                                            @Override
                                            public void onAnimationUpdate(ValueAnimator animator) {
                                                activity
                                                        .getWindow()
                                                        .setNavigationBarColor((Integer) animator.getAnimatedValue());
                                            }
                                        });
                                colorAnimation.setDuration((Integer) duration).setStartDelay(0);
                                colorAnimation.start();
                            } else {
                                activity.getWindow().setNavigationBarColor(color);
                            }
                        }
                    });
        }
    }

    @ReactMethod
    public void setStatusBarColor(final String colorString, final boolean animated, final int duration) {
        final Activity activity = getCurrentActivity();
        final int color = Color.parseColor(colorString);

        if (activity == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            UiThreadUtil.runOnUiThread(
                    new GuardedRunnable(getReactApplicationContext()) {
                        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void runGuarded() {
                            activity
                                    .getWindow()
                                    .addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            if (animated) {
                                int curColor = activity.getWindow().getStatusBarColor();
                                ValueAnimator colorAnimation =
                                        ValueAnimator.ofObject(new ArgbEvaluator(), curColor, color);

                                colorAnimation.addUpdateListener(
                                        new ValueAnimator.AnimatorUpdateListener() {
                                            @Override
                                            public void onAnimationUpdate(ValueAnimator animator) {
                                                activity
                                                        .getWindow()
                                                        .setStatusBarColor((Integer) animator.getAnimatedValue());
                                            }
                                        });
                                colorAnimation.setDuration((Integer) duration).setStartDelay(0);
                                colorAnimation.start();
                            } else {
                                activity.getWindow().setStatusBarColor(color);
                            }
                        }
                    });
        }
    }

    @ReactMethod
    public void setRecentColor(final String colorString) {
        final Activity activity = getCurrentActivity();

        if (activity == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Context context = getReactApplicationContext();
            final String appName = context.getString(context.getApplicationInfo().labelRes);
            final Bitmap appIcon = BitmapFactory.decodeResource(activity.getResources(), context.getApplicationInfo().icon);
            final int color = Color.parseColor(colorString);

            TaskDescription taskDescription = new TaskDescription(appName, appIcon, color);
            ((Activity) activity).setTaskDescription(taskDescription);
        }
    }

    @ReactMethod
    public String isNightMode() {
        final Context context = getReactApplicationContext();
        UiModeManager manager = (UiModeManager) context.getSystemService(Context.UI_MODE_SERVICE);
        switch (manager.getNightMode()) {
            case UiModeManager.MODE_NIGHT_AUTO:
                return "AUTO";
            case UiModeManager.MODE_NIGHT_NO:
                return "NO";
            case UiModeManager.MODE_NIGHT_YES:
                return "YES";
            default:
                return "NO";
        }
    }
}
