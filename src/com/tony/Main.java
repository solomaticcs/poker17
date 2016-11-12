package com.tony;

import com.tony.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //秀出手中的牌
    static void ShowCardMessage(Player player) {
        System.out.print(player.getName()+"目前的牌：");
        for(int i=0;i<player.getCards().size();i++){
            System.out.print(""+player.getCards().get(i).getName());
            if(i!=player.getCards().size()-1)
                System.out.print(" , ");
            else
                System.out.println();
        }
    }

    public static void main(String[] args) {

        /*
         * 初始化
         */

        Scanner scanner = new Scanner(System.in);

        //建立玩家與電腦
        Player User = new Player("玩家");
        Player Computer = new Player("電腦");

        //random
        Random random = new Random();

        //建立所有排型
        CardType cardtype = new CardType();

        //原本17張撲克牌的個別名稱
        Poker[] originpoker = {
                new Poker("黑桃","A"),new Poker("黑桃","K"),new Poker("黑桃","Q"),new Poker("黑桃","J"),
                new Poker("方塊","A"),new Poker("方塊","K"),new Poker("方塊","Q"),new Poker("方塊","J"),
                new Poker("紅心","A"),new Poker("紅心","K"),new Poker("紅心","Q"),new Poker("紅心","J"),
                new Poker("梅花","A"),new Poker("梅花","K"),new Poker("梅花","Q"),new Poker("梅花","J"),
                new Poker("鬼牌","Joker")
                                };

        ArrayList<Poker> originpokerList = new ArrayList<Poker>(Arrays.asList(originpoker));

        /*
         * 進行洗牌動作與切牌的前置動作
         */

        //隨機洗牌次數
        int shuffle_count = random.nextInt(10) + 1;
        //放入要洗的牌
        Shuffle shuffle = new Shuffle(originpokerList);
        //進行交錯式洗牌動作，並給予洗牌次數
        shuffle.doDovetailShuffle(shuffle_count);
        System.out.println("洗"+shuffle_count+"次牌。");
        //切牌要切幾張
        System.out.print("要切幾張牌：");
        int user_cut_count = scanner.nextInt();
        System.out.println("玩家選擇切"+user_cut_count+"張牌。");
        //切牌
        shuffle.doCut(user_cut_count);
        //電腦隨機切1~10張牌
        int x = random.nextInt(10)+1;
        System.out.println("電腦選擇切" + x + "張牌。");
        shuffle.doCut(x);
        //取得洗牌與切牌後的撲克牌
        ArrayList<Poker> resultPoker = shuffle.getPoker();

        /*
         * 發牌與換牌
         */
        shuffle.dealToCard(User.getCards(),Computer.getCards());
        //秀出玩家的牌
        ShowCardMessage(User);

        //詢問是否換牌
        System.out.print("換牌(0: 不換，欲換得牌以逗點分隔)：");
        String changecards_word = scanner.next();
        if(!changecards_word.equals("0")) {
            //以逗點來做分割，取得要更換的撲克牌索引
            String[] words = changecards_word.split(",");
            //建立整數陣列，將索引由字串轉成整數
            int[] indexs = new int[words.length];
            for(int i=0;i<words.length;i++){
                indexs[i] = Integer.parseInt(words[i]);
            }
            //換牌
            shuffle.changeCard(User.getCards(),indexs);
        }

        //秀出玩家的牌
        ShowCardMessage(User);
        //秀出電腦的牌
        ShowCardMessage(Computer);

        //取得使用者與電腦的牌組(包含牌組名與牌組大小)
        CardGroup usercardGroup = cardtype.cardtypeCheck(User.getCards());
        CardGroup computercardGroup = cardtype.cardtypeCheck(Computer.getCards());

        //顯示使用者的牌組
        System.out.println(User.getName()+"的牌組為：" + usercardGroup.getName());

        //顯示電腦的牌組
        System.out.println(Computer.getName()+"的牌組為：" + computercardGroup.getName());

        //顯示結果
        if(usercardGroup.getId()>computercardGroup.getId())
            System.out.println(User.getName()+"勝利!");
        else if(computercardGroup.getId()>usercardGroup.getId())
            System.out.println(Computer.getName() + "勝利!");
        else if(usercardGroup.getId()==computercardGroup.getId()){
            System.out.println("平手!");
        }
    }
}
