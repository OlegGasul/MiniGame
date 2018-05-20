package com.yggdrasil.entity;

public enum Card {
    EUR_100(100), EUR_20(20), EUR_10(10), EUR_5(5), EXTRA_LIFE, GAME_OVER, SECOND_CHANCE;

    private int award = 0;

    Card() { }

    Card(int award) {
        this.award = award;
    }

    public int getAward() {
        return this.award;
    }

}
