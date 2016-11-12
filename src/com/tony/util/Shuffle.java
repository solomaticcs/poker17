package com.tony.util;

import java.util.ArrayList;

/**
 * Created by Tony on 2014/12/28.
 */
public class Shuffle {
    private ArrayList<Poker> shuffleArr;
    private int boundary_line=0;
    CardType cardtype;
    //input一組牌組
    public Shuffle(ArrayList<Poker> input_originPoker) {
        shuffleArr = new ArrayList<Poker>(input_originPoker);
        if(shuffleArr.size()%2==0){
            boundary_line= shuffleArr.size()/2;
        } else {
            boundary_line=(shuffleArr.size()/2)+1;
        }
        cardtype = new CardType();
    }

    //做交錯式洗牌，每次交錯式洗牌都會是完美洗牌
    public void doDovetailShuffle(int doCount) { //引數為洗牌次數
        for(int j=0;j<doCount;j++){
            ArrayList<Poker> tmpLeftpoker = new ArrayList<Poker>();
            ArrayList<Poker> tmpRightpoker = new ArrayList<Poker>();

            //left
            for(int i=0;i<boundary_line;i++) {
                tmpLeftpoker.add(shuffleArr.get(i));
            }
            //right
            for(int i=boundary_line;i< shuffleArr.size();i++) {
                tmpRightpoker.add(shuffleArr.get(i));
            }

            int rightcount = 0, leftcount = 0;
            for(int i=0;i< shuffleArr.size();i++) {
                if(i%2==0) {
                    shuffleArr.set(i,tmpLeftpoker.get(leftcount));
                    leftcount++;
                } else {
                    shuffleArr.set(i,tmpRightpoker.get(rightcount));
                    rightcount++;
                }
            }
        }
    }

    //切牌
    public void doCut(int cutCount) {
        ArrayList<Poker> cutPoker = new ArrayList<Poker>();
        ArrayList<Poker> anotherPoker = new ArrayList<Poker>();

        //cut poker
        for(int i=0;i<cutCount;i++){
            cutPoker.add(shuffleArr.get(i));
        }
        //another poker
        for(int i=cutCount;i<shuffleArr.size();i++){
            anotherPoker.add(shuffleArr.get(i));
        }

        int cutpokercount=0, anotherpokercount=0;
        for(int i=0;i<shuffleArr.size();i++){
            if(i<shuffleArr.size()-cutCount){
                shuffleArr.set(i,anotherPoker.get(anotherpokercount));
                anotherpokercount++;
            } else {
                shuffleArr.set(i,cutPoker.get(cutpokercount));
                cutpokercount++;
            }
        }
    }

    //取得牌組
    public ArrayList<Poker> getPoker() {
        return shuffleArr;
    }

    //發牌
    public void dealToCard(ArrayList<Poker> player01_cards, ArrayList<Poker> player02_cards){
        //player01
        for(int i=0;i<5;i++){
            player01_cards.add(shuffleArr.get(0));
            shuffleArr.remove(0);
        }
        //player02
        for(int i=0;i<5;i++){
            player02_cards.add(shuffleArr.get(0));
            shuffleArr.remove(0);
        }
    }

    //換牌
    public void changeCard(ArrayList<Poker> cards, int[] indexs){
        //建立欲換牌的ArrayList
        ArrayList<Poker> changecards = new ArrayList<Poker>();
        //將欲換的牌加入想換的牌組中
        for(int i=0;i<indexs.length;i++){
            //要-1是因為在陣列中是從0開始
            changecards.add(cards.get(indexs[i] - 1));
        }

        //先把要交換的牌放入莊家的牌組最後
        //將莊家牌組拿出N張   取得當前牌組第一張牌並移除
        for(int i=0;i<changecards.size();i++) {
            shuffleArr.add(changecards.get(i));
            changecards.set(i, shuffleArr.get(0));
            shuffleArr.remove(0);
        }

        //將牌放回手中
        for(int i=0;i<indexs.length;i++){
            cards.set(indexs[i] - 1, changecards.get(i));
        }
    }

    /***************
    //計算換與不換牌，要換牌要換哪幾張
    public void autochangeCard(ArrayList<Poker> cards){
        //先取得前5張
        ArrayList<Poker> shuffleArr5 = new ArrayList<Poker>(shuffleArr.subList(0, 5));
        //
        CardGroup[] cardGroup = new CardGroup[6];
        //不換
        cardGroup[0] = cardtype.cardtypeCheck(cards);
        //換一張
        for(int i=0;i<cards.size();i++){
            ArrayList<Poker> new_shuffleArr5 = new ArrayList<Poker>(shuffleArr5);
            new_shuffleArr5.set(i,shuffleArr.get(0));
            cardGroup[1] = cardtype.cardtypeCheck(new_shuffleArr5);
        }
        //換二張
        for(int i=0;i<cards.size();i++){

        }
        for(int i=1;i<cards.size()-1;i++){

        }
        for(int i=2;i<cards.size()-2;i++){

        }
        //換三張
        //換四張
        //換五張
    }
     ******************/
}
