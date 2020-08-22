// IWebViewProcessToMainProcessAidlInterface.aidl
package com.rzm.webview;

// Declare any non-default types here with import statements
import com.rzm.webview.ICallbackFromMainprocessToWebViewProcessInterface;

interface IWebViewProcessToMainProcessAidlInterface {
 /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handleWebCommand(String commandName, String jsonParams, ICallbackFromMainprocessToWebViewProcessInterface callback);

}
