package com.angie.service_aidl;

// Declare any non-default types here with import statements

interface DataService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

     // TODO: 自定义方法 供客户端Client调用
     // 需要注意的是，在AIDL语言中前面不能写public

    int getData(String arg);

}


// TODO: 对于这里的改变，都要去build->Clean Project, 否则不生效
