package Sights;

/**
 * 图的边类
 */
public class Edge {
    
	/**
     * 边的尾部节点名称
     */
    String tailName;
    
    /**
     * 边的权值
     */
    int weight;
    
    /**
     * 头节点的其他边
     */
    Edge broEdge;
    
    /**
     * 边尾节点在节点列表中的位置
     */
    int adjvex;
    
    /**
     * 设置下一个节点
     */
    public void setBroEdge(Edge broEdge) {
    	this.broEdge = broEdge;
    }
    
    public Edge getEdge(int adjvex) {
    	if(broEdge.adjvex == adjvex) {
    		return broEdge;
    	}
    	if(broEdge.hasNextNode()){
			return broEdge.getEdge(adjvex);
		}
		return null;
    }
    
    public Edge getEdge(String s) {
    	if(broEdge.tailName.equals(s)) {
    		return broEdge;
    	}
    	if(broEdge.hasNextNode()) {
    		return broEdge.getEdge(s);
    	}
    	return null;
    }
    
    public boolean hasNextNode() {
		return (broEdge!=null);
	}
}