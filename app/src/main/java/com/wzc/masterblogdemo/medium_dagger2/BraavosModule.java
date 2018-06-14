package com.wzc.masterblogdemo.medium_dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * @author wzc
 * @date 2018/6/4
 */
@Module
public class BraavosModule {
    Cash mCash;
    Soldiers mSoldiers;

    public BraavosModule(Cash cash, Soldiers soldiers) {
        mCash = cash;
        mSoldiers = soldiers;
    }

    @Provides
    Cash provideCash() {
        return mCash;
    }

    @Provides
    Soldiers provideSoldiers() {
        return mSoldiers;
    }
}
