//sample.txt为2维点集
//result.txt为结果
题目 :
	Assignment II (b) Spatial Clusterring
  There are 100,000 points in 2D space.
Try any kind of cluster algorithm to get the final results.
  Because there are so many data here, any hierachical algorithm, 
grid algorithm or preprocessing should be concidered to decrease the time cost. 
  The sample data are in a separate file ‘sample.txt’.
	


程序说明：
	 本程序采用clique算法，先将点所分布的二维空间(由xmax,xmin,ymax,ymin确定)化成子单元，读取文件。
然后统计每个子单元的点个数，也就是单位密度。当子单元密度大于一定阈值(densityThreshold)的时候 
就认为该单元可以被聚类。当统计完单元格信息后，开始聚类，扫描单元格，当发现密度大于阈值的点时，创建新的聚类，
然后递归吸收其邻居，当邻居的密度大于阈值，且其密度与整个聚类密度的比大于密度比阈值（densityRatioThreshold）时
将被吸收到此聚类中，直至此类别聚类结束。继续扫描未检查过的单元格，重复上面的步骤，直至所有单元格被检查过。聚类结束。
	程序包含Cell.java //单元格信息
		  Cluster.java//类别信息
		  MyClusterByClique.java//clique聚类
		  ClusterConfig.java//聚类信息
	其中ClusterConfig.java//聚类信息拥有如下变量 
		 densityThreshold = 20 ; //密度阈值，聚类中每个单元含有点的最小数目 
		 densityRatioThreshold = 0.6;//新单元与簇的密度比最小值
		 double xmin = 0.0;
		 double xmax = 30.0;
		 double ymin = 0.0;
		 double ymax = 30.0;
		 String filePath = "D:\\myproject\\MyEclipse6\\JavaStudy\\mycode\\sample.txt";
		//filePath为样本文件路径
		int cellColumn = 60;//单元格列数
		int cellRow = 60;//单元格行数
		double intervalx = (xmax - xmin) / cellColumn;
		double intervaly = (ymax - ymin) / cellRow;
		public static String outPath = "D:\\myproject\\MyEclipse6\\JavaStudy\\mycode\\result.txt";
		//outPath为输出结果文件路径		.
		
	针对不同的样本点，更改	ClusterConfig.java中的变量参数，满足不同聚类的需要。  