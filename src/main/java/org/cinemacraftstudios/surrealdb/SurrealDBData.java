package org.cinemacraftstudios.surrealdb;

public class SurrealDBData {
    public String user, password, bind;
    public boolean strict;

    public SurrealDBData() {}

    public SurrealDBData(String user, String password, String bind, boolean strict) {
        this.user = user;
        this.password = password;
        this.bind = bind;
        this.strict = strict;
    }
}
