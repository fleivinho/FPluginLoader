package com.github.fleivinho.fpluginloader.platform.bukkit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class BukkitLicense {

    private static final String LICENSE_URL = "https://meuapi.com?plugin={0}&address={1}";

    public static LicenseResponse getResponse(BukkitPlugin bukkitPlugin) {
        try {
            String address = getIpLocalHost();
            if (address == null) {
                // Muito provavelmente, caso o valor retorne null, o client está sem conexão.
                bukkitPlugin.getLogger().severe("Sem conexão com a internet.");
                return LicenseResponse.UNKNOWN;
            }
            // Substituindo o {1} da URL com o endereço IP da máquina local e {0} com o nome do plugin
            String finalUrl = LICENSE_URL.replace("{1}", Objects.requireNonNull(getIpLocalHost())).replace("{0}", bukkitPlugin.getName());
            bukkitPlugin.getLogger().info("Tentando conexao com " + finalUrl + "...");
            URL url = new URL(finalUrl);
            Scanner scanner = new Scanner(url.openStream());
            String result = scanner.nextLine();
            return LicenseResponse.from(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LicenseResponse.UNKNOWN;
    }

    private static String getIpLocalHost() {
        try {
            return (new BufferedReader(new InputStreamReader((new URL("http://checkip.amazonaws.com")).openStream())))
                    .readLine();
        } catch (IOException e) {
            return null;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum LicenseResponse {

        /*
            Respostas esperadas pela API: "true" e "false"
            Caso não seja algum dos esperados, será "UNKNOWN"
         */
        ALLOWED("Allowed", "true", false),
        BLOCKED("Blocked", "false", true),
        UNKNOWN("Unknown", "null", true);

        private final String name, response;
        private final boolean shutdown;;

        public boolean isDeny() {
            return this != ALLOWED;
        }

        public boolean isBlocked() {
            return this == BLOCKED;
        }

        public static LicenseResponse from(String value) {
            return Arrays.stream(values()).filter(licenseResponse -> licenseResponse.name().equalsIgnoreCase(value)
                    || licenseResponse.response.equalsIgnoreCase(value)
                    || licenseResponse.getName().equalsIgnoreCase(value)).findFirst().orElse(UNKNOWN);
        }
    }
}
