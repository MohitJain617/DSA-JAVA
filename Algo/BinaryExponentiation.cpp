#include <iostream>
using namespace std;

const unsigned int M=1000000007;

//recursive solution
long long powerRec(long long a, long long b)
{
	if(a==1 || b==0) return 1;
	if(b==1) return a;
	
	long long ans;
	ans=(powerRec(a,b/2));
	ans=(ans*ans)%M;
	
	if(b%2!=0) ans=(ans*a)%M;
	
	return ans;
}

//iterative solution
long long power(long long a, long long b)
{
	if(a==1 || b==0) return 1;
	if(b==1) return a;
	
	int result=1;
	while(b)
	{
		if(b%2==1) result=(result*a)%M;
		a=(a*a)%M; b/=2;
	}
	return result;
}

int main()
{
	cout<<power(2,444444444);
}
