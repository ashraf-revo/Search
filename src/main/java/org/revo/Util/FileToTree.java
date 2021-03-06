package org.revo.Util;

import org.revo.Hoolder.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class FileToTree {
    // دى مثود بتقرا الفايل و تحولوا ل treeو تعمل return  root
    //Arad,366 Sibiu_140,Timisoara_118,Zerind_75
    // اول مرخ بعمل split space
    //Arad,366                                       Sibiu_140,Timisoara_118,Zerind_75
// بعمل split تانى ب ,
//    Arad                                                  366
//  بعد كدا    باخد القيمه الاولى ودى تبقا الفاليوا بتاعه النود     و الرقم بيبقا دا قيمه heuristic
    public static Node read(Path path) throws IOException {
        List<String> lines = Files.lines(path).collect(toList());
        List<Node> collect = lines.stream().map(s -> {
            String[] split = s.split(" ")[0].split(",");
            return new Node(split[0], Integer.valueOf(split[1]));
        }).collect(toList());

//  بعد كدا   بخزن كل node و مكانها فى listفى map
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < collect.size(); i++) {
            map.put(collect.get(i).getValue(), i);
        }

//بعد كدا باخد الجزء التانى
//Sibiu_140,Timisoara_118,Zerind_75
//        واعملهsplit ,
//        واخد كل قيمه اعمله split ب _
//        Arad//        القيمه الاولى هى leafو الرقم التانى هو المسافه بين القيمه الاولى    و القيمه الاولى من  اول المثود الى هى
        for (int i = 0; i < lines.size(); i++) {
            String[] split1 = lines.get(i).split(" ");
            if (split1.length > 1) {
                String[] split = split1[1].split(",");
                for (String s : split) {
                    String[] si = s.split("_");
                    Node parent = collect.get(i);
                    parent.AddNode(collect.get(map.get(si[0])), Integer.valueOf(si[1]));
                }
            }
        }
        return collect.get(0);
    }
}
