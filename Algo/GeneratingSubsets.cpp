#include <iostream>
#include <vector>
using namespace std;

void Search(int k,int n,vector<int> subsets)
{
	if(k==n){
		cout<<"[";
		for(int i=0;i<subsets.size();i++){
			cout<<subsets[i]<<", ";
		}
		cout<<"]";
		cout<<endl;
		return;
	}
	Search(k+1,n,subsets);
	subsets.push_back(k);
	Search(k+1,n,subsets);
	subsets.pop_back();
}

void Search_using_bits(int n){
	for(int i=0;i<(1<<n);i++){
		vector<int> subsets;
		int b=i;
		int ctr=0;
		for(b;b>0;b=b>>1){
			if(b&1==1) subsets.push_back(ctr);
			ctr++;
		}
		cout<<"[";
		for(int j=0;j<subsets.size();j++){
			cout<<subsets[j]<<",";
		}
		cout<<"] \n";
	}
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	vector<int> subsets;
	Search_using_bits(3);
	//cout<<subsets.size();	
	return 0;
}
