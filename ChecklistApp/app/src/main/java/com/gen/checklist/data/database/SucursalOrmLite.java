package com.gen.checklist.data.database;

import com.gen.checklist.data.database.ormlite.base.OrmLiteBaseRepository;
import com.gen.checklist.data.database.ormlite.base.OrmLiteDatabaseHelper;
import com.gen.checklist.model.Sucursal;


public class SucursalOrmLite extends OrmLiteBaseRepository<Sucursal> {

    public SucursalOrmLite(OrmLiteDatabaseHelper helper) {
        super(helper, Sucursal.class);
    }
}
