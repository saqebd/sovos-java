package com.sovos.coupa.utility;

import java.util.ArrayList;
import java.util.List;


public enum Permissions {
    TRANSACTION_EVALUATE("SMP_AR_EVE_TRANS", "API");

    private String permissionName;
    private String type;

    private Permissions(String permission, String type) {

        this.permissionName = permission;
        this.type = type;
    }

    public static List<String> getPermissionNameByType(String type) {

        List<String> permissions = new ArrayList<>();
        for (Permissions permission : Permissions.values()) {
            if (permission.type.equals(type)) {
                permissions.add(permission.permissionName);
            }
        }
        return permissions;
    }

}
