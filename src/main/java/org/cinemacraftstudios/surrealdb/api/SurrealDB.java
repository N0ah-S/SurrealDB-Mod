package org.cinemacraftstudios.surrealdb.api;

import org.cinemacraftstudios.surrealdb.intern.SurrealDBData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class SurrealDB {

    // defined behaviour
    protected static SurrealDB instance;
    
    protected SurrealDBData data;

    protected SurrealDB() { // prevent empty instances from the outside
        instance = this;
    }

    public static SurrealDB getInstance() {
        if(instance == null) new SurrealDBEmpty();
        return instance;
    }

    // to-be-defined behaviour

    /**
     * @throws RestrictedAuthDataException
     * @return null, if not yet initialized, the authentication data required for a WebSocket/REST Connection, if allowed per config file/db entry
     */
    @Nullable
    public abstract SurrealDBData getData() throws RestrictedAuthDataException;

    @Nonnull
    public abstract <T> Result<T> query(String ns, String db, String query);
    @Nonnull
    public abstract <T> Result<T> query(String ns, String db, String query, String... args);
}
