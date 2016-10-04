package com.osb_ag.test;

import com.osb_ag.test.DAO.ADAO;
import com.osb_ag.test.DAO.BDAO;
import com.osb_ag.test.Exception.InitializationException;

/**
 * Created by deHrys.
 */
public class Core {

    public static void main(String[] args) {
        try {
            int tableSize = 1000000;
            ADAO adao = new ADAO();
//            adao.putEntry(1, "12345");
//            adao.putEntry(1, "12345");
            adao.fillTableWithRandomData(tableSize);
            BDAO bdao = new BDAO();
            bdao.fillTableWithRandomData(tableSize);
        } catch (InitializationException e) {
            e.printStackTrace();
        }
    }
}
