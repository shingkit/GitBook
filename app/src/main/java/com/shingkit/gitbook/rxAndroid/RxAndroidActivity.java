package com.shingkit.gitbook.rxAndroid;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

import com.shingkit.gitbook.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.schedulers.HandlerScheduler;
import rx.functions.Func0;
import rx.functions.Func1;

public class RxAndroidActivity extends AppCompatActivity {

    private static final String TAG = RxAndroidActivity.class.getSimpleName();
    Handler backgroundHandler;

    @Bind(R.id.btn_flatmap)
    Button btn_flatmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android);
        ButterKnife.bind(this);
        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundHandler = new Handler(backgroundThread.getLooper());

        findViewById(R.id.btn).setOnClickListener(v -> {
            onRunSchedulerButtonClicked();
        });


    }

    @OnClick(R.id.btn_flatmap)
    void printFlatmapLog() {
        Observable.from(getPersonList())
//                .flatMap(new Func1<Person, Observable<Person.Course>>() {
//                    @Override
//                    public Observable<Person.Course> call(Person person) {
//                        return Observable.from(person.getList());
//                    }
//                })
                .flatMap((Person person) -> Observable.from(person.getList()))
                .subscribe(new Subscriber<Person.Course>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Person.Course course) {
                        Log.i(TAG, course.getTitle());
                    }
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
//        return Observable.just("one", "two", "three", "four", "five");
    }

     List<Person> getPersonList() {

        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person person = new Person();
            person.setName("name:" + i);
            person.setAge(i);

            List<Person.Course> courses = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                Person.Course course = person.new Course("course:" + i);
                courses.add(course);
            }
            person.setList(courses);
            list.add(person);
        }
        Toast.makeText(RxAndroidActivity.this, "list.size:"+list.size(), Toast.LENGTH_SHORT).show();
        return list;
    }

    static class BackgroundThread extends HandlerThread {

        public BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }
}
