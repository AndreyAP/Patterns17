/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mafiaheader;

/**
 *
 * @author Андрей
 */

public class Player {
    boolean life;  //жив игрок, или нет
    int number;  //номер игрока
    int oratory;  //красноречие
    int charisma;  //харизма
    int lucky;  //удача
    int naivete;  //доверчивость, наивность
    double rateSuccess;  //коэффициент успешности в игре
    boolean ingame;//в игре или нет
    
    public double Decision(){  //Метод, генерирующий решение игрока
        rateSuccess = ((lucky * 1.5) + (oratory * 2.5) + (charisma * 2)) / naivete;
        return rateSuccess;
    }
    public int Voice(Player[] players,Constants c){//голосовать
        int k=0;
        int t=0;
        for(int i=0;i<players.length;i++){
            if(players[i].life){t++;}
        }
        if(t!=1){
        do{
                k=c.rnd.nextInt(12);// выбираем рандомного пользователя
            }while((!players[k].life)||(k==this.number));//так мы ищем того, кто еще жив, кроме себя
            return k;//возвращаем против кого голосовать
        }
        return -1;
    }
    public Player(){//конструктор
        oratory = 0;
        charisma = 0;
        lucky = 0;
        naivete = 0;
        life=true;
        ingame=false;
    }
    public void set_rnd(Constants c){  //здесь присваиваются рандомные характеристики каждого игрока
        final int rangeOfStats = 5;
        oratory = c.rnd.nextInt(rangeOfStats) + 1;
        charisma = c.rnd.nextInt(rangeOfStats) + 1;
        lucky = c.rnd.nextInt(rangeOfStats) + 1;
        naivete = c.rnd.nextInt(rangeOfStats) + 1;
    }
    public void Set_from_player(Player x){
        this.charisma=x.charisma;
        this.life=x.life;
        this.lucky=x.lucky;
        this.naivete=x.naivete;
        this.number=x.number;
        this.oratory=x.oratory;
        this.rateSuccess=x.rateSuccess;
        ingame=x.ingame;
    }
    /* Получить значения параметров */
    
    public int getOratory() {
        return oratory;
    }
    
    public int getCharisma() {
        return charisma;
    }
    
    public int getLucky() {
        return lucky;
    }
    
    public int getNaivete() {
        return naivete;
    }
    
    /* Задать значения параметров */
    
    public void setOratory(int o) {
        oratory = o;
    }
    
    public void setCharisma(int c) {
        charisma = c;
    }
    
    public void getLucky(int l) {
        lucky = l;
    }
    
    public void getNaivete(int n) {
        naivete = n;
    }
    
}
