package pr2.tree;

/*
 * Copyright (C) 2007-2014 by Brett Alistair Kromkamp <brett@polishedcode.com>.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Tree {

    private final static int ROOT = 0;

    private HashMap<String, Node> nodes;
    private TraversalStrategy traversalStrategy;
    private String root;

    // Constructors
    public Tree() {
        this(TraversalStrategy.DEPTH_FIRST);
    }

    public Tree(TraversalStrategy traversalStrategy) {
        this.nodes = new HashMap<String, Node>();
        this.traversalStrategy = traversalStrategy;
        this.root = "";
    }
    
    public String getRoot(){ return root; }

    // Properties
    public HashMap<String, Node> getNodes() {
        return nodes;
    }

    public TraversalStrategy getTraversalStrategy() {
        return traversalStrategy;
    }

    public void setTraversalStrategy(TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }

    // Public interface
    public Node addNode(String identifier) {
    	root = identifier;
        return this.addNode(identifier, null);
    }

    public Node addNode(String identifier, String parent) {
        Node node = new Node(identifier);
        nodes.put(identifier, node);

        if (parent != null) {
            nodes.get(parent).addChild(identifier);
        }

        return node;
    }

    public void display(String identifier) {
        this.display(identifier, ROOT);
    }

    public void display(String identifier, int depth) {
        ArrayList<String> children = nodes.get(identifier).getChildren();

        if (depth == ROOT) {
            System.out.println(nodes.get(identifier).getIdentifier());
        } else {
            String tabs = String.format("%0" + depth + "d", 0).replace("0", "    "); // 4 spaces
            System.out.println(tabs + nodes.get(identifier).getIdentifier());
        }
        depth++;
        for (String child : children) {

            // Recursive call
            this.display(child, depth);
        }
    }
    
    public String muestra() {
    	
    	StringBuffer salida = new StringBuffer();
    	
    	return pdisplay(salida, root, ROOT).toString();
    	
    }
    
    private StringBuffer pdisplay(StringBuffer salida, String identifier, int depth) {
        ArrayList<String> children = nodes.get(identifier).getChildren();

        if (depth == ROOT) {
            salida.append("(" + nodes.get(identifier).getIdentifier() + ")" + "\n");
        } else {
            String tabs = String.format("%0" + depth + "d", 0)
            						.replace("0", " \u217C    ")
            						.replaceFirst("\u217C", " "); // 4 spaces"
            salida.append(tabs + " \u0370 (" +
            						nodes.get(identifier).getIdentifier() +
            						")" + "\n");
        }
        depth++;
        for (String child : children) {

            // Recursive call
        	this.pdisplay(salida, child, depth);
        }
        
        return salida;
    }
    
    public void toRules(List<String> rules, String rule, String whoami){
    	
    	StringBuffer sb = new StringBuffer(rule);
    	
    	if (whoami == null) whoami = root;
    	
    	ArrayList<String> children = nodes.get(whoami).getChildren();
    	
    	if (children.isEmpty()){
    		
    		sb.append(">" + whoami);
    		
    		rules.add(sb.toString());
    		
    		return;
    		
    	}
    	
    	if (rule.equals("")) sb.append("|" + whoami + "=");
    	else if (rule.endsWith("|")) sb.append("&|" + whoami + "=");
    	else if (rule.endsWith("=")) sb.append(whoami + "|");
    	
    	for (String child : children) {
			
    		toRules(rules, sb.toString(), child);
    		
		}
    }
    
    

    public Iterator<Node> iterator(String identifier) {
        return this.iterator(identifier, traversalStrategy);
    }

    public Iterator<Node> iterator(String identifier, TraversalStrategy traversalStrategy) {
        return traversalStrategy == TraversalStrategy.BREADTH_FIRST ?
                new BreadthFirstTreeIterator(nodes, identifier) :
                new DepthFirstTreeIterator(nodes, identifier);
    }
}
