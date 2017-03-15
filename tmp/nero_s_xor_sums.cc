//http://codeforces.com/contest/766/problem/E

#include <stdio.h>
#include <algorithm>
#include <vector>
#include <iostream>

const int kN = 100000 + 5;
int n;
std::vector<int> edges[kN];
int a[kN];
int c[kN][2];

long long dfs(int u, int fa, int bit)
{
    c[u][0] = c[u][1] = 0;
    c[u][a[u] >> bit & 1] ++;
    long long ret = c[u][1];
    for (int v: edges[u]) if (v != fa) {
        ret += dfs(v, u, bit);
        for (int i = 0; i < 2; ++ i)
            ret += c[u][i] * 1ll * c[v][i ^ 1];
        for (int i = 0; i < 2; ++ i)
            c[u][i] += c[v][i ^ (a[u] >> bit & 1)];
    }
    return ret;
}

int main()
{
    scanf("%d", &n);
    for (int i = 0; i < n; ++ i) scanf("%d", a + i);
    for (int i = 0; i < n - 1; ++ i) {
        int a, b;
        scanf("%d%d", &a, &b); a --; b --;
        edges[a].emplace_back(b);
        edges[b].emplace_back(a);
    }
    long long result = 0;
    for (int bit = 0; bit < 20; ++ bit) {
        result += dfs(0, -1, bit) * (1 << bit);
    }
    std::cout << result << std::endl;
}
