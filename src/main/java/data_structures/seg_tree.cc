#include <iostream>
#include <vector>
#include <set>

using namespace std;
#define REP(i,n) for(int i=0;i<(int)n;++i)
#define FOR(i,b,n) for(int i=b;i<n;++i)
#define ALL(c) (c).begin(),(c).end()
#define SS size()
#define PB push_back
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef set<int> si;
typedef long long ll;
typedef pair<int,int> pii;
#define ST first
#define ND second

template <typename T>
void sout(T &what){
    for(auto w : what) cout << w <<", ";
    cout << endl;
}

/**
 * Gives (quickly, 40 operations for args 0..1e6) max value on any interval [left...right],
 * Allows to change the values equally quickly.
 */
class seg_tree {
    ll MIN_VALUE = -2e16;
    vector<ll> t;  //data

public:
    seg_tree(int max_arg) {
        t = vector<ll>(2 * max_arg);
    }

public:
    //maximum value in [left,right] (both ends inclusive)
    ll get_max(int left, int right) {
        int n = t.size() / 2;
        left += n;
        right += n;
        ll res = MIN_VALUE;
        for (int lx = left, rx = right; lx <= rx; lx=(lx+1)/2, rx=(rx-1)/2) {
            if ((lx&1)!=0) {
                res = max(res, t[lx]);
            }
            if ((rx&1)==0) {
                res = max(res, t[rx]);
            }
        }
        return res;
    }

    ll get(int arg) {
        return t[arg];
    }

    //set the value at `arg`
    void put(ll arg, int value) {
        int n = t.size() / 2;
        arg += n;
        t[arg] = value;
        for (int tx = arg; tx > 0; tx /= 2)
            if (tx > 1) {
                t[tx / 2] = max(t[tx], t[tx ^ 1]);
            }
    }
};


int main() {
    seg_tree s(100);

    s.put(4, 3);
    cout << "3==" << (s.get_max(0,50)) << endl;
    cout << "0==" << (s.get_max(51,99)) << endl;

    s.put(56, 2);
    cout << "2==" << (s.get_max(51,99)) << endl;

    s.put(5, 4);
    cout << "4==" << (s.get_max(0,5)) << endl;

    s.put(5, 1);
    cout << "3==" << (s.get_max(0,5)) << endl;
    s.put(4, 1);
    cout << "1==" << (s.get_max(0,5)) << endl;

    s.put(99,10);
    cout << "10==" << (s.get_max(0,99)) << endl;

    cout << "10==" << s.get(99) << endl;

}