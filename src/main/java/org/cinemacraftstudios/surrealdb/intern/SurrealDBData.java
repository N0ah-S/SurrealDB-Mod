package org.cinemacraftstudios.surrealdb.intern;

public class SurrealDBData {
    public String user, password, bind;
    public boolean strict, auth_data_public;

    public SurrealDBData() {}

    public SurrealDBData(String user, String password, String bind, boolean strict, boolean auth_data_public) {
        this.user = user;
        this.password = password;
        this.bind = bind;
        this.strict = strict;
        this.auth_data_public = auth_data_public;
    }
}
