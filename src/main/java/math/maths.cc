
#include<bits/stdc++.h>
using namespace std;
typedef long long ll;

ll gcd(ll x,ll y){return !y?x:gcd(y,x%y);}  //can also use __gcd(x,y) from stl_algo.h


int main() {
    cout << __gcd(4,5) << endl;
}