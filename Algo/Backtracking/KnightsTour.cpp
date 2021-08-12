#include <iostream>
#include <vector>
using namespace std;

bool isSafe(int x, int y, vector< vector<int> >& ans, int n){
	return (x>=0 && y>=0 && x<n && y<n && ans[x][y]==-1); 
}

bool tour(vector<vector<int>>& ans,int n, int ctr=0, int x=0, int y=0){
	if(ctr==(n*n)-1){
		ans[x][y]=ctr;
		return true;
	}

	else{
		ans[x][y]=ctr;
		ctr++;
		bool flag=false;
		if((!flag) && isSafe(x+2,y+1,ans,n)){
			flag=tour(ans,n,ctr,x+2,y+1);
		}
		if((!flag) && isSafe(x+2,y-1,ans,n)){
			flag=tour(ans,n,ctr,x+2,y-1);
		}
		if((!flag) && isSafe(x-2,y+1,ans,n)){
			flag=tour(ans,n,ctr,x-2,y+1);
		}
		if((!flag) && isSafe(x-2,y-1,ans,n)){
			flag=tour(ans,n,ctr,x-2,y-1);
		}
		if((!flag) && isSafe(x+1,y+2,ans,n)){
			flag=tour(ans,n,ctr,x+1,y+2);
		}
		if((!flag) && isSafe(x+1,y-2,ans,n)){
			flag=tour(ans,n,ctr,x+1,y-2);
		}
		if((!flag) && isSafe(x-1,y+2,ans,n)){
			flag=tour(ans,n,ctr,x-1,y+2);
		}
		if((!flag) && isSafe(x-1,y-2,ans,n)){
			flag=tour(ans,n,ctr,x-1,y-2);
		}
		if(flag) return true;
		else{
			ans[x][y]=-1;
			return false;
		}
	}

}

int main(){
	ios::sync_with_stdio(false);
	cin.tie(0);
	
	int n;
	cin>>n;
	vector< vector<int> > ans(n,vector<int> (n));
	for(int i=0; i < n; i++){
		for(int j=0; j < n; j++){
			ans[i][j]=-1;
		}
	}
	
	tour(ans, n);

	for(int i=0; i < n; i++){
		for(int j=0; j < n; j++){
			cout<<ans[i][j]<<" ";
		}
		cout<<"\n";
	}

	return 0;
}
