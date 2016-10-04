package com.osb_ag.test.DAO;

import com.osb_ag.test.Exception.InitializationException;

/**
 * Created by deHrys.
 */
public class CDAO extends AbstractDAO {
    protected CDAO() throws InitializationException {
    }

    @Override
    protected String getTableName() {
        return "c";
    }
}
