#include <bits/stdc++.h>
using namespace std;
#define REP(i,n) for(int i=0;i<(int)n;++i)
#define FOR(i,b,n) for(int i=b;i<n;++i)
#define ALL(c) (c).begin(),(c).end()
#define SS size()
#define PB push_back
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef long long ll;
typedef pair<int,int> pii;
typedef pair<double,double> pdd;
#define ST first
#define ND second

typedef pair<ll,ll> pll;

pll diff(pll a, pll b) {
    return pll{a.ST-b.ST,a.ND-b.ND};
}

pll sum(pll a, pll b) {
    return pll{a.ST+b.ST,a.ND+b.ND};
}

ll vectorProd(pll a, pll b) {
    return abs(a.ST * b.ND - a.ND * b.ST);
}

bool isCollinear(pll a, pll b, pll c) {
    return vectorProd(diff(a,b),diff(a,c))==0;
}




int main() {
    cout << isCollinear({0, 0}, {1, 1}, {3, 2});
}



