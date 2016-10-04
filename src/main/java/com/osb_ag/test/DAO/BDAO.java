package com.osb_ag.test.DAO;

import com.osb_ag.test.Exception.InitializationException;

/**
 * Created by deHrys.
 */
public class BDAO extends AbstractDAO {
    public BDAO() throws InitializationException {
    }

    @Override
    protected String getTableName() {
        return "b";
    }
}
