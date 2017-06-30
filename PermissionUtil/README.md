## 使用方法
1. 拷贝PermissionUtil代码中的permission文件夹到项目中
2. 注册PermissionActivity
```
<activity
            android:name=".permission.PermissionActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:theme="@android:style/Theme.Translucent.NoTitleBar"
            android:windowSoftInputMode="stateHidden|stateAlwaysHidden" />
```
3. 使用
```
PermissionUtil.requestPermission(this, new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                //// TODO: 2017/6/28  获取权限后的操作
                call();
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                //// TODO: 2017/6/28  获取权限失败的提示
                PermissionUtil.showSettingTip(MainActivity.this,"需要该权限,请到设置页面设置");
            }
        }, Manifest.permission.CALL_PHONE);

```