#include <bits/stdc++.h> 
using namespace std; 
#define ll long long
#define pb push_back
#define mod 1000000007
void dfs(vector<int>graph[],int ind,bool vis[],int component[],int count)
{
    if(vis[ind]==1)return;
    vis[ind] = 1;
    component[ind] = count;
    vector<int>ne = graph[ind];
    for (auto i : ne) 
        dfs(graph ,i, vis,component,count); 
    
}
int main()
{
    int t;
    cin>>t;
    while(t--)
    {
        ll n,e;
        cin>>n>>e;
        vector<int> graph[n+1]; 
        ll u,v;
        for(int i=0;i<e;i++)
        {
            cin>>u>>v;
            graph[u].pb(v); 
            graph[v].pb(u); 
        }
        bool vis[n+1] = {0};
        int component[n+1] = {0};
        int count = 0;
        for(int i=1;i<n+1;i++)
        {
            if(!vis[i])
            {
                dfs(graph,i,vis,component,count);
                count++;
            }
        }
        cout<<"Total Connected Components : "<<count<<"\n";
        for(int i=1;i<n+1;i++)
        {
          cout<<i<<" "<<component[i]<<"\n";
        }
    }
    
    return 0;
}
