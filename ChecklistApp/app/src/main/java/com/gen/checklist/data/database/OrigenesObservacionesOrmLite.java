package com.gen.checklist.data.database;

import com.gen.checklist.data.database.ormlite.base.OrmLiteBaseRepository;
import com.gen.checklist.data.database.ormlite.base.OrmLiteDatabaseHelper;
import com.gen.checklist.model.OrigenesObservaciones;


public class OrigenesObservacionesOrmLite extends OrmLiteBaseRepository<OrigenesObservaciones> {

    public OrigenesObservacionesOrmLite(OrmLiteDatabaseHelper helper) {
        super(helper, OrigenesObservaciones.class);
    }
}
