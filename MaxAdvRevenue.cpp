#include <iostream>
#include <vector>
#include <algorithm>
#include <random>

using namespace std;

int64_t MaxRev (vector<int>& profit, vector<int>& clicks) {
    sort (profit.begin(), profit.end());
    sort (clicks.begin(), clicks.end());
    int64_t total_sum = 0;
    for (int i = 0; i < profit.size(); i++) {
        total_sum += static_cast<int64_t>(profit[i]) * clicks[i];
    }
    return total_sum;
}

int main() {
    int n = 0;
    cin >> n;
    vector <int> profit (n);
    vector <int> clicks (n);
    for (auto& elem : profit) {
        cin >> elem;
    }
    for (auto& elem : clicks) {
        cin >> elem;
    }
    cout << MaxRev (profit, clicks) << endl;
    return 0;
}