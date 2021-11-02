/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.blankm.rxerrorhandler.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import me.blankm.rxerrorhandler.core.RxErrorHandler;
import me.blankm.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.blankm.rxerrorhandler.handler.ErrorHandleSubscriberOfFlowable;
import me.blankm.rxerrorhandler.handler.RetryWithDelay;
import me.blankm.rxerrorhandler.handler.RetryWithDelayOfFlowable;


public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxErrorHandler rxErrorHandler = ((App) getApplicationContext()).getRxErrorHandler();
        Observable
                .error(new Exception("Error"))
                .retryWhen(new RetryWithDelay(3, 2))//retry(http connect timeout)
                .subscribe(new ErrorHandleSubscriber<Object>(rxErrorHandler) {
                    @Override
                    public void onNext(Object o) {

                    }
                });

        Flowable //Backpressure
                .error(new Exception("Error"))
                .retryWhen(new RetryWithDelayOfFlowable(3, 2))//retry(http connect timeout)
                .subscribe(new ErrorHandleSubscriberOfFlowable<Object>(rxErrorHandler) {
                    @Override
                    public void onNext(Object o) {

                    }
                });

    }
}
