package org.revo.Search.Impl;


import org.revo.Hoolder.Node;
import org.revo.Search.BaseSearch;

import java.util.Comparator;

public class Gready implements BaseSearch {
    //فى مثود جاهزه فى الجافا هو compareTo ودى بترجع قيمه trueلو القيمه الاولى اكبر  و false لو القيمه التانيه هى الى اكبر
    @Override
    public Comparator<Node> getCmp() {
        return (o1, o2) -> Integer.valueOf(getTotalCost(o1)).compareTo(getTotalCost(o2));
    }

    // فى greadyبحسب الكوست عن طريق h
    @Override
    public int getTotalCost(Node node) {
        return node.getH() ;
    }
}

