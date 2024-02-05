package aHARD500;

import java.util.*;

public class DirectoryNavigation {

    class Node {
        String path;
        Set<Node> children;

        public Node(String path) {
            this.path = path;
            children = new HashSet<>();
        }

    }

    int all = 0, dep = 0;
    Map<String, Node> nodes = new HashMap<>();
    Map<String, Boolean> visited = new HashMap<>();

    void rek(Node curr, int depth) {

        if (visited.containsKey(curr.path))
            return;
        visited.put(curr.path, true);
        // System.out.println("Children of " + curr.path);

        // for (Node child : curr.children) System.out.print(child.path + " ");
        // System.out.println();
        for (Node next : curr.children) {
            all += 2;
            rek(next, depth + 1);
            if (depth > dep)
                dep = depth;
        }
    }

    public int min(String[] directories) {
        all = 0;
        dep = 0;
        nodes.clear();
        visited.clear();
        Node root = new Node("/");
        nodes.put("/", root);
        for (String path : directories) {
            path = path.substring(1).replace('/', '-');
            String[] dirs = path.split("-");
            StringBuilder sb = new StringBuilder();
            Node parent = root;
            for (int i = 0; i < dirs.length; i++) {
                sb.append("/");
                sb.append(dirs[i]);

                if (!nodes.containsKey(sb.toString()))
                    nodes.put(sb.toString(), new Node(sb.toString()));

                parent.children.add(nodes.get(sb.toString()));
                if (i != dirs.length - 1) {
                    parent = nodes.get(sb.toString());
                }
            }
        }
        rek(root, 1);
        return all - dep;
    }

    // public static void main(String[] args) {
    // DirectoryNavigation d = new DirectoryNavigation();
    // System.out.println(d.min(new String[]{"/b", "/b/c", "/b/c/d", "/b/c/d/a/g/h",
    // "/b/c/d/a"}));
    // System.out.println(d.min(new
    // String[]{"/b","/b/c","/b/c/d/a/g/h","/b/c/d/a"}));
    // System.out.println(d.min(new String[]{"/b", "/b/c", "/b/c/d/a"}));
    // }
}
