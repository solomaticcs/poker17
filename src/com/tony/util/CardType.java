package com.tony.util;

import java.util.ArrayList;

/**
 * Created by Tony on 2014/12/28.
 */
public class CardType {
    public int A_5CARD = 8; //五條
    public int ROYAL_STARIGHT_FLUSH = 7; //同花順
    public int A_4CARD = 6; //四條
    public int FULL_HOUSE = 5; //葫蘆
    public int STRAIGHT = 4; //順子
    public int A_3CARD = 3; //三條
    public int A_2PAIR = 2; //二對
    public int A_1PAIR = 1; //單對
    public int A_0CARD = 0; //散牌

    String[] pokerNum = {"A","K","Q","J","Joker"};
    int[] pokerNumCount = new int[5];

    //value to zero
    private void valueToZero(int[] intarray) {
        for(int i=0;i<intarray.length;i++) {
            intarray[i]=0;
        }
    }

    public void markCount(ArrayList<Poker> cards, int[] PNC) {
        for(int i=0;i<cards.size();i++) {
            for(int j=0;j<pokerNum.length;j++){
                if(cards.get(i).Num.equals(pokerNum[j])){
                    PNC[j]+=1;
                    break;
                }
            }
        }
    }

    //找出出現最多次的卡
    public String findMoreCard(ArrayList<Poker> cards) {

        valueToZero(pokerNumCount);
        markCount(cards, pokerNumCount);
        int max_value = 0;
        int max_ptr = 0;
        for (int i = 0 ; i < pokerNumCount.length ; i++) {
            if (pokerNumCount[i] > max_value) {
                max_value = pokerNumCount[i];
                max_ptr = i;
            }
        }
        return pokerNum[max_ptr];
    }

