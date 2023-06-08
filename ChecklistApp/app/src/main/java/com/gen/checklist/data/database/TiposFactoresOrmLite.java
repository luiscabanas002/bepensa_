package com.gen.checklist.data.database;

import com.gen.checklist.data.database.ormlite.base.OrmLiteBaseRepository;
import com.gen.checklist.data.database.ormlite.base.OrmLiteDatabaseHelper;
import com.gen.checklist.model.TiposFactores;


public class TiposFactoresOrmLite extends OrmLiteBaseRepository<TiposFactores> {

    public TiposFactoresOrmLite(OrmLiteDatabaseHelper helper) {
        super(helper, TiposFactores.class);
    }
}
