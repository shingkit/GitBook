package com.shingkit.gitbook.rxAndroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shingkit.gitbook.R;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxFragment;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by LENOVO on 2015/12/23 0023.
 */
public class RxLifecycleFragment extends RxFragment {
    String TAG = RxLifecycleFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initialize();
        return inflater.inflate(R.layout.fragment_rxlifecycle,container,false);
    }

    private void initialize() {
        Observable.just(1, 1, 1, 1, 1, 1, 1, 5245, 153, 132)
                .compose(this.bindUntilEvent(FragmentEvent.PAUSE))
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.d(TAG, "unsubscribed");
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext" + integer);
                    }
                });
    }
}