    //找出牌組的類型
    public CardGroup cardtypeCheck(ArrayList<Poker> cards) {

        //判斷是否五條
        String[] pokerNum = {"A","K","Q","J","Joker"};
        valueToZero(pokerNumCount);
        markCount(cards, pokerNumCount);
        if(pokerNumCount[4]==1) //先判斷有沒有Joker
            if(pokerNumCount[0]==4 || pokerNumCount[1]==4 ||
                    pokerNumCount[2]==4 || pokerNumCount[3]==4)
                return new CardGroup(A_5CARD);


        //判斷是否同花順或順子
        valueToZero(pokerNumCount);
        markCount(cards, pokerNumCount);
        if(pokerNumCount[0]==1 && pokerNumCount[1]==1 && pokerNumCount[2]==1
                && pokerNumCount[3]==1 && pokerNumCount[4]==1){ //滿足A,K,Q,J,Joker各一張
            //判斷牌類型是否都相同
            boolean allsame = true;
            for(int i=0;i<cards.size();i++){
                if(!allsame) //一旦牌型不相同就直接跳出迴圈不運算
                    break;
                for(int j=0;j<cards.size();j++)
                    if(i!=j) //不能與自己比較
                        if(!cards.get(i).Type.equals(cards.get(j).Type)){
                            //牌型不相同就設定allsame為false並跳出迴圈
                            allsame = false;
                            break;
                        }
            }
            if(allsame) //如果牌型全部一樣，那就是同花順
                return new CardGroup(ROYAL_STARIGHT_FLUSH);
            else            //如果牌型不一樣，就是順子
                return new CardGroup(STRAIGHT);
        }


        //判斷是否四條
        valueToZero(pokerNumCount);
        markCount(cards, pokerNumCount);
        if(pokerNumCount[4]==1) //先判斷有沒有Joker
        {
            if(pokerNumCount[0]==3 || pokerNumCount[1]==3 ||
                    pokerNumCount[2]==3 || pokerNumCount[3]==3)
                return new CardGroup(A_4CARD);
        } else {
            if(pokerNumCount[0]==4 || pokerNumCount[1]==4 ||
                    pokerNumCount[2]==4 || pokerNumCount[3]==4)
                return new CardGroup(A_4CARD);
        }


        //判斷是否葫蘆
        valueToZero(pokerNumCount);
        markCount(cards, pokerNumCount);
        if(pokerNumCount[4]==1){ //先判斷有沒有Joker
            if((pokerNumCount[0]==2 && pokerNumCount[1]==2)
                    || (pokerNumCount[0]==2 && pokerNumCount[2]==2)
                    || (pokerNumCount[0]==2 && pokerNumCount[3]==2)
                    || (pokerNumCount[1]==2 && pokerNumCount[2]==2)
                    || (pokerNumCount[1]==2 && pokerNumCount[3]==2)
                    || (pokerNumCount[2]==2 && pokerNumCount[3]==2))
                return new CardGroup(FULL_HOUSE);
        } else { //沒有Joker
            if((pokerNumCount[0]==2 && pokerNumCount[1]==3)
                    || (pokerNumCount[0]==3 && pokerNumCount[1]==2)
                    || (pokerNumCount[0]==2 && pokerNumCount[2]==3)
                    || (pokerNumCount[0]==3 && pokerNumCount[2]==2)
                    || (pokerNumCount[0]==2 && pokerNumCount[3]==3)
                    || (pokerNumCount[0]==3 && pokerNumCount[3]==2)
                    || (pokerNumCount[1]==2 && pokerNumCount[2]==3)
                    || (pokerNumCount[1]==3 && pokerNumCount[2]==2)
                    || (pokerNumCount[1]==2 && pokerNumCount[3]==3)
                    || (pokerNumCount[1]==3 && pokerNumCount[3]==2)
                    || (pokerNumCount[2]==2 && pokerNumCount[3]==3)
                    || (pokerNumCount[2]==3 && pokerNumCount[3]==2))
                return new CardGroup(FULL_HOUSE);
        }

        //判斷是否三條
        valueToZero(pokerNumCount);
        markCount(cards, pokerNumCount);
        if(pokerNumCount[4]==1) { //先判斷有沒有Joker
            if(pokerNumCount[0]==2 || pokerNumCount[1]==2
                    || pokerNumCount[2]==2 || pokerNumCount[3]==2)
                return new CardGroup(A_3CARD);
        } else {
            if(pokerNumCount[0]==3 || pokerNumCount[1]==3
                    || pokerNumCount[2]==3 || pokerNumCount[3]==3)
                return new CardGroup(A_3CARD);
        }

        //判斷是否二對
        valueToZero(pokerNumCount);
        markCount(cards, pokerNumCount);
        if(pokerNumCount[4]==1) { //先判斷有沒有Joker
            if((pokerNumCount[0]==1 && pokerNumCount[1]==2)
                    || (pokerNumCount[0]==2 && pokerNumCount[1]==1)
                    || (pokerNumCount[0]==1 && pokerNumCount[2]==2)
                    || (pokerNumCount[0]==2 && pokerNumCount[2]==1)
                    || (pokerNumCount[0]==1 && pokerNumCount[3]==2)
                    || (pokerNumCount[0]==2 && pokerNumCount[3]==1)
                    || (pokerNumCount[1]==1 && pokerNumCount[2]==2)
                    || (pokerNumCount[1]==2 && pokerNumCount[2]==1)
                    || (pokerNumCount[1]==1 && pokerNumCount[3]==2)
                    || (pokerNumCount[1]==2 && pokerNumCount[3]==1)
                    || (pokerNumCount[2]==1 && pokerNumCount[3]==2)
                    || (pokerNumCount[2]==2 && pokerNumCount[3]==1))
                return new CardGroup(A_2PAIR);
        } else {
            if((pokerNumCount[0]==2 && pokerNumCount[1]==2)
                    || (pokerNumCount[0]==2 && pokerNumCount[2]==2)
                    || (pokerNumCount[0]==2 && pokerNumCount[3]==2)
                    || (pokerNumCount[1]==2 && pokerNumCount[2]==2)
                    || (pokerNumCount[1]==2 && pokerNumCount[3]==2)
                    || (pokerNumCount[2]==2 && pokerNumCount[3]==2))
                return new CardGroup(A_2PAIR);
        }

        //判斷是否單對
        valueToZero(pokerNumCount);
        markCount(cards, pokerNumCount);
        if(pokerNumCount[4]==1) { //先判斷有沒有Joker
            if(pokerNumCount[0]==1 || pokerNumCount[1]==1
                    || pokerNumCount[2]==1 || pokerNumCount[3]==1)
                return new CardGroup(A_1PAIR);
        } else {
            if(pokerNumCount[0]==2 || pokerNumCount[1]==2
                    || pokerNumCount[2]==2 || pokerNumCount[3]==2)
                return new CardGroup(A_1PAIR);
        }

        //如果都沒有
        return new CardGroup(A_0CARD);
    }


}
