package com.rzm.webview.mainprocess;
import android.os.RemoteException;

import com.google.gson.Gson;
import com.rzm.utils.LogUtils;
import com.rzm.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import com.rzm.webview.IWebViewProcessToMainProcessAidlInterface;
import com.rzm.webview.command.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class MainProcessCommandsManager extends IWebViewProcessToMainProcessAidlInterface.Stub {
    private static volatile MainProcessCommandsManager mInstance;
    private HashMap<String,Command> mCommands = new HashMap<>();

    public static MainProcessCommandsManager getInstance() {
        if (mInstance == null) {
            synchronized (MainProcessCommandsManager.class) {
                if (mInstance == null) {
                    mInstance = new MainProcessCommandsManager();
                }
            }
        }
        return mInstance;
    }

    private MainProcessCommandsManager() {
        ServiceLoader<Command> loader = ServiceLoader.load(Command.class);
        for (Command command : loader) {
            if (!mCommands.containsKey(command.name())){
                LogUtils.d("MainProcessCommandsManager find command class :" + command.name() );
                mCommands.put(command.name(),command);
            }
        }
    }

    public void executeCommand(String commandName, Map params, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        mCommands.get(commandName).execute(params,callback);
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams, ICallbackFromMainprocessToWebViewProcessInterface callback) throws RemoteException {
        LogUtils.d("MainProcessCommandsManager handleWebCommand commandName = " + commandName);
        executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class),callback);
    }
}
