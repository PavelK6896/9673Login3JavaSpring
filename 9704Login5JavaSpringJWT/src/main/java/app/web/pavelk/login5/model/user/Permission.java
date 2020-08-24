package app.web.pavelk.login5.model.user;

public enum Permission {
    DEVELOPERS_READ("developers:read"),//положить в стрин финл
    DEVELOPERS_WRITE("developers:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() { //получить у енума стринг
        return permission;
    }
}
