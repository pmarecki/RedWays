#include "bits/stdc++.h"
using namespace std;
const int N = 1e3 + 3;
int n , m , k;
int x[N] , y[N];
typedef pair<int,int> pii;


/*
 * Given points of type (.ND) =1 (begin), or =2 (end), find out if a place on [1..m] exists that
 * is not covered by these intervals.
 */
bool rekt(vector<pii> &v , int M){
	int sum = 0;
	sort(v.begin() , v.end());
	if(v.empty() || v[0].first > 1) return 0; //already 1 is not covered

	for(auto it : v){
		if(it.first > M) break; //OK -- covered whole interval
		if(it.second == 1) ++sum;   //interval began
		else --sum;                 //interval ended

		if(sum == 0) return 0;      //OK -- have a point that is
	}
	return 1;
}

//check if for line at x=idx all y=[1..m] are covered; if not -- update ql=minx, qr=maxx
void checkx(int idx , int &ql , int &qr , int tim){
	if(idx < 1 || idx > n){
		return;
	}
	vector<pii> v;
	v.clear();
	for(int i = 1 ; i <= k ; ++i){
		if(abs(idx - x[i]) <= tim){
			v.emplace_back(max(1 , y[i] - tim) , 1);
			v.emplace_back(min(m , y[i] + tim) + 1 , 2);
		}
	}
	if(!rekt(v , m)){  //if for line at x=idx some y in [1..m] not covered => update ql, qr
		ql = min(ql , idx);
		qr = max(qr , idx);
	}
}

bool check(int tim){
	int minx , miny , maxx , maxy;
	minx = n + 1;   //minx "line" for which at least one 'y' exists that is not covered
	maxx = 0;   //
	checkx(1 , minx , maxx , tim);  //line at x=1
	checkx(n , minx , maxx , tim);  //line at x=n
	for(int i = 1 ; i <= k ; ++i){
		int l = x[i] - tim;
		int r = x[i] + tim;
		checkx(l - 1 , minx , maxx , tim);  //candidates for lines that can be !fully_covered
		checkx(l , minx , maxx , tim);
		checkx(r , minx , maxx , tim);
		checkx(r + 1 , minx , maxx , tim);
	}
}

