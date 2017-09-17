/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mafiaheader;
import java.util.Random;
/**
 *
 * @author Андрей
 */
public class Constants {
    //пути для картинок
    public String stz;
    public String doc;
    public String maf;
    public int count_of_player;
    public Random rnd;
    public Constants(){
        stz="/my/mafiaheader/imagepackage/ctz.jpg";
        doc="/my/mafiaheader/imagepackage/doc.jpg";
        maf="/my/mafiaheader/imagepackage/maf.jpg";
        count_of_player=0;
        rnd = new Random();
    }
}
