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
public class Game {
    public Mafia[] mafias;//массив мафий
    public Citizen[] citizens;//массив жителей
    public Doctor doctor;//врач
    public Player[] players;//все игроки
    public Game(Mafia[] mafia,Citizen[] citizen,Doctor doc,Player[] player){//конструктор по всем параметрам
        mafias=new Mafia[mafia.length];
        for(int i=0;i<mafia.length;i++){
            this.mafias[i]=new Mafia(mafia[i].Getplayer());
        }//массив мафий
        citizens=new Citizen[citizen.length];
        for(int i=0;i<citizen.length;i++){
            this.citizens[i]=new Citizen(citizen[i].Getplayer());
        }//массив мирных
        doctor=new Doctor(doc.Getplayer());//доктор
        players=new Player[player.length];
        for(int i=0;i<player.length;i++){
            this.players[i]=new Player();
            this.players[i].Set_from_player(player[i]);
        }//массив всех игроков
    }
    public void Night(Constants c,MafiaHeader h){
        //мафия убивает одного, доктор лечит одного
        String s;
        if(this.Check(h)==0){//если игра еще имеет смысл
            int kill=-1,heal=-1;
            if(doctor.life){//если  доктор жив, то он лечит случайного
                heal=doctor.Heal(players, c);
            }
            if(mafias[0].life){
               kill=mafias[0].Kill(mafias, players, c);
            }
            if(mafias[1].life){
               kill=mafias[1].Kill(mafias, players, c);
            }

            if(kill!=-1){//если кого-то убили
                if(heal!=-1){//док кого-то вылечил
                    if(kill!=heal){// и его не вылечил доктор
                        players[kill].life=false;//убиваем его
                        if(doctor.number==kill){doctor.life=false;}
                        if(mafias[0].number==kill){mafias[0].life=false;}
                        if(mafias[1].number==kill){mafias[1].life=false;}
                        //запись в лог "убили того то того-то"
                        s="К сожалению, этой ночью не проснулся игрок с номером #"+kill+"...";
                        h.WriteLog(3,s);
                        
                    }
                    else{
                        //пишим в чат что убили того-то того-то, но его вылечили
                        s="Ночью мафия указала на игрока с номером #"+kill+", но его вылечил Доктор! Какая удача!";
                        h.WriteLog(1,s);
                        
                    }
                }
                else{//никого не вылечили(доктора нет)
                    players[kill].life=false;//убиваем его
                    if(doctor.number==kill){doctor.life=false;}
                    if(mafias[0].number==kill){mafias[0].life=false;}
                    if(mafias[1].number==kill){mafias[1].life=false;}
                    //запись в лог "убили того то того-то"
                    s="К сожалению, этой ночью не проснулся игрок с номером #"+kill+"...";
                    h.WriteLog(3,s);
                    
                }
            }
        }
    }
    public int Day(Constants c,MafiaHeader h){
        String s;
        if(this.Check(h)==0){//если игра еще имеет смысл            
            //все голосуют, и кого-то обязательно вешают
            int[] voice_mas=new int[players.length];//массив голосов
            for(int i=0;i<voice_mas.length;i++){
                voice_mas[i]=0;
            }//зануляем массив голосов каждый раз перед использованием
            int t=0;
            for(int i=0;i<players.length;i++){
                if(players[i].life){
                    if(players[i].Voice(players, c)!=-1){
                        voice_mas[players[i].Voice(players, c)]++;//учитываем голос каждого кто жив
                        t++;
                    }
                    else{//остался 1 игрок
                        t=1;
                    }
                }
            }
            if(t==1){
                return 1;
            }
            if(t==2){//осталось 2 игрока, никто не голосует
                s="Осталось два игрока, они не могут договориться, кого убить (против себя голосовать нельзя)\n-----------------------------------------------------------";
                h.WriteLog(2,s);
                return 1;
            }
            int max_voice=-1;
            for(int i=0;i<voice_mas.length;i++){//ищем больше всего голосов против кого то
                if(max_voice<=voice_mas[i]){max_voice=voice_mas[i];}
            }//нашли за кого проголосовали больше всех (возможно за нескольких)
            int min_char=10,k=-1;
            for(int i=0;i<voice_mas.length;i++){//найдем кого среди них убить с минимальной харизмой
                if(max_voice==voice_mas[i]){
                    if(min_char>=players[i].getCharisma()){
                        min_char=players[i].getCharisma();
                        k=i;
                    }
                }
            }
            //в итоге в k номер того против кого больше всего голосов и он еще и с меньшей харизмой
            //убиваем его
            players[k].life=false;
            if(doctor.number==k){doctor.life=false;}
            if(mafias[0].number==k){mafias[0].life=false;}
            if(mafias[1].number==k){mafias[1].life=false;}
            //выводим в лог того "повешали такого то - такого то"
            s="На дневном голосовании было решено убить игрока #" + k + ", набравшего " + max_voice + " голоса(ов)\n-----------------------------------------------------------";
            h.WriteLog(2,s);
        }
        return 0;
    }
    public int Check(MafiaHeader h){ //-1 - победа мафии, 0 - игра продолжается, 1 - победа мирных
        int maf=0;
        int mirn=0;
        if(mafias[0].life){maf++;}
        if(mafias[1].life){maf++;}
        if(doctor.life){mirn++;}
        for(int i=0;i<citizens.length;i++){
            if(citizens[i].life){mirn++;}
        }
        if(mirn==0){
            return -1;
        }
        if(maf==0){
            return 1;
        }
        return 0;
    }
    
}
