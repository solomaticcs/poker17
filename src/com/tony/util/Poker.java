package com.tony.util;

/**
 * Created by Tony on 2014/12/29.
 */
public class Poker {
    public String Type;
    public String Num;
    public Poker(String Type,String Num) {
        this.Type = Type; //這張牌的花色 黑桃(spades)4 紅心(hearts)3 方塊(diamonds)2 梅花(clubs)1
        this.Num = Num;   //這張牌的數字
    }
    public String getName() {
        return Type+" "+Num;
    }
}
