package com.wzc.masterblogdemo.medium_dagger2;

import dagger.Component;

/**
 * @author wzc
 * @date 2018/6/4
 */
public class BattleOfBastards {
    public static void main(String[] args) {

//        Starks starks = new Starks();
//        Boltons boltons = new Boltons();
//        War war = new War(starks, boltons);
//        war.prepare();
//        war.report();

//        BattleComponent battleComponent = DaggerBattleOfBastards_BattleComponent.create();

        Cash cash = new Cash();
        Soldiers soldiers = new Soldiers();
        BattleComponent battleComponent = DaggerBattleOfBastards_BattleComponent.builder()
                .braavosModule(new BraavosModule(cash, soldiers)).build();
        War war = battleComponent.getWar();
        war.prepare();
        war.report();



    }

    @Component(modules = BraavosModule.class)
    interface BattleComponent {
        War getWar();
        Starks getStarks();
        Boltons getBoltons();

        Cash getCash();
        Soldiers getSoldiers();
    }
}
