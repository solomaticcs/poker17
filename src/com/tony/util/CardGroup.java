package com.tony.util;

/**
 * Created by Tony on 2014/12/29.
 */
public class CardGroup {
    private int cardtypeId; //牌組代號
    public CardGroup(int cardtypeId) {
        this.cardtypeId = cardtypeId;
    }
    public int getId() {
        return this.cardtypeId;
    }
    public String getName() {
        if(cardtypeId==8) return "五條";
        if(cardtypeId==7) return "同花順";
        if(cardtypeId==6) return "四條";
        if(cardtypeId==5) return "葫蘆";
        if(cardtypeId==4) return "順子";
        if(cardtypeId==3) return "三條";
        if(cardtypeId==2) return "二對";
        if(cardtypeId==1) return "單對";
        if(cardtypeId==0) return "散牌";
        return "Non";
    }
}
