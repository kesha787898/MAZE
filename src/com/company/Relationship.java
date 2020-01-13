package com.company;
import java.util.HashSet;
public  abstract class Relationship {
abstract void createArc(int F,int S);
abstract void deleteArc(int F,int S);
abstract boolean isAdjacent(int F,int S);
abstract HashSet<Integer> allAdjacent(int arg);
abstract Integer V_quantity();
abstract Integer E_quantity();
abstract Adjacency_list toList();
abstract Adjacency_matrix toMatrix();
}