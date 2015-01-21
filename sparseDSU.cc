#include <bits/stdc++.h>
#define REP(i,n)  for(int i=0;i<(int)(n);++i)
#define FOR(i,b,n)  for(int i=b;i<(n);++i)
#define ALL(c) (c).begin(),(c).end()
#define SS size()
#define CLR(a,v) memset((a),(v), sizeof a)
#define ST first
#define ND second
template<typename T, typename U> inline void AMIN(T &x, U y) { if(y < x) x = y; }
template<typename T, typename U> inline void AMAX(T &x, U y) { if(x < y) x = y; }
using namespace std;
typedef long long ll;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef vector<ll> vl;
typedef vector<vl> vvl;
typedef vector<double> vd;
typedef vector<vd> vvd;
typedef pair<int,int> pii;

template <typename T> void printContainer(T& a) {
  auto it = a.begin();
  cout << "{" << *(it++);
  for(; it!=a.end(); ++it) cout << ", " << (*it);
  cout << "}\n";
}
timespec startCLK, stopCLK;
#define START clock_gettime(CLOCK_MONOTONIC, &startCLK)
#define STOP(c)   clock_gettime(CLOCK_MONOTONIC, &stopCLK); \
    printf("dt = %6.3f [ms]%s\n", (stopCLK.tv_sec - startCLK.tv_sec) * 1000. +\
        1. * (stopCLK.tv_nsec - startCLK.tv_nsec) / 1e6, (c))

using namespace std;


//simple sparse DSU
unordered_map<int,int> Par;
int Root(int v) {
  if (Par.count(v)==0) {
    Par[v]=-1;
  }
  if (Par[v]<0) return v;
  else return Par[v] = Root(Par[v]);
}
void Merge(int x, int y){
  x = Root(x), y = Root(y);
  if (x==y) return;
  if (!Par.count(x)) {
    Par[x]=-1;
  }
  if (!Par.count(y)) {
    Par[y]=-1;
  }
  if(Par[y] < Par[x]) // balancing the height of the tree
    swap(x, y);
  Par[x] += Par[y];
  Par[y] = x;
}


const int SPREAD = 1e7;     //labels of nodes
int NMERGES = 1e5;          //merge operations

//vector-based sparse DSU (slightly faster)
vi par;
vi vimap;
int root(int v){
  if (par[v]<0) return v;
  else return par[v] = root(par[v]);
}
void merge(int x,int y){
  x = root(x), y = root(y);
  if (x==y) return;
  if(par[y] < par[x]) // balancing the height of the tree
    swap(x, y);
  par[x] += par[y];
  par[y] = x;
}

int main() {
  srand(111);
  vector<pii> Qs; //merge queries
  REP(i,NMERGES) {
    int a = rand() % SPREAD;
    int b = rand() % SPREAD;
    if (a==b) continue;
    Qs.emplace_back(a,b);
  }
  Qs.emplace_back(1, 2);
  Qs.emplace_back(2, 3);
  ///hash_DSU
  START;
  for(pii p : Qs) {
    Merge(p.ST, p.ND);
  }
  STOP("hash");
  ///vimap_DSU
  START;
  for(pii p : Qs) {
    vimap.push_back(p.ST);
    vimap.push_back(p.ND);
  }
  sort(ALL(vimap));
  vimap.erase(unique(ALL(vimap)), vimap.end());
  par = vi(vimap.SS,-1);
  for(pii p : Qs) {
    int x = lower_bound(ALL(vimap),p.ST) - vimap.begin();
    int y = lower_bound(ALL(vimap),p.ND) - vimap.begin();
    merge(x,y);
  }
  STOP("vimap");
  int sh = 0, sv = 0;
  for(int node : vimap) {
    if (Par[node]>0) {
      sh ^= Par[node];
      sv ^= vimap[par[lower_bound(ALL(vimap), node) - vimap.begin()]];
    }
  }
  printf("%i\n%i\n", sh, sv);

}



