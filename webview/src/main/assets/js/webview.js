var renzhenmingjs = {};
renzhenmingjs.os = {};
renzhenmingjs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
renzhenmingjs.os.isAndroid = !renzhenmingjs.os.isIOS;
renzhenmingjs.callbacks = {}

renzhenmingjs.callback = function (callbackname, response) {
   var callbackobject = renzhenmingjs.callbacks[callbackname];
   console.log("xxxx"+callbackname);
   if (callbackobject !== undefined){
       if(callbackobject.callback != undefined){
          console.log("xxxxxx"+response);
           var ret = callbackobject.callback(response);
           if(ret === false){
               return
           }
           delete renzhenmingjs.callbacks[callbackname];
       }
   }
}

renzhenmingjs.takeNativeAction = function(commandname, parameters){
    console.log("renzhenmingjs takenativeaction")
    var request = {};
    request.name = commandname;
    request.param = parameters;
    if(window.renzhenmingjs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.renzhenmingwebview.takeNativeAction(JSON.stringify(request));
        console.log("window.renzhenmingwebview = "+window.renzhenmingwebview);
    } else {
        //  IOS 只能发一个参数，所以Android为了配合iOS也使用一个参数
        window.webkit.messageHandlers.renzhenmingwebview.postMessage(JSON.stringify(request))
    }
}

renzhenmingjs.takeNativeActionWithCallback = function(commandname, parameters, callback) {
    var callbackname = "nativetojs_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    renzhenmingjs.callbacks[callbackname] = {callback:callback};

    var request = {};
    request.name = commandname;
    request.param = parameters;
    request.param.callbackname = callbackname;
    console.log("takeNativeActionWithCallback = "+commandname);
    if(window.renzhenmingjs.os.isAndroid){
        window.renzhenmingwebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.renzhenmingwebview.postMessage(JSON.stringify(request))
    }
}

window.renzhenmingjs = renzhenmingjs;
