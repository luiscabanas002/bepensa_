package com.gen.checklist.data.database;

import com.gen.checklist.data.database.ormlite.base.OrmLiteBaseRepository;
import com.gen.checklist.data.database.ormlite.base.OrmLiteDatabaseHelper;
import com.gen.checklist.model.Factores;


public class FactoresOrmLite extends OrmLiteBaseRepository<Factores> {

    public FactoresOrmLite(OrmLiteDatabaseHelper helper) {
        super(helper, Factores.class);
    }
}
