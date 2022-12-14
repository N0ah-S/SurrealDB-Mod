package org.cinemacraftstudios.surrealdb.api;

import org.cinemacraftstudios.surrealdb.intern.SurrealDBData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SurrealDBEmpty extends SurrealDB {

    @Override
    @Nullable
    public SurrealDBData getData() throws RestrictedAuthDataException  {
        if(data != null && !data.auth_data_public)
            throw new RestrictedAuthDataException("disallowed by config file to ensure namespace sovereignty");
        return data;
    }

    @Override
    @Nonnull
    public <T> Result<T> query(String ns, String db, String query) {
        return new Result<T>(null, Status.ERR, -1);
    }

    @Override
    @Nonnull
    public <T> Result<T> query(String ns, String db, String query, String... args) {
        return new Result<T>(null, Status.ERR, -1);
    }
}
