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
public class Doctor extends Player{
    String nameRole;

    /**
     *
     * @param x
     */
    public Doctor(Player x){//конструктор из игрока
        this.charisma=x.charisma;
        this.life=x.life;
        this.lucky=x.lucky;
        this.naivete=x.naivete;
        this.number=x.number;
        this.oratory=x.oratory;
        this.rateSuccess=x.rateSuccess;
        nameRole="Доктор";  
    }
    public Player Getplayer(){
        Player x=new Player();
        x.charisma=this.charisma;
        x.life=this.life;
        x.lucky=this.lucky;
        x.naivete=this.naivete;
        x.number=this.number;
        x.oratory=this.oratory;
        x.rateSuccess=this.rateSuccess;
        return x;
    }
    public int Heal(Player[] players,Constants c){
        int k=0;
        do{
            k=c.rnd.nextInt(12);// выбираем рандомного пользователя
        }while(!players[k].life);//так мы ищем того кто еще жив
        return k;//возвращаем кого лечить
    }
}
