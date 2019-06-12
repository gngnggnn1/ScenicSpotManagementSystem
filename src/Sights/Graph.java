package Sights;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *图类, 在构造方法中完成图的构造
 */
public class Graph{
	
    /**
     * 图的节点个数
     */
    int verNum;
    
    /**
     * 图的边的条数
     */
    int edgeNum;
    
    /**
     * 邻接矩阵
     */
    int matrix[][];
    
    public int[][] getMatrix(){
		return this.matrix;
	}
    
    /**
     * 图的邻接表中存储节点的数组
     */
    ArrayList<Vertex> verArray = new ArrayList<Vertex>();
    ArrayList<String> vers = new ArrayList<String>();//存储节点名称
	ArrayList<String> edges = new ArrayList<String>();//存储各边信息
	ArrayList<String> edgesTodel = new ArrayList<String>();
	
    public void printSightList(Graph graph) { //输出邻接表
    	 for (int i=0;i<graph.verNum;i++){
             Vertex vertex = graph.verArray.get(i);
             System.out.print(vertex.verName);

             Edge current = vertex.edgeLink;
             while (current != null){
                 System.out.print("--"+current.weight+"-->"+current.tailName);
                 current = current.broEdge;
             }
             System.out.println();
    	 }
    }
    
    public void createSightMatrix(Graph graph) {//创建矩阵
    	verNum = verArray.size();
    	matrix = new int[graph.verNum][graph.verNum];//创建矩阵
        for(int m = 0; m < graph.verNum; m++) {
    		matrix[m][m] = 0;// 自己和自己没距离
    		for(int n = m+1; n < graph.verNum; n++) {
    			Edge current = verArray.get(m).getEdge(n);
    			if(current != null) {
    				matrix[m][n] = matrix[n][m] = current.weight;//添加元素
    				
    			}
    			else {
    				matrix[m][n] = matrix[n][m] = 32767;//不连通为32767
    			}
    		}
    	}
    }
    	
    public void printSightMatrix(Graph graph){//对外输出矩阵
    	verNum = verArray.size();
    	if(matrix==null){
			createSightMatrix(graph);
		}
		System.out.print("    ");
		for(int m = 0; m<verNum; m++) {
    		Vertex vertex = verArray.get(m);
    		System.out.print(vertex.verName + " ");
    	}
    	System.out.println();
    	for(int m = 0; m<verNum; m++) {
    		Vertex vertex = verArray.get(m);
    		System.out.print(vertex.verName + " ");
    		
    		for(int n = 0; n<verNum; n++) {
    			System.out.print(matrix[m][n]+" ");
    		}
    		System.out.println();
    	}
    	
    }
    
    public void getInfo() throws IOException {//从文件中获得景点信息
    	
    	File edgeFile = new File("Edges.txt");
    	BufferedReader edgein=new BufferedReader(new FileReader(edgeFile));;
		String edgeline=null;                                //将边信息存入edges表中
    	while((edgeline = edgein.readLine())!=null) {
    		edges.add(edgeline);
    	}
    	edgeNum = edges.size();
    	edgein.close();
    	
    	/////////////////////////////////////////////////
    	
    	File vertexFile = new File("Vertex.txt");//在存储节点名字文件上操作
    	BufferedReader verin=new BufferedReader(new FileReader(vertexFile));
		String verline=null;
    	
		while((verline = verin.readLine())!=null) {
    		vers.add(verline);
    	}
		verNum = vers.size();
		verin.close();
		
		for(int i = 0;i<verNum;i++) {//通过名字添加节点
			Vertex vertex = new Vertex();
			String[] verElement = (vers.get(i).trim()).split(" ");
            vertex.verName = verElement[0];
            vertex.welcomed = Integer.parseInt(verElement[1]);
            vertex.edgeLink = null;
            verArray.add(vertex);
		}
		
		for(int i = 0;i<edgeNum;i++) {
			String[] edgeElement = (edges.get(i).trim()).split("——");
			String preName = edgeElement[0];
            String weightstr = edgeElement[2];
            int weight = Integer.parseInt(weightstr);
            String folName = edgeElement[1];
            
            Vertex preV = getVertex(preName);//得到第一个节点
            Vertex folV = getVertex(folName);//第二个
            
            Edge edge1 = new Edge(); 
            for(int j = 0;j < verNum; j ++) {
            	if(verArray.get(j).getVerName().equals(folName)) {
            		edge1.adjvex = j;
            	}
            }
            edge1.tailName = folName; 
            edge1.weight = weight; //将边加入到节点的链表中去 
            edge1.broEdge = preV.edgeLink;
            preV.edgeLink = edge1; 
            
            Edge edge2 = new Edge(); //无向图 
            for(int j = 0;j < verNum; j ++) {
            	if(verArray.get(j).getVerName().equals(preName)) {
            		edge2.adjvex = j;
            	}
            }
            edge2.weight = weight; 
            edge2.tailName = preName;
            edge2.broEdge = folV.edgeLink;
            folV.edgeLink = edge2;
		}
	}
    
