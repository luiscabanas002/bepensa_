package com.gen.checklist.data.database;

import com.gen.checklist.data.database.ormlite.base.OrmLiteBaseRepository;
import com.gen.checklist.data.database.ormlite.base.OrmLiteDatabaseHelper;
import com.gen.checklist.model.Area;


public class AreaOrmLite extends OrmLiteBaseRepository<Area> {

    public AreaOrmLite(OrmLiteDatabaseHelper helper) {
        super(helper, Area.class);
    }
}
