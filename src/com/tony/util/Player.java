package com.tony.util;

import java.util.ArrayList;

/**
 * Created by Tony on 2014/12/28.
 */
public class Player {
    //player名稱
    private String Name;
    //player牌
    private ArrayList<Poker> cards = new ArrayList<Poker>();
    public Player(String PlayerName) {
        this.Name = PlayerName;
    }
    //取得player名稱
    public String getName() {
        return Name;
    }
    //取得player牌
    public ArrayList<Poker> getCards() {
        return cards;
    }
}
