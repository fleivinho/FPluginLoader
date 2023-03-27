package com.github.fleivinho.fpluginloader.platform.bukkit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoaderInfo {

    private final String pluginName;
    private final boolean checkPremium;
}