    /**
     * 根据节点名称获取该节点
     * @param verName 节点的名称
     * @return 节点或null
     */
    public Vertex getVertex(String verName){
        for (int i=0;i<verNum;i++){
            if (verArray.get(i).getVerName().equals(verName))
                return verArray.get(i);
        }
        return null;
    }
    
    public void welcomeSort() {//冒泡排序对景点进行排序
    	Vertex cup;
    	for(int i=0;i<verNum-1;i++){//外层循环控制排序趟数
    	    for(int j=0;j<verNum-1-i;j++){//内层循环控制每一趟排序多少次
    	    	if(verArray.get(j).getWelcomed()>verArray.get(j+1).getWelcomed()){
    	    		cup = verArray.get(j);
    	    		verArray.set(j, verArray.get(j+1));
    	    		verArray.set(j+1, cup);
    	    	}
    	    }
    	}
    	for(int i=0;i<verNum;i++) {
    		System.out.println(verArray.get(i).getVerName() + ": " + verArray.get(i).getWelcomed());
    	}
    	
    }
    
    public int getverNum() {
    	return verNum;
    }
    
    public int getedgeNum() {
    	return edgeNum;
    }
    
    
    public void addVertex(String newFolname,int newWelcomed,int newWeight,String newPrename,String description) {//管理员添加景点
		
		vers.add(newFolname + " " +newWelcomed + " " +description);// 向vers加入新的节点信息
		verNum++;
		edges.add(newFolname + "——" + newPrename + "——" + newWeight);//向edges加入新的边信息
		edgeNum++;
		
		Vertex vertex = new Vertex();//通过名字添加节点
		vertex.verName = newFolname;
		vertex.welcomed = newWelcomed;
		vertex.edgeLink = null;
		verArray.add(vertex);
		
		Vertex preV = getVertex(newPrename);//与新节点连接的旧节点:北门
	    Vertex folV = getVertex(newFolname);//新节点:ad
	   
	    Edge edge1 = new Edge();//新节点——北门
	    edge1.tailName = newPrename;
	    edge1.weight = newWeight;
	    edge1.broEdge = folV.edgeLink;
	    folV.edgeLink = edge1;
	    
	    Edge edge2 = new Edge();
	    edge2.tailName = newFolname;
	    edge2.adjvex = verNum-1;
	    edge2.weight = newWeight;
	    edge2.broEdge = preV.edgeLink;
	    preV.edgeLink = edge2;
	    
	    verNum = verArray.size();
	
    }
    
    public void deleEdge(String sight1,String sight2) {
    	//共2种情况，1为顶点后面的边 2为边的下一个边
    	if(getVertex(sight1).edgeLink.equals(getVertex(sight1).getEdge(sight2))) {//情况1
    		getVertex(sight1).setEdgeLink(getVertex(sight1).edgeLink.broEdge);
    	}
    	
    	else{    //情况2
    		Edge signEdge = getVertex(sight1).edgeLink;
    		getVertex(sight1).setEdgeLink(signEdge);
    		if(signEdge.broEdge.equals(getVertex(sight1).getEdge(sight2))) {
    			signEdge.broEdge = getVertex(sight1).getEdge(sight2).broEdge;
    		}
    		else {
    			signEdge = signEdge.broEdge;
    		}
    		
    	}
		
    	///////////////////////////////////////////考虑无向图
		if(getVertex(sight2).edgeLink.equals(getVertex(sight2).getEdge(sight1))) {//情况1
		getVertex(sight2).setEdgeLink(getVertex(sight2).edgeLink.broEdge);
		}
    	
    	else{    //情况2
    		Edge signEdge = getVertex(sight2).edgeLink;//
    		getVertex(sight2).setEdgeLink(signEdge);
    		boolean flag = true;
    		while(flag) {
	    		if(signEdge.broEdge.equals(getVertex(sight2).getEdge(sight1))) {
	    			signEdge.broEdge = getVertex(sight2).getEdge(sight1).broEdge;
	    			flag = false;
	    		}
	    		else {
	    			signEdge = signEdge.broEdge;
	    		}
    		}
    	}
    	
    }

	public void deleVer(String sight) {
		for(int i=0;i<verNum;i++) {
    		Edge flagEdge = verArray.get(i).edgeLink;
    		verArray.get(i).setEdgeLink(flagEdge);
    		if(flagEdge.tailName.equals(sight)) {
    			verArray.get(i).setEdgeLink(flagEdge.broEdge);
    		}
    		else {
    			if(flagEdge.broEdge.tailName.equals(sight)) {
    				flagEdge.broEdge = flagEdge.broEdge.broEdge;
    			}
    			else {
    				flagEdge = flagEdge.broEdge;
    			}
    		}
    	}
		verArray.remove(getVertex(sight));
    	verNum = verArray.size();
    }
    
