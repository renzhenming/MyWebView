var renzhenmingjs = {};
renzhenmingjs.os = {};
renzhenmingjs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
renzhenmingjs.os.isAndroid = !renzhenmingjs.os.isIOS;

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

window.renzhenmingjs = renzhenmingjs;
