package org.cinemacraftstudios.surrealdb.api;

import org.cinemacraftstudios.surrealdb.intern.SurrealDBData;
import org.cinemacraftstudios.surrealdb.intern.SurrealDBInstance;

import javax.annotation.Nullable;
import java.io.File;

public class SurrealDBFullyInitialized extends SurrealDBInstance {

    public SurrealDBFullyInitialized(SurrealDBData data, File jarFile) {
        super(data, jarFile);
    }



    @Nullable
    @Override
    public SurrealDBData getData() throws RestrictedAuthDataException {
        if(data != null && !data.auth_data_public)
            throw new RestrictedAuthDataException("disallowed by config file to ensure namespace sovereignty");
        // ToDo Check Database against namespace authority requests
        return data;
    }
}
