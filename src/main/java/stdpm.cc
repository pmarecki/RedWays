#include <bits/stdc++.h>
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

void lower_upper_bound() {
    vi u = {2, 4, 6, 8};
    cout << (*lower_bound(ALL(u), 4)) << endl;  // 3->4; 4->4
    cout << (*upper_bound(ALL(u), 4)) << endl;  // 3->4; 4->6
}

void queue_priority() {
    priority_queue<int,vi,greater<>> pq;     //greater==lowest_on_top; lower== greatst_on_top (standard)
    pq.push(12);
    pq.push(15);
    cout << pq.top() << endl; //12
    pq.pop();
    cout << pq.top() << endl; //15
}

int main() {
    queue_priority();

}