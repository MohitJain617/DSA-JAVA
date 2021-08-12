#include <iostream>
#include <vector>
using namespace std;

//prints all the permutation of a string
void permut(string s,string ans=""){
	int l=s.size();
	if(l==0){
		//process 
		cout<<ans<<endl;
		return;
	}
	if(l==1){
		permut("",ans+s[0]);
		return;
	}
	for(int i=0;i<l;i++){
		string temp;
		if(i==0){
			permut(s.substr(1,l-1),ans+s[0]);
		}
		else if(i==(l-1)){
			permut(s.substr(0,l-1),ans+s[l-1]);
		}
		else{
			temp=s.substr(0,i)+s.substr(i+1,(l-(i+1)));
			permut(temp,ans+s[i]);
		}
		
	}
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	permut("abcd");
	
	return 0;
}
