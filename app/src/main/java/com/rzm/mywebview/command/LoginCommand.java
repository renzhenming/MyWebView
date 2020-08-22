package com.rzm.mywebview.command;

import com.google.auto.service.AutoService;
import com.rzm.webview.command.Command;

import java.util.Map;

@AutoService({Command.class})
public class LoginCommand implements Command {
    @Override
    public String name() {
        return "";
    }

    @Override
    public void execute(Map parameters) {

    }
}
