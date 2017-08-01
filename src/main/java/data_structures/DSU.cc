#include <bits/stdc++.h>
using namespace std;
#define REP(i,n) for(int i=0;i<(int)n;++i)
#define FOR(i,b,n) for(int i=b;i<n;++i)
#define RFOR(i,n,b) for(int i=n;i>=b;--i)
#define ALL(c) (c).begin(),(c).end()
#define SS size()
#define PB push_back
#define CLR(a, val) memset((a),(val), sizeof a)
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef set<int> si;
typedef long long ll;
typedef pair<int,int> pii;
#define ST first
#define ND second
#define MP(a, b) make_pair((a),(b))

template <typename T>
void debugContainer(T &what){
    for(auto w : what) cout << w <<", ";
    cout << endl;
}

//DSU by bmerry
//each: root v, par[v]=-(number_of_children)
// ranges as array[size], 0..size-1
// 1e3 runs of 1e4 merge will be OK
class Dsu {
    vi parent;
public:
    Dsu(int size) : parent(vi(size,-1)) {}

    //Root of the set in which `elem` resides
    int root(int elem) {
        if (parent[elem]<0) return elem;
        else return parent[elem] = root(parent[elem]);
    }
    void merge(int x,int y){
        x = root(x); y = root(y);
        if (x==y) return;
        if(parent[y] < parent[x]) {
            int t = x; x=y; y=t;
        }
        parent[x] += parent[y];
        parent[y] = x;
    }
    //--------cut here-------------------------------------

    //number of elements in tree in which "v" resides
    int elems(int v) {
        return -parent[root(v)];
    }

    //`parent` gives the position of the root of each of the sets
    //For root elements, it gives the (-) number of elements in current set
    vi& getPar() {
        return parent;
    }
};

/**
 * Notes:
 * - can join dsu's L, R:
 *   forEach(node:  R.root(node)!=node)
 *      L.merge(node, R.root(node))
 * - int[] par is the whole information; just clone() if Dsu needs to be cloned
 */


int main() {
    Dsu dsu(20);
    dsu.merge(0, 1);
    dsu.merge(5, 6);
    dsu.merge(5, 7);
    debugContainer(dsu.getPar());
}
