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
 * Segment tree storing "elements" at each node.
 *
 * Typical query: which persons selected seats between [5..10]
 * (person assigned to seat index; dont want to go 1 by 1)
 */

typedef set<int> si;

class SegmentTree1D {
public:

    si join(si& sink, si& addition) {
        for(int i : addition) sink.insert(i);
        return sink;
    }


    si query(vector<si>& t, int x1, int x2) {
        int n = t.SS / 2;
        x1 += n;
        x2 += n;
        si res;
        for (int lx = x1, rx = x2; lx <= rx; lx = (lx + 1) / 2, rx = (rx - 1) / 2) {
            if ((lx & 1) != 0) {
                join(res, t[lx]);
            }
            if ((rx & 1) == 0) {
                join(res, t[rx]);
            }
            printf("%i,%i; res size=%i\n", lx, rx, SZ(res));
        }
        return res;
    }

    //add
    void add(vector<si>& t, int x, int value) {
        int n = t.SS / 2;
        x += n;
        t[x].insert(value);
        for (int tx = x; tx > 0; tx /= 2)
            if (tx > 1) {
                si tmp = t[tx];
                join(tmp, t[tx ^ 1]);
                join(t[tx / 2], tmp);
                printf("(%i:%i)? --> writing into: %i\n", tx, tx^1,(tx/2));
            }
    }
};


int main() {
    vector<si> t(16);
    SegmentTree1D s;
    s.add(t, 4, 1); //add to set at 4 value 1
    s.add(t, 2, 2);
    s.add(t, 3, 2);
    s.add(t, 7, 3);
//    printf("--->%i\n", s.query(t, 0, 6));
    si res = s.query(t,0,7);
    sout(res);

}
