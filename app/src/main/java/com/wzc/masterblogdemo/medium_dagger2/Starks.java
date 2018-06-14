package com.wzc.masterblogdemo.medium_dagger2;

import javax.inject.Inject;

/**
 * @author wzc
 * @date 2018/6/4
 */
public class Starks implements House {
    @Inject
    public Starks() {
    }

    @Override
    public void prepareForWar() {
        //do something
        System.out.println(this.getClass().getSimpleName() + " prepared for war");
    }

    @Override
    public void reportForWar() {
        //do something
        System.out.println(this.getClass().getSimpleName() + " reporting..");
    }
}
