#include <iostream>
#include <vector>

using namespace std;

int CarFueling (const int& d, const int& m, vector<int> v) {
    int numRefills = 0;
    int currentRefill = 0;
    int lastRefill = 0;
    v.insert(v.begin(), 0);
    v.push_back(d);
    int destination = v.size() - 1;
    while (currentRefill < destination) {
        lastRefill = currentRefill;
        while (currentRefill < destination && v[currentRefill + 1] - v[lastRefill] <= m) {
            currentRefill ++;
        }
        if (currentRefill == lastRefill) {
            return -1;
        }
        if (currentRefill < destination) {
            numRefills ++;
        }
    }
    return numRefills;
}

int main() {
    int d = 0;
    int m = 0;
    int n = 0;
    cin >> d >> m >> n;
    vector <int> v (n);
    for (int i = 0; i < n; i++) {
        cin >> v[i];
    }
    cout << CarFueling (d, m, v) << endl;
    return 0;
}