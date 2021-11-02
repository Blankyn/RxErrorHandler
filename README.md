# RxErrorHandler

## Error Handle Of Rxjava

## Download

``` gradle
implementation 'com.github.Blankyn:RxErrorHandler:2.0.1' //rxjava2
```

## Initialization

``` java
  RxErrorHandler rxErrorHandler = RxErrorHandler 
                .builder()
                .with(this)
                .responseErrorListener(new ResponseErrorListener() {
                    @Override
                    public void handleResponseError(Context context, Throwable t) {
                        if (t instanceof UnknownHostException) {
                            //do something ...
                        } else if (t instanceof SocketTimeoutException) {
                            //do something ...
                        } else {
                            //handle other Exception ...
                        }
                        Log.w(TAG, "Error handle");
                    }
                }).build();
```

## Usage

``` java
  Observable
            .error(new Exception("Error"))
            .retryWhen(new RetryWithDelay(3, 2))//retry(http connect timeout) 
            .subscribe(new ErrorHandleSubscriber<Object>(rxErrorHandler) {
                    @Override
                    public void onNext(Object o) {

                    }

                });

  //Backpressure
  Flowable
          .error(new Exception("Error"))
          .retryWhen(new RetryWithDelayOfFlowable(3, 2))//retry(http connect timeout)
          .subscribe(new ErrorHandleSubscriberOfFlowable<Object>(rxErrorHandler) {
                   @Override
               public void onNext(Object o) {

                  }
               });
```

## 特别鸣谢

>
> 感谢[JessYanCoding](https://github.com/JessYanCoding)项目代码
>


## License
```
 Copyright 2021, blankm             
  
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at   

       http://www.apache.org/licenses/LICENSE-2.0  

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. 
```
