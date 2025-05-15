package src.PlanetSystem;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a node in the planetary system tree. Each node could be a star, planet, or satellite.
 */
public class TreeNode{
    private String name;
    private String type;
    private SensorData sensorData;
    List<TreeNode> children;

    /**
     * Constructs a new TreeNode with the specified properties.
     * @param name the name of the node
     * @param type the type of the node (e.g., Star, Planet, Satellite)
     * @param data the environmental sensor data associated with this node
     */
    public TreeNode(String name, String type, SensorData data){
        this.name = name;
        this.type = type;
        this.sensorData = data;
        this.children = new ArrayList<TreeNode>();
    }

    /** @return the name of the node */
    public final String getName(){
        return this.name;
    }

    /** @return the type of the node */
    public final String getType(){
        return this.type;
    }

    /** @return the environmental sensor data associated with this node */
    public final SensorData getSensorData(){
        return this.sensorData;
    }

    /**
     * Adds a child node to this node.
     * @param child the TreeNode to be added
     */
    public void addChild(TreeNode child){
        children.add(child);
    }

     /** @return a list of child nodes */
    public List<TreeNode> getChildren(){
        return this.children;
    }

    /** @return the number of children */
    public int getChildNumber(){
        return children.size();
    }
}