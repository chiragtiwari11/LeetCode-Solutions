class Solution {
    int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n=grid.size();
        int[][] dist=new int[n][n];

        for(int[] r:dist) Arrays.fill(r,-1);

        Queue<int[]> q=new ArrayDeque<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid.get(i).get(j)==1){
                    dist[i][j]=0;
                    q.offer(new int[]{i,j});
                }
            }
        }

        // Multi-source BFS
        while(!q.isEmpty()){
            int[] cur=q.poll();
            for(int[] d:dir){
                int x=cur[0]+d[0],y=cur[1]+d[1];
                if(x>=0&&y>=0&&x<n&&y<n&&dist[x][y]==-1){
                    dist[x][y]=dist[cur[0]][cur[1]]+1;
                    q.offer(new int[]{x,y});
                }
            }
        }

        // Max Heap
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->b[2]-a[2]);
        boolean[][] vis=new boolean[n][n];

        pq.offer(new int[]{0,0,dist[0][0]});

        while(!pq.isEmpty()){
            int[] cur=pq.poll();
            int x=cur[0],y=cur[1],safe=cur[2];

            if(vis[x][y]) continue;
            vis[x][y]=true;

            if(x==n-1&&y==n-1) return safe;

            for(int[] d:dir){
                int nx=x+d[0],ny=y+d[1];
                if(nx>=0&&ny>=0&&nx<n&&ny<n&&!vis[nx][ny]){
                    pq.offer(new int[]{
                        nx,
                        ny,
                        Math.min(safe,dist[nx][ny])
                    });
                }
            }
        }
        return 0;
    }
}