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
public class Mafia extends Player{
    private String nameRole;
    public Mafia(Player x){//конструктор из игрока
        this.charisma=x.charisma;
        this.life=x.life;
        this.lucky=x.lucky;
        this.naivete=x.naivete;
        this.number=x.number;
        this.oratory=x.oratory;
        this.rateSuccess=x.rateSuccess;
        nameRole="Мафия";
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
    public int Kill(Mafia[] mafias,Player[] players,Constants c){//мафия убивает
        int k=-1;
        double max=-1;
        for(int i=0;i<players.length;i++){
            if((players[i].life)&&(mafias[0].number!=i)&&(mafias[1].number!=i)){
                max=players[i].Decision();//нашли первого живого
                k=i;
            }
        }
        if(k==-1){
            //осталась одна мафия, расходимся
            return k;
        }
        for(int i=0;i<players.length;i++){
            if(players[i].life){
                if((mafias[0].number!=i)&&(mafias[1].number!=i)&&(players[i].Decision()>max)){
                    max=players[i].Decision();
                    k=i;
                }
            }
        }//так мы узнаем человека с большим приоритетом, который может что-то знать (но не мафию)
        return k;//возвращаем того кого убить
    }

}
