package net.crystalmc.ddostnt.handler;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DDOSHandler {

    @Getter private List<String> bannedIPs;
    @Getter private List<Integer> ddosTntList;
    private static DDOSHandler instance;

    private DDOSHandler() {
        bannedIPs = new ArrayList<>();
        ddosTntList = new ArrayList<>();
    }

    public static DDOSHandler getInstance() {
        if (instance == null) {
            instance = new DDOSHandler();
        }

        return instance;
    }

    public void addBannedIP(String ip) {
        bannedIPs.add(ip);
    }

    public boolean isBanned(String ip) {
        return bannedIPs.contains(ip);
    }

    public void removeBannedIP(String ip) {
        bannedIPs.remove(ip);
    }

    public boolean isDdosTnt(int id) {
        return ddosTntList.contains(id);
    }

    public void addDdosTnt(int id) {
        ddosTntList.add(id);
    }

    public void removeDdosTnt(int id) {
        Integer integer = id;
        if (ddosTntList.contains(integer)) {
            ddosTntList.remove(integer);
        }
    }

}
