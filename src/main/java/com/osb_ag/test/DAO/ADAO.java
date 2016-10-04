package com.osb_ag.test.DAO;


import com.osb_ag.test.Exception.InitializationException;

/**
 * Created by deHrys.
 */
public class ADAO extends AbstractDAO {

    public ADAO() throws InitializationException {
    }

    @Override
    protected String getTableName() {
        return "a";
    }
}