    int[] Dijsktra(int[][] matrix,int start){
    		 
        int n = verNum;
        //存放从start到其他各点的最短路径
        int[] shortPath = new int[n];
        //存放从start到其他各点的最短路径的字符串表示
        String[] path=new String[n];
        for(int i=0;i<n;i++)
        {	
        	String s = verArray.get(start).verName;
            path[i] = s + "-->" + verArray.get(i).verName;
        }
        //标记当前该顶点的最短路径是否已经求出,1表示已求出
        int[] visited = new int[n];
 
        shortPath[start] = 0;
        visited[start] = 1;
        for(int count = 1;count <= n - 1;count++)
        {
            //选出一个距离初始顶点start最近的未标记顶点
            int k = -1;
            int dmin = Integer.MAX_VALUE;
            for(int i = 0;i < n;i++){
                if(visited[i] == 0 && matrix[start][i] < dmin)
                {
                    dmin = matrix[start][i];
                    k = i;
                }
            }
            //将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
            shortPath[k] = dmin;
            visited[k] = 1;
            //以k为中间点，修正从start到未访问各点的距离
            for(int i = 0;i < n;i++)
            {
                if(visited[i] == 0 && matrix[start][k] + matrix[k][i] < matrix[start][i])
                {
                    matrix[start][i] = matrix[start][k] + matrix[k][i];
                    String t = verArray.get(i).verName;
                    path[i]=path[k]+"-->"+t;
                }
            }
        }
        for(int i=0;i<n;i++)
        {	String to = verArray.get(i).verName;
        	String from = verArray.get(start).verName;
            System.out.println("从"+from+"出发到"+to+"的最短路径为："+path[i]);
        }
        System.out.println("=====================================");
        return shortPath;
    }
    
    public void shortestPath(String startVer) {
    	int start = 0;
    	for(int i=0;i<verNum;i++) {
    		if(verArray.get(i).verName.equals(startVer))
    			start = i;
    	}
    	System.out.println(start);
    	int[] shortPath = Dijsktra(matrix,start);
        for(int i = 0;i < shortPath.length;i++){
        	String to = verArray.get(i).verName;
            System.out.println("从"+startVer+"出发到"+ to +"的最短距离为："+shortPath[i]);
        }
    }
    
    public void searchSight(String keyWord) {
    	String result = null;
    	for(int i= 0;i<vers.size();i++) {
    		if(vers.get(i).contains(keyWord)) {
    			result = vers.get(i);
    		}
    	}
    	if(result!=null) {
    		System.out.println("结果为");
        	System.out.println(result);
    	}
    	else {
    		System.out.println("没找到");
    	}
    }
    
    public void getHamiltonCircuit(String begin,String end) {
    	int beginNum = 0;
    	int endNum = 0;
    	for(int k=0;k<verNum;k++) {
    		if(verArray.get(k).verName.equals(begin)) {
    			beginNum = k;
    		}
    	}
    	
    	for(int k=0;k<verNum;k++) {
    		if(verArray.get(k).verName.equals(end)) {
    			endNum = k;
    		}
    	}
    	boolean[] used = new boolean[matrix.length];       //用于标记图中顶点是否被访问
        int[] path = new int[matrix.length];       //记录哈密顿回路路径
        for(int i = 0;i < matrix.length;i++) {
            used[i] = false;     //初始化，所有顶点均未被遍历
            path[i] = -1;        //初始化，未选中起点及到达任何顶点
        }
        
        used[beginNum] = true;          //表示从第1个顶点开始遍历
        path[0] = beginNum;             //表示哈密顿回路起点为第0个顶点
        dfs(matrix, path, used, 1,beginNum,endNum);     //从第0个顶点开始进行深度优先遍历,如果存在哈密顿回路，输出一条回路，否则无输出
    }
    /*
     * 参数step:当前行走的步数，即已经遍历顶点的个数
     */
    public boolean dfs(int[][] matrix, int[] path, boolean[] used, int step,int beginNum,int endNum) {
        if(step == matrix.length) {     //当已经遍历完图中所有顶点
            if(matrix[path[step - 1]][endNum] != 32767) { //最后一步到达的顶点能够回到起点
                for(int j = 0;j < path.length;j++)
                    System.out.print((verArray.get(path[j]).verName)+"——>");
	                System.out.print(verArray.get(path[endNum]).verName);
	                System.out.println();
	                return true;
            }
            return false;
        } 
        else {
            for(int i = 0;i < matrix.length;i++) {
                if(!used[i] && matrix[path[step - 1]][i] != 32767) {
                    used[i] = true;
                    path[step] = i;
                    if(dfs(matrix, path, used, step + 1,beginNum,endNum))
                        return true;
                    else {
                        used[i] = false;    //进行回溯处理
                        path[step] = -1;
                    }
                }
            }
        }
        return false;
    }
    
}