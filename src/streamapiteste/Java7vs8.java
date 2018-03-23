/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamapiteste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author luciano
 */
public class Java7vs8 {
    
    Integer time1,time2,size=10000000;
    
    public void run(){
        //List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        
        //Implementação tradicional
        time1 = Calendar.getInstance().get(Calendar.MILLISECOND);
        for(Integer n: list){
            System.out.print(n);
        }
        System.out.println("\nTradicional(For)"+time1+" ms.");
        
        time2 = Calendar.getInstance().get(Calendar.MILLISECOND);
        //Implementação com expressões lambda e StreamAPI
        list.forEach(n -> System.out.print(n));
        System.out.println("\nLambda(Stream.forEach())"+time2+" ms.");
        
        String melhor;
        if(time1 < time2){
            melhor="Java 7 (For) é "+(time2 - time1)+"ms mais rápido. ("+size+")operações.";
        }else{
            melhor="Java 8 (StreamAPI) é "+(time1 - time2)+"ms mais rápido. ("+size+")operações.";
        }
       
        System.out.println(melhor);
    }
    
}   
