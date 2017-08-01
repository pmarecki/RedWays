#include <bits/stdc++.h>
using namespace std;
#define REP(i,n) for(int i=0;i<(int)n;++i)
#define FOR(i,b,n) for(int i=b;i<n;++i)
#define RFOR(i,n,b) for(int i=n;i>=b;--i)
#define ALL(c) (c).begin(),(c).end()
#define SS size()
#define SZ(a) (int)(a.size())
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
void sout(T &what){
    for(auto w : what) cout << w <<", ";
    cout << endl;
}

/**
 * Gives max value on any interval [x1...x2]
 */

class SegmentTree1D {
    const int MIN_VALUE = -2e9;
public:
    int query(vi& t, int x1, int x2) {
        int n = t.SS / 2;
        x1 += n;
        x2 += n;
        int res = MIN_VALUE;
        for (int lx = x1, rx = x2; lx <= rx; lx=(lx+1)/2, rx=(rx-1)/2) {
            if ((lx&1)!=0) {
                res = max(res, t[lx]);
            }
            if ((rx&1)==0) {
                res = max(res, t[rx]);
            }
        }
        return res;
    }

    //add
    void add(vi& t, int x, int value) {
        int n = t.SS / 2;
        x += n;
        t[x] += value;
        for (int tx = x; tx > 0; tx /= 2) if (tx > 1) {
            t[tx / 2] = max(t[tx], t[tx ^ 1]);
            printf("(%d:%d)? --> writing into: %d\n", tx, tx^1,(tx/2));
        }
    }

    int at(vi &t, int x) {
        return t[t.SS/2+x];
    }
};


int main() {
    vi t(16);
    SegmentTree1D s;
    s.add(t, 4, 1);
    printf("--->%i\n", s.query(t, 0, 6));
}
