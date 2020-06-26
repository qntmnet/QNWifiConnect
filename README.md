# QNWifiConnect

QNWifiConnect is an Android library for dealing with QN wifi connection.

Warning:  you need to use AndroidX in your project

## How to?
Add qnwificonnect dependency to build.gradle in app module.
## Gradle
```groovy
dependencies {
     implementation 'com.github.qntmnet:QNWifiConnect:1.0'
}


```

### Activity/Fragment
```java

public class MainActivity extends AppCompatActivity {

    private Qnwificonnection qnwificonnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        qnwificonnection.Connect("test","test","test","test");
    }
}

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
