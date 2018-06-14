package com.wzc.masterblogdemo.medium_dagger2;

import javax.inject.Inject;

/**
 * @author wzc
 * @date 2018/6/4
 */
public class War {

    private Starks mStarks;

    private Boltons mBoltons;

//    public War() {
//        mStarks = new Starks();
//        mBoltons = new Boltons();
//
//        mStarks.prepareForWar();
//        mBoltons.prepareForWar();
//        mStarks.reportForWar();
//        mBoltons.reportForWar();
//    }
    @Inject
    public War(Starks starks, Boltons boltons) {
        mStarks = starks;
        mBoltons = boltons;
    }

    public void prepare() {
        mStarks.prepareForWar();
        mBoltons.prepareForWar();
    }

    public void report() {
        mStarks.reportForWar();
        mBoltons.reportForWar();
    }

}
