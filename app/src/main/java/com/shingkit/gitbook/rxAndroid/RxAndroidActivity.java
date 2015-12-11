package com.shingkit.gitbook.rxAndroid;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

import com.shingkit.gitbook.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.schedulers.HandlerScheduler;
import rx.functions.Func0;

public class RxAndroidActivity extends AppCompatActivity {

    private static final String TAG = RxAndroidActivity.class.getSimpleName();
    Handler backgroundHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android);

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundHandler = new Handler(backgroundThread.getLooper());

        findViewById(R.id.btn).setOnClickListener(v -> {
            onRunSchedulerButtonClicked();
        });

    }

    void onRunSchedulerButtonClicked() {
        sampleObserable()
                .subscribeOn(HandlerScheduler.from(backgroundHandler))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "onNext:" + s);
                    }
                });
    }

    static Observable<String> sampleObserable() {
//        return Observable.defer(new Func0<Observable<String>>() {
//            @Override
//            public Observable<String> call() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return Observable.just("one", "two", "three", "four", "five");
//            }
//        });
        //defer 延期发送
        return Observable.defer(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Observable.just("one", "two", "three", "four", "five");
                }
        );
    }

    static class BackgroundThread extends HandlerThread {

        public BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }
}
