#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>

using namespace std;

bool IsLargerOrEqual (int& lhs, int& rhs) {
    string lhs_s = to_string(lhs);
    string rhs_s = to_string(rhs);
    string res1 = lhs_s + rhs_s;
    string res2 = rhs_s + lhs_s;
    return res1 >= res2;
}

string MaxSalary (const int& n, vector<int>& v) {
    string res;
    while (!v.empty()) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < v.size(); i++) {
            if (IsLargerOrEqual(v[i], max)) {
                max = v[i];
                index = i;
            }
        }
        v.erase(v.begin() + index);
        res += to_string(max);
    }
    return res;
}

int main() {
    int n;
    cin >> n;
    vector<int> v(n);
    for (int i = 0; i < n; i++) {
        cin >> v[i];
    }
    cout << MaxSalary (n, v) << endl;
    return 0;
}