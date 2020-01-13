package com.company;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.reflect.Array;
import java.util.*;

public class Adjacency_matrix extends Relationship{

    public static Adjacency_matrix Empty(int V) {
        Integer [][] data=new Integer[V][V];
        for(int i=0;i< V;i++){
            for (int j=0;j< V;j++){
                data[i][j]=0;
            }
        }
        return new Adjacency_matrix(data);
    }
    private Adjacency_matrix(Integer[][] data) {
        this.data = data;
    }
    Integer [][] data;
    @Override
    void createArc(int F, int S) {
    data[F][S]=1;
    data[S][F]=1;
    }
    @Override
    void deleteArc(int F, int S) {
        data[F][S]=0;
        data[S][F]=0;
    }
    @Override
    boolean isAdjacent(int F, int S) {
        if (data[F][S]==1)
            return  true;
        else
            return false;
    }
    @Override
    HashSet<Integer> allAdjacent(int arg) {
        LinkedList<Integer> rez=new LinkedList<>();
        for(int i=0;i<V_quantity();i++){
            if (data[arg][i]==1)rez.add(i);


        }

        return new HashSet<Integer>(rez);
    }
    @Override
    Integer V_quantity() {
        return data.length;
    }
    @Override
    Integer E_quantity() {
        Integer count=0;
        for(int i=0;i< data.length;i++){
            for (int j=0;j< data.length;j++){
               if (data[i][j] ==1){
                   count++;
                   if (i==j)count++; };

            }


        }


       return count/2;
    }
    @Override
    Adjacency_list toList(){
Adjacency_list rez=Adjacency_list.Empty(V_quantity());
        for(int i=0;i< data.length;i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][j] == 1) {
                    rez.createArc(i, j);

                }


            }
        }


return rez;
    }
    @Override
    Adjacency_matrix toMatrix(){return this;}
}
