package org.cinemacraftstudios.surrealdb.api;

import kong.unirest.Unirest;
import org.cinemacraftstudios.surrealdb.intern.SurrealDBData;
import org.cinemacraftstudios.surrealdb.intern.SurrealDBInstance;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;

public class SurrealDBFullyInitialized extends SurrealDBInstance {

    public SurrealDBFullyInitialized(SurrealDBData data, File jarFile) {
        super(data, jarFile);

        Unirest.config().setDefaultBasicAuth(data.user, data.password); // TODO check if this is a security vulnerability [1]
        Unirest.config().addDefaultHeader("Content-Type", "application/json");
    }

    @Nullable
    @Override
    public SurrealDBData getData() throws RestrictedAuthDataException {
        if(data != null && !data.auth_data_public)
            throw new RestrictedAuthDataException("disallowed by config file to ensure namespace sovereignty");
        // ToDo Check Database against namespace authority requests
        return data;
    }

    @Nonnull
    @Override
    public <T> Result<T> query(String ns, String db, String query) {
        return null;
    }

    @Nonnull
    @Override
    public <T> Result<T> query(String ns, String db, String query, String... args) {
        return null;
    }


}

/*
[1] security isn't really a concern anyway, since mods can apparently download and execute everything they want.
 */
