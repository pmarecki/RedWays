#include <iostream>
#include <map>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;

int N,M,Q;
string A[100000];
map<string,int> MP;

int head[100000];
int dif[100000];
vector<int> lst[100000];

bool join(int i,int j,int d)
{
	int hi = head[i];
	int hj = head[j];
	d ^= dif[i];
	d ^= dif[j];
	if(head[i]==head[j])
		return d==0;
	if(lst[hi].size()>lst[hj].size())
		swap(hi,hj);
	for(int k=0;k<lst[hi].size();k++)
	{
		head[lst[hi][k]] = hj;
		dif[lst[hi][k]] ^= d;
		lst[hj].push_back(lst[hi][k]);
	}
	lst[hi].clear();
	return 1;
}

int main()
{
	cin.tie(0);
	ios::sync_with_stdio(0);
	cin >> N >> M >> Q;
	for(int i=0;i<N;i++)
	{
		cin >> A[i];
		MP[A[i]] = i;
		head[i] = i, dif[i] = 0;
		lst[i].push_back(i);
	}
	string s,t;
	int tp;
	for(int i=0;i<M;i++)
	{
		cin >> tp >> s >> t;
		if(join(MP[s],MP[t],tp-1))
			cout << "YES\n";
		else
			cout << "NO\n";
	}
	for(int i=0;i<Q;i++)
	{
		cin >> s >> t;
		int a = MP[s];
		int b = MP[t];
		if(head[a]!=head[b]) cout << 3 << '\n';
		else if(dif[a]==dif[b]) cout << 1 << '\n';
		else cout << 2 << '\n';
	}
}
