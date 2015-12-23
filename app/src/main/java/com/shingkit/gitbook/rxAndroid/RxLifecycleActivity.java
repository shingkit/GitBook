package com.shingkit.gitbook.rxAndroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.shingkit.gitbook.R;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * interval 每隔一段时间发送一个自增的数字
 */
public class RxLifecycleActivity extends RxAppCompatActivity {

    private static final String TAG = RxLifecycleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_lifecycle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.framelayout,new RxLifecycleFragment())
                .commit();

        
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnUnsubscribe(() ->
                        Log.i(TAG, "unSubscribed")
                )
                .compose(this.bindUntilEvent(ActivityEvent.PAUSE))//在pause时unsubscribe  compose将被观察者通过某种方法转化
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i(TAG, "onNext"+aLong);
                    }
                });




    }
}
