package com.gen.checklist.data.database;

import com.gen.checklist.data.database.ormlite.base.OrmLiteBaseRepository;
import com.gen.checklist.data.database.ormlite.base.OrmLiteDatabaseHelper;
import com.gen.checklist.model.RiesgosIdentificados;


public class RiesgosIdentificadosOrmLite extends OrmLiteBaseRepository<RiesgosIdentificados> {

    public RiesgosIdentificadosOrmLite(OrmLiteDatabaseHelper helper) {
        super(helper, RiesgosIdentificados.class);
    }
}
