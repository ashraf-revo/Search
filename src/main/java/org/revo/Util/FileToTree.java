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
    public static Node read(Path path) throws IOException {
        List<String> lines = Files.lines(path).collect(toList());
        List<Node> collect = lines.stream().map(s -> {
            String[] split = s.split(" ")[0].split(",");
            return new Node(split[0], Integer.valueOf(split[1]));
        }).collect(toList());


        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < collect.size(); i++) {
            map.put(collect.get(i).getValue(), i);
        }


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
