package com.company;
import java.util.HashMap;
import java.util.HashSet;
public class Adjacency_list extends  Relationship {
    public static Adjacency_list Empty(Integer V) {
        HashMap<Integer, HashSet<Integer>> rez=new HashMap<Integer, HashSet<Integer>>(V);
        for (int i=0;i<V;i++)rez.put(i,new HashSet<Integer>() );
        return new Adjacency_list(rez);
    }
    private Adjacency_list(HashMap<Integer, HashSet<Integer>> data) {
        this.data = data;
    }
    private HashMap<Integer, HashSet<Integer>> data;
    @Override
    void createArc(int F, int S) {
        data.get(F).add(S);
        data.get(S).add(F);
    }
    @Override
    void deleteArc(int F, int S) {
        data.get(F).remove(S);
        data.get(S).remove(F);
    }
    @Override
    boolean isAdjacent(int F, int S) {
        if (data.get(F).contains((S)))
            return true;
        else
            return false;
    }
    @Override
    HashSet<Integer> allAdjacent(int arg) {
        return data.get(arg);

    }
    @Override
    Integer V_quantity() {
        return data.size();
    }
    @Override
    Integer E_quantity() {
        Integer count=0;
        for (int i=0;i<E_quantity();i++){
          if (data.get(i).contains(i) )count++;
          count+=data.get(i).size();
        }
        return count/2;
    }

    @Override
    Adjacency_list toList() {return this;}
    @Override
    Adjacency_matrix toMatrix(){
        Adjacency_matrix rez=Adjacency_matrix.Empty(V_quantity());
        for (int i=0;i<data.size();i++) {
            for (int e:
                    data.get(i)) {
                rez.createArc(i,e);
            }
        }
        return rez;
    }

}
