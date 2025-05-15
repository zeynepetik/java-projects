package src.PlanetSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a hierarchical tree structure for a planetary system.
 * Each node in the tree represents a celestial body.
 */
class Tree {
    private TreeNode root;

    /** @param root the root node of the tree */
    Tree(TreeNode root){
        this.root=root;
    }

     /** Constructs an empty tree */
    Tree(){
        root= null;
    }

    /**
     * Constructs a tree with a root node.
     * @param name the name of the root node
     * @param type the type of the node (e.g., Star)
     * @param temp temperature
     * @param rad radiation
     * @param hum humidity
     * @param pres pressure
     */
    Tree(String name, String type, double temp, double rad, double hum, double pres){
        if(type.equals("Star") && hum!=0.0){
            throw new IllegalArgumentException("Humidity can be just 0.0 for sun. \n");
        }
        if(hum<0.0 || hum>100.0){
            throw new IllegalArgumentException("Humidity can be between 0.0 and 100.0. \n");
        }
        if(rad<0.0){
            throw new IllegalArgumentException("Radiation can not be negative. \n");
        }
        if(temp<0.0){
            throw new IllegalArgumentException("Temperature can not be lower than absolute temperature 0.0 Kelvin. \n");
        }
        SensorData data = new SensorData(temp, hum, pres, rad);
        root = new TreeNode(name, type, data);
    }

    /** @return the root node */
    TreeNode getRoot(){
        return root;
    }

    /** @return true if tree is empty */
    boolean isEmpty(){
        return root==null;
    }

    /**
     * Recursively finds a node by its name in the tree.
     * @param node
     * @param name
     * @return
     */
    private TreeNode findNode(TreeNode node, String name){
        if(node.getName().equals(name)){
            return node;
        }
        for(TreeNode child : node.getChildren()){
            TreeNode result= findNode(child, name);
            if(result!= null){
                return result;
            }
        }
        return null;
    }

    /**
     * Adds a planet to the tree under the specified parent.
     * @return true if added successfully
     */
    boolean addPlanet(String planet, String parentName, double temp, double press, double hum, double rad){
        if(root == null){
            return false;
        }
        if(findNode(root, planet)!=null){
            throw new IllegalArgumentException("There is already a node with the same name.\n");
        }
        TreeNode parentNode=findNode(root,parentName);
        if(parentNode==null){
            return false;
        }
        if(!parentNode.getType().equals("Star") && !parentNode.getType().equals("Planet")){
            throw new IllegalArgumentException("Parent node of a planet must be star or planet.\n");
        }
        SensorData data = new SensorData(temp, hum, press, rad);
        TreeNode newPlanet= new TreeNode(planet, "Planet", data);
        parentNode.addChild(newPlanet);
        return true;
    }

    /**
     * Adds a satellite to the tree under the specified parent.
     * @return true if added successfully
     */
    boolean addSatellite(String satName, String parentName, double temp, double hum, double rad, double press){
        if(root==null){
            return false;
        }
        if(findNode(root, satName)!=null){
            throw new IllegalArgumentException("There is already a node with the same name.\n");
        }
        TreeNode parentNode=findNode(root,parentName);
        if(parentNode==null){
            return false;
        }
        SensorData data= new SensorData(temp,hum,press,rad);
        TreeNode newSat= new TreeNode(satName,"Satellite",data);
        parentNode.addChild(newSat);
        return true;
    }

    /**
     * Recursively finds nodes with radiation levels above the threshold.
     * @param node
     * @param threshold
     * @return
     */
    private List<TreeNode> findRadiationAnomalies(TreeNode node,double threshold){
        List<TreeNode> anomalies=new ArrayList<>();
        if(node==null){
            return anomalies;
        }
        if(node.getSensorData().getRad()>threshold){
            anomalies.add(node);
        }
        for(TreeNode child : node.getChildren()){
            anomalies.addAll(findRadiationAnomalies(child, threshold));
        }
        return anomalies;
    }
    /**
    * Wrapper method 
    * Returns a list of nodes with radiation levels above the threshold.
    */
    List<TreeNode> getRadiationAnomalies(double threshold){
        return findRadiationAnomalies(root, threshold);
    }

    /**
     * Finds the path to the given node name from the root.
     * @return stack containing the path or empty if not found
     */
    Stack<String> getPathTo(String planetName){
        Stack<String> path=new Stack<>();
        if(findPathTo(root,planetName,path)){
            return path;
        }
        else{
            /*Since path has data coming from finPathTo function it is need to be cleaned*/
            path.clear();
            return path;
        }
    }
    /**
     * Recursively finds the path to the specified node name.
     * @param node
     * @param nodeName
     * @param path
     * @return
     */
    private boolean findPathTo(TreeNode node, String nodeName, Stack<String> path){
        if(node==null){
            return false;
        }

        path.push(node.getName());

        if(node.getName().equals(nodeName)){
            return true;
        }

        for(TreeNode child: node.getChildren()){
            if(findPathTo(child,nodeName,path)){
                return true;
            }
        }
        /*After controlling all of the properties of the branch if not found this node should be removed. */
        path.pop();
        return false;
    }

    /**
     * Prints a report starting from the specified node.
     * @param planetName the node to start the report from
     */
    void printMissionReport(String planetName){
        if(root==null){
            throw new IllegalStateException("No planetary system found. \n");
        }
        TreeNode node= findNode(root,planetName);
        if(node==null){
            throw new IllegalStateException("Node with name "+ planetName+" not found. \n");
        }
        System.out.println(planetName+" => "+ node.getSensorData().getTemp()+" Kelvin "+ node.getSensorData().getHum()+" Percentage "+ node.getSensorData().getPres()+" Pascals "+node.getSensorData().getRad()+" Sieverts");
        System.out.println("\nEnd of the report. \n");
    }

    /** Prints a report for the whole system from the root. */
    void printMissionReport(){
        if(root==null){
            throw new IllegalStateException("No planetary system found. \n");
        }
        System.out.println(root.getName()+" => "+ root.getSensorData().getTemp()+" Kelvin "+root.getSensorData().getHum()+" Percentage "+root.getSensorData().getPres()+" Pascals "+root.getSensorData().getRad()+" Sieverts");
        printTree(root,0);
        System.out.println("\nEnd of the report. \n");
    }
    /**
     * Recursively prints the tree structure starting from the given node.
     * @param node
     * @param depth
     */
    private void printTree(TreeNode node,int depth){
        String order="";
        for(int i=0;i<=depth;i++){
            order+="    ";
        }
        for(TreeNode child: node.getChildren()){
            System.out.println(order+child.getName()+" => "+ child.getSensorData().getTemp()+" Kelvin "+child.getSensorData().getHum()+" Percentage "+child.getSensorData().getPres()+" Pascals "+child.getSensorData().getRad()+" Sieverts");
            printTree(child, depth+1);
        }
    }

    
}
