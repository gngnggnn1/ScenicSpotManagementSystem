package Sights;

public class Vertex {
    /**
     * 节点存储的内容
     */
    String verName;
    /**
     * 顶点的边链
     */
    Edge edgeLink;
    /**
     * 景点欢迎度
     */
    int welcomed;
    /**
     * 下标
     */
    int verIndex;
    
    /**
     * 设置下一个节点
     */
    public void setEdgeLink(Edge edgeLink) {
    	this.edgeLink = edgeLink;
    }
    
    public Edge getEdge(int n) {
    	if(edgeLink.adjvex == n) {
    		return edgeLink;
    	}
    	if(edgeLink.hasNextNode()){
			return edgeLink.getEdge(n);
		}
		return null;
    }
    
    public Edge getEdge(String s) {
    	if(edgeLink.tailName.equals(s)) {
    		return edgeLink;
    	}
    	if(edgeLink.hasNextNode()) {
    		return edgeLink.getEdge(s);
    	}
    	return null;
    }
    
    public String getVerName() {
    	return verName;
    }
    
    public int getWelcomed() {
    	return welcomed;
    }
    
    public void setWelcomed(int welcomed) {
    	this.welcomed = welcomed;
    }

}