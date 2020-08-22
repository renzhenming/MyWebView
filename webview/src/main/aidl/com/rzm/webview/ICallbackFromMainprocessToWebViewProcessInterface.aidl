// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.rzm.webview;

// Declare any non-default types here with import statements

interface ICallbackFromMainprocessToWebViewProcessInterface {
    void onResult(String callbackname, String response);
}
