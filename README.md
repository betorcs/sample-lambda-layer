#### Small sample using custom lambda layer 

#### Project structure

| Module | Description |
|-------------|-------------|
| lib | Native library wrote in kotlin-native. |
| sls | Aws serverless using `aws-java-gradle` template. |


The `calculator-lib` folder content will to deployed to `/opt` path in the lambda runtime machine, then the absolute path will be `/opt/lib/libcalc.so` 


#### How to build

- Build native shared lib

```bash
./gradlew :lib:assemble
```

- Copy the built library `lib/build/bin/linuxX64/calcReleaseShared/libcalc.so` to `calculator-lib/lib/libcalc.so` 

```bash
mkdir -p calculator-lib/lib/
cp lib/build/bin/linuxX64/calcReleaseShared/libcalc.so calculator-lib/lib/
```

- Builder servervess package

```bash
./gradlew :sls:build
```

- Deploy serverless 

```bash
./gradlew :sls:deployServerless
```

##### To simplify: 

- Create shared lib and copy to lambda layer folder.

```bash
./gradlew buildLayer
```

- Build and deploy serverless

````bash
./gradlew deploySls
````

#### How to test

```bash
$ curl -XPOST https://xxxxxxx.execute-api.xxxxxx.amazonaws.com/dev/calc \ 
   -d '{"a": 3, "b": 7, "operation": "mult"}'

{"message":"3 * 7 => 21"}
```

```bash
$ curl -XPOST https://xxxxxxx.execute-api.xxxxxx.amazonaws.com/dev/calc \ 
   -d '{"a": 3, "b": 7, "operation": "sum"}'

{"message":"3 * 7 => 10"}
```

