package org.revo.Search;

import org.revo.Hoolder.Node;
import org.revo.Hoolder.Result;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Supplier;



//كل طرق السيرش التلاته زى بعض  بس الاختتلاف بيبقا فى
//1    getCmp      دى مثود  كل طريقه سرش  بتحط بتعمل امبلمنتيشن لازم تككتب الكود الخاص بيها
//2    getTotalCost   دى مثود بتحسب الكوست   كل طريقه سيرش بتحط هى امبلمنتيشن الخاص بيها
public interface BaseSearch {

//مثود find ثابته فى كلوا لانى بشتغل فى التلات طرق ب queue وهى انوا يحط فى الكيوا اول قيمه وبعدين يسحبها و بعدين يعملها visit و ببعين يبحث بيها و بعدين يحط childبتوعها فى queueواعمل لوب لحد ما queue يخلص
    //  بتاخد 2 argument  الاولى Supplier<Node>sroot
//    ودا قيمه بتحفظ rootبتاع tree
    //String s
    //دى القيمه الى بيعمل searchبيها
//برجع resultو دى قيمه بتحمل pathو القيمه الى ببحث بيها
    default Result find(Supplier<Node> sroot, String s) {
        PriorityQueue<Node> queue = new PriorityQueue<>(this.getCmp());
        Result result = new Result();
        queue.add(sroot.get());
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            n.setVisited(true);
            result.addToPath(n);
            if (n.getValue().equals(s)) {
                return result.setOut(n);
            }
            for (Node v : n.getChiled().keySet()) {
                if (v.isVisited()) {
                    continue;
                }
                setMinParent(n, v);
                queue.add(v);
            }
        }
        return result;
    }
//  دى مثود بتحسب اقل باص   هستخدمها لو عندى قيمتين فى queueليهم نفس الاسم بس اقدر اوصلهم من طريقين مختلفين ف انا هختار اقل طريق
    default void setMinParent(Node parent, Node node) {
        if (node.getParent() == null) node.setParent(parent);
        else {
            int totalCost1 = getTotalCost(node);
            Node temp = node.getParent();
            node.setParent(parent);
            int totalCosts2 = getTotalCost(node);
            if (totalCost1 < totalCosts2) node.setParent(temp);
            else node.setParent(parent);
        }
    }

    Comparator<Node> getCmp();

    int getTotalCost(Node node);
//  دى مثود بتحسب g  عن طريق ادخل فى لوب و اقعد اجمع المسافات للparent بتاعى  و اجمعهم
    default int getG(Node sNode) {
        int cost = 0;
        while (sNode != null) {
            Integer integer = sNode.getChiled().get(sNode.getParent());
            if (integer != null) {
                cost += integer;
            }
            sNode = sNode.getParent();
        }
        return cost;
    }
}
